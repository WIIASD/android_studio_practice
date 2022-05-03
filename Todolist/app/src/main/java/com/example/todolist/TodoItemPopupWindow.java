package com.example.todolist;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class TodoItemPopupWindow extends PopupWindow {
    public TodoItemPopupWindow(Context context, TodoItem todoItem, TDI_RecyclerViewAdapter adapter) {
        super(null, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        setContentView(LayoutInflater.from(context).inflate(R.layout.todo_item_description_page, null));
        setOutsideTouchable(false);
        View popUpWindowView = getContentView();
        TextInputLayout titleLayout = popUpWindowView.findViewById(R.id.Popup_tilTodoTitleLayout);
        TextInputEditText titleView = titleLayout.findViewById(R.id.Popup_tietTitleInput);
        TextInputEditText descriptionView = popUpWindowView.findViewById((R.id.Popup_tietDescriptionInput));
        titleView.setText(todoItem.getTodoItemTitle());
        descriptionView.setText(todoItem.getTodoItemDescription());
        popUpWindowView.findViewById(R.id.Popup_btnDone).setOnClickListener(view1 -> {
            String title_string = titleView.getText().toString();
            String description_string = descriptionView.getText().toString();
            if(title_string.equals("")){
                titleLayout.setErrorEnabled(true);
                titleLayout.setError("Please Enter Something");
                return;
            }
            todoItem.setTodoItemTitle(title_string);
            todoItem.setTodoItemDescription(description_string);
            adapter.notifyItemChanged(adapter.getTodoItems().indexOf(todoItem));//have to do a search in case position is updated
            dismiss();
        });
        descriptionView.addTextChangedListener(new TextWatcher() {
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
    }
}
