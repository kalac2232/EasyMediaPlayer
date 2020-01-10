package cn.kalac.easymediaplayer_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.kalac.easymediaplayer.EasyMediaListener;
import cn.kalac.easymediaplayer.EasyMediaPlayer;
import cn.kalac.easymediaplayer.MediaManager;
import cn.kalac.easymediaplayer.MediaOperator;
import cn.kalac.easymediaplayer.handle.VolumeGradientHandle;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);


        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyMediaPlayer.with(mContext).listener(new EasyMediaListener() {
                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: 1");
                    }
                }).load(R.raw.great).start();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyMediaPlayer.with(mContext).handle(new VolumeGradientHandle()).load(R.raw.record_true).start();
            }
        });



    }
}
