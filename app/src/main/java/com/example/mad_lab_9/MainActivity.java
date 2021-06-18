package com.example.mad_lab_9;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements android.view.View.OnClickListener {
    SQLiteDatabase db;
    EditText editempname,editempmail,editempsalary;
    Button Add, Delete, Modify, View,search ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=openOrCreateDatabase("EmployeeDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Employee(EmpId INTEGER PRIMARY KEY AUTOINCREMENT,EmpName VARCHAR,EmpMail VARCHAR,EmpSalary VARCHAR);");
        editempname = (EditText) findViewById(R.id.username);
        editempmail = (EditText) findViewById(R.id.email);
        editempsalary = (EditText) findViewById(R.id.salary);
        Add = (Button) findViewById(R.id.add);
        Delete= (Button) findViewById(R.id.delete);
        Modify= (Button) findViewById(R.id.modify);
        View= (Button) findViewById(R.id. view);
        Add.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Modify.setOnClickListener(this);
        View.setOnClickListener(this);
        Employee adapter;

    }

    public void msg(Context context,String str)
    {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.add)
        {
            // code for save data
            if(editempname.getText().toString().trim().length()==0||
                    editempmail.getText().toString().trim().length()==0||
                    editempsalary.getText().toString().trim().length()==0)
            {
                msg(this, "Please enter all values");
                return;
            }
            db.execSQL("INSERT INTO Employee(EmpName,EmpMail,EmpSalary)VALUES('"+ editempname.getText()+"','"+ editempmail.getText()+ "','"+    editempsalary.getText()+"');");
            msg(this, "Welcome User : \t  "+ editempname.getText() +" \n \n Record added");
        }

        else if(v.getId()==R.id.modify)
        {
            //code for update data
            if(editempname.getText().toString().trim().length()==0)
            {
                msg(this, "Enter Employee  Name");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM Employee WHERE EmpName='"+ editempname.getText()+"'", null);
            if(c.moveToFirst()) {
                db.execSQL("UPDATE Employee  SET EmpName ='"+ editempname.getText()+"', EmpMail='"+ editempmail.getText()+"',EmpSalary='"+editempsalary.getText()+"' WHERE EmpName ='"+editempname.getText()+"'");
                msg(this, "Record Modified");
            }
            else
            {
                msg(this, "Invalid Employee Name");
            }
        }
        else if(v.getId()==R.id.delete)
        {
            //code for delete data
            if(editempname.getText().toString().trim().length()==0)
            {
                msg(this, " Please enter Employee  Name ");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM Employee WHERE EmpName ='"+ editempname.getText()+"'", null);
           // Cursor c1=db.rawQuery()
            if(c.moveToFirst())
            {
                db.execSQL("DELETE FROM Employee WHERE EmpName ='"+ editempname.getText()+"'");
                msg(this, "Record Deleted");
            }
            else
            {
                msg(this, "Invalid Employee Name ");
            }
        }
        else if (v.getId() == R.id.view)
        {
            Intent i =new Intent(MainActivity.this,Activy_view.class);
            startActivity(i);
        }

    }
}