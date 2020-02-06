package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.automotive.automotiveplatform.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.Brand;
import model.ProductModel;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.ProductHolder> {
    private Context context;
    private List<ProductModel> productList;

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
        ProductModel product = productList.get(position);

        Picasso.get().load("http://10.0.2.2:9000/static/"+product.getImage()).into(holder.imgProduct);
        holder.txtName.setText(product.getName());
        holder.txtPrice.setText("Rs."+product.getPrice());
        holder.btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}
