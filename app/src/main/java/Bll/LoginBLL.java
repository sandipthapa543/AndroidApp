package Bll;

import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

import api.ApiClass;
import api.UserApi;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Url;
import serverresponse.UserResponse;
import url.ApiUrl;

import static android.content.Context.MODE_PRIVATE;

public class LoginBLL {

    boolean isSuccess = false;
    public static String token;
    public static String id;
    UserApi usersAPI;

    public LoginBLL() {
        usersAPI = ApiClass.getInstance().create(UserApi.class);

    }

    public boolean checkUser(String email, String password) {

//        ApiClass usersAPI = new ApiClass();
        // Url.getInstance().create(UsersAPI.class);
        Call<UserResponse> usersCall = usersAPI.checkUser(email, password);

        try {
            Response<UserResponse> loginResponse = usersCall.execute();
            Log.d("response_user", ""+loginResponse.body());
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {
                UserResponse signUpResponse = loginResponse.body();
                id = loginResponse.body().getId();
                Log.d("user_id", ""+id);
                token = signUpResponse.getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
