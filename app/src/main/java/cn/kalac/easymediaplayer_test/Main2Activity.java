package cn.kalac.easymediaplayer_test;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import cn.kalac.easymediaplayer.EasyMediaListener;
import cn.kalac.easymediaplayer.EasyMediaPlayer;
import cn.kalac.easymediaplayer.handle.VolumeGradientHandle;


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
                EasyMediaPlayer.with(mContext).listener(new EasyMediaListener() {
                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: 1");
                    }

                    @Override
                    public void onError(String errorMessage) {
                        super.onError(errorMessage);
                        Log.e(TAG, "onError1: " + errorMessage );
                    }
                }).load(R.raw.great).outputInterrupterError().start();

            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyMediaPlayer.with(mContext).handle(new VolumeGradientHandle()).listener(new EasyMediaListener() {
                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: 2");
                    }

                    @Override
                    public void onError(String errorMessage) {
                        super.onError(errorMessage);
                        Log.e(TAG, "onError2: " + errorMessage );
                    }
                }).load(R.raw.record_true).start();
            }
        });



    }
}
