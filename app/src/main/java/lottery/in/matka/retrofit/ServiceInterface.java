package lottery.in.matka.retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by desktop on 28-10-2017.
 */

public interface ServiceInterface {

    @POST("send")
    Call<ServiceResponse> sendNotification(@Body JsonObject object);

}
