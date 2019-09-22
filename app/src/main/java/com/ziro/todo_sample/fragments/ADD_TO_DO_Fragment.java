package com.ziro.todo_sample.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ziro.todo_sample.DataBaseClient;
import com.ziro.todo_sample.R;
import com.ziro.todo_sample.Utils.UtilClass;
import com.ziro.todo_sample.models.ToDo;

public class ADD_TO_DO_Fragment extends Fragment implements View.OnClickListener {
    EditText etTitle, etDesc;
    Button btnAddToDo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_to_do, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        etTitle = view.findViewById(R.id.et_title);
        etDesc = view.findViewById(R.id.et_desc);
        btnAddToDo = view.findViewById(R.id.btn_add_to_do);
        btnAddToDo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_to_do:
                if (etTitle.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please Enter To Do Title", Toast.LENGTH_SHORT).show();
                }else if (etDesc.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please Enter To Do Description", Toast.LENGTH_SHORT).show();
                }else {
                    insertNewToDo(etTitle.getText().toString(),etDesc.getText().toString(),new UtilClass().getDateTime());
                }
                break;
        }
    }

    private void insertNewToDo(String title, String desc, String formattedDate) {
        ToDo toDo=new ToDo();
        toDo.setTitle(title);
        toDo.setDesc(desc);
        toDo.setDate(formattedDate);
        DataBaseClient.getInstance(getActivity()).getAppDatabase()
                .toDoDao().insertNewToDo(toDo);
        getActivity().onBackPressed();

    }
}
