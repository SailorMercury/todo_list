package com.google.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static TodoDatabase.Category selectedCategory = null;
    TodoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);


        db = new TodoDatabase(this);

        //EditText searchText = (EditText) findViewById(R.id.listView);
        //searchText.addTextChangedListener(this);

        search("");

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

    }

    public void search(String searchText)
    {
        CategoryAdapter adapter = new CategoryAdapter(this, db.searchByName(searchText));

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TodoDatabase.Category category = (TodoDatabase.Category) parent.getItemAtPosition(position);

        Intent intent = new Intent(this,TaskListActivity.class);
        intent.putExtra("id", category.id);

        startActivity(intent);
    }

    public void show_badge(MenuItem item)
    {
        Intent intent = new Intent (this, BadgeActivity.class);

        startActivity(intent);
    }

    public void show_profile(MenuItem item)
    {
        Intent intent = new Intent (this, UserProfileActivity.class);

        startActivity(intent);
    }
}
