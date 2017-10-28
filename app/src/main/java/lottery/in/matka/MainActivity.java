package lottery.in.matka;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import lottery.in.matka.fragments.DashboardFragment;

public class MainActivity extends FirebaseAcitivity {
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
        //  Util.showToast(this, "login successfull");
        toolbar.setVisibility(View.VISIBLE);
        hideBaseLoader();
        DashboardFragment frag = new DashboardFragment();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            frag.setEnterTransition(new Explode());
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag)
                .addToBackStack(null)
                .commit();
    }
}
