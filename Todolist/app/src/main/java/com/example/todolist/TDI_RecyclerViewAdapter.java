package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TDI_RecyclerViewAdapter extends RecyclerView.Adapter<TDI_RecyclerViewAdapter.ViewHolder>{

    private ArrayList<TodoItem> todoItems;

    public void addTodoItem(TodoItem todoItem){
        todoItems.add(todoItem);
        notifyItemInserted(todoItems.size() - 1);
    }

    public TDI_RecyclerViewAdapter(ArrayList<TodoItem> todoItems){
        this.todoItems = todoItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TodoItem i = todoItems.get(position);
        holder.tvTodoItemString.setText(i.getTodoItemString());
        holder.getCbTodoItemChecked().setChecked(i.isChecked());
        holder.getCbTodoItemChecked().setOnCheckedChangeListener((view, ischecked) -> {
            i.setChecked(ischecked);
            System.out.println("ischeck = " + ischecked);
        });
    }

    @Override
    public int getItemCount() {
        return todoItems.size();
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


        public TextView getTvTodoItemString() {
            return tvTodoItemString;
        }

        public CheckBox getCbTodoItemChecked() {
            return cbTodoItemChecked;
        }
    }
}
