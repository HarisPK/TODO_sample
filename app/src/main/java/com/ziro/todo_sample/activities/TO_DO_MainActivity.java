package com.ziro.todo_sample.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ziro.todo_sample.R;
import com.ziro.todo_sample.Utils.FragmentCall;
import com.ziro.todo_sample.fragments.TO_DO_Lists_Fragment;

public class TO_DO_MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to__do__main);
        setTitle(getResources().getString(R.string.to_do_lists_title));
        initViews();
        new FragmentCall().addFragment(R.id.cn_container, new TO_DO_Lists_Fragment(),
                getSupportFragmentManager()); //adding to do list fragment
    }

    private void initViews() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
