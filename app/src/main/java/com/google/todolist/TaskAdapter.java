package com.google.todolist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by mercury on 04/10/15.
 */
public class TaskAdapter extends BaseAdapter {

    final Activity activity;
    final TodoDatabase.Task[] tasks;

    public TaskAdapter(Activity activity, TodoDatabase.Task[] tasks )
    {
        this.activity = activity;
        this.tasks = tasks;
    }


    @Override
    public int getCount() {
        return tasks.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_category_list_item, parent, false);

        TextView nameView = (TextView) view.findViewById(R.id.cl_title);
        TextView positionView = (TextView) view.findViewById(R.id.cl_desc);

        TodoDatabase.Task task = tasks[position];

        nameView.setText(task.name);
        positionView.setText(task.deadline);

        return view;
    }
}
