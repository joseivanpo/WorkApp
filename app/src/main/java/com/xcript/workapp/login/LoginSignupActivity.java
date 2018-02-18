package com.xcript.workapp.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xcript.workapp.R;
import com.xcript.workapp.login.wizard.WizardStablishmentPerson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginSignupActivity extends AppCompatActivity {

    @BindView(R.id.buttonLogin)
    Button buttonLogin;

    @BindView(R.id.buttonSignup)
    Button buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        ButterKnife.bind(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSignupActivity.this, UserLoginSignupActivity.class);
                startActivity(intent);
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSignupActivity.this, WizardStablishmentPerson.class);
                startActivity(intent);
            }
        });
    }
}
