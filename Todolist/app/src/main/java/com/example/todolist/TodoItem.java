package com.example.todolist;

import java.util.Objects;

public class TodoItem {
    private String todoItemString;
    private boolean checked;

    public TodoItem(TodoItem i){
        todoItemString = i.getTodoItemString();
        checked = i.isChecked();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return Objects.equals(todoItemString, todoItem.todoItemString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todoItemString);
    }
}
