package com.example.mirimswshow2;
//인증키받는법:터미널 열어서 keytool -list -v -alias androiddebugkey -keystore %USERPROFILE%\.android\debug.keystore -alias androiddebugkey -storepass android -keypass android
//77:B9:15:93:99:F3:B8:DB:BF:46:02:FB:5F:E0:10:AB:2B:4F:65:51
//7E:5D:9F:39:96:C1:46:39:01:67:BA:C2:11:F5:92:18:64:26:28:B5

//00:B8:69:5E:F8:6E:4F:E7:39:48:60:0A:18:D4:C9:A6:A8:A0:6C:9B
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener{


    private SignInButton mSigninBtn;
    //private android.content.Context context;
    private GoogleApiClient mGoogleApiClient;
    //private Object mFirebaseAuth = FirebaseAuth.getInstance();
    //private KeyFactory FirebaseAuth;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        getWindow().setStatusBarColor(Color.parseColor("BLACK"));
        mSigninBtn = (SignInButton) findViewById(R.id.sign_in_btn);
        mFirebaseAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Context context;
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, (GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();


        mSigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //구글로그인 클릭했을때
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(intent, 100);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100/*100분*/) {
//            //토스트를 통해서 실행됬는지 확인
//            Toast.makeText(AuthActivity.this,"onActivityResult",Toast.LENGTH_LONG).show();
            GoogleSignInResult result
                    = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GoogleSignInAccount account = result.getSignInAccount();
            //성공과 실패 여부 처리
            if (result.isSuccess()) {
                firebaseAuthWithGoogle(account);
                //Toast.makeText(AuthActivity.this,"ActivityResult",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "인증에 실패하였습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "인증에 실패하였습니다. (네트워크 오류)", Toast.LENGTH_LONG).show();

    }

    public void onFailure(@NonNull Exception exception) {
//                        int errorCode = ((StorageException) exception).getErrorCode();
        String errorMessage = exception.getMessage();
        // test the errorCode and errorMessage, and handle accordingly
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential
                = GoogleAuthProvider.getCredential(account.getIdToken(), null);

        Task<AuthResult> authResultTask
                = mFirebaseAuth.signInWithCredential(credential);

        authResultTask.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                //로그인 성공확인
                FirebaseUser firebaseUser = authResult.getUser();
                Toast.makeText(AuthActivity.this,firebaseUser.getDisplayName()+"님 환영합니다",Toast.LENGTH_LONG).show();
                //Toast.makeText(AuthActivity.this, "onActivity", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(AuthActivity.this, MainActivity.class));
            }
        });


    }

}

