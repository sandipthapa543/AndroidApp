package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.automotive.automotiveplatform.R;
import com.automotive.automotiveplatform.ui.profileupdate.ProfileUpdateFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import api.ApiClass;
import api.ProductAPI;
import de.hdodenhof.circleimageview.CircleImageView;
import model.CartModel;
import model.ProductModel;
import retrofit2.Call;
import retrofit2.Response;
import strictmode.StrictModeClass;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.CartHolder> {
    Context context;
    List<CartModel> cartModelList;
    String productName, productPrice, productImage;
    String userName, token;
    Fragment fragment;

    public CartViewAdapter(Context context, List<CartModel> cartModelList, String token, Fragment fragment) {
        this.context = context;
        this.cartModelList = cartModelList;
        this.token = token;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false);
        CartHolder holder = new CartHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        final CartModel cart = cartModelList.get(position);

        getProductById(cart.getProduct());

        String temp_status = "Checkout";
        if(cart.getStatus()==temp_status){
            holder.cartHolder.setVisibility(View.GONE);
        }

        holder.txtProductName.setText(productName);
        holder.txtProductPrice.setText("Rs. "+productPrice);
        Picasso.get().load("http://10.0.2.2:9000/static/"+productImage).into(holder.imgProduct);

        holder.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOutFromCart(cart.get_id());
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFromCart(cart.get_id());
            }
        });


    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder{
        RelativeLayout cartHolder;
        ImageView imgProduct;
        TextView txtProductName, txtProductPrice;
        Button btnCheckout, btnDelete;

        public CartHolder(@NonNull View itemView) {
            super(itemView);

            cartHolder = itemView.findViewById(R.id.cartHolder);
            imgProduct = itemView.findViewById(R.id.imgProductRV);
            txtProductName = (TextView) itemView.findViewById(R.id.txtProductName);
            txtProductPrice = (TextView) itemView.findViewById(R.id.txtProductPrice);
            btnCheckout = itemView.findViewById(R.id.btnCheckOut);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }
    }

    private void getProductById(String id) {
        ProductAPI api = ApiClass.getInstance().create(ProductAPI.class);
        Call<ProductModel> call = api.findProductById(id);
        StrictModeClass.StrictMode();
        try{
            Response<ProductModel> response = call.execute();
            if(response.isSuccessful()){
                productName = response.body().getName();
                productPrice = response.body().getPrice();
                productImage = response.body().getImage();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void checkOutFromCart(String id){
        ProductAPI api = ApiClass.getInstance().create(ProductAPI.class);
        Call<Void> call = api.checkOutFromCart(token, id, "Checkout");
        StrictModeClass.StrictMode();
        try{
            Response<Void> response = call.execute();
            if(response.isSuccessful()){
                reload();
                Toast.makeText(context, "Item successfully checked out!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void deleteFromCart(String id){
        ProductAPI api = ApiClass.getInstance().create(ProductAPI.class);
        Call<Void> call = api.deleteFromCart(token, id);
        StrictModeClass.StrictMode();
        try{
            Response<Void> response = call.execute();
            if(response.isSuccessful()){
                reload();
                Toast.makeText(context, "Item successfully deleted!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void reload(){
        final FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        transaction.detach(fragment).attach(fragment).commit();
    }
}
