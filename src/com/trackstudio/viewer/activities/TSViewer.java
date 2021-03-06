package com.trackstudio.viewer.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.trackstudio.viewer.R;
import com.trackstudio.viewer.fragments.TasksFragment;
import com.trackstudio.viewer.models.Constrains;

/**
 * The main activity.
 */
public class TSViewer extends Activity {
    /**
     * Tasks list.
     */
    final TasksFragment fragment = new TasksFragment();

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.getFragmentManager()
                .beginTransaction()
                .add(
                    android.R.id.content, fragment,
                    fragment.getClass().getSimpleName()
                )
                .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                final String filter = this.getIntent()
                    .getStringExtra(Constrains.FILTER);
                this.fragment.updateUI(filter);
                return true;
            case R.id.settings:
                this.startActivity(new Intent(this, Settings.class));
                return true;
            case R.id.filters:
                this.startActivity(new Intent(this, Filters.class));
                return true;
            case R.id.notifications:
                this.startActivity(new Intent(this, Notifications.class));
                return true;
            default:
                return false;
        }
    }
}
