package com.example.todolist;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TDI_RecyclerViewAdapter extends RecyclerView.Adapter<TDI_RecyclerViewAdapter.ViewHolder>{

    private ArrayList<TodoItem> todoItems;

    public TDI_RecyclerViewAdapter(ArrayList<TodoItem> todoItems){
        this.todoItems = todoItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item_row, parent, false);

        return new ViewHolder(view);
    }

    public ArrayList<TodoItem> getTodoItems() {
        return todoItems;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TodoItem i = todoItems.get(position);
        holder.tvTodoItemString.setText(i.getTodoItemTitle());
        holder.cbTodoItemChecked.setChecked(i.isChecked());
        holder.cbTodoItemChecked.setOnCheckedChangeListener((view, ischecked) -> {
            i.setChecked(ischecked);
        });
        holder.itemView.findViewById(R.id.todo_item_card).setOnClickListener(view -> {
            final TodoItemPopupWindow popupWindow = new TodoItemPopupWindow(view.getContext(), i, this);
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        });
    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    public void addTodoItem(TodoItem todoItem){
        todoItems.add(todoItem);
        notifyItemInserted(todoItems.size() - 1);
    }

    public void deleteCheckedItems() {
        for (int i = todoItems.size() - 1; i >= 0; i-- ) {
            if(todoItems.get(i).isChecked()){
                todoItems.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvTodoItemString;
        private final CheckBox cbTodoItemChecked;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTodoItemString = itemView.findViewById(R.id.todo_item_string);
            cbTodoItemChecked = itemView.findViewById(R.id.todo_item_checked);
        }
    }
}
