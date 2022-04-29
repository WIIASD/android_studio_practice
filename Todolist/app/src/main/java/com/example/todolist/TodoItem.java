package com.example.todolist;

import androidx.annotation.Nullable;

public class TodoItem {
    private String todoItemTitle;
    private String todoItemDescription;
    private boolean checked;

    public TodoItem(String todoItemTitle) {
        this.todoItemTitle = todoItemTitle;
        this.todoItemDescription = "";
        checked = false;
    }

    public TodoItem(String todoItemTitle, boolean checked) {
        this.todoItemTitle = todoItemTitle;
        this.todoItemDescription = "";
        this.checked = checked;
    }

    public TodoItem(String todoItemTitle, String todoItemDescription, boolean checked) {
        this.todoItemTitle = todoItemTitle;
        this.todoItemDescription = todoItemDescription;
        this.checked = checked;
    }

    public String getTodoItemTitle() {
        return todoItemTitle;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setTodoItemTitle(String todoItemTitle) {
        this.todoItemTitle = todoItemTitle;
    }

    public String getTodoItemDescription() {
        return todoItemDescription;
    }

    public void setTodoItemDescription(String todoItemDescription) {
        this.todoItemDescription = todoItemDescription;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof TodoItem)){
            return false;
        }
        TodoItem todoItem = (TodoItem) obj;
        return todoItem.getTodoItemTitle().equals(todoItemTitle) && todoItem.getTodoItemDescription().equals(todoItemDescription); 
    }
}
