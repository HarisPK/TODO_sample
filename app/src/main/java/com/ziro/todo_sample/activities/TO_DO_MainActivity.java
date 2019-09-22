package com.ziro.todo_sample.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ziro.todo_sample.GetDataService;
import com.ziro.todo_sample.R;
import com.ziro.todo_sample.RetrofitClientInstance;
import com.ziro.todo_sample.Utils.FragmentCall;
import com.ziro.todo_sample.fragments.TO_DO_Lists_Fragment;
import com.ziro.todo_sample.models.ResponseModelClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TO_DO_MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to__do__main);
        setTitle(getResources().getString(R.string.to_do_lists_title));
        initViews();

        sampleRetrofitCall(); //(Optional) Backend API to connect the application

        new FragmentCall().addFragment(R.id.cn_container, new TO_DO_Lists_Fragment(),
                getSupportFragmentManager()); //adding to do list fragment
    }

    private void sampleRetrofitCall() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseModelClass> call = service.getResponse("Zfg8sSa4SZk3Ypf9ApjR23CisgJplc7G");
        call.enqueue(new Callback<ResponseModelClass>() {
            @Override
            public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                Log.d("Response","success");
            }

            @Override
            public void onFailure(Call<ResponseModelClass> call, Throwable t) {
                Log.d("Response","failure");
            }
        });
    }

    private void initViews() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
