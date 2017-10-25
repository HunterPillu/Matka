package lottery.in.matka.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.ButterKnife;
import lottery.in.matka.R;
import lottery.in.matka.utils.Constant;


public class LoginFragment extends AppCompatDialogFragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.dialog_login, container, false);
        ButterKnife.bind(this, v);
        AppCompatButton bLogin = (AppCompatButton) v.findViewById(R.id.bLogin);
        final EditText etPassword = (EditText) v.findViewById(R.id.etPassword);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    etPassword.setError(Constant.MSG_ENTER_PASSWORD);
                } else {
                    dismiss();
                    checkLogin(password);
                }

            }
        });

        return v;
    }


    private void checkLogin(String password) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ResultSubmitFragment())
                .addToBackStack(null)
                .commit();
    }

}
