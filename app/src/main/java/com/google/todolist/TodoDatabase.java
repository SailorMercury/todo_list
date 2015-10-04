package com.google.todolist;

        import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by mercury on 03/10/15.
 */
public class TodoDatabase extends SQLiteOpenHelper {



    public static class Category {

        public String id;
        public String name;
        public String description;
    }

    public static class Task {

        public String id;
        public String category_id;
        public String name;
        public int deadline;
    }

    public TodoDatabase(Context context) {
        super(context, "TodoDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS category (" +
                "ID TEXT(12) PRIMARY KEY," +
                "name TEXT," +
                "description TEXT )";

        String sql2 = "CREATE TABLE IF NOT EXISTS task_list (" +
                "ID TEXT(12) PRIMARY KEY," +
                "category_id TEXT," +
                "name TEXT," +
                "deadline INTEGER, " +
                "status BOOLEAN )";

        db.execSQL(sql);
        db.execSQL(sql2);

        Long longCurrentTime = System.currentTimeMillis();
        int currentTime = longCurrentTime.intValue();

        addCategory(db, "tdc001", "House Chores", "House stuff to do");
        addCategory(db,"tdc002","Office Work", "Work related stuff");
        addCategory(db,"tdc003","Kids list", "Kids stuff to do");
        addTask(db, "tdl001", "tdc001", "Sweep Floor",currentTime+777777, true);
        addTask(db, "tdl002", "tdc001", "Clean Dishes",currentTime+888888, false);
        addTask(db, "tdl003", "tdc001", "Throw out rubbish", currentTime + 999999, false);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL("DROP TABLE IF EXISTS task_list");
        onCreate(db);
    }

    public Category[] searchByName(String name){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(("SELECT * FROM category where name like ? "), new String[] { "%" + name + "%"});
        Category[] categories = new Category[cursor.getCount()];
        for (int c = 0;c < categories.length; c++)
        {
            cursor.moveToPosition(c);
            String id = cursor.getString(cursor.getColumnIndex("ID"));
            categories[c] = getCategory(id);
        }
        db.close();
        return categories;
    }

    public Task[] taskSearchByName(String name){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(("SELECT * FROM task_list where name like ? "), new String[] { "%" + name + "%"});
        Task[] tasks = new Task[cursor.getCount()];
        for (int c = 0;c < tasks.length; c++)
        {
            cursor.moveToPosition(c);
            String id = cursor.getString(cursor.getColumnIndex("ID"));
            tasks[c] = getTask(id);
        }
        db.close();
        return tasks;
    }

    public Task[] taskSearchByStatus(boolean status){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(("SELECT * FROM task_list where status is ? "), new String[] { Boolean.toString(status) });
        Task[] tasks = new Task[cursor.getCount()];
        for (int c = 0;c < tasks.length; c++)
        {
            cursor.moveToPosition(c);
            String id = cursor.getString(cursor.getColumnIndex("ID"));
            tasks[c] = getTask(id);
        }
        db.close();
        return tasks;
    }

    public Task[] taskSearchByCategory(String category_id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(("SELECT * FROM task_list where category_id = ?"), new String[] { category_id });
        Task[] tasks = new Task[cursor.getCount()];
        for (int c = 0;c < tasks.length; c++)
        {
            cursor.moveToPosition(c);
            String id = cursor.getString(cursor.getColumnIndex("ID"));
            tasks[c] = getTask(id);
        }
        db.close();
        return tasks;
    }

    public Category getCategory(String id)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(("SELECT * FROM category WHERE ID = ? "), new String[]{id});
        //Cursor cursor = db.rawQuery(("SELECT ID, name, position, officePhone, cellPhone, department," +
        //        "email, salary, profileUrl, supervisorID, annualLeaveLeft, " +
        //        "ifnull((SELECT name FROM employee WHERE ID = emp.supervisorID), '-') As supervisorName FROM employee emp WHERE " +
        //        "ID = ?"), new String[] {id});

        cursor.moveToPosition(0);

        //Log.d("DEBUG", "D: " + cursor.getCount() + " " + cursor.getColumnCount());

        Category category = new Category();
        category.id = cursor.getString(cursor.getColumnIndex("ID"));
        category.name = cursor.getString(cursor.getColumnIndex("name"));
        category.description = cursor.getString(cursor.getColumnIndex("description"));
        cursor.close();
        db.close();
        return category;
    }

    public Task getTask(String id)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(("SELECT * FROM task_list WHERE ID = ? "), new String[]{id});
        //Cursor cursor = db.rawQuery(("SELECT ID, name, position, officePhone, cellPhone, department," +
        //        "email, salary, profileUrl, supervisorID, annualLeaveLeft, " +
        //        "ifnull((SELECT name FROM employee WHERE ID = emp.supervisorID), '-') As supervisorName FROM employee emp WHERE " +
        //        "ID = ?"), new String[] {id});

        cursor.moveToPosition(0);

        //Log.d("DEBUG", "D: " + cursor.getCount() + " " + cursor.getColumnCount());

        Task task = new Task();
        task.id = cursor.getString(cursor.getColumnIndex("ID"));
        task.category_id = cursor.getString(cursor.getColumnIndex("category_id"));
        task.name = cursor.getString(cursor.getColumnIndex("name"));
        task.deadline = cursor.getInt(cursor.getColumnIndex("deadline"));
        cursor.close();
        db.close();
        return task;
    }

    public void addCategory(SQLiteDatabase db, String id, String name, String description)
    {
        ContentValues values = new ContentValues();
        values.put("ID", id);
        values.put("name", name);
        values.put("description", description);

        db.insert("category", "name", values);

    }

    public void addTask(SQLiteDatabase db, String id, String category_id, String name, Integer deadline, Boolean status)
    {
        ContentValues values = new ContentValues();
        values.put("ID", id);
        values.put("category_id", category_id);
        values.put("name", name);
        values.put("deadline", deadline);
        values.put("deadline", status);

        db.insert("task_list", "name" ,values);

    }
}
