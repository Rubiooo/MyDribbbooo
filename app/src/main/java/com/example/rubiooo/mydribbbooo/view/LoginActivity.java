package com.example.rubiooo.mydribbbooo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.rubioo.mydribbbooo.R;
import com.example.rubiooo.mydribbbooo.dribbble.Dribbble;
import com.example.rubiooo.mydribbbooo.dribbble.auth.Auth;
import com.example.rubiooo.mydribbbooo.dribbble.auth.AuthActivity;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jimmy on 2018/2/18.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.activity_login_btn) TextView loginBtn;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //load accessToken from shared preference
        Dribbble.init(this);

        if (!Dribbble.isLoggedIn()) {
            loginBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Auth.openAuthActivity(LoginActivity.this);
                }
            });
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Auth.REQ_CODE && resultCode == RESULT_OK) {
            final String authCode = data.getStringExtra(AuthActivity.KEY_CODE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //this is an internet call, and it's time consuming
                        // that's why we do it in a non-UI thread
                        String token = Auth.fetchAccessToken(authCode);

                        //store accessToken in SharedPreferences
                        Dribbble.login(LoginActivity.this, token);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (IOException | JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
