package com.google.todolist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.todolist.TodoDatabase.Category;
/**
 * Created by Esther on 10/3/15.
 */
public class CategoryAdapter extends BaseAdapter {

    final Activity activity;
    final Category[] categories;

    public CategoryAdapter(Activity activity, Category[] categories )
    {
        this.activity = activity;
        this.categories = categories;
    }


    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public Object getItem(int position) {
        return categories[position];
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

        Category category = categories[position];

        nameView.setText(category.name);
        positionView.setText(category.description);

        return view;
    }
}
