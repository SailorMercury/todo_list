package com.google.todolist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class TaskListActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    TodoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        // Get Intention
        String categoryId = getIntent().getStringExtra("id");

        db = new TodoDatabase(this);

        //EditText searchText = (EditText) findViewById(R.id.listView);
        //searchText.addTextChangedListener(this);

        TaskAdapter adapter = new TaskAdapter(this, db.taskSearchByCategory(categoryId));

        ListView listView = (ListView) findViewById(R.id.tl_listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
