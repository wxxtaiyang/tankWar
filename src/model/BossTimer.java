package model;

import commen.DataBoss;
import controller.Controller;
import controller.CustomAtcLis;
import view.CustomJFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BossTimer {
    public Timer timer;
    public Controller con;
    public int time;

    public BossTimer(Controller con) {
        this.con = con;
        timer = new Timer(10,act);
        time = 0;
//        timer.start();// 开启动画
    }

    public ActionListener act = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < con.bullets.size(); i++) {
                con.bullets.get(i).move();
            }
            for (int i = 0; i < con.enemyTanks.size(); i++) {
                con.enemyTanks.get(i).move();
            }
            for (int i = 0; i < con.blastTank.size(); i++) {
                if(con.blastTank.get(i).weight > 40){
                    con.blastTank.remove(con.blastTank.get(i));
                }else{
                    con.blastTank.get(i).weight += 5;
                    con.blastTank.get(i).height += 5;
                }

            }
            if(con.tankP1.life == 0){
                if(con.tankP2 == null ){
                    timer.stop();
                    JOptionPane.showMessageDialog(null,"游戏结束，很遗憾，你没打过");
                    con.ctmLis = new CustomAtcLis(con); // 初始化自定义界面的动作监听事件
                    con.ctmJFrame = new CustomJFrame(con);// 初始化自定义界面面板
                    con.dataRestore();
                    con.bossJFrame.setVisible(false);
                    con.welJFrame.setVisible(true);
                }else if(con.tankP2.life == 0){
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "游戏结束，很遗憾，你没打过");
                    con.ctmLis = new CustomAtcLis(con); // 初始化自定义界面的动作监听事件
                    con.ctmJFrame = new CustomJFrame(con);// 初始化自定义界面面板
                    con.dataRestore();
                    con.bossJFrame.setVisible(false);
                    con.welJFrame.setVisible(true);
                }
            }
            con.frameTimer.tankMove();
            con.frameTimer.checkTankBlood();
            con.frameTimer.itemInset();
            con.frameTimer.enemyTankMove();
            con.frameTimer.blast();
            if(time <= 99 && con.boss.beginShow()){
                if(time % 85 == 0){
                    con.boss.image = DataBoss.BOSS_JUMP4;
                }else if(time % 90 == 0){
                    con.boss.image = DataBoss.BOSS_JUMP5;
                }else if(time % 95 == 0){
                    con.boss.image = DataBoss.BOSS_STOP;
                }
            }else if(time > 99){
                if(con.boss.blood <= 0){
                    con.boss.dieShow();
                    if(con.boss.dieTime >= 350){
                        timer.stop();
                        JOptionPane.showMessageDialog(null,"游戏结束，您真厉害");
                        con.ctmLis = new CustomAtcLis(con); // 初始化自定义界面的动作监听事件
                        con.ctmJFrame = new CustomJFrame(con);// 初始化自定义界面面板
                        con.dataRestore();
                        con.bossJFrame.setVisible(false);
                        con.welJFrame.setVisible(true);
                    }
                }else {
                    if (con.boss.pointY > 0 && time > 320 && time <= 400) {// 执行跳跃的，Y轴位置改变
                        con.boss.jumpShow();
                    } else if (time == 270 || time == 470) {
                        con.boss.fireBullet();
                    } else if (con.boss.blood <= 12 && time > 150 && time < 250) {
                        con.boss.move();
                    } else {// 播放奔跑动画
                        con.boss.defaultShow();
                        if (time == 700) {
                            con.boss.image = DataBoss.BOSS_JUMP3;
                            con.boss.jumpTime = 0;
//                            con.boss.blood-=6;
                            time = -1;
                        }
                    }
                }
            }
            con.bossJFrame.repaint();
            time++;
        }
    };
}
