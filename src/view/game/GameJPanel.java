package view.game;

import commen.ImageData;
import commen.MusicData;
import controller.Controller;
import model.Bullet;
import model.EnemyTank;
import model.Item;

import javax.swing.*;
import java.awt.*;

public class GameJPanel extends JPanel {

    public Controller con;
    public GameJPanel(Controller con) {
        // 开始音效
        if(con.welMenuActLis.allMusic && con.welMenuActLis.beginMusic) {
            MusicData.startClip.start();
            MusicData.startClip.setFramePosition(0);
        }

        this.con = con;
//        addKeyListener(con.gKeyLis);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageData.bg_black,0,0,780,560,null);
        g.drawImage(ImageData.symbol,360,520,60,40,null);// 老家 ；390，540
        // 地图中墙体绘制
        for (int i = 0; i < con.map.gameMap[con.index].length; i++) {
            for (int j = 0; j < con.map.gameMap[con.index][i].length; j++) {
                switch (con.map.gameMap[con.index][i][j]){
                    case 1:
                        g.drawImage(ImageData.wall,i*20,j*20,20,20,null);
                        break;
                    case 2:
                        g.drawImage(ImageData.steels,i*20,j*20,20,20,null);
                        break;
                    case 3:
                        g.drawImage(ImageData.grass,i*20,j*20,20,20,null);
                        break;
                    case 4:
                        g.drawImage(ImageData.water,i*20,j*20,20,20,null);
                        break;
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        g.drawImage(ImageData.blast,i*20,j*20,20,20,null);
                }
            }
        }
        // 敌方坦克绘制
        for (EnemyTank enemyTank : con.enemyTanks) {
            for (int i = 0; i < enemyTank.blood; i++) {// 敌方坦克血条绘制
                int j = enemyTank.weight/enemyTank.HP;
                g.drawImage(ImageData.bloodBar,enemyTank.pointX+i*j,enemyTank.pointY-5,j,5,null);
            }
            g.drawImage(enemyTank.image,enemyTank.pointX,enemyTank.pointY,enemyTank.weight,enemyTank.height,null);
        }
        // P1坦克绘制
        if(con.tankP1.life > 0) {
            for (int i = 0; i < con.tankP1.blood; i++) {//血条绘制
                int j = con.tankP1.weight / con.tankP1.HP;
                g.drawImage(ImageData.bloodBar, con.tankP1.pointX + i * j, con.tankP1.pointY - 5, j, 5, null);
            }
            g.drawImage(con.tankP1.image, con.tankP1.pointX, con.tankP1.pointY, con.tankP1.weight, con.tankP1.height, null);
        }
        // P2坦克绘制
        if(con.tankP2 != null && con.tankP2.life > 0) {
            for (int i = 0; i < con.tankP2.blood; i++) {//血条绘制
                int j = con.tankP2.weight / con.tankP2.HP;
                g.drawImage(ImageData.bloodBar, con.tankP2.pointX + i * j, con.tankP2.pointY - 5, j, 5, null);
            }
            g.drawImage(con.tankP2.image, con.tankP2.pointX, con.tankP2.pointY, con.tankP2.weight, con.tankP2.height, null);
        }
        // 子弹绘制
        for (Bullet bullet : con.bullets) {
            g.drawImage(bullet.image,bullet.pointX,bullet.pointY,bullet.weight,bullet.height,null);
        }
        // 道具绘制
        for (Item item : con.itemList) {
            item.changeImage();
            g.drawImage(item.image,item.pointX,item.pointY,40,40,null);
        }
        // 已爆炸的坦克绘制
        for (int i = 0; i < con.blastTank.size(); i++) {
            g.drawImage(ImageData.blast,con.blastTank.get(i).pointX,con.blastTank.get(i).pointY,con.blastTank.get(i).weight,con.blastTank.get(i).height,null);
        }
    }
}
