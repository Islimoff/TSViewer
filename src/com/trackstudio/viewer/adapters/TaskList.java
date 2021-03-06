package com.trackstudio.viewer.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.trackstudio.viewer.R;
import com.trackstudio.viewer.models.TaskItem;
import java.util.List;

/**
 * Task list adapter.
 */
public class TaskList extends ArrayAdapter<TaskItem> {
    /**
     * Default constructor.
     * @param context Context
     * @param items Items
     */
    public TaskList(final Activity context, final List<TaskItem> items) {
        super(context, R.layout.tasks_fragment, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            convertView = inflater.inflate(R.layout.tasks_fragment, parent, false);
            viewHolder = this.initConvertView(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        this.updateTaskItem(viewHolder, position);
        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.rgb(245, 245, 245));
        } else {
            convertView.setBackgroundColor(Color.rgb(238, 236, 243));
        }
        return convertView;
    }

    /**
     * Initiate convert view.
     * @param convertView Convert view
     * @return View holder
     */
    private ViewHolder initConvertView(View convertView) {
        final ViewHolder viewHolder = new ViewHolder();
        viewHolder.submitter = (TextView) convertView.findViewById(R.id.submitter);
        viewHolder.submitDate = (TextView) convertView.findViewById(R.id.submib_date);
        viewHolder.taskName = (TextView) convertView.findViewById(R.id.task_name);
        convertView.setTag(viewHolder);
        return viewHolder;
    }

    /**
     * Update current item.
     * @param viewHolder View holder
     * @param position Position
     */
    private void updateTaskItem(final ViewHolder viewHolder, final int position) {
        final TaskItem item = this.getItem(position);
        viewHolder.submitter.setText(item.getSubmitter());
        viewHolder.submitDate.setText(item.getSubmitDate());
        viewHolder.taskName.setText(
            String.format("%s [#%s]", item.getTaskName(), item.getNumber())
        );
    }


    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     */
    private static class ViewHolder {
        TextView submitter;
        TextView submitDate;
        TextView taskName;
    }
}