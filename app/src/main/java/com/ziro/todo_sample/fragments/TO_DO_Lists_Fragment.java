package com.ziro.todo_sample.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ziro.todo_sample.DataBaseClient;
import com.ziro.todo_sample.R;
import com.ziro.todo_sample.Utils.FragmentCall;
import com.ziro.todo_sample.adapters.ToDoAdapter;
import com.ziro.todo_sample.models.ToDo;

import java.util.ArrayList;
import java.util.List;

public class TO_DO_Lists_Fragment extends Fragment implements View.OnClickListener {

    RecyclerView recyclerToDoLists;
    ToDoAdapter toDoAdapter;
    List<ToDo> toDoArrayList;
    ImageView ivAddToDo,ivDelete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_lists, container, false);
        initViews(view);
        toDoArrayList = DataBaseClient.getInstance(getActivity()).getAppDatabase().toDoDao().getAllTodo();
        setRecyclerAdapter();
        return view;
    }

    private void setRecyclerAdapter() {
        recyclerToDoLists.setLayoutManager(new LinearLayoutManager(getActivity()));
        toDoAdapter = new ToDoAdapter(toDoArrayList, getActivity());
        recyclerToDoLists.setAdapter(toDoAdapter);
    }

    private void initViews(View view) {
        recyclerToDoLists = view.findViewById(R.id.rv_to_do);
        ivAddToDo = view.findViewById(R.id.iv_add_to_do);
        ivDelete = view.findViewById(R.id.iv_delete_to_do);

        ivAddToDo.setOnClickListener(this);
        ivDelete.setOnClickListener(this);
        toDoArrayList = new ArrayList<>();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_to_do:
                new FragmentCall().replaceFragment(R.id.cn_container, new ADD_TO_DO_Fragment(),
                        getActivity().getSupportFragmentManager()); //replacing to do fragment
                break;
            case R.id.iv_delete_to_do:
                DataBaseClient.getInstance(getActivity()).getAppDatabase().toDoDao().getCheckedToDo(true);
                toDoArrayList = DataBaseClient.getInstance(getActivity()).getAppDatabase().toDoDao().getAllTodo();
                setRecyclerAdapter();
                break;
        }
    }
}
