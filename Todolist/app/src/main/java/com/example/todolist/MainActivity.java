package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TodoItem> todoItems;
    Button addButton, deleteButton;
    RecyclerView recyclerView;
    TextInputLayout todoListItemTextBoxLayout;
    TextInputEditText todoListItemTextBox;

    TDI_RecyclerViewAdapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.btnAddButton);
        deleteButton = findViewById(R.id.btnDeleteButton);
        todoListItemTextBox = findViewById(R.id.tietTodoItemEditText);
        todoListItemTextBoxLayout = findViewById(R.id.tilTodoItemEditTextLayout);
        recyclerView = findViewById(R.id.rvTodoList);

        fillTodoItems();

        adapter = new TDI_RecyclerViewAdapter(todoItems);
        layoutManager = new LinearLayoutManager(this);

        initAddButton();
        initDeleteButton();
        initTodoListItemTextBox();
        // TODO: 4/29/2022 clean up this chunk of garbage 
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                try {
                    FileWriter.writeToJson(recyclerView.getContext(), adapter.getTodoItems(), "list_data.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onChanged() {
                super.onChanged();
                try {
                    FileWriter.writeToJson(recyclerView.getContext(), adapter.getTodoItems(), "list_data.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                try {
                    FileWriter.writeToJson(recyclerView.getContext(), adapter.getTodoItems(), "list_data.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);
                try {
                    FileWriter.writeToJson(recyclerView.getContext(), adapter.getTodoItems(), "list_data.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                try {
                    FileWriter.writeToJson(recyclerView.getContext(), adapter.getTodoItems(), "list_data.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                try {
                    FileWriter.writeToJson(recyclerView.getContext(), adapter.getTodoItems(), "list_data.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        setupAdapter();
    }

    private void initTodoListItemTextBox() {
        todoListItemTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(todoListItemTextBoxLayout.isErrorEnabled()){
                    todoListItemTextBoxLayout.setErrorEnabled(false);
                    todoListItemTextBoxLayout.setError(null);
                }
            }
        });
    }

    private void initAddButton() {
        addButton.setOnClickListener(view -> {
            String s = todoListItemTextBox.getText().toString();
            if(s.equals("")) {
                todoListItemTextBoxLayout.setErrorEnabled(true);
                todoListItemTextBoxLayout.setError("Please Enter Something");
                return;
            }
            todoListItemTextBoxLayout.setErrorEnabled(false);
            todoListItemTextBoxLayout.setError(null);
            TodoItem i = new TodoItem(s, false);
            adapter.addTodoItem(i);
            todoListItemTextBox.getText().clear();
        });
    }

    private void initDeleteButton() {
        deleteButton.setOnClickListener(view -> {
            adapter.deleteCheckedItems();
        });
    }

    private void fillTodoItems(){
        todoItems =  new ArrayList<>();
        TodoItem i1 = new TodoItem("🐱——————————");
        TodoItem i2 = new TodoItem("Planetside is fun, play tonight at 9");
        todoItems.add(i1);
        todoItems.add(i2);
    }

    private void setupAdapter(){
        RecyclerView recyclerView = findViewById(R.id.rvTodoList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}