package com.automotive.automotiveplatform.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.automotive.automotiveplatform.R;

import java.util.List;

import adapter.HomeViewAdapter;
import api.ApiClass;
import api.ProductAPI;
import model.Brand;
import retrofit2.Call;
import retrofit2.Response;
import strictmode.StrictModeClass;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=root.findViewById(R.id.RecyclerView);

        TextView toolbarHeading = root.findViewById(R.id.toolbarHeading);
        toolbarHeading.setText("Discover All Brands");

        fillUpRecyclerView();
        return root;
    }

    private void fillUpRecyclerView(){
        ProductAPI api = ApiClass.getInstance().create(ProductAPI.class);
        Call<List<Brand>> call = api.getAllBrands();
        StrictModeClass.StrictMode();
        try{
            Response<List<Brand>> response = call.execute();

            if(response.isSuccessful()){
                List<Brand> brandList = response.body();
                HomeViewAdapter adapter = new HomeViewAdapter(getContext(), brandList);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}