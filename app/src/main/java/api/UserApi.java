package api;

import model.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import serverresponse.UserResponse;

public interface UserApi {

    //User signup routing for api link
    @POST("users/SignUp")
    Call<UserResponse> register(@Body UserModel users);

   // User login route with token check
   @FormUrlEncoded
    @POST("users/login")
    Call<UserResponse> checkUser(@Field("email") String email, @Field("password") String password);

   //for user profile get me router
   @GET("users/me")
    Call<UserModel> getMe(@Header("Authorization") String token);

   //for user profile update put router
    @FormUrlEncoded
    @PUT("users/edit/{id}")
    Call<UserModel> updateuser(@Path("id") String _id, @Header("Authorization") String token,
                               @Field("first_Name") String first_Name, @Field("last_Name") String last_Name,
                               @Field("phone") String phone,
                               @Field("address")String address);
}
