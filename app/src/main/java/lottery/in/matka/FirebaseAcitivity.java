package lottery.in.matka;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

import lottery.in.matka.fragments.BaseFragment;
import lottery.in.matka.interfaces.DatabaseListener;
import lottery.in.matka.interfaces.TokenListener;
import lottery.in.matka.models.ChartItem;
import lottery.in.matka.models.UserToken;
import lottery.in.matka.utils.Constant;
import lottery.in.matka.utils.ISFLog;
import lottery.in.matka.utils.Util;

/**
 * Created by WarFly on 10/23/2017.
 */

public abstract class FirebaseAcitivity extends AppCompatActivity {
    private static final String TAG = "FIREBASE";
    private FirebaseAuth mAuth;
    public BaseFragment currentFragment;
    // private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
      /*  mAuthListener = new FirebaseAuth.AuthStateListener() {
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
*/
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
    }

    private void askForPermission() {
        try {
            new TedPermission(this)
                    .setPermissionListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted() {
                            if (null == currentFragment) {
                                showBaseLoader();
                            }
                            signInWithFirebase(getDeviceId() + "@FIREBASE.COM");
                            // mAuth.addAuthStateListener(mAuthListener);
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
        if (mAuth != null) {
            //  mAuth.removeAuthStateListener(mAuthListener);
            mAuth.signOut();
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
                            ISFLog.e(TAG, "signInWithEmail:failed\n" + task.getException());
                            Util.showToast(FirebaseAcitivity.this, Constant.AUTH_FAIL);
                            signupWithFirebase(getDeviceId() + "@FIREBASE.COM");
                        } else if (null == currentFragment) {
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
                            Util.showToast(FirebaseAcitivity.this, Constant.AUTH_FAIL);
                        } else {
                            String token = FirebaseInstanceId.getInstance().getToken();
                            ISFLog.e("Firebase", "token "+ token);
                            saveUserToken(new UserToken(token));
                            if (null == currentFragment) {

                                loginSuccessfull();
                            }
                        }
                    }
                });
    }

    abstract void loginSuccessfull();

    public void saveUserToken(UserToken vo){
        mDatabase.child(Constant.TABLE_USER_MASTER).push().setValue(vo);
    }

    public String getDeviceId() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        ISFLog.e("imei number ", telephonyManager.getDeviceId());
        return telephonyManager.getDeviceId();
    }

    public void fetchUserTokens(final TokenListener listener, final String tableName) {

        ValueEventListener gameValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ISFLog.d(Constant.TABLE_USER_MASTER, "onDataChange");
                List<UserToken> userTokens = new ArrayList<>();
                try {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        userTokens.add(snap.getValue(UserToken.class));
                    }
                } catch (Exception e) {
                    ISFLog.e(e);
                }
                listener.onSuccess(tableName, userTokens);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure(databaseError.toException());
            }
        };
        try {
            mDatabase.child(tableName).addListenerForSingleValueEvent(gameValueListener);
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    public void fetchChart(final DatabaseListener listener, final String tableName) {

        ValueEventListener gameValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ISFLog.d(Constant.TABLE_CHART_SHAKTI, "onDataChange");
                List<ChartItem> chartList = new ArrayList<>();
                try {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        chartList.add(snap.getValue(ChartItem.class));
                    }
                } catch (Exception e) {
                    ISFLog.e(e);
                }
                listener.onSuccess(tableName, chartList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure(databaseError.toException());
            }
        };
        try {
            mDatabase.child(tableName).addListenerForSingleValueEvent(gameValueListener);
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }


    /*public void fetchChartByDate(final DatabaseListener listener, final String tableName, final String date) {

        ValueEventListener gameValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ISFLog.d(Constant.TABLE_CHART_SHAKTI, "onDataChange");
                List<ChartItem> chartList = new ArrayList<>();
                try {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        chartList.add(snap.getValue(ChartItem.class));
                    }
                    ISFLog.d(tableName, ""+new Gson().toJson(chartList));
                } catch (Exception e) {
                    ISFLog.e(e);
                }

                ChartItem vo = new ChartItem();
                vo.setDate(date);
                vo.setTimeStamp(Util.convertStringToDate(date).getTime());
                if (chartList.size() > 0) {
                    vo = chartList.get(0);
                }

                if (isOpenToggleActive) {
                    vo.setSingleOpen(single);
                    vo.setPattiOpen(patti);
                } else {
                    vo.setSingleClose(single);
                    vo.setPattiClose(patti);
                }
                listener.onSuccess(tableName, chartList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure(databaseError.toException());
            }
        };
        try {
            mDatabase.child(tableName).orderByChild("date").equalTo(date).addListenerForSingleValueEvent(gameValueListener);
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }
*/
    public void showBaseLoader() {
        try {
            progressDialog = ProgressDialog.show(this, "", "", true);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.dialog_progress);
            // new showBaseLoaderAsync(context).execute();
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    public void hideBaseLoader() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    public void uploadResult(final DatabaseListener listener, final boolean isOpenToggleActive, final String tableName, final String date, final String single, final String patti) {
        ValueEventListener gameValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ISFLog.d(Constant.TABLE_CHART_SHAKTI, "onDataChange");
                List<ChartItem> chartList = new ArrayList<>();
                try {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        chartList.add(snap.getValue(ChartItem.class));
                    }
                    ISFLog.d(tableName, ""+new Gson().toJson(chartList));
                } catch (Exception e) {
                    ISFLog.e(e);
                }

                ChartItem vo = new ChartItem();
                vo.setDate(date);
                vo.setTimeStamp(Util.convertStringToDate(date).getTime());
                if (chartList.size() > 0) {
                    vo = chartList.get(0);
                }

                if (isOpenToggleActive) {
                    vo.setSingleOpen(single);
                    vo.setPattiOpen(patti);
                } else {
                    vo.setSingleClose(single);
                    vo.setPattiClose(patti);
                }

                if(TextUtils.isEmpty(vo.getKey())){
                    String key= mDatabase.child(tableName).push().getKey();
                    vo.setKey(key);
                }
                mDatabase.child(tableName).child(vo.getKey()).setValue(vo);
                listener.onSuccess(tableName, chartList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure(databaseError.toException());
            }
        };
        try {
            mDatabase.child(tableName).orderByChild("date").equalTo(date).addListenerForSingleValueEvent(gameValueListener);
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }
}