package com.example.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class Myactivity3 extends MainActivity {

    private ArrayList<String> taskList;
    private ArrayAdapter<String> adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myactivity3);

        // Initialize task list and adapter
        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);

        ListView listViewTasks = findViewById(R.id.listViewTasks);
        listViewTasks.setAdapter(adapter);
        sharedPreferences = getSharedPreferences("tasks", MODE_PRIVATE);
        Set<String> savedTasks = sharedPreferences.getStringSet("taskSet", new HashSet<String>());
        taskList.addAll(savedTasks);
        adapter.notifyDataSetChanged();
        ImageButton addButton = findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        ImageButton deleteButton = findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask();
            }
        });
    }

    public void onBackButtonClicked(View view) {
        // Handle back button click here
        finish(); // Close the activity
    }

    private void addTask() {
        EditText editTextTask = findViewById(R.id.editTextTask);
        String task = editTextTask.getText().toString().trim();

        if (!task.isEmpty()) {
            taskList.add(task);
            adapter.notifyDataSetChanged();
            editTextTask.setText("");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet("taskSet", new HashSet<>(taskList));
            editor.apply();
        } else {
            Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteTask() {
        ListView listViewTasks = findViewById(R.id.listViewTasks);
        int position = listViewTasks.getCheckedItemPosition();

        if (position != ListView.INVALID_POSITION) {
            taskList.remove(position);
            adapter.notifyDataSetChanged();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet("taskSet", new HashSet<>(taskList));
            editor.apply();
        } else {
            Toast.makeText(this, "Please select a task to delete", Toast.LENGTH_SHORT).show();
        }
    }
}
