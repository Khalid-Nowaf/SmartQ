package com.smartq.xor_gate.smartq;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Khalid on 2017-03-20.
 * Singleton Object AuthSate
 */

public class AuthState {
    private static final String TAG = "AuthState";
    private Context c;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private static AuthState  authState;
    private FirebaseAuth.AuthStateListener listener;


    private AuthState(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
    }

    public static AuthState getAuthState(){

        if(authState == null)
           return  authState = new AuthState();
        else
            return authState;

    }

    public void onCreate(final Context c){
        this.c = c;
        if(this.authState != null)
            this.authState = new AuthState();


        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();


                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());


                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    c.startActivity(new Intent(c, LoginActivity.class));
                }
            }
        };  // [END auth_state_listener]

    }

    public void onStart(){
        if(listener != null){
            mFirebaseAuth.removeAuthStateListener(listener);
        }
        mFirebaseAuth.addAuthStateListener(listener);
    }

    public void OnStop(){
        if(listener != null){
            mFirebaseAuth.removeAuthStateListener(listener);
        }
    }


    public void signOut() {
        mFirebaseAuth.signOut();
    }
}
