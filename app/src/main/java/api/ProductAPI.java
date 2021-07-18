package api;

import java.util.List;

import model.Brand;
import model.Cart;
import model.Product;
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
import retrofit2.http.Query;

public interface ProductAPI {

    @GET("brand")
    Call<List<Brand>> getAllBrands();

    @GET("product/{id}")
    Call<Product> findProductById(@Path("id") String id);

    @GET("product/brand/{id}")
    Call<List<Product>> getAllProducts(@Path("id") String id);

    @POST("cart")
    Call<Void> addToCart(@Header("Authorization") String token, @Body Cart cart);

    @GET("cart")
    Call<List<Cart>> getAllItemsInCart(@Header("Authorization") String token, @Query("userId") String id);

    @DELETE("cart/{id}")
    Call<Void> deleteFromCart(@Header("Authorization") String token, @Path("id") String id);

    @FormUrlEncoded
    @PUT("cart/{id}")
    Call<Void> checkOutFromCart(@Header("Authorization") String token, @Path("id") String id, @Field("status") String status);
}
