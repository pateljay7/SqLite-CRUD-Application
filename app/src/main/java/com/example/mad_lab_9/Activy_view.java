package com.example.mad_lab_9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;

public class Activy_view extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        SQLiteDatabase db;
         mListView = (ListView) findViewById(R.id.listview);


        db=openOrCreateDatabase("EmployeeDB", Context.MODE_PRIVATE, null);

        //create table Employee
        db.execSQL("CREATE TABLE IF NOT EXISTS Employee(EmpId INTEGER PRIMARY KEY AUTOINCREMENT,EmpName VARCHAR,EmpMail VARCHAR,EmpSalary VARCHAR);");
        ArrayList<Employee> peopleList = new ArrayList<>();
        ListView mListView = (ListView) findViewById(R.id.listview);
        Cursor c=db.rawQuery("SELECT * FROM Employee", null);
        if(c.getCount()==0)
        {
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {
            buffer.append("Employee Name: "+c.getString(1)+"\n\n");
            buffer.append("Employee Mail: "+c.getString(2)+"\n\n");
            buffer.append("Employee Mobileno: "+c.getString(3)+"\n\n");

            Employee e= new Employee(c.getString(1),c.getString(2),c.getString(3));
            peopleList.add(e);
            EmployeeListAdapter adapter = new EmployeeListAdapter(this, R.layout.list_layout_employee, peopleList);
            mListView.setAdapter(adapter);

        }
    }
}