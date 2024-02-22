package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class shopping extends AppCompatActivity {
    private ArrayList<String> taskList;
    private ArrayAdapter<String> adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.activity_item_task, R.id.textViewTask, taskList);

        ListView listViewTasks = findViewById(R.id.listViewTasks);
        listViewTasks.setAdapter(adapter);

        // Load saved tasks
        sharedPreferences = getSharedPreferences("tasks", MODE_PRIVATE);
        Set<String> savedTasks = sharedPreferences.getStringSet("taskSet", new HashSet<String>());
        taskList.addAll(savedTasks);
        adapter.notifyDataSetChanged();
        listViewTasks.setOnTouchListener(new shopping.SwipeToDeleteListener(listViewTasks));

        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle task item click here (if needed)
            }
        });

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
    private class SwipeToDeleteListener implements View.OnTouchListener {

        private float startX;
        private ListView listView;

        SwipeToDeleteListener(ListView listView) {
            this.listView = listView;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = event.getX();
                    return true;
                case MotionEvent.ACTION_UP:
                    float endX = event.getX();
                    if (endX - startX > 100) {
                        // Swiped from left to right
                        int position = listView.pointToPosition((int) startX, (int) event.getY());
                        if (position != ListView.INVALID_POSITION) {
                            taskList.remove(position);
                            adapter.notifyDataSetChanged();
                            saveTasks();
                        }
                    }
                    return true;
            }
            return false;
        }
    }
    private void saveTasks() {
        // Save tasks
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("taskSet", new HashSet<>(taskList));
        editor.apply();
    }
    private void addTask() {
        EditText editTextTask = findViewById(R.id.editTextTask);
        String task = editTextTask.getText().toString().trim();

        if (!task.isEmpty()) {
            taskList.add(task);
            adapter.notifyDataSetChanged();
            editTextTask.setText("");
            saveTasks();

            // Save tasks
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet("taskSet", new HashSet<>(taskList));
            editor.apply();
        } else {
            Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteTask() {
        taskList.clear();
        adapter.notifyDataSetChanged();
    }
}