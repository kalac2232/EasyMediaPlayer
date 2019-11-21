# EasyMediaPlayer
A MediaPlayer Use On  Android

一个更易使用音乐播放器

![release](https://img.shields.io/github/v/release/kalac2232/EasyMediaPlayer)
&nbsp;
![Api](https://img.shields.io/badge/API-17%2B-brightgreen.svg)
&nbsp;
![license](https://img.shields.io/github/license/kalac2232/EasyMediaPlayer)

## 项目描述
> 对MediaPlayer进行封装，使开发者更容易使用，避免掉入常见的坑中

### 依赖


```
repositories {
    jcenter()

}


dependencies {
    implementation 'cn.kalac:EasyMediaPlayer:0.0.3'
}

```
### 播放音频
1. 播放url音频
    ```
    final String url = "http://www.kalac.cn:8080/music.mp3";

    EasyMediaPlayer.with(this).load(url).start();
    ```
2. 播放raw下音频

    ```
    EasyMediaPlayer.with(mContext).load(R.raw.music).start();
    ```
3. 其他`Assets`等资源同理
### 添加监听
```
EasyMediaPlayer.with(this).listener(new EasyMediaListener() {
    @Override
    public void onComplete() {
        Log.i("---", "onComplete:");
    }
}).load(url).start();
```
在`EasyMediaListener`中`onComplete()`为抽象方法，为必须实现，并提供其他可选实现方法
```
public void onPrepare() {

}

public void onStart() {

}

public void onError(String errorMessage) {

}
```
### 资源释放
**不需要关系资源释放的问题，EasyMediaPlayer会自动进行释放**
## Todo
1. 可灵活配置的选项
2. 按序播放列表中音频
3. 音频焦点检测