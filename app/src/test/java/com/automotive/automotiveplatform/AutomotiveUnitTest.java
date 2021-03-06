package com.automotive.automotiveplatform;

import org.junit.Test;

import java.util.List;

import api.ApiClass;
import api.ProductAPI;
import api.UserApi;
import model.Brand;
import model.User;
import retrofit2.Call;
import retrofit2.Response;
import serverresponse.UserResponse;

import static junit.framework.TestCase.assertTrue;

public class AutomotiveUnitTest {
    UserApi api;
    ProductAPI productAPI;

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
            Call<UserResponse> userCall = api.register(new User( "Sandip", "Thapa", "sandip1@gmail.com", "admin1234",
                    "9814103679", "Pokhara"));
            try {
                Response<UserResponse> registrationResponse = userCall.execute();
                assertTrue(registrationResponse.isSuccessful());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // testing profile or /me api

        @Test
        public  void test_Profile(){
            api =ApiClass.getInstance().create(UserApi.class);
            Call<User>userModelCall =api.getMe("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InJvbGUiOiJjdXN0b21lciIsIl9pZCI6IjVlMzg2Njk4M2EwMDFiMmMzY2MxMzBhNSIsImZpcnN0X05hbWUiOiJhZG1pbiIsImxhc3RfTmFtZSI6ImFsbGwiLCJlbWFpbCI6ImFza0BnbWFpbC5jb20iLCJwYXNzd29yZCI6IiQyYSQxMCRpZmROSFpzY1Q2aWtpUHBSc1BrdC9lSVh2MGU2bnJpVUQwSXFwYUZYNFo3NXBRUjlnbXg5LiIsInBob25lIjoiOTAxNDEwMzY4OSIsImFkZHJlc3MiOiJwa3IiLCJfX3YiOjB9LCJpYXQiOjE1ODA3NTQ2MjcsImV4cCI6MTU4MDg0MTAyN30.wXMSdeuLjuyl97qFUAd3VY1ZXxsw6F5oUJbWpoQBIJY");
        try{
            Response<User> userModelResponse =userModelCall.execute();
            assertTrue(userModelResponse.isSuccessful());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        }

        // testing updateProfile or put api
       @Test
      public  void updateProfile(){
            api = ApiClass.getInstance().create(UserApi.class);
            Call<User>userModelCall=api.updateuser("5e3866983a001b2c3cc130a5","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InJvbGUiOiJjdXN0b21lciIsIl9pZCI6IjVlMzg2Njk4M2EwMDFiMmMzY2MxMzBhNSIsImZpcnN0X05hbWUiOiJhZG1pbiIsImxhc3RfTmFtZSI6ImFsbGwiLCJlbWFpbCI6ImFza0BnbWFpbC5jb20iLCJwYXNzd29yZCI6IiQyYSQxMCRpZmROSFpzY1Q2aWtpUHBSc1BrdC9lSVh2MGU2bnJpVUQwSXFwYUZYNFo3NXBRUjlnbXg5LiIsInBob25lIjoiOTAxNDEwMzY4OSIsImFkZHJlc3MiOiJwa3IiLCJfX3YiOjB9LCJpYXQiOjE1ODA3NTQ2MjcsImV4cCI6MTU4MDg0MTAyN30.wXMSdeuLjuyl97qFUAd3VY1ZXxsw6F5oUJbWpoQBIJY",
                    "sandip","thapa","912103699","pokhara");
            try{
                Response<User>userModelResponse=userModelCall.execute();
                assertTrue(userModelResponse.isSuccessful());
            }
            catch (Exception e){
                e.printStackTrace();
            }
       }

       @Test
    public void getBrand(){
            productAPI = ApiClass.getInstance().create(ProductAPI.class);
           Call<List<Brand>> call = productAPI.getAllBrands();
           try{
               Response<List<Brand>> callresponse =call.execute();
               assertTrue(callresponse.isSuccessful());
           }
           catch (Exception e){
               e.printStackTrace();
           }
       }


    }


