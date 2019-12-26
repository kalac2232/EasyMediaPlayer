package cn.kalac.easymediaplayer_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.kalac.easymediaplayer.EasyMediaListener;
import cn.kalac.easymediaplayer.EasyMediaPlayer;
import cn.kalac.easymediaplayer.MediaOperator;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);

        final int res = R.raw.great;
        final String url = "http://www.kalac.cn:8080/music.mp3";

        MediaOperator mediaOperator = EasyMediaPlayer.with(this).load(url);
        mediaOperator.start();

        mediaOperator.pause();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EasyMediaPlayer.with(mContext).listener(new EasyMediaListener() {
                    @Override
                    public void onComplete() {
                        Log.i("---", "onComplete:2");
                    }

                    @Override
                    public void onError(String errorMessage) {
                        super.onError(errorMessage);
                        Log.e("---", "onError2: " + errorMessage);
                    }
                }).load(res).start();
            }
        },6000);





    }
}
