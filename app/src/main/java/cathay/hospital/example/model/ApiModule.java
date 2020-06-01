package cathay.hospital.example.model;

import java.util.HashMap;

import cathay.hospital.example.model.bean.LoginData;
import cathay.hospital.example.model.bean.ReturnData;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiModule {
//    @GET("member/login")
//    Single<LoginData> getLoginData(@QueryMap HashMap<String, String> params);

    /**
     *  post 範例
     */
    @FormUrlEncoded
    @POST("member/login")
    Single<LoginData> getLoginData(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("member/signin")
    Single<LoginData> signin(@FieldMap HashMap<String, String> params);//沒有@Field不會動,LoginData才有call adapter
}
