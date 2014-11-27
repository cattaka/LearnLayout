package net.cattaka.android.learnlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

/**
 * Created by cattaka on 14/11/27.
 */
public class CustomActionBarActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_action_bar);

        getActionBar().setCustomView(R.layout.action_bar);
        getActionBar().setDisplayShowCustomEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
