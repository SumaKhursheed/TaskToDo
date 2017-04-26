package com.example.todo.adapters;

import android.database.Cursor;

import com.example.todo.model.Task;

import java.util.ArrayList;

public interface UpdateAdapter {
    public void updateTaskArrayAdapter(ArrayList<Task> tasks);
    public void updateTaskArrayAdapter(Cursor cursor);
    public void update();
}
