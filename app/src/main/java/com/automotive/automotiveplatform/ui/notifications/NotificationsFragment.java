package com.automotive.automotiveplatform.ui.notifications;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.automotive.automotiveplatform.R;

import java.io.IOException;
import java.util.List;

import adapter.CartViewAdapter;
import api.ApiClass;
import api.ProductAPI;
import api.UserApi;
import model.CartModel;
import model.UserModel;
import retrofit2.Call;
import retrofit2.Response;
import strictmode.StrictModeClass;

import static android.content.Context.MODE_PRIVATE;

public class NotificationsFragment extends Fragment {
    RecyclerView rvCart;
    String token, id;
    List<CartModel> itemsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        rvCart = root.findViewById(R.id.rvCart);
        SharedPreferences preferences = getContext().getSharedPreferences("tokens", MODE_PRIVATE);
        token = preferences.getString("token", "");
        getAllItems();
        getUserID();

        CartViewAdapter adapter = new CartViewAdapter(getContext(), itemsList, token, NotificationsFragment.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvCart.setAdapter(adapter);
        rvCart.setLayoutManager(layoutManager);

        return root;
    }

    private void getUserID() {
        UserApi usersAPI = ApiClass.getInstance().create(UserApi.class);


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

    private void getAllItems(){
        ProductAPI api = ApiClass.getInstance().create(ProductAPI.class);
        Call<List<CartModel>> call = api.getAllItemsInCart(token);
        StrictModeClass.StrictMode();
        try{
            Response<List<CartModel>> response = call.execute();
            if(response.isSuccessful()){
                itemsList = response.body();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}