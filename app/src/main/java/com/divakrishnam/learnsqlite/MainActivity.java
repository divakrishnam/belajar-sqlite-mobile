package com.divakrishnam.learnsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvData;
    EditText etStudentId, etStudentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = findViewById(R.id.tv_data);
        etStudentId = findViewById(R.id.et_student_id);
        etStudentName = findViewById(R.id.et_student_name);
    }

    public void loadStudent(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        tvData.setText(dbHandler.loadHandler());
        etStudentId.setText("");
        etStudentName.setText("");
    }

    public void findStudent(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        Student student = dbHandler.findHandler(etStudentName.getText().toString());
        if (student!=null){
            tvData.setText(String.valueOf(student.getStudentID())+" "+student.getStudentName()+System.getProperty("line.separator"));
            etStudentId.setText("");
            etStudentName.setText("");
        }else{
            tvData.setText("No Match Found");
            etStudentId.setText("");
            etStudentName.setText("");
        }
    }

    public void addStudent(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        int id = Integer.parseInt(etStudentId.getText().toString());
        String name = etStudentName.getText().toString();
        Student student= new Student(id, name);
        dbHandler.addHandler(student);
        etStudentId.setText("");
        etStudentName.setText("");
    }

    public void removeStudent(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        boolean result = dbHandler.deleteHandler(Integer.parseInt(etStudentId.getText().toString()));
        if (result){
            etStudentId.setText("");
            etStudentName.setText("");
            tvData.setText("Record Deleted");
        }else{
            etStudentId.setText("No Match Found");
        }
    }

    public void updateStudent(View view) {
        DBHandler dbHandler= new DBHandler(this, null, null, 1);
        boolean result = dbHandler.updateHandler(Integer.parseInt(etStudentId.getText().toString()), etStudentName.getText().toString());
        if (result){
            etStudentId.setText("");
            etStudentName.setText("");
            tvData.setText("Record Updated");
        }else{
            tvData.setText("No Match Found");
        }
    }
}
