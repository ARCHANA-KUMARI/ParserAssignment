package com.robosoft.archanakumari.parserassignment.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.ShareApi;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.robosoft.archanakumari.parserassignment.R;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFacebookFragment extends Fragment {

    LoginButton loginButton;
    TextView mText;
    AccessToken accessToken;
    Profile profile;
    ProfileTracker profileTracker;
    private CallbackManager mCallbackManager;
    AccessTokenTracker accessTokenTracker;
    private LoginManager loginManager;
    private ShareButton mShareButton;
  //  List<string> permissionNeeds = Arrays.asList("publish_actions");

    public LoginFacebookFragment() {
        // Required empty public constructor
    }


    private FacebookCallback<LoginResult> mFacebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            accessToken = loginResult.getAccessToken();
            profile = Profile.getCurrentProfile();
            displayWelcomeMessage(profile);
            sharePhotoToFacebook();


        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                displayWelcomeMessage(currentProfile);
            }
        };


        //this loginManager helps you eliminate adding a LoginButton to your UI

        accessTokenTracker.startTracking();
        profileTracker.startTracking();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_facebook, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mText = (TextView) view.findViewById(R.id.text);
         loginButton = (LoginButton) view.findViewById(R.id.login_button);
        mShareButton = (ShareButton) view.findViewById(R.id.share);
        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(mCallbackManager, mFacebookCallback);
        ShareLinkContent content = new ShareLinkContent.Builder().build();
        mShareButton.setShareContent(content);
        mShareButton.setFragment(this);
        loginManager = LoginManager.getInstance();
        List<String> permissionNeeds = Arrays.asList("publish_actions");
        loginManager.logInWithPublishPermissions(this, permissionNeeds);
        loginManager.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getContext(), "hi i am in onSuccess method", Toast.LENGTH_LONG).show();
                Log.i("HI", "IN onSuccess");
                sharePhotoToFacebook();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getContext(), "hi i am in onCancel method", Toast.LENGTH_LONG).show();
                System.out.println("onCancel");
                Log.i("HI", "IN CANCEL");
            }

            @Override
            public void onError(FacebookException exception) {

                Log.i("HI", "IN CANCEL");
                Toast.makeText(getContext(), "hi i am in onError method", Toast.LENGTH_LONG).show();
                System.out.println("onError");
            }
        });




    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayWelcomeMessage(profile);
        AppEventsLogger.activateApp(getContext());
    }

    public void displayWelcomeMessage(Profile profile){
        if(profile!=null)
        {
            mText.setText("Welcome to"+profile.getName());
            Log.i("Hi i am in displey me", "hhddhs");
            Toast.makeText(getContext(), "Hi i am in onSuccess", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }
  private void sharePhotoToFacebook(){
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.robo);
        SharePhoto photo;
      photo = new SharePhoto.Builder()
              .setBitmap(image)
              .setCaption("Welcome to share data with facebook")
              .build();

      SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();

        ShareApi.share(content, null);

    }



}
