package com.example.myapplication;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import android.content.SharedPreferences;
import android.content.Context;
public class MainActivity6 extends
        AppCompatActivity {
    private EditText editTextTask;
    private Button buttonAddTask;
    private ListView listViewTasks;
    private ArrayList<String> tasks;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAddTask = findViewById(R.id.button);
        listViewTasks = findViewById(R.id.listViewTasks);

        tasks = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, tasks);
        listViewTasks.setAdapter(taskAdapter);

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });
        loadTasksFromSharedPreferences();
    }
    public void onBackButtonClicked(View view) {
        // Handle back button click
        finish(); // Finish the current activity to go back to the previous one
    }
    private void loadTasksFromSharedPreferences() {
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);

        // Retrieve tasks from SharedPreferences
        Set<String> taskSet = preferences.getStringSet("tasks", new HashSet<>());

        // Convert Set back to ArrayList
        tasks = new ArrayList<>(taskSet);

        taskAdapter = new TaskAdapter(this, tasks);
        listViewTasks.setAdapter(taskAdapter);
    }
    private void addTask() {
        String task = editTextTask.getText().toString().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            taskAdapter.notifyDataSetChanged();
            editTextTask.setText("");
            saveTasksToSharedPreferences();
        }
    }
    private void saveTasksToSharedPreferences() {
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Convert ArrayList to a Set for storage
        Set<String> taskSet = new HashSet<>(tasks);

        editor.putStringSet("tasks", taskSet);
        editor.apply();

}}