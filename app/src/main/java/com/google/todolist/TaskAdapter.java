package com.google.todolist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.todolist.TodoDatabase.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mercury on 04/10/15.
 */
public class TaskAdapter extends BaseAdapter {

    final Activity activity;
    final Task[] tasks;

    public TaskAdapter(Activity activity, Task[] tasks )
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

        View view = inflater.inflate(R.layout.activity_task_list_item, parent, false);

        TextView nameView = (TextView) view.findViewById(R.id.task_name);
        TextView positionView = (TextView) view.findViewById(R.id.task_deadline);

        TodoDatabase.Task task = tasks[position];

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date dateline = new Date(task.deadline);
        String reportDate = df.format(dateline);

        nameView.setText(task.name);
        positionView.setText(reportDate);

        return view;
    }
}
