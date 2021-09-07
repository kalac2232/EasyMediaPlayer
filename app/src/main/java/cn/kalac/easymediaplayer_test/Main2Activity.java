package cn.kalac.easymediaplayer_test;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import cn.kalac.easymediaplayer.listener.EasyMediaListener;
import cn.kalac.easymediaplayer.EasyMediaPlayer;
import cn.kalac.easymediaplayer.handle.LoopMediaHandle;
import cn.kalac.easymediaplayer.handle.VolumeGradientHandle;
import cn.kalac.easymediaplayer.listener.LoopMediaListener;


public class Main2Activity extends AppCompatActivity {
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
                EasyMediaPlayer.with(mContext).load(R.raw.great).start();

            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyMediaPlayer.with(mContext).listener(new EasyMediaListener() {
                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: 2");
                    }

                    @Override
                    public void onError(String errorMessage) {
                        super.onError(errorMessage);
                        Log.e(TAG, "onError2: " + errorMessage );
                    }
                }).loadAssets("test_music48k.mp3").handle(new VolumeGradientHandle()).start();
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyMediaPlayer.with(mContext).listener(new LoopMediaListener() {
                    @Override
                    public void onComplete(int remainderCount) {
                        super.onComplete(remainderCount);
                        Log.i(TAG, "onComplete: " + remainderCount);
                    }

                    @Override
                    public void onLoopComplete() {
                        super.onLoopComplete();
                        Log.i(TAG, "onLoopComplete: ");
                    }

                    @Override
                    public void onError(String errorMessage) {
                        super.onError(errorMessage);
                        Log.e(TAG, "onError1: " + errorMessage );
                    }
                }).load(R.raw.great).handle(new LoopMediaHandle(3)).start();

            }
        });

    }
}
