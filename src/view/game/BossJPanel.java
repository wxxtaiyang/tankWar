package view.game;

import commen.DataBoss;
import commen.ImageData;
import controller.Controller;
import model.Bullet;
import model.EnemyTank;
import model.Item;

import javax.swing.*;
import java.awt.*;

public class BossJPanel extends JPanel {
    public Controller con;

    public BossJPanel(Controller con) {
        this.con = con;
        setSize(800,600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageData.bg_black,0,0,800,600,null);
//        for (int i = 0; i < con.tankP1.blood; i++) {
//            int j = con.tankP1.weight / con.tankP1.HP;
//            g.drawImage(ImageData.bloodBar, con.tankP1.pointX + i * j, con.tankP1.pointY - 5, j, 5, null);
//        }
//        g.drawImage(con.tankP1.image,con.tankP1.pointX,con.tankP1.pointY,con.tankP1.weight,con.tankP1.height,null);
        for (int i = 0; i < con.boss.blood; i++) {
            int j = con.boss.weight /con.boss.HP;
            g.drawImage(ImageData.bloodBar,con.boss.pointX+j*i,con.boss.pointY-10,j,10,null);
        }
        g.drawImage(con.boss.image,con.boss.pointX,con.boss.pointY,con.boss.weight,con.boss.height,null);// 5:6
        // 敌方坦克绘制
        for (EnemyTank enemyTank : con.enemyTanks) {
            for (int i = 0; i < enemyTank.blood; i++) {// 敌方坦克血条绘制
                int j = enemyTank.weight/enemyTank.HP;
                g.drawImage(ImageData.bloodBar,enemyTank.pointX+i*j,enemyTank.pointY-5,j,5,null);
            }
            g.drawImage(enemyTank.image,enemyTank.pointX,enemyTank.pointY,enemyTank.weight,enemyTank.height,null);
        }
        // 子弹绘制
        for (int i = 0; i < con.bullets.size(); i++) {
            g.drawImage(con.bullets.get(i).image,con.bullets.get(i).pointX,con.bullets.get(i).pointY,
                    con.bullets.get(i).weight,con.bullets.get(i).height,null);
        }
        // 道具绘制
        for (Item item : con.itemList) {
            item.changeImage();
            g.drawImage(item.image,item.pointX,item.pointY,40,40,null);
        }
        for (int i = 0; i < con.blastTank.size(); i++) {
            g.drawImage(ImageData.blast,con.blastTank.get(i).pointX,con.blastTank.get(i).pointY,
                    con.blastTank.get(i).weight,con.blastTank.get(i).height,null);
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
    }
}
