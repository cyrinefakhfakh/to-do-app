package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import java.util.ArrayList;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.EditText;
public class TaskAdapter extends ArrayAdapter<String> {
    private ArrayList<String> tasks;
    public TaskAdapter(Context context, ArrayList<String> tasks) {
        super(context, 0, tasks);
        this.tasks = tasks;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final String task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_task, parent, false);
        }

        CheckBox checkBoxTask = convertView.findViewById(R.id.checkBoxTask);
        EditText edittext = convertView.findViewById(R.id.editTextTask);
        ImageButton delete = convertView.findViewById(R.id.delete);

        checkBoxTask.setText(task);
        edittext.setText(task);

        // Set a listener for delete button click
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove the task from the list
                tasks.remove(position);
                notifyDataSetChanged(); // Update the ListView
            }
        });

        return convertView;
    }}


