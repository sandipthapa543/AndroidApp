package api;

import java.util.List;

import model.Brand;
//import model.CartModel;
import model.CartModel;
import model.ProductModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductAPI {

    @GET("brand/all")
    Call<List<Brand>> getAllBrands();

    @GET("product/{id}")
    Call<ProductModel> findProductById(@Path("id") String id);

    @GET("product/brand/{id}")
    Call<List<ProductModel>> getAllProducts(@Path("id") String id);

    @POST("cart/addTo")
    Call<Void> addToCart(@Header("Authorization") String token, @Body CartModel cart);

    @GET("cart")
    Call<List<CartModel>> getAllItemsInCart(@Header("Authorization") String token);

    @DELETE("cart/{id}")
    Call<Void> deleteFromCart(@Header("Authorization") String token, @Path("id") String id);

    @FormUrlEncoded
    @PUT("cart/{id}")
    Call<Void> checkOutFromCart(@Header("Authorization") String token, @Path("id") String id, @Field("status") String status);
}
