package commen;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicData {
    //创建相当于音乐播放器的对象
    public static Clip startClip;// 开始
    public static Clip moveClip;// 移动
    public static Clip addClip;// 道具音效
    public static Clip fireClip;// 开火
    public static Clip hitClip;// 打中
    public static Clip blastClip;// 爆炸
    public static boolean isOpen;
    //将传入的文件转成可播放的文件
//    private static AudioInputStream audioInput;

    static {
        isOpen = true;
        try{
            // 游戏开启音效
            startClip = AudioSystem.getClip();
            AudioInputStream audioInput1 = AudioSystem.getAudioInputStream(new File("resource/music/start.wav"));
            startClip.open(audioInput1);
            // 移动音效
            moveClip = AudioSystem.getClip();
            AudioInputStream audioInput2 = AudioSystem.getAudioInputStream(new File("resource/music/move.wav"));
            moveClip.open(audioInput2);
            // 加血音效
            addClip = AudioSystem.getClip();
            AudioInputStream audioInput3 = AudioSystem.getAudioInputStream(new File("resource/music/add.wav"));
            addClip.open(audioInput3);
            // 开火音效
            fireClip = AudioSystem.getClip();
            AudioInputStream audioInput4 = AudioSystem.getAudioInputStream(new File("resource/music/fire.wav"));
            fireClip.open(audioInput4);
            // 打中音效
            hitClip = AudioSystem.getClip();
            AudioInputStream audioInput5 = AudioSystem.getAudioInputStream(new File("resource/music/hit.wav"));
            hitClip.open(audioInput5);
            // 爆炸音效
            blastClip = AudioSystem.getClip();
            AudioInputStream audioInput6 = AudioSystem.getAudioInputStream(new File("resource/music/blast.wav"));
            blastClip.open(audioInput6);

        }catch (Exception e){

        }

    }
}
