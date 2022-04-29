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
            View popUpView = LayoutInflater.from(view.getContext()).inflate(R.layout.todo_item_description_page, null);
            int w = ViewGroup.LayoutParams.MATCH_PARENT;
            int h = ViewGroup.LayoutParams.MATCH_PARENT;
            // TODO: 4/29/2022 put popupwindow stuff in a class 
            final PopupWindow popupWindow = new PopupWindow(popUpView, w, h, true);
            popupWindow.setOutsideTouchable(false);
            View popUpWindowView = popupWindow.getContentView();
            TextInputLayout titleLayout = popUpWindowView.findViewById(R.id.Popup_tilTodoTitleLayout);
            TextInputEditText title = titleLayout.findViewById(R.id.Popup_tietTitleInput);
            TextInputEditText description = popUpWindowView.findViewById((R.id.Popup_tietDescriptionInput));
            title.setText(i.getTodoItemTitle());
            description.setText(i.getTodoItemDescription());
            popUpWindowView.findViewById(R.id.Popup_btnDone).setOnClickListener(view1 -> {
                String title_string = title.getText().toString();
                String description_string = description.getText().toString();
                if(title_string.equals("")){
                    titleLayout.setErrorEnabled(true);
                    titleLayout.setError("Please Enter Something");
                    return;
                }
                i.setTodoItemTitle(title_string);
                i.setTodoItemDescription(description_string);
                notifyItemChanged(todoItems.indexOf(i));//have to do a search in case position is updated
                popupWindow.dismiss();
            });
            title.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                @Override
                public void afterTextChanged(Editable editable) {
                    if(titleLayout.isErrorEnabled()){
                        titleLayout.setErrorEnabled(false);
                        titleLayout.setError(null);
                    }
                }
            });
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
