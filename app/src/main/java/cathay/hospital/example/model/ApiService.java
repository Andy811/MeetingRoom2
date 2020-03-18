package cathay.hospital.example.model;

import android.util.Log;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import cathay.hospital.example.model.bean.LoginData;
import cathay.hospital.example.model.bean.ReturnData;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private ApiModule api;

    public ApiService(String divno) {
        String ip = "localhost:8699";
       if(divno.equals("test")){
           ip = "localhost:8699";
       }
        String BASE_URL = "http://"+ip+"/";

        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)//true:默认重试一次，若需要重试N次，则要实现拦截器。
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        api = retrofit.create(ApiModule.class);
    }

    public Single<LoginData> getLoginData(HashMap<String, String> params) {
        return api.getLoginData(params);
    }

}
