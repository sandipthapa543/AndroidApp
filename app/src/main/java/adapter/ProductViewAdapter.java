package adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.automotive.automotiveplatform.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import api.ApiClass;
import api.ProductAPI;
import api.UserApi;
import model.Brand;
import model.CartModel;
import model.ProductModel;
import model.UserModel;
import retrofit2.Call;
import retrofit2.Response;
import strictmode.StrictModeClass;

import static android.content.Context.MODE_PRIVATE;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.ProductHolder> {
    private Context context;
    private List<ProductModel> productList;
    private String id, token;

    public ProductViewAdapter(Context context, List<ProductModel> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_product, parent, false);
        ProductHolder holder = new ProductHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        final ProductModel product = productList.get(position);

        Picasso.get().load("http://10.0.2.2:9000/static/"+product.getImage()).into(holder.imgProduct);
        holder.txtName.setText(product.getName());
        holder.txtPrice.setText("Rs."+product.getPrice());
        holder.btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUser();

                CartModel cart = new CartModel(null, id, product.get_id(), "Cart");

                ProductAPI api = ApiClass.getInstance().create(ProductAPI.class);
                Call<Void> call = api.addToCart(token, cart);
                StrictModeClass.StrictMode();
                try{
                    Response<Void> response = call.execute();
                    if(response.isSuccessful()){
                        Toast.makeText(context, "Product Added To Cart!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder{
        ImageView imgProduct;
        TextView txtName, txtPrice;
        ImageButton btnAddtoCart;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtName = itemView.findViewById(R.id.txtProduct);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            btnAddtoCart = itemView.findViewById(R.id.btnAddtoCart);
        }
    }

    private void loadUser() {
        UserApi usersAPI = ApiClass.getInstance().create(UserApi.class);
        SharedPreferences preferences = context.getSharedPreferences("tokens", MODE_PRIVATE);
        token = preferences.getString("token", "");

        Call<UserModel> userModelCall = usersAPI.getMe(token);
        StrictModeClass.StrictMode();
        try {
            Response<UserModel> response = userModelCall.execute();
            if (response.isSuccessful()) {
                UserModel userModel = response.body();
                id = userModel.get_id();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
