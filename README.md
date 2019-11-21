# EasyMediaPlayer
A MediaPlayer Use On  Android

一个更易使用音乐播放器

![release](https://img.shields.io/github/v/release/kalac2232/EasyMediaPlayer)

![license](https://img.shields.io/github/license/kalac2232/EasyMediaPlayer)

### How to use

1. 依赖
    ```
    repositories {
        jcenter()

    }
    
    
    dependencies {
        implementation 'cn.kalac:EasyMediaPlayer:0.0.2'
    }
    
    ```
2. 播放url音频
    ```
    final String url = "http://www.kalac.cn:8080/music.mp3";

    EasyMediaPlayer.with(this).listener(new EasyMediaListener() {
        @Override
        public void onComplete() {
            Log.i("---", "onComplete:");
        }

        @Override
        public void onError(String errorMessage) {
            super.onError(errorMessage);
            Log.e("---", "onError: " + errorMessage);
        }
    }).load(url).start();
    ```
3. 播放raw下音频

    ```
    EasyMediaPlayer.with(mContext).listener(new EasyMediaListener() {
        @Override
        public void onComplete() {
            Log.i("---", "onComplete:");
        }

        @Override
        public void onError(String errorMessage) {
            super.onError(errorMessage);
            Log.e("---", "onError: " + errorMessage);
        }
    }).load(R.raw.music).start();
    ```
### Todo
1. 可灵活配置的选项
2. 按序播放列表中音频
3. 音频焦点检测