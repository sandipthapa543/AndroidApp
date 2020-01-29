package api;

import model.UserDetailsModel;
import model.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
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
    Call<UserDetailsModel> getMe(@Header("Authorization") String token);

}
