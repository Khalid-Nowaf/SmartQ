package com.smartq.xor_gate.smartq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private AuthState authState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        authState = AuthState.getAuthState();
        authState.onCreate(this);

    }

    @Override
    protected void onStart(){
        super.onStart();
        authState.onStart();
    }

    @Override
    protected void onStop(){
        super.onStop();
        authState.OnStop();
    }

    public void signOut(View v){
        authState.signOut();
    }
}
