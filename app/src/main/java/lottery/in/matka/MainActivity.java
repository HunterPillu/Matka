package lottery.in.matka;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lottery.in.matka.fragments.DashboardFragment;
import lottery.in.matka.interfaces.DatabaseListener;
import lottery.in.matka.interfaces.TokenListener;
import lottery.in.matka.models.ChartItem;
import lottery.in.matka.models.UserToken;
import lottery.in.matka.retrofit.ApiService;
import lottery.in.matka.retrofit.ServiceInterface;
import lottery.in.matka.retrofit.ServiceResponse;
import lottery.in.matka.utils.Constant;
import lottery.in.matka.utils.ISFLog;
import lottery.in.matka.utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends FirebaseAcitivity implements TokenListener {
    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    private ServiceInterface apiServiceFirebase;
    private Call<ServiceResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fetchUserTokens(this, Constant.TABLE_USER_MASTER);
    }

    @Override
    void loginSuccessfull() {
        Util.showToast(this, "login successfull");
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new DashboardFragment())
                .addToBackStack(null)
                .commit();
    }

    private void sendPushNotification(List<UserToken> userTokensList) {
        //make json
        JsonObject jsonObject = new JsonObject();
        //jsonObject.addProperty("to", token);

        jsonObject.addProperty("priority", "high");
        JsonObject jsonObjectInner = new JsonObject();
        jsonObjectInner.addProperty("msg", "Hello Firebase");
        jsonObjectInner.addProperty("type", "7");
        jsonObjectInner.addProperty("recruiterId", "4");
        jsonObject.add("data",jsonObjectInner);
        JsonArray userTokensArray = new JsonArray();
        for (UserToken userToken : userTokensList
             ) {
            userTokensArray.add(userToken.token);
        }
        jsonObject.add("registration_ids", userTokensArray);
        Log.d("TAG", "jsonObject: " + jsonObject);



        call = apiServiceFirebase.sendNotification(jsonObject);
        call.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                Log.d("TAG", "send notofication response is- : " + response.body().toString());
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onSuccess(String code, List<UserToken> response) {
        apiServiceFirebase = ApiService.getRetrofitClient().create(ServiceInterface.class);
        sendPushNotification(response);
    }

    @Override
    public void onFailure(Exception e) {

    }
}
