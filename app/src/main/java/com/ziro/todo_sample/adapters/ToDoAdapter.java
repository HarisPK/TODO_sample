package com.ziro.todo_sample.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ziro.todo_sample.DataBaseClient;
import com.ziro.todo_sample.R;
import com.ziro.todo_sample.activities.ToDoUpdateDeleteActivity;
import com.ziro.todo_sample.models.ToDo;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    List<ToDo> toDoArrayList;
    Context mContext;


    public ToDoAdapter(List<ToDo> toDos, Context context) {
        this.toDoArrayList = toDos;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.adapter_to_do, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        final ToDo toDo = toDoArrayList.get(position);
        myViewHolder.tvTitle.setText(toDo.getTitle());
        myViewHolder.tvDesc.setText(toDo.getDesc());
        myViewHolder.tvDate.setText(toDo.getDate());
        myViewHolder.checkItem.setChecked(toDo.isCheck());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ToDoUpdateDeleteActivity.class);
                intent.putExtra("id", toDo.getId());
                mContext.startActivity(intent);
            }
        });
        myViewHolder.checkItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    toDoArrayList.get(position).setIsCheck(true);
                    DataBaseClient.getInstance(mContext).getAppDatabase().toDoDao()
                            .updateIsChecked(toDo.getId(), true);

                } else {
                    toDoArrayList.get(position).setIsCheck(false);
                    DataBaseClient.getInstance(mContext).getAppDatabase().toDoDao()
                            .updateIsChecked(toDo.getId(), false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return toDoArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDesc,tvDate;
        CheckBox checkItem;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tv_title);
            tvDesc = view.findViewById(R.id.tv_desc);
            tvDate = view.findViewById(R.id.tv_date);
            checkItem = view.findViewById(R.id.check_item);

        }
    }
}
