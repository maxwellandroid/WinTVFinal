package com.maxwell.win_news;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;


import tyrantgit.explosionfield.ExplosionField;

public class Splash extends AppCompatActivity {
    private ExplosionField mExplosionField;
    ConstraintLayout expo;
    VideoView videoview;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        videoview = (VideoView) findViewById(R.id.videoview);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.promo);
        videoview.setVideoURI(uri);
        videoview.start();
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                  Intent i=new Intent(Splash.this,MainActivity_new.class);
                  finish();
                  startActivity(i);
            }
        });

}

    @Override
    public void onStart() {
        super.onStart();

    }




    }
