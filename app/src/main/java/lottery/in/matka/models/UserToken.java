package lottery.in.matka.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by WarFly on 10/22/2017.
 */
@IgnoreExtraProperties
public class UserToken {
    public String token;

    public UserToken()
    {

    }

    public UserToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
