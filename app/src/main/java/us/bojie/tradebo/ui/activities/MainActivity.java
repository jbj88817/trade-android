package us.bojie.tradebo.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import us.bojie.tradebo.R;
import us.bojie.tradebo.ui.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}
