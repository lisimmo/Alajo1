package com.akinola.alajo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.akinola.alajo.API.Alajo;
import com.google.gson.Gson;

import java.util.HashMap;

public class ActivitySplash extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 5000;

    private Context context;
    private ImageView slideShowImage1;

    private int[] IMAGE_IDS_ODD = {
            R.drawable.back1, R.drawable.back3, R.drawable.back4};

    private SharedPreferences prefs;
    private Gson gson;
    private HashMap<String,String> post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        context = this;

        slideShowImage1 = (ImageView) findViewById(R.id.imageView1);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        post = new HashMap<>();
        gson = new Gson();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                //if (isOnline()) {
                if (true) { //override

                    Alajo alajo = new Alajo();


                    String auth = prefs.getString("AUTHORIZATION","");
                    String email = prefs.getString("EMAIL","");

                    //Log.e("Solodem", "email: " + email);
                    /*if(TextUtils.isEmpty(email)){
                        Intent i = new Intent(ActivitySplash.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                        return;
                    }*/

                    post.put("MARKETER_EMAIL",email);
                    if (!TextUtils.isEmpty(auth)){
                        //Log.e("Solodem", "auth: " + auth);
                        AttemptLogin attemptLogin = new AttemptLogin(auth,post);
                        attemptLogin.execute((Void) null);
                    }

                } else {
                    ShowAlert("You are not connected to the Internet!!", "Error");
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);

        animate(slideShowImage1, IMAGE_IDS_ODD, 0, true);

    }

    private class AttemptLogin extends AsyncTask<Void, String, Boolean>{
        String auth;
        //TheAPI theAPI;
        private String response, body="Invalid connection!";
        private HashMap<String,String> post;

        public AttemptLogin(String auth,HashMap<String,String> post) {
            this.auth = auth;
            //theAPI = new TheAPI(context);
            this.post = post;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

    private void animate(final ImageView imageView, final int images[], final int imageIndex, final boolean forever) {

        //imageView <-- The View which displays the images
        //images[] <-- Holds R references to the images to display
        //imageIndex <-- index of the first image to show in images[]
        //forever <-- If equals true then after the last image it starts all over again with the first image resulting in an infinite loop. You have been warned.

        int fadeInDuration = 500; // Configure time values here
        int timeBetween = 3000;
        int fadeOutDuration = 1000;

        imageView.setVisibility(View.INVISIBLE);    //Visible or invisible by default - this will apply when the animation ends
        imageView.setImageResource(images[imageIndex]);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); // add this
        fadeIn.setDuration(fadeInDuration);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
        fadeOut.setStartOffset(fadeInDuration + timeBetween);
        fadeOut.setDuration(fadeOutDuration);

        AnimationSet animation = new AnimationSet(false); // change to false
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);
        animation.setRepeatCount(1);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                if (images.length - 1 > imageIndex) {
                    animate(imageView, images, imageIndex + 1, forever); //Calls itself until it gets to the end of the array
                } else {
                    if (forever) {
                        animate(imageView, images, 0, forever);  //Calls itself to start the animation all over again in a loop if forever = true
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }
        });
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void ShowAlert(String message,String title){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //GetCategories getCategories = new GetCategories();
                //getCategories.execute((Void)null);
            }
        }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        }).setCancelable(false);
        alert.show();
    }

}
