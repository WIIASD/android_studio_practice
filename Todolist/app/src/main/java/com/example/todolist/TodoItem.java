package com.example.todolist;

import java.util.Objects;

public class TodoItem {
    private String todoItemString;
    private boolean checked;

    public TodoItem(String todoItemString) {
        this.todoItemString = todoItemString;
        checked = false;
    }

    public TodoItem(String todoItemString, boolean checked) {
        this.todoItemString = todoItemString;
        this.checked = checked;
    }

    public String getTodoItemString() {
        return todoItemString;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
