package com.ziro.todo_sample.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.ziro.todo_sample.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    LoginButton loginButton;
    TextView tvLoginName, tvEmail;
    CircleImageView ivLogin;
    Button btnSkip;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        initViews();
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null) {
                tvEmail.setText("");
                tvLoginName.setText("");
                ivLogin.setImageResource(0);
                Toast.makeText(LoginActivity.this, "User Logged Out", Toast.LENGTH_SHORT).show();
            } else {
                loadUserProfile(currentAccessToken);
            }
        }
    };

    private void loadUserProfile(AccessToken accessToken) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String firstName = object.getString("first_name");
                    String lastName = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");
                    String imageUrl = "https://graph.facebook.com/" + id + "/picture?type=normal";
                    tvLoginName.setText(firstName + " " + lastName);
                    tvEmail.setText(email);
                    Glide.with(LoginActivity.this)
                            .load(imageUrl).centerCrop().into(ivLogin);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(LoginActivity.this,TO_DO_MainActivity.class);
                            startActivity(intent);
                        }
                    },2000);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    private void initViews() {
        loginButton = findViewById(R.id.btn_login);
        tvLoginName = findViewById(R.id.tv_login_name);
        tvEmail = findViewById(R.id.tv_login_email);
        ivLogin = findViewById(R.id.profile_image);
        btnSkip = findViewById(R.id.btn_skip);

        btnSkip.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_skip:
                Intent intent = new Intent(this, TO_DO_MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
