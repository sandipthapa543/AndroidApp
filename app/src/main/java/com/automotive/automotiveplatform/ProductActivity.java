package com.automotive.automotiveplatform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import adapter.ProductViewAdapter;
import api.ApiClass;
import api.ProductAPI;
import model.Product;
import retrofit2.Call;
import retrofit2.Response;
import strictmode.StrictModeClass;

public class ProductActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Product> productList;
    TextView toolbarHeading;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        recyclerView = findViewById(R.id.RecyclerViewProducts);
        toolbarHeading = findViewById(R.id.toolbarHeading);

        toolbarHeading.setText("All Products");

        getProductList();

        ProductViewAdapter adapter = new ProductViewAdapter(getApplicationContext(), productList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void getProductList(){
        ProductAPI api = ApiClass.getInstance().create(ProductAPI.class);
        Call<List<Product>> call = api.getAllProducts("xyz");
        StrictModeClass.StrictMode();
        try{
            Response<List<Product>> response = call.execute();
            Log.d("rs_prodct", ""+response.body());
            if(response.isSuccessful()){
                productList = response.body();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
