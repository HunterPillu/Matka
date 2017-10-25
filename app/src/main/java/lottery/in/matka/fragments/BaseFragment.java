package lottery.in.matka.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.transition.Explode;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.util.List;

import lottery.in.matka.MainActivity;
import lottery.in.matka.R;
import lottery.in.matka.animate.DetailsTransition;
import lottery.in.matka.models.ChartItem;
import lottery.in.matka.utils.Constant;
import lottery.in.matka.utils.ISFLog;


public abstract class BaseFragment extends Fragment {
    public Context context;
    public MainActivity activity;
    public FragmentManager fragmentManager;


    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public ProgressDialog progressDialog;

    /**
     * create directory
     *
     * @return
     */
    public void makeDir(String image_path_source_temp) {
        File dir = new File(image_path_source_temp);
        try {
            if (dir.mkdir()) {
            } else {
            }
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

/*    public void askForPermission(Context context, PermissionListener permissionlistener, String permission) {
        try {
            new TedPermission(context)
                    .setPermissionListener(permissionlistener)
                    .setDeniedMessage("If you reject permission,you can not use these services\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(permission)
                    .check();
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }*/

    /*void dialogWithButton(Context context, String msg) {
        try {
            Typeface defaultTypeface = Typeface.createFromAsset(context.getAssets(), Constant.FONT_GEORGIA);
            if (null != progressDialog && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = ProgressDialog.show(context, "", "", true);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.dialog_message_with_ok);
            TextView tvLoading = (TextView) progressDialog.findViewById(R.id.tvDialogText);
            tvLoading.setTypeface(defaultTypeface, Typeface.NORMAL);
            tvLoading.setText(msg);
            TextView tvOk = (TextView) progressDialog.findViewById(R.id.tvOk);
            tvOk.setTypeface(defaultTypeface, Typeface.BOLD);
            tvOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog.dismiss();
                }
            });
        } catch (Exception e) {
            PwcCustomLog.e(e);
        }
    }*/

   /* public void dialogRefresh(final Context context,View.OnClickListener listener) {
        try {
            Typeface defaultTypeface = Typeface.createFromAsset(context.getAssets(), Constant.FONT_GEORGIA);
            if (null != progressDialog && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = ProgressDialog.show(context, "", "", true);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.dialog_with_yes_no_button);
            TextView tvLoading = (TextView) progressDialog.findViewById(R.id.tvDialogText);
            tvLoading.setTypeface(defaultTypeface, Typeface.NORMAL);
            tvLoading.setText(Constant.MSG_DELETE_REFRESH);
            TextView tvLogin = (TextView) progressDialog.findViewById(R.id.tvLeft);
            tvLogin.setTypeface(defaultTypeface, Typeface.BOLD);
            tvLogin.setText("Yes");
            tvLogin.setOnClickListener(listener);
            TextView tvRegister = (TextView) progressDialog.findViewById(R.id.tvRight);
            tvRegister.setTypeface(defaultTypeface, Typeface.BOLD);
            tvRegister.setText("No");
            tvRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog.dismiss();
                }
            });
        } catch (Exception e) {
            PwcCustomLog.e(e);
        }
    }*/

    /*public void dialogLogin(final Context context, String msg) {
        try {
            Typeface defaultTypeface = Typeface.createFromAsset(context.getAssets(), Constant.FONT_GEORGIA);
            if (null != progressDialog && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = ProgressDialog.show(context, "", "", true);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.dialog_with_yes_no_button);
            TextView tvLoading = (TextView) progressDialog.findViewById(R.id.tvDialogText);
            tvLoading.setTypeface(defaultTypeface, Typeface.NORMAL);
            tvLoading.setText(msg);
            TextView tvLogin = (TextView) progressDialog.findViewById(R.id.tvLeft);
            tvLogin.setTypeface(defaultTypeface, Typeface.BOLD);
            tvLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog.dismiss();
                    Intent intent = new Intent(context, LoginActivity.class);
                    //Create the bundle
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.KEY_FROM_LOGIN, Constant.IS_FOR_LOGIN);
                    bundle.putBoolean(Constant.KEY_NAVIGATION, true);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    ((HomeActivity) context).finish();
                }
            });

            TextView tvRegister = (TextView) progressDialog.findViewById(R.id.tvRight);
            tvRegister.setTypeface(defaultTypeface, Typeface.BOLD);
            tvRegister.setText(Constant.REGISTER);
            tvRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog.dismiss();
                    Intent intent = new Intent(context, LoginActivity.class);
                    //Create the bundle
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.KEY_FROM_LOGIN, Constant.IS_FOR_REGISTER);
                    bundle.putBoolean(Constant.KEY_NAVIGATION, true);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    ((HomeActivity) context).finish();
                }
            });
        } catch (Exception e) {
            PwcCustomLog.e(e);
        }
    }*/


    /*void dialogWithButtonAction(Context context, String msg, final Dialog dialog, final String email, final EditText etEmail, final EditText password) {
        try {
            Typeface defaultTypeface = Typeface.createFromAsset(context.getAssets(), Constant.FONT_GEORGIA);
            if (null != progressDialog && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = ProgressDialog.show(context, "", "", true);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.dialog_message_with_ok);
            TextView tvLoading = (TextView) progressDialog.findViewById(R.id.tvDialogText);
            tvLoading.setTypeface(defaultTypeface, Typeface.NORMAL);
            tvLoading.setText(msg);
            TextView tvOk = (TextView) progressDialog.findViewById(R.id.tvOk);
            tvOk.setTypeface(defaultTypeface, Typeface.BOLD);
            tvOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog.dismiss();
                    dialog.dismiss();
                    etEmail.setText(email);
                    int pos = etEmail.getText().length();
                    etEmail.setSelection(pos);
                    password.setText("");
                }
            });
        } catch (Exception e) {
            PwcCustomLog.e(e);
        }
    }*/


    void showBaseLoader(String msg) {
        try {
            progressDialog = ProgressDialog.show(context, "", "", true);
            progressDialog.setCancelable(msg.equalsIgnoreCase(Constant.CANCEL));
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.dialog_progress);
            // new showBaseLoaderAsync(context).execute();
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    void hideBaseLoader() {
        try {
            if (getActivity() != null && !getActivity().isFinishing() && progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    public void closeKeyboard(Context context) {
        try {
            InputMethodManager inputManager = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            View v = ((Activity) context).getCurrentFocus();
            if (v == null) {
                return;
            }
            inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    public void openKeyboard(Context context) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    public boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    /*public void enabledInternetSetting(boolean isCancelable, Context context, final OnRetryListener onRetryListener, final int callValue) {

        try {
            hideBaseLoader();
            Typeface defaultTypeface = Typeface.createFromAsset(context.getAssets(), Constant.FONT_GEORGIA);
            if (null != progressDialog && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = ProgressDialog.show(context, "", "", true);
            progressDialog.setCanceledOnTouchOutside(isCancelable);
            progressDialog.setCancelable(isCancelable);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.dialog_with_yes_no_button);
            TextView tvLoading = (TextView) progressDialog.findViewById(R.id.tvDialogText);
            tvLoading.setTypeface(defaultTypeface, Typeface.NORMAL);
            tvLoading.setText(Constant.SERVER_BUSY);
            TextView tvRetry = (TextView) progressDialog.findViewById(R.id.tvLeft);
            tvRetry.setText("Retry");
            tvRetry.setTypeface(defaultTypeface, Typeface.BOLD);
            tvRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog.dismiss();
                    onRetryListener.onRetry(callValue);
                }
            });

            TextView tvCancel = (TextView) progressDialog.findViewById(R.id.tvRight);
            tvCancel.setTypeface(defaultTypeface, Typeface.BOLD);
            tvCancel.setText(Constant.CANCEL);
            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog.dismiss();
                }
            });
        } catch (Exception e) {
            PwcCustomLog.e(e);
        }
    }*/

    public int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    /*public void saveShareInfo(String result) {
        try {
            JSONObject object = new JSONObject(result);
            String about = object.getJSONObject(Constant.DATA).getString(Constant.ABOUT_US);
            String privacy = object.getJSONObject(Constant.DATA).getString(Constant.PRIVACY_POLICY);
            String info = object.getJSONObject(Constant.DATA).getString(Constant.LEGAL_INFO);
            String disclaimer = object.getJSONObject(Constant.DATA).getString(Constant.DISCLAIMER);
            String shareInfo = Constant.TEXT_BODY;
            try {
                //    shareInfo = object.getJSONObject(Constant.DATA).getString(Constant.SHARE_INFO);
                shareInfo = object.getJSONObject(Constant.DATA).getString(Constant.ANDROID_SHARE_INFO);
            } catch (Exception e) {
                PwcCustomLog.e(e);
            }
            AboutPwcVo.deleteAll(AboutPwcVo.class);
            AboutPwcVo aboutPwcVo = new AboutPwcVo();
            aboutPwcVo.setAboutUs(about);
            aboutPwcVo.setPrivacyPolicy(privacy);
            aboutPwcVo.setLegalInfo(info);
            aboutPwcVo.setDisclaimer(disclaimer);
            aboutPwcVo.setShareInfo(shareInfo);
            aboutPwcVo.save();
        } catch (Exception e) {
            PwcCustomLog.e(e);
        }
    }*/

    public void gotoChartFragment(Fragment prevFrag, String showChartFor, String title, List<ChartItem> response) {
        //    Resources res = context.getResources();
        Fragment newFrag = ChartFragment.newInstance(showChartFor, title, response);

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //   prevFrag.setExitTransition(new Explode());
                newFrag.setSharedElementEnterTransition(new DetailsTransition());
                newFrag.setEnterTransition(new Explode());
                newFrag.setExitTransition(new Explode());
                newFrag.setAllowEnterTransitionOverlap(false);
                prevFrag.setAllowEnterTransitionOverlap(false);
                newFrag.setAllowReturnTransitionOverlap(false);
                prevFrag.setAllowReturnTransitionOverlap(false);

                newFrag.setSharedElementReturnTransition(new DetailsTransition());
            }
            fragmentManager.beginTransaction()
                    .replace(R.id.container, newFrag)
                    // .addSharedElement(cvLogin, res.getString(R.string.login_card))
                    //    .addSharedElement(ivUserImage, res.getString(R.string.user_image))
                    // .addSharedElement(tvUserName, res.getString(R.string.username))
                    //     .addSharedElement(ivMobile, res.getString(R.string.login_mobile))
                    //    .addSharedElement(ivPassword, res.getString(R.string.login_password))
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) {
            ISFLog.e(e);
            ISFLog.e("TRANSITION_ERROR", "Build.VERSION.SDK_INT =" + Build.VERSION.SDK_INT);
            fragmentManager.beginTransaction()
                    .replace(R.id.container, newFrag)
                    .addToBackStack(null)
                    .commit();
        }
        //   res = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.context = context;
            activity = (MainActivity) context;
            fragmentManager = activity.getSupportFragmentManager();
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //activity.selectedFragment = this;
        try {
            if (getView() != null) {
                getView().setFocusableInTouchMode(true);
                getView().requestFocus();
                getView().setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
                            onBackPressed();
                        }
                        return true;
                    }
                });
            }
        } catch (Exception e) {
            ISFLog.e(e);
        }
    }

    abstract void onBackPressed();
}
