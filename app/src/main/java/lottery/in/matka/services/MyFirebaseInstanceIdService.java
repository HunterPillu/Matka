package lottery.in.matka.services;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

import lottery.in.matka.models.UserToken;
import lottery.in.matka.utils.Constant;
import lottery.in.matka.utils.ISFLog;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    private static final String FRIENDLY_ENGAGE_TOPIC = "Chat_Topic";

    /**
     * The Application's current Instance ID token is no longer valid and thus a new one must be requested.
     */

    @Override
    public void onTokenRefresh() {
        // If you need to handle the generation of a token, initially or after a refresh this is
        // where you should do that.
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "FCM Token: " + token);

        /*DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        mDatabase.child(Constant.TABLE_USER_MASTER).push().setValue(new UserToken(token));
*/
        // Once a token is generated, we subscribe to topic.
        FirebaseMessaging.getInstance().subscribeToTopic(FRIENDLY_ENGAGE_TOPIC);
    }
}
