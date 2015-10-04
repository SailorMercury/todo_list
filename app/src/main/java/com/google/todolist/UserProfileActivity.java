package com.google.todolist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class UserProfileActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        TodoDatabase db = new TodoDatabase(this);
        CategoryAdapter category = new CategoryAdapter(this, db.searchByName(""));
        TaskAdapter all_task = new TaskAdapter(this, db.taskSearchByName(""));
        TaskAdapter completed_task = new TaskAdapter(this, db.taskSearchByStatus(true));

        TextView category_count = (TextView) findViewById(R.id.todo_category_count);
        TextView todo_task_count = (TextView) findViewById(R.id.todo_task_count);
        TextView todo_task_completed = (TextView) findViewById(R.id.todo_task_completed);


        category_count.setText(category.getCount());
        todo_task_count.setText(all_task.getCount());
        todo_task_completed.setText(completed_task.getCount());
    }
}
