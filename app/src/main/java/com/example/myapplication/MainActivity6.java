package com.example.myapplication;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import java.util.ArrayList;


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
    }

    private void addTask() {
        String task = editTextTask.getText().toString().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            taskAdapter.notifyDataSetChanged();
            editTextTask.setText("");
        }
    }


}