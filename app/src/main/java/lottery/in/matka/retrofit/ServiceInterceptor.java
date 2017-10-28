package lottery.in.matka.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by desktop on 28-10-2017.
 */

public class ServiceInterceptor implements Interceptor {//

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest = chain.request().newBuilder()
                .header("Authorization", "key=AIzaSyBjI-DU96U75xf6DryIszMtTV7y9CX6TRY")
                .build();
        return chain.proceed(newRequest);
    }
}
