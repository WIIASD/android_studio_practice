package com.example.todolist;

public class TodoItem {
    private String todoItemTitle;
    private boolean checked;

    public TodoItem(String todoItemTitle) {
        this.todoItemTitle = todoItemTitle;
        checked = false;
    }

    public TodoItem(String todoItemTitle, boolean checked) {
        this.todoItemTitle = todoItemTitle;
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
}
