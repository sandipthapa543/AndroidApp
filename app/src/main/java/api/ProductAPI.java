package api;

import java.util.List;

import model.Brand;
import model.CartModel;
import model.ProductModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductAPI {

    @GET("brand/all")
    Call<List<Brand>> getAllBrands();


    @GET("product/brand/{id}")
    Call<List<ProductModel>> getAllProducts(@Path("id") String id);

   @POST("cart/addto")
    Call<List<CartModel>> postToCart();
}
