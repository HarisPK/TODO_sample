package com.ziro.todo_sample.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ziro.todo_sample.DataBaseClient;
import com.ziro.todo_sample.R;
import com.ziro.todo_sample.Utils.UtilClass;
import com.ziro.todo_sample.models.ToDo;

public class ToDoUpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etTitle, etDesc;
    Button btnUpdate, btnDelete;
    int id;
    ToDo toDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_update_delete);
        setTitle("Update or Delete");
        initViews();
        id = getIntent().getIntExtra("id", 1);
        toDo = DataBaseClient.getInstance(this).getAppDatabase().toDoDao().getToDo(id);
        etTitle.setText(toDo.getTitle());
        etDesc.setText(toDo.getDesc());
    }

    private void initViews() {
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        etTitle = findViewById(R.id.et_title);
        etDesc = findViewById(R.id.et_desc);

        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update:
                DataBaseClient.getInstance(this).getAppDatabase().toDoDao()
                        .updateTodo(id,etTitle.getText().toString(),etDesc.getText().toString(),new UtilClass().getDateTime());
                Intent toDoMainActivity=new Intent(this,TO_DO_MainActivity.class);
                startActivity(toDoMainActivity);
                finish();
                break;
            case R.id.btn_delete:
                DataBaseClient.getInstance(this).getAppDatabase().toDoDao().deleteToDo(id);
                Intent toDoMainActivityTwo=new Intent(this,TO_DO_MainActivity.class);
                startActivity(toDoMainActivityTwo);
                finish();
                break;
        }
    }
}
