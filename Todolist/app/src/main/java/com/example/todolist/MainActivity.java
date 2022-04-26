package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TodoItem> todoItems;
    TDI_RecyclerViewAdapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTodoItems();
        adapter = new TDI_RecyclerViewAdapter(todoItems);
        layoutManager = new LinearLayoutManager(this);

        initAddButton();
        initDeleteButton();
//        initTodoItemSelectedCheckbox(adapter);

        setupAdapter();
    }

//    private void initTodoItemSelectedCheckbox(TDI_RecyclerViewAdapter adapter) {
//        CheckBox TodoItemSeleted = findViewById(R.id.todo_item_seleted);
//        TodoItemSeleted.setOnClickListener(view -> {
//            adapter.setSelected();
//        });
//    }

    private void initAddButton() {
        Button addButton =  findViewById(R.id.AddButton);
        addButton.setOnClickListener(view -> {
            EditText s = findViewById(R.id.et_todo_item_string_enter);
            TodoItem i = new TodoItem(s.getText().toString(), false);
            adapter.addTodoItem(i);
            s.getText().clear();
        });
    }

    private void initDeleteButton() {
        Button deleteButton = findViewById((R.id.DeleteButton));
        deleteButton.setOnClickListener(view -> {
            adapter.deleteCheckedItems();
        });
    }

    private void initTodoItems(){
        todoItems =  new ArrayList<>();
        TodoItem i1 = new TodoItem("Planetside is fun, play tonight at 9");
        TodoItem i2 = new TodoItem("kasjdfklsfhjkashfsahflashfhnlasjhfasj");
        todoItems.add(i1);
        todoItems.add(i2);
    }

    private void setupAdapter(){
        RecyclerView recyclerView = findViewById(R.id.rv_todo_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}