package lottery.in.matka;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import lottery.in.matka.utils.Constant;
import lottery.in.matka.utils.ISFLog;
import lottery.in.matka.utils.Util;

/**
 * Created by WarFly on 10/23/2017.
 */

public abstract class BaseAcitivity extends AppCompatActivity {
    private static final String TAG = "FIREBASE";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    ISFLog.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    loginSuccessfull();
                } else {
                    // User is signed out
                    ISFLog.e(TAG, "onAuthStateChanged:signed_out");
                    signInWithFirebase(getDeviceId() + "@FIREBASE.COM");
                }
            }
        };

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
    }

    private void askForPermission() {
        try {
            new TedPermission(this)
                    .setPermissionListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted() {
                            mAuth.addAuthStateListener(mAuthListener);
                        }

                        @Override
                        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                            finish();
                        }
                    })
                    .setDeniedMessage("If you reject permission,you can not use these services\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(
                            Manifest.permission.READ_PHONE_STATE
                         /*   Manifest.permission.INTERNET*/
                    )
                    .check();
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        askForPermission();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
            //  mAuth.signOut();
        }
    }

    public void signInWithFirebase(String email) {
        mAuth.signInWithEmailAndPassword(email, Constant.COMMON_PASSWORD)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        ISFLog.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            ISFLog.d(TAG, "signInWithEmail:failed\n" + task.getException());
                            Util.showToast(BaseAcitivity.this, Constant.AUTH_FAIL);
                            signupWithFirebase(getDeviceId() + "@FIREBASE.COM");
                        } else {
                            loginSuccessfull();
                        }

                    }
                });
    }

    public void signupWithFirebase(String email) {
        mAuth.createUserWithEmailAndPassword(email, Constant.COMMON_PASSWORD)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        ISFLog.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Util.showToast(BaseAcitivity.this, Constant.AUTH_FAIL);
                        } else {
                            loginSuccessfull();
                        }
                    }
                });
    }

    abstract void loginSuccessfull();

    public String getDeviceId() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        ISFLog.e("imei number ", telephonyManager.getDeviceId());
        return telephonyManager.getDeviceId();
    }
}