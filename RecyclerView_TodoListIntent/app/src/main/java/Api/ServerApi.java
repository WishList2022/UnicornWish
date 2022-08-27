package Api;

import org.techtown.Activity.FetchFeedResponse;

import java.util.ArrayList;

import Login.LoginRequest;
import Login.LoginResponse;
import Request.EditRequest;
import Request.TextEditRequest;
import Request.TextPostRequest;
import Request.WishPostRequest;
import Register.RegisterRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServerApi {

    @POST("/user/token")// 로그인
    Call<LoginResponse> login(
            @Body LoginRequest loginRequest
    );

    @POST("/user/signup")// 회원가입
    Call<Void> Singnup(
            @Body RegisterRequest registerRequest
    );

    @POST("/feed")// Wish 작성
    Call<Void> WishPost(
            @Header("Authorization") String accessToken ,
            @Body WishPostRequest wishPostRequest
    );


    @GET("/feed")// Wish 조회
    Call <FetchFeedResponse> wishInquiry(
            @Header("Authorization") String accessToken
    );

    @DELETE("/feed/{feed_id}")// Wish 삭제
    Call<Void> WishDel(
            @Header("Authorization") String accessToken,
            @Path("feed_id") int feed_id
    );


    @PUT("/feed/{feed_id}")//Wish 수정
    Call<Void> WishEdit(
            @Header("Authorization") String accessToken,
            @Body EditRequest editRequest,
            @Path("feed_id") int feed_id

    );

    @POST("/text")// Text 작성
    Call<Void> textPost(
            @Header("Authorization") String accessToken,
            @Body TextPostRequest textPostRequest
    );

    @PUT("/text/{text-id}")// Text 수정
    Call<Void> TestEdit(
            @Header("Authorization") String accessToken,
            @Path("textId") long textId,
            @Body TextEditRequest textEditRequest
    );

}


