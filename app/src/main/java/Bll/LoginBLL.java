package Bll;

import java.io.IOException;

import api.ApiClass;
import retrofit2.Call;
import retrofit2.Response;
import serverresponse.UserResponse;
import url.ApiUrl;

public class LoginBLL {

    boolean isSuccess = false;
    public static   String Token;

    public boolean checkUser(String email, String password) {

        ApiClass usersAPI = new ApiClass();
        // Url.getInstance().create(UsersAPI.class);
        Call<UserResponse> usersCall = usersAPI.calls().checkUser(email, password);

        try {
            Response<UserResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {
                UserResponse signUpResponse=loginResponse.body();
                Token=signUpResponse.getToken();


                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
