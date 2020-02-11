package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.automotive.automotiveplatform.ProductActivity;
import com.automotive.automotiveplatform.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.Brand;

public class HomeViewAdapter extends RecyclerView.Adapter<HomeViewAdapter.HomeViewHolder>{
    private Context context;
    private List<Brand> brandList;

    public HomeViewAdapter(Context context, List<Brand> brandList) {
        this.context = context;
        this.brandList = brandList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.activity_cardview, parent, false);
        HomeViewHolder holder = new HomeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Brand brand = brandList.get(position);

        holder.txtBrand.setText(brand.getBrandName());
        Picasso.get().load("http://10.0.2.2:9000/static/"+brand.getBrandImage().trim()).into(holder.imgBrand);

        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{
        CardView cvItem;
        ImageView imgBrand;
        TextView txtBrand;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            cvItem = itemView.findViewById(R.id.cvItem);
            imgBrand = itemView.findViewById(R.id.imgBrand);
            txtBrand = itemView.findViewById(R.id.txtBrand);
        }
    }
}
