package com.automotive.automotiveplatform;

import org.junit.Test;

import api.ApiClass;
import api.UserApi;
import model.UserModel;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import serverresponse.UserResponse;

import static junit.framework.TestCase.assertTrue;

public class AutomotiveUnitTest {
    UserApi api;

       //Login testing

        @Test
        public void test_Login() {
            api = ApiClass.getInstance().create(UserApi.class);
            Call<UserResponse> userCall = api.checkUser("admin@gmail.com", "admin1234");
            try {
                Response<UserResponse> loginResponse = userCall.execute();
                assertTrue(loginResponse.isSuccessful());
            } catch (Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
            }
        }

        // Signup Testing
        @Test
        public void test_Registration() {
            api = ApiClass.getInstance().create(UserApi.class);
            Call<UserResponse> userCall = api.register(new UserModel( "Sandip", "Thapa", "sandip1@gmail.com", "admin1234",
                    "9814103679", "Pokhara"));
            try {
                Response<UserResponse> registrationResponse = userCall.execute();
                assertTrue(registrationResponse.isSuccessful());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


