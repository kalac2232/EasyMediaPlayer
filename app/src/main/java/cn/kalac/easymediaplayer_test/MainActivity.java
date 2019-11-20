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


public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);

        final String url = "http://www.kalac.cn:8080/music.mp3";
        final int res = R.raw.great;

        EasyMediaPlayer.with(this).listener(new EasyMediaListener() {
            @Override
            public void onComplete() {
                Log.i("---", "onComplete:1 ");
            }

            @Override
            public void onError(String errorMessage) {
                super.onError(errorMessage);
                Log.e("---", "onError1: " + errorMessage);
            }
        }).load(url).start();

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
