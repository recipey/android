package com.recipey.nhnic.recipey.tabs.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.recipey.nhnic.recipey.R;
import com.recipey.nhnic.recipey.app.Application;

import java.util.Arrays;

/**
 * Created by nhnic on 6/17/2018.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static final String PERMISSIONS = "email";

    CallbackManager callbackManager;
    LoginManager loginManager;
    AccessTokenTracker accessTokenTracker;

    ImageView loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        assignViews(rootView);
        assignVariables(savedInstanceState);
        assignHandlers();

        return rootView;
    }

    private void assignViews(View rootView) {
        loginButton = rootView.findViewById(R.id.login_button);
    }

    private void assignVariables(Bundle savedInstanceState) {
        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };

        LoginManager.getInstance().registerCallback(callbackManager,
            new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    // App code
                }

                @Override
                public void onCancel() {
                    // App code
                }

                @Override
                public void onError(FacebookException exception) {
                    // App code
                }
        });
    }

    private void assignHandlers() {
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (AccessToken.getCurrentAccessToken() != null){
            loginManager.logOut();
        } else {
            accessTokenTracker.startTracking();
            loginManager.logInWithReadPermissions(this, Arrays.asList(PERMISSIONS));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
