package api;

import model.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import serverresponse.UserResponse;

public interface UserApi {


    @POST("users/SignUp")
    Call<UserResponse> register(@Body UserModel users);


    @FormUrlEncoded
    @POST("users/login")
    Call<UserResponse> checkUser(@Field("email") String email, @Field("password") String password);

}
