package lottery.in.matka;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import lottery.in.matka.fragments.DashboardFragment;
import lottery.in.matka.utils.Util;

public class MainActivity extends BaseAcitivity {
    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


    }

    @Override
    void loginSuccessfull() {
        Util.showToast(this, "login successfull");
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new DashboardFragment())
                .addToBackStack(null)
                .commit();
    }
}
