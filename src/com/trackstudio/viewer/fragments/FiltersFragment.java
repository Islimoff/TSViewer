package com.trackstudio.viewer.fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.trackstudio.viewer.R;
import com.trackstudio.viewer.activities.TSViewer;
import com.trackstudio.viewer.adapters.FilterList;
import com.trackstudio.viewer.models.FilterItem;
import com.trackstudio.viewer.services.FiltersUpdater;
import java.util.ArrayList;
import java.util.List;

/**
 * Filters fragments.
 */
public class FiltersFragment extends ListFragment {
    /**
     * Storage filters
     */
    private final List<FilterItem> list = new ArrayList<FilterItem>();

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        this.setListAdapter(
            new FilterList(this.getActivity(), this.list)
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        final Button refresh = new Button(getActivity());
        refresh.setText("Refresh");
        refresh.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FiltersFragment.this.updateUI();
                }
            }
        );
        getListView().addHeaderView(refresh);
        this.updateUI();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        TasksFragment fragment = (TasksFragment) this.getFragmentManager().findFragmentById(R.id.fragment_tasks);
        if (fragment != null && fragment.isVisible()) {
            fragment.update(id);
        } else {
            this.startActivity(
                new Intent(this.getActivity(), TSViewer.class)
            );
        }
    }

    /**
     * Updates the UI by Async thread.
     */
    private void updateUI() {
        new FiltersUpdater(
            (ArrayAdapter) getListAdapter(),
            list
        ).execute();
    }
}
