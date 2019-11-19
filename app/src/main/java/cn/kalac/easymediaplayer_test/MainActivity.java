package cn.kalac.easymediaplayer_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.kalac.easymediaplayer.EasyMediaListener;
import cn.kalac.easymediaplayer.EasyMediaPlayer;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String url = "http://m10.music.126.net/20191119230535/d924aea7765136c899283a31fd25c5ab/ymusic/d60e/d53a/a031/1578f4093912b3c1f41a0bfd6c10115d.mp3";
        int res = R.raw.great;
        List list = new ArrayList();
        list.add(url);
        list.add(res);

        EasyMediaPlayer.with(this).listener(new EasyMediaListener() {
            @Override
            public void onComplete() {
                Log.i("---", "onComplete: ");
            }

            @Override
            public void onError(String errorMessage) {
                super.onError(errorMessage);
                Log.e("---", "onError: " + errorMessage);
            }
        }).load(url).start();

    }
}
