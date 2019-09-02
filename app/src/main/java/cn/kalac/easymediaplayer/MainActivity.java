package cn.kalac.easymediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String url = "http://m10.music.126.net/20190818192731/f40a52804328c08c64b1485c7233384c/ymusic/f3fb/235e/a216/2f95cb16b58562445a95eadda702952d.mp3";

        EasyMediaPlayer.with(this).listener(new EasyMediaListener() {
            @Override
            public void onComplete() {

            }
        }).load(url).start();


    }
}
