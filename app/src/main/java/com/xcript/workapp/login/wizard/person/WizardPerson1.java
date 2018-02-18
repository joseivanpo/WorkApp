package com.xcript.workapp.login.wizard.person;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.xcript.workapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WizardPerson1 extends AppCompatActivity {

    @BindView(R.id.imageButtonPhoto)
    ImageButton imageButtonPhoto;

    @BindView(R.id.editTextFirstName)
    EditText editTextFirstName;

    @BindView(R.id.editTextLastNames)
    EditText editTextLastNames;

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;

    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    @BindView(R.id.buttonFillWithFacebook)
    Button buttonFillWithFacebook;

    private LoginManager facebookLoginManager;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_person1);

        ButterKnife.bind(this);

        facebookLoginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();

        facebookLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        facebookLoginManager.logOut();
                        fillInfo(object);

                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,birthday,gender,first_name,last_name");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        buttonFillWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLogin();
            }
        });
    }

    public void fillInfo(JSONObject object) {

        try {

            String userId = object.getString("id");
            String profilePicture = getFacebookPictureURL(userId);
            String firstName = object.getString("first_name");
            String lastName = object.getString("last_name");
            String email = object.getString("email");
            String birthday = object.getString("birthday");
            String gender = object.getString("gender");

            new ProfilePictureThread().execute(userId);

            editTextFirstName.setText(firstName);
            editTextLastNames.setText(lastName);
            editTextEmail.setText(email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void facebookLogin() {
        facebookLoginManager.logInWithReadPermissions(this, Arrays.asList("email,user_about_me,user_birthday"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getFacebookPictureURL(String userId) {
        return "https://graph.facebook.com/" + userId + "/picture?type=large";
    }

    public class ProfilePictureThread extends AsyncTask<String, Void, Void> {
        private Bitmap bitmap;

        @Override
        protected Void doInBackground(String... strings) {

            URL imageURL = null;
            bitmap = null;

            try {

                imageURL = new URL(getFacebookPictureURL(strings[0]));
                bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            imageButtonPhoto.setImageBitmap(bitmap);
        }
    }
}
