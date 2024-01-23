package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<String> {

    public TaskAdapter(Context context, ArrayList<String> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_task, parent, false);
        }

        CheckBox checkBoxTask = convertView.findViewById(R.id.checkBoxTask);
        checkBoxTask.setText(task);

        return convertView;
    }
}


