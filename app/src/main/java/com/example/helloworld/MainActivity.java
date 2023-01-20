package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

//    AppDatabase db =  Room.databaseBuilder(getApplicationContext(),
//            AppDatabase.class, "database").build();
    AppDatabase db =  Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "database")
            .allowMainThreadQueries()
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void addPerson(View view) {
        db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();

        Employee employee = new Employee();
        employee.id = 1;
        employee.name = "John Smith";
        employee.salary = 10000;

        employeeDao.insert(employee);
    }

    public void getAllEmployees(View v) {
        db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();

        List<Employee> employees = employeeDao.getAll();
    }

    public void getEmployee(View v, int emId) {
        db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();

        Employee employee = employeeDao.getById(emId);
    }

    public void updateEmployee(View v, int emId) {
        db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();
        Employee employee = employeeDao.getById(emId);

        employee.salary = 20000;
        employeeDao.update(employee);
    }

    public void deleteEmployee(View v, int emId) {
        db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();

        Employee employee = employeeDao.getById(emId);
        employeeDao.delete(employee);
    }
}