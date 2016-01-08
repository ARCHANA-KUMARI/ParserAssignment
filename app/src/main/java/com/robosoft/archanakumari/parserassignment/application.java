package com.robosoft.archanakumari.parserassignment;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by archanakumari on 24/12/15.
 */
public class application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        printHashKey();
    }
    public void printHashKey(){


        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.robosoft.archanakumari.parserassignment",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                //Toast.makeText(getBaseContext(),)
                Log.v("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                Log.e("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
