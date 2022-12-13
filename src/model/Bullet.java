package model;

import commen.ImageData;
import commen.MusicData;
import controller.Controller;

import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.Objects;

public class Bullet extends Role {
    public int bulletSpeed;// 子弹速度
    public String direction;// 方向 N上，S下，W左，E右
    public int type;// 子弹类型
    public int power;// 子弹威力
    public boolean isSteel; // 是否可以打穿铁
    public int reflex;// 反射

    public Controller con;

    public Bullet(int bulletSpeed, String direction, int type, int power, Image image,Controller con) {
        this.bulletSpeed = bulletSpeed;
        this.direction = direction;
        this.type = type;
        this.power = power;
        this.weight = 10;
        this.height = 10;
        this.image = image;
        this.isSteel = false;
        this.con = con;
        this.reflex = 0;
    }

    // 绘制子弹
    public void DrawRole(int X, int Y) {
        this.pointX = X;
        this.pointY = Y;
    }
    // 子弹移动
    public void move() {
        switch (direction) {
            case "N":
                pointY -= bulletSpeed;
                if (pointY < 0 || boom())
                    con.bullets.remove(this);
                break;
            case "S":
                pointY += bulletSpeed;
                if (pointY > 560 || boom())
                    con.bullets.remove(this);
                break;
            case "W":
                pointX -= bulletSpeed;
                if (pointY < 0 || boom())
                    con.bullets.remove(this);
                break;
            case "E":
                pointX += bulletSpeed;
                if(reflex > 0 && pointX > 780){
                    direction = "W";
                    reflex--;
                }else if (pointX > 780 || boom()) {
                    con.bullets.remove(this);
                }
                break;
            case "NE":
                pointX += bulletSpeed;
                pointY -= bulletSpeed;
                if(reflex > 0 && (pointX > 780 || pointY < 0)){
                    direction = "SE";
                    reflex-- ;
                }else if(pointX > 780 || pointY < 0 || boom()){
                    con.bullets.remove(this);
                }
                break;
            case "SE":
                pointX += bulletSpeed;
                pointY += bulletSpeed;
                if(reflex > 0 && (pointX > 780 || pointY > 560)){
                    direction = "NE";
                    reflex-- ;
                }else if(pointX > 780 || pointY > 560 || boom()){
                    con.bullets.remove(this);
                }
        }
    }
    // 子弹碰撞检测
    public boolean boom() {
        Rectangle rec = new Rectangle(pointX, pointY, weight, height);

        Rectangle homeRec = new Rectangle(360,520,60,30);// 老家碰撞体积
        Rectangle bossRec = new Rectangle(con.boss.pointX+50,con.boss.pointY+30,50,30);

        if(rec.intersects(homeRec) && con.index < 8){
            ImageData.changeHomeImage();
            con.gameJFrame.repaint();
            con.isOver = true;
            return true;
        }
        // 敌方子弹判断打中我方坦克或者
        if (type < 0) {
            // P1坦克
            Rectangle p1Rec = new Rectangle(con.tankP1.pointX, con.tankP1.pointY, con.tankP1.weight, con.tankP1.height);
            if (rec.intersects(p1Rec)) {
                con.tankP1.blood--;

                if(con.welMenuActLis.allMusic && con.welMenuActLis.hitMusic) {
                    // 打中的音效
                    MusicData.hitClip.start();
                    MusicData.hitClip.setFramePosition(0);
                }

                return true;
            }
            if (con.tankP2 != null && // P2坦克碰撞
                    rec.intersects(new Rectangle(con.tankP2.pointX, con.tankP2.pointY, con.tankP2.weight, con.tankP2.height)) ) {

                if(con.welMenuActLis.allMusic && con.welMenuActLis.hitMusic) {
                    // 打中的音效
                    MusicData.hitClip.start();
                    MusicData.hitClip.setFramePosition(0);
                }

                con.tankP2.blood--;
                return true;
            }
        }
        // 我方子弹判断打中敌方坦克
        else if (type > 0) {
            for (EnemyTank enemyTank : con.enemyTanks) {
                Rectangle en = new Rectangle(enemyTank.pointX, enemyTank.pointY, enemyTank.weight, enemyTank.height);
                if (rec.intersects(en)) {
                    enemyTank.blood--;
                    if(con.welMenuActLis.allMusic && con.welMenuActLis.hitMusic) {
                        // 打中的音效
                        MusicData.hitClip.start();
                        MusicData.hitClip.setFramePosition(0);
                    }

                    if(enemyTank.blood == 0 && type == 1){
                        switch (enemyTank.type){
                            case -1:
                                con.tankP1.point += 100;
                                break;
                            case -2:
                            case -3:
                                con.tankP1.point += 200;
                                break;
                            case -4:
                                con.tankP1.point += 300;
                                break;
                            case -5:
                                con.tankP1.point += 500;
                        }
                        if(con.index < 8) {
                            con.gameJFrame.infoJPanel.p1Point.setText(String.valueOf(con.tankP1.point));
                        }
                    }else if(enemyTank.blood == 0 && type == 2){
                        switch (enemyTank.type){
                            case -1:
                                con.tankP2.point += 100;
                                break;
                            case -2:
                            case -3:
                                con.tankP2.point += 200;
                                break;
                            case -4:
                                con.tankP2.point += 300;
                                break;
                            case -5:
                                con.tankP2.point += 500;
                        }
                        if(con.index < 8) {
                            con.gameJFrame.infoJPanel.p2Point.setText(String.valueOf(con.tankP2.point));
                        }
                    }
                    return true;
                }
            }
            if(con.index == 8 && bossRec.intersects(rec)){
                con.boss.blood--;
                return true;
            }
        }
        // 碰撞地图判断
        for (int i = 0; i < con.map.gameMap[con.index].length; i++) {
            for (int j = 0; j < con.map.gameMap[con.index][i].length; j++) {
                Rectangle mapRec = new Rectangle(i * 20, j * 20, 20, 20);
                if (con.map.gameMap[con.index][i][j] == 1 && rec.intersects(mapRec)) {// 判断土块重叠
                    con.map.gameMap[con.index][i][j] = 5;

                    if(con.welMenuActLis.allMusic && con.welMenuActLis.hitMusic) {
                        // 打中的音效
                        MusicData.hitClip.start();
                        MusicData.hitClip.setFramePosition(0);
                    }

//                    con.map.gameMap[con.index][i][j] = 0;
                    // 以下四个判断为判断隔壁
                    Rectangle LMapRec = new Rectangle((i-1) * 20, j * 20, 20, 20);
                    Rectangle RMapRec = new Rectangle((i+1) * 20, j * 20, 20, 20);
                    Rectangle UMapRec = new Rectangle(i * 20, (j-1) * 20, 20, 20);
                    Rectangle DMapRec = new Rectangle(i * 20, (j+1) * 20, 20, 20);
                    try {
                        if (con.map.gameMap[con.index][i - 1][j] == 1 && rec.intersects(LMapRec)) {
                            con.map.gameMap[con.index][i - 1][j] = 5;
                        }
                        if (con.map.gameMap[con.index][i + 1][j] == 1 && rec.intersects(RMapRec)) {
                            con.map.gameMap[con.index][i + 1][j] = 5;
                        }
                        if (con.map.gameMap[con.index][i][j - 1] == 1 && rec.intersects(UMapRec)) {
                            con.map.gameMap[con.index][i][j - 1] = 5;
                        }
                        if (con.map.gameMap[con.index][i][j + 1] == 1 && rec.intersects(DMapRec)) {
                            con.map.gameMap[con.index][i][j + 1] = 5;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("超出边界，不做判断");
                    }
                    return true;
                }
                if(con.map.gameMap[con.index][i][j] == 2 && rec.intersects(mapRec)){

                    if(con.welMenuActLis.allMusic && con.welMenuActLis.hitMusic) {
                        // 打中的音效
                        MusicData.hitClip.start();
                        MusicData.hitClip.setFramePosition(0);
                    }

                    if(isSteel){// 判断是否可以打破铁
//                        con.map.gameMap[con.index][i][j] = 0;
                        con.map.gameMap[con.index][i][j] = 5;
                        // 以下四个判断为判断隔壁
                        Rectangle LMapRec = new Rectangle((i-1) * 20, j * 20, 20, 20);
                        Rectangle RMapRec = new Rectangle((i+1) * 20, j * 20, 20, 20);
                        Rectangle UMapRec = new Rectangle(i * 20, (j-1) * 20, 20, 20);
                        Rectangle DMapRec = new Rectangle(i * 20, (j+1) * 20, 20, 20);
                        try {
                            if ((con.map.gameMap[con.index][i - 1][j] == 1 || con.map.gameMap[con.index][i - 1][j] == 2)
                                    && rec.intersects(LMapRec)) {
                                con.map.gameMap[con.index][i - 1][j] = 5;
                            }
                            if ((con.map.gameMap[con.index][i + 1][j] == 1 || con.map.gameMap[con.index][i + 1][j] == 2)
                                    && rec.intersects(RMapRec)) {
                                con.map.gameMap[con.index][i + 1][j] = 5;
                            }
                            if ((con.map.gameMap[con.index][i][j - 1] == 1 || con.map.gameMap[con.index][i][j-1] == 2)
                                    && rec.intersects(UMapRec)) {
                                con.map.gameMap[con.index][i][j - 1] = 5;
                            }
                            if ((con.map.gameMap[con.index][i][j + 1] == 1 || con.map.gameMap[con.index][i][j+1] == 2)
                                    && rec.intersects(DMapRec)) {
                                con.map.gameMap[con.index][i][j + 1] = 5;
                            }
                        }catch (ArrayIndexOutOfBoundsException e){
                            e.printStackTrace();
                        }
                        return true;
                    }else{
                        return true;
                    }
                }
            }
        }
        // 碰撞地方子弹
        for (int i = 0; i < con.bullets.size(); i++) {
            Rectangle bulletRec = new Rectangle(con.bullets.get(i).pointX, con.bullets.get(i).pointY, con.bullets.get(i).weight, con.bullets.get(i).height);
            if(rec.intersects(bulletRec) && !rec.contains(bulletRec) &&
                    ((type>0 && con.bullets.get(i).type<0) || (type<0 && con.bullets.get(i).type>0))){

                if(con.welMenuActLis.allMusic && con.welMenuActLis.hitMusic) {
                    // 打中的音效
                    MusicData.hitClip.start();
                    MusicData.hitClip.setFramePosition(0);
                }

                con.bullets.remove(con.bullets.get(i));
                return true;
            }
        }
        return false;
    }

}
