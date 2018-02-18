package com.xcript.workapp.login.wizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xcript.workapp.R;
import com.xcript.workapp.login.wizard.person.WizardPerson1;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WizardStablishmentPerson extends AppCompatActivity {

    @BindView(R.id.buttonImStablishment)
    Button buttonImStablishment;

    @BindView(R.id.buttonImPerson)
    Button buttonImPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_stablishment_person);

        ButterKnife.bind(this);

        buttonImStablishment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonImPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WizardStablishmentPerson.this, WizardPerson1.class);
                startActivity(intent);
            }
        });
    }
}
