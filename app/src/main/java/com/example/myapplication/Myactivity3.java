package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Myactivity3 extends AppCompatActivity {
    private ImageView backButton;
    private ImageButton addButton;
    private  EditText editTextTask;
    private ListView listViewTasks;
    private ArrayList<String> tasks;
    private TaskAdapter taskAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myactivity3);

        editTextTask = findViewById(R.id.editTextTask);
        backButton = findViewById(R.id.back);
        addButton = findViewById(R.id.button);
        listViewTasks = findViewById(R.id.listViewTasks);

        tasks = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, tasks);
        listViewTasks.setAdapter(taskAdapter);

        // Load tasks from SharedPreferences
        loadTasksFromSharedPreferences();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity and return to the previous one
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });
    }

    private void loadTasksFromSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("MyTasks", Context.MODE_PRIVATE);
        Set<String> taskSet = preferences.getStringSet("tasks", new HashSet<>());
        tasks.addAll(taskSet);
        taskAdapter.notifyDataSetChanged();
    }
    private void addTask() {
        String task = editTextTask.getText().toString().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            taskAdapter.notifyDataSetChanged();
            editTextTask.setText("");
            saveTasksToSharedPreferences();
        }}
    private void saveTasksToSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("MyTasks", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> taskSet = new HashSet<>(tasks);
        editor.putStringSet("tasks", taskSet);
        editor.apply();
    }

}