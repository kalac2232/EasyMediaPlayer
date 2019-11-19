package cn.kalac.easymediaplayer_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import cn.kalac.easymediaplayer.EasyMediaPlayer;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String url = "";
        int res = R.raw.great;
        List list = new ArrayList();
        list.add(url);
        list.add(res);

        EasyMediaPlayer.with(this).load(list).start();

    }
}
