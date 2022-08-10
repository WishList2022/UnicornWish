package Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {

    private static Retrofit retrofit;
    private static String BASE_URL = "http://172.30.1.4:9090";

    public static Retrofit getRetrofit() {
        if (retrofit == null){
                retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit;
    }
}
