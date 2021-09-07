# EasyMediaPlayer
A MediaPlayer Use On  Android

一个更易使用音乐播放器
适用于非音乐播放器应用，整个app中只需要在特定情形下播放音效的场景

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
    implementation 'cn.kalac:EasyMediaPlayer:$lastRelease'
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
3. 播放`Assets`下音频

    ```
    EasyMediaPlayer.with(mContext)..loadAssets("test_music48k.mp3").start();
    ```
4. 其他资源同理
### 暂停等其他操作
在调用`load`方法后，会返回一个`MediaManager`对象，使用该对象即可进行对当前`res`音频的操作管理
1. 暂停
    ```
    String url = "http://www.kalac.cn:8080/music.mp3";

    MediaManager mediaManager = EasyMediaPlayer.with(this).load(url);
    mediaManager.start();
    //... 
    mediaManager.pause();
    ```
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

### 设置处理器
现内置了一个播放时声音从小变大，暂停（停止）时音量有大变小的处理器

```
EasyMediaPlayer.with(mContext).handle(new VolumeGradientHandle()).load(R.raw.voice).start();
```

### 资源释放
**不需要关系资源释放的问题，EasyMediaPlayer会自动进行释放**
## Todo
- [x] 添加更多的对音频的操作
- [ ] 可灵活配置的选项
- [ ] 按序播放列表中音频
- [ ] 音频焦点检测