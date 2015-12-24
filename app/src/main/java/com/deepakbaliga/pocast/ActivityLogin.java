package com.deepakbaliga.pocast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.deepakbaliga.pocast.intro.ActivityIntro;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import me.alexrs.prefs.lib.Prefs;

import static com.deepakbaliga.pocast.R.id.textview_app_subtitle;

public class ActivityLogin extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private TextView appTitle;
    private TextView appSubTitle;

    private Button signInButton;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions googleSignInOptions;

    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "LoginActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

         googleSignInOptions =  new GoogleSignInOptions.Builder().requestEmail().requestProfile().requestId().build();

         googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();






    }

    private void init(){

        appTitle = (TextView) findViewById(R.id.textview_app_title);
        appSubTitle = (TextView) findViewById(R.id.textview_app_subtitle);
        signInButton = (Button) findViewById(R.id.sign_in_button);

        appTitle.setTypeface(PocastApp.robotoBlack);
        appSubTitle.setTypeface(PocastApp.robotoLight);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect(v);
            }
        });



    }

    public void connect(View view){

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.d(TAG, "Logged In");

            Prefs.with(this).save("loggedin", true);
            Prefs.with(this).save("name", acct.getDisplayName());
            Prefs.with(this).save("email", acct.getEmail());
            Prefs.with(this).save("picture", acct.getPhotoUrl().toString());

            startActivity(new Intent(this, ActivityIntro.class));
            finish();


        } else {
            // Signed out, show unauthenticated UI.
            Prefs.with(this).save("loggedin", false);

        }
    }
}
