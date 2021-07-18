package url;

import api.UserApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUrl {

    public static final String base_url="http://10.0.2.2:9000/";


    public UserApi Connect() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory( GsonConverterFactory.create())
                .build();
        UserApi userApi=retrofit.create(UserApi.class);
        return userApi;
    }

}
