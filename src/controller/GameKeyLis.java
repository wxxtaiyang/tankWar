package controller;

import commen.ImageData;
import commen.MusicData;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeyLis extends KeyAdapter {

    public Controller con;

    public GameKeyLis(Controller con) {
        this.con = con;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_P:
                if(con.welMenuActLis.allMusic) {
                    con.welMenuActLis.allMusic = false;
                    MusicData.startClip.stop();
                    MusicData.startClip.setFramePosition(0);
                    MusicData.blastClip.stop();
                    MusicData.blastClip.setFramePosition(0);
                    MusicData.fireClip.stop();
                    MusicData.fireClip.setFramePosition(0);
                    MusicData.moveClip.stop();
                    MusicData.moveClip.setFramePosition(0);
                    MusicData.addClip.stop();
                    MusicData.addClip.setFramePosition(0);
                    MusicData.hitClip.stop();
                    MusicData.hitClip.setFramePosition(0);
                    con.welJFrame.allMusic.setText("开启全部声音\tX");
                }
                else {
                    con.welMenuActLis.allMusic = true;
                    con.welJFrame.allMusic.setText("开启全部声音\t√");
                }
                break;
            case KeyEvent.VK_R:
                if(con.index < 8) {
                    if (con.frameTimer.timer.isRunning()) {
                        con.frameTimer.timer.stop();
                        con.gameJFrame.suspend.setText("继续");
                    } else {
                        con.gameJFrame.startGame.setEnabled(false);
                        con.frameTimer.timer.start();
                        con.gameJFrame.suspend.setText("暂停");
                    }
                }
                break;
            // P1操作
            case KeyEvent.VK_W:
                con.tankP1.image = ImageData.tank1U;
                con.tankP1.moveU = true;
                con.tankP1.direction = "N";
                break;
            case KeyEvent.VK_S:
                con.tankP1.image = ImageData.tank1D;
                con.tankP1.moveD = true;
                con.tankP1.direction = "S";
                break;
            case KeyEvent.VK_A:
                con.tankP1.image = ImageData.tank1L;
                con.tankP1.moveL = true;
                con.tankP1.direction = "W";
                break;
            case KeyEvent.VK_D:
                con.tankP1.image = ImageData.tank1R;
                con.tankP1.moveR = true;
                con.tankP1.direction = "E";
                break;
            case KeyEvent.VK_J:
                if(con.tankP1.fireTime >= 50){
                    // 将介质重绕到剪辑的开头。
                    MusicData.fireClip.setFramePosition(0);
                    MusicData.fireClip.stop();

                    con.tankP1.fire();
                    con.tankP1.fireTime = 0;
                }
                break;
            // P2操作
            case KeyEvent.VK_UP:
                if(con.tankP2 != null) {
                    con.tankP2.image = ImageData.tank2U;
                    con.tankP2.moveU = true;
                    con.tankP2.direction = "N";
                }
                break;
            case KeyEvent.VK_DOWN:
                if(con.tankP2 != null) {
                    con.tankP2.image = ImageData.tank2D;
                    con.tankP2.moveD = true;
                    con.tankP2.direction = "S";
                }
                break;
            case KeyEvent.VK_LEFT:
                if(con.tankP2 != null) {
                    con.tankP2.image = ImageData.tank2L;
                    con.tankP2.moveL = true;
                    con.tankP2.direction = "W";
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(con.tankP2 != null) {
                    con.tankP2.image = ImageData.tank2R;
                    con.tankP2.moveR = true;
                    con.tankP2.direction = "E";
                }
                break;
            case KeyEvent.VK_ENTER:
                if(con.tankP2 != null && con.tankP2.fireTime >= 50){
                    // 将介质重绕到剪辑的开头。
                    MusicData.fireClip.setFramePosition(0);
                    MusicData.fireClip.stop();

                    con.tankP2.fire();
                    con.tankP2.fireTime = 0;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        switch (e.getKeyCode()){
            // P1操作
            case KeyEvent.VK_W:
                con.tankP1.moveU = false;
                break;
            case KeyEvent.VK_S:
                con.tankP1.moveD = false;
                break;
            case KeyEvent.VK_A:
                con.tankP1.moveL = false;
                break;
            case KeyEvent.VK_D:
                con.tankP1.moveR = false;
                break;
            // P2操作
            case KeyEvent.VK_UP:
                if(con.tankP2 != null) {
                    con.tankP2.moveU = false;
                }
                break;
            case KeyEvent.VK_DOWN:
                if(con.tankP2 != null) {
                    con.tankP2.moveD = false;
                }
                break;
            case KeyEvent.VK_LEFT:
                if(con.tankP2 != null) {
                    con.tankP2.moveL = false;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(con.tankP2 != null) {
                    con.tankP2.moveR = false;
                }
                break;
        }
    }

}
