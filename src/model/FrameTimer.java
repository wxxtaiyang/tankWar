package model;

import commen.ImageData;
import commen.MusicData;
import controller.Controller;
import controller.CustomAtcLis;
import view.CustomJFrame;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class FrameTimer {
    public Timer timer;//
    public int delay = 10;
    public int[][] point = {{0, 0}, {12, 0}, {24, 0}, {37, 0}};// 固定生成的坐标
    public int insertTankTime = 150;// 生成坦克时间
    public int itemTimer = 5;// 生成道具时间
    public Controller con;
    public Tank oldTank1;// 旧坦克，用于记录血量和生命值
    public Tank oldTank2;// 旧坦克，用于记录血量和生命值
    public int playAllPoint;// 之前的积分和


    public FrameTimer(Controller con) {
        this.con = con;
        this.timer = new Timer(delay, actLis);
        oldTank1 = new Tank(3, 3, "", 0, null, null);
        oldTank2 = new Tank(3, 3, "", 0, null, null);
        playAllPoint = 0;
    }

    public ActionListener actLis = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //p1,p2坦克移动。子弹移动
            tankMove();
            //敌方坦克生成
            enemyTankBron();
            //敌方坦克移动
            enemyTankMove();
            // 道具生成
            itemInset();
            //爆炸动画
            blast();
            //判断坦克P1和P2是血量是否为零
            checkTankBlood();
            con.gameJFrame.repaint();
            //判断是否结束游戏
            checkOver();
            //结算界面
            checkNext();

            con.gameJFrame.repaint();
        }
    };
    //p1,p2坦克移动。子弹移动
    public void tankMove(){
        if (con.tankP1.moveL || con.tankP1.moveU || con.tankP1.moveD || con.tankP1.moveR) {
            con.tankP1.move();
        }
        con.tankP1.fireTime++;// 可发射子弹时间
        if(con.tankP2 != null) {
            if (con.tankP2.moveU || con.tankP2.moveD || con.tankP2.moveL || con.tankP2.moveR) {
                con.tankP2.move();
            }
            con.tankP2.fireTime++;
        }
        for (int i = 0; i < con.bullets.size(); i++) {
            con.bullets.get(i).move();
        }
    }
    // 敌方坦克生成
    public void enemyTankBron(){
        if (insertTankTime != 300) {
            insertTankTime++;
        } else { // 两秒生成一个,最多存在十个,且关卡的坦克存在还没出现
            if (con.enemyTanks.size() < 10 && !con.tankLevel[con.index].isEmpty()) {
                // 敌方坦克刷新
                Collections.shuffle(con.tankLevel[con.index]);// 顺序打乱
                EnemyTank t = con.tankLevel[con.index].get(0);// 拿取肯定存在的第一个
                Random rd = new Random();
                do {
                    int i = rd.nextInt(point.length);// 随机获取出生点
                    t.pointX = point[i][0] * 20;// 随机的X坐标
                    t.pointY = point[i][1] * 20;// 随机的Y坐标
                } while (!t.Collision());// 判断这个点是否存在坦克
                con.enemyTanks.add(t);// 界面坦克集合添加
                con.tankLevel[con.index].remove(t);// 关卡总集合减少
                insertTankTime = 0;
            }
        }
    }
    // 敌方坦克移动
    public void enemyTankMove(){
        for (int i = 0; i < con.enemyTanks.size(); i++) {
            con.enemyTanks.get(i).move();
            con.enemyTanks.get(i).updateTime++;// 换方向时间
            con.enemyTanks.get(i).isFire++;// 可发射子弹时间
        }
    }
    // 判断爆炸和爆炸动画
    public void blast(){
        for (int i = 0; i < con.map.gameMap[con.index].length; i++) {
            for (int j = 0; j < con.map.gameMap[con.index][i].length; j++) {
                if (con.map.gameMap[con.index][i][j] >= 5) { // 地图动画，5以上不判断且用数值代表时间
                    // 爆炸音效
                    if(con.welMenuActLis.allMusic && con.welMenuActLis.blastMusic) {
                        MusicData.blastClip.start();
                        MusicData.blastClip.setFramePosition(0);
                    }

                    con.map.gameMap[con.index][i][j]++;
                }
                if (con.map.gameMap[con.index][i][j] >= 10) {
                    con.map.gameMap[con.index][i][j] = 0;
                }
            }
        }
        for (int i = 0; i < con.enemyTanks.size(); i++) {
            if (con.enemyTanks.get(i).blood == 0) {
                // 爆炸音效
                if(con.welMenuActLis.allMusic && con.welMenuActLis.blastMusic) {
                    MusicData.blastClip.start();
                    MusicData.blastClip.setFramePosition(0);
                }

                switch (con.enemyTanks.get(i).type) {
                    case -1:
                        if(con.index < 8) {
                            con.tankMap[con.index][0]--;
                            con.gameJFrame.infoJPanel.enemy1.setText("×  " + con.tankMap[con.index][0]);
                        }
                        break;
                    case -2:
                        if(con.index < 8) {
                            con.tankMap[con.index][1]--;
                            con.gameJFrame.infoJPanel.enemy2.setText("×  " + con.tankMap[con.index][1]);
                        }
                        break;
                    case -3:
                        if(con.index < 8) {
                            con.tankMap[con.index][2]--;
                            con.gameJFrame.infoJPanel.enemy3.setText("×  " + con.tankMap[con.index][2]);
                        }
                        break;
                    case -4:
                        if(con.index < 8) {
                            con.tankMap[con.index][3]--;
                            con.gameJFrame.infoJPanel.enemy4.setText("×  " + con.tankMap[con.index][3]);
                        }
                        break;
                    case -6:
                    case -5:
                        if(con.index < 8) {
                            con.tankMap[con.index][4]--;
                            con.gameJFrame.infoJPanel.enemy5.setText("×  " + con.tankMap[con.index][4]);
                        }
                }
                EnemyTank t = new EnemyTank(con.enemyTanks.get(i).pointX, con.enemyTanks.get(i).pointY, 0, 0);
                con.blastTank.add(t);
                con.enemyTanks.remove(con.enemyTanks.get(i));
            }
        }
        for (int i = 0; i < con.blastTank.size(); i++) {
            con.blastTank.get(i).height += 4;
            con.blastTank.get(i).weight += 4;
            if (con.blastTank.get(i).height == 40) {
                con.blastTank.remove(con.blastTank.get(i));
            }
        }
    }
    // 判断坦克P1和P2是血量是否为零
    public void checkTankBlood(){
        if(con.tankP2 != null) {
            if (con.tankP2.life == 0) {
                con.tankP2.pointX = -100;
                con.tankP2.pointY = -100;
            } else if (con.tankP2.blood == 0) {
                // 爆炸音效
                if(con.welMenuActLis.allMusic && con.welMenuActLis.blastMusic) {
                    MusicData.blastClip.start();
                    MusicData.blastClip.setFramePosition(0);
                }

                Tank t = new Tank(con.tankP2.pointX, con.tankP2.pointY, 0, 0);
                con.blastTank.add(t);
                con.tankP2.direction = "N";
                con.tankP2.DrawRole(22, 26);
                if (con.tankP2.Collision()) {// 当坦克2的起始位置没有其他坦克的时候生成坦克2
                    con.tankP2.life--;// 生命减一
                    con.tankP2.blood = 3;// 血量初始为3
                    con.tankP2.checkSteel = false;// 无法打铁
                    con.tankP2.image = ImageData.tank2U;
                } else { // 当生命值为零时坐标移到地图外
                    con.tankP2.DrawRole(-200, -200);
                }
            }
        }
        if (con.tankP1.life == 0) {
            con.tankP1.pointX = -100;
            con.tankP1.pointY = -100;
        }
        else if (con.tankP1.blood == 0) {// 当坦克1的起始位置没有其他坦克的时候生成坦克1
            // 爆炸音效
            if(con.welMenuActLis.allMusic && con.welMenuActLis.blastMusic) {
                MusicData.blastClip.start();
                MusicData.blastClip.setFramePosition(0);
            }

            Role t = new Tank(con.tankP1.pointX, con.tankP1.pointY, 0, 0);
            con.blastTank.add(t);
            con.tankP1.DrawRole(15, 26);
            con.tankP1.direction = "N";
            if(con.tankP1.Collision()){
                con.tankP1.life--;// 生命减一
                con.tankP1.blood = 3;// 血量初始为3
                con.tankP1.checkSteel = false;// 不能打铁
                con.tankP1.image = ImageData.tank1U;
            }else{
                con.tankP1.DrawRole(-200, -200);
            }
        }
    }
    // 判断是否结束游戏
    public void checkOver(){
        if (con.isOver) {
            con.frameTimer.timer.stop();
            if(con.ctmLis.normalOrCustom){
                JOptionPane.showMessageDialog(null,"游戏结束！");
                con.gameJFrame.setVisible(false);
                con.ctmLis = new CustomAtcLis(con); // 初始化自定义界面的动作监听事件
                con.ctmJFrame = new CustomJFrame(con);
                con.dataRestore();
                con.welJFrame.setVisible(true);
                return;
            }
            con.overTimer = new OverTimer(con);// 重新生成结束时的定时器
            con.overJFrame.overJPanel.next.setText("结束游戏");
            JOptionPane.showMessageDialog(null,"游戏结束！");
            con.overTimer.timer.start();// 启动动画
            con.gameJFrame.setVisible(false);
            con.overJFrame.setVisible(true);
        }
    }
    // 通关结算界面
    public void checkNext(){
        if (con.enemyTanks.size()+con.tankLevel[con.index].size() == 0) { // 关卡的坦克数量为0时，下一关。
            if(con.ctmLis.normalOrCustom){ // 判断是否为自定义
                JOptionPane.showMessageDialog(null,"游戏结束！");
                con.gameJFrame.setVisible(false);
                con.dataRestore();
                con.ctmJFrame = new CustomJFrame(con);
                con.welJFrame.setVisible(true);
                return;
            }
            oldTank1.life = con.tankP1.life;
            oldTank1.blood = con.tankP1.blood;
            if(con.tankP2 != null) {
                oldTank2.life = con.tankP2.life;
                oldTank2.blood = con.tankP2.blood;
            }
            con.frameTimer.timer.stop();
            con.overTimer = new OverTimer(con);// 重新生成结束时的定时器，让数组刷新
            if(con.index+1 > 7){ //  判断光卡数是否大于7，只有八关
                con.overJFrame.overJPanel.next.setText("结束游戏");
                JOptionPane.showMessageDialog(null,"恭喜你通关了");
            }else {
                con.overJFrame.overJPanel.next.setText("下一关");
                JOptionPane.showMessageDialog(null, "恭喜您过关了");
            }
            con.overTimer.timer.start();// 启动动画
            con.gameJFrame.setVisible(false);
            con.overJFrame.setVisible(true);

        }// p1 和 p2坐标为-100时表示两个生命为零，结算
        else if(con.tankP1.pointX == -100 && (con.tankP2 == null || con.tankP2.pointX == -100)){
            con.isOver = true;
        }
    }
    // 道具的生成与消失
    public void itemInset(){
        int X = 0;// 随机的X坐标
        int Y = 0;// 随机的Y坐标
        if(itemTimer % 800 ==0){// 判断八秒生成一个道具
            switch (new Random().nextInt(3)){
                case 0:
                    Item itemBlood = new Item("血包",0,ImageData.blood,1200,con);
                    do{
                        X = new Random().nextInt(741);
                        Y = new Random().nextInt(521);
                    }while(!itemBlood.setItem(X,Y));
                    itemBlood.pointX = X;
                    itemBlood.pointY = Y;
                    con.itemList.add(itemBlood);
                    break;
                case 1:
                    Item itemBullet = new Item("子弹",1,ImageData.pistol,1200,con);
                    do{
                        X = new Random().nextInt(741);
                        Y = new Random().nextInt(521);
                    }while(!itemBullet.setItem(X,Y));
                    itemBullet.pointX = X;
                    itemBullet.pointY = Y;
                    con.itemList.add(itemBullet);
                    break;
                case 2:
                    Item itemBoom = new Item("炸弹",2,ImageData.bomb,1200,con);
                    do{
                        X = new Random().nextInt(741);
                        Y = new Random().nextInt(521);
                    }while(!itemBoom.setItem(X,Y));
                    itemBoom.pointX = X;
                    itemBoom.pointY = Y;
                    con.itemList.add(itemBoom);
                    break;
//                case 3:
//                    break;
//                case 4:
//                    break;
            }
        }
        for (int i = 0; i < con.itemList.size(); i++) {
            con.itemList.get(i).dieTime--;
            if(con.itemList.get(i).dieTime == 0){
                con.itemList.remove(con.itemList.get(i));
            }
        }
        itemTimer++;
    }
}