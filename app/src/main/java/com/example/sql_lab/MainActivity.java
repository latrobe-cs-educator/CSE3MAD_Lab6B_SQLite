package com.example.sql_lab;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends Activity {

    private Button addBtn, popBtn;
    private EditText nameET, phoneET;
    private DBHandler db;
    private String name = "";
    private String phone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = (Button)findViewById(R.id.addBtn);
        popBtn = (Button)findViewById(R.id.popBtn);

        nameET = (EditText)findViewById(R.id.nameET);
        phoneET = (EditText) findViewById(R.id.phoneET);

        db = new DBHandler(this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get details from edit text
                name = nameET.getText().toString();
                phone = phoneET.getText().toString();
                db.addStudent(new Student(name, phone));
                Log.d("Insert: ", "Added " + name + " to database");
                nameET.getText().clear();
                phoneET.getText().clear();
                readAllStudents();
            }
        });

        popBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inserting students
                Log.d("Insert: ", "Inserting ..");
                db.addStudent(new Student("Mat", "43540"));
                db.addStudent(new Student("Alex", "54334"));
                db.addStudent(new Student("Sameer", "34422"));
                db.addStudent(new Student("Shaz", "48465"));
                Log.d("Insert: ", "Database Population Complete");
                readAllStudents();
            }
        });

    }

    public void readAllStudents()
    {
        // Reading all students
        Log.d("Reading: ", "Reading all students..");
        List<Student> students = db.getAllStudents();

        for (Student cn : students) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: "
                    + cn.getPhone();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }

}
