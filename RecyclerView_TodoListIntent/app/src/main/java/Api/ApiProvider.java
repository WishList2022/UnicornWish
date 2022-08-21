package Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {

    private static Retrofit retrofit;
    private static String BASE_URL = "http://10.156.147.115:8087";

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
