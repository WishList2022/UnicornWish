package Api;

import Login.LoginRequest;
import WishPostRequest.PostRequest;
import Register.RegisterRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerApi {

    @POST("/user/login")
    Call<Void> login(
            @Body LoginRequest loginRequest
    );

    @POST("/user/signup")
    Call<Void> Singnup(
            @Body RegisterRequest registerRequest
    );

    @POST("/post/{user-id}")
    Call<Void> WishPost(
            @Body PostRequest postRequest
    );
}
