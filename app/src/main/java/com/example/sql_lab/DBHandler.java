package com.example.sql_lab;

import java.util.ArrayList; import
        java.util.List;
import android.content.ContentValues;
import android.content.Context; import
        android.database.Cursor;
import android.database.sqlite.SQLiteDatabase; import
        android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by smann
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentsManager";
    private static final String TABLE_STUDENTS = "students";
    private static final String KEY_ID = "id"; private
    static final String KEY_NAME = "name"; private static
    final String KEY_PH_NO = "phone_number";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);

        // Create table again
        onCreate(db);
    }

    // Adding new student
    void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName()); // Contact Name
        values.put(KEY_PH_NO, student.getPhone()); // Contact Phone
        // Inserting Row
        db.insert(TABLE_STUDENTS, null, values);
//2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // Getting single student
    Student getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();

        Student student = new Student(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return student
        return student;
    }

    // Getting All Contacts
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) { do {
            Student student = new Student();
            student.setID(Integer.parseInt(cursor.getString(0)));
            student.setName(cursor.getString(1));
            student.setPhone(cursor.getString(2));
            // Adding student to list
            studentList.add(student); }
        while (cursor.moveToNext());
        }

        // return student list
        return studentList;
    }

    // Updating single student
    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName()); values.put(KEY_PH_NO,
                student.getPhone());

        // updating row
        return db.update(TABLE_STUDENTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getID()) });
    }

    // Deleting single student
    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getID()) });
        db.close();
    }

    // Getting student Count
    public int getStudentsCount() {
        String countQuery = "SELECT * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    } }
