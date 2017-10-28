package lottery.in.matka.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by desktop on 28-10-2017.
 */

public class ApiService {
    private static String BASE_URL_FIREBASE = "https://fcm.googleapis.com/fcm/";
    public static Retrofit getRetrofitClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES).addInterceptor(new ServiceInterceptor()).addInterceptor(loggingInterceptor).build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL_FIREBASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
