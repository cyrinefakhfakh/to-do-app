package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<String> {

    public TaskAdapter(Context context, ArrayList<String> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        String task = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_task, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.checkBoxTask = convertView.findViewById(R.id.checkBoxTask);
            viewHolder.editTextTask = convertView.findViewById(R.id.editTextTask);
            viewHolder.deleteButton = convertView.findViewById(R.id.delete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.checkBoxTask.setText(task);
        viewHolder.editTextTask.setText(task);

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement delete functionality here
                remove(getItem(position));
            }
        });

        return convertView;
    }

    static class ViewHolder {
        CheckBox checkBoxTask;
        EditText editTextTask;
        ImageButton deleteButton;
    }
}
