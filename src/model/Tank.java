package model;

import commen.ImageData;
import commen.MusicData;
import controller.Controller;

import javax.sound.sampled.Clip;
import java.awt.*;

// P1起始坐标 300，520{15，26}
// P2起始坐标 440，520{22，26}
public class Tank extends Role {

    public int HP;// 生命值
    public int blood; // 当前血量
    public int life; // 生命值
    public int point;// 积分
    public String direction;// 方向 N上，S下，W左，E右
    public int tankSpeed; // 速度
    public int fireTime;// 发射子弹间隔时间
    public Controller con;

    public boolean checkSteel;// 是否可打掉铁
    public boolean moveU; // 上移
    public boolean moveD;// 下移
    public boolean moveL;//  左移
    public boolean moveR;//  右移

    public Tank(int pointX,int pointY,int height,int weight) { // 用于爆照的构造函数，只需坐标与大小
        this.pointX = pointX;
        this.pointY = pointY;
        this.weight = weight;
        this.height = height;
    }

    public Tank(int life, int blood, String direction, int tankSpeed, Image image, Controller con) {
        this.life = life;
        this.blood = blood;
        this.direction = direction;
        this.tankSpeed = tankSpeed;
        weight = 40;
        height = 40;
        this.image = image;
        this.checkSteel = false;
        this.moveD = false;
        this.moveU = false;
        this.moveR = false;
        this.moveL = false;
        this.pointX = -100;
        this.pointY = -100;
        this.fireTime = 49;
        this.con = con;
        point = 0;
        HP = 3;
    }
    // 绘制坦克
    public void DrawRole(int X, int Y) {
        this.pointX = X * 20;
        this.pointY = Y * 20;
    }
    // 坦克移动
    public boolean move() {
        if(con.welMenuActLis.allMusic && con.welMenuActLis.moveMusic) {
            MusicData.moveClip.start();
            MusicData.moveClip.setFramePosition(0);
        }
        switch (direction) {
            case "N":
                if (!Collision()) {
                    return false;
                }
                pointY -= tankSpeed;
                break;
            case "S":
                if (!Collision()) {
                    return false;
                }
                pointY += tankSpeed;
                break;
            case "W":
                if (!Collision()) {
                    return false;
                }
                pointX -= tankSpeed;
                break;
            case "E":
                if (!Collision()) {
                    return false;
                }
                pointX += tankSpeed;
                break;
        }
        return true;
    }
    // 坦克射击
    public void fire() {
        if(con.welMenuActLis.allMusic && con.welMenuActLis.fireMusic) {
            MusicData.fireClip.start();// 开火音效
            MusicData.fireClip.setFramePosition(0);
        }

        Bullet bullet = new Bullet(4, direction, 1, 1, ImageData.bullet, con);
        bullet.isSteel = checkSteel;// 继承是否可以打铁的属性
        if(this.equals(con.tankP2)){
            bullet.type = 2;
        }
        switch (direction) {
            case "N":
                bullet.DrawRole(pointX + 15, pointY - 10);
                break;
            case "S":
                bullet.DrawRole(pointX + 15, pointY + height + 5);
                break;
            case "W":
                bullet.DrawRole(pointX - 10, pointY + 15);
                break;
            case "E":
                bullet.DrawRole(pointX + weight + 5, pointY + 15);
                break;
        }

        con.bullets.add(bullet);
    }
    // 碰撞检测
    public boolean Collision() {
        int X = pointX; // 预判的x坐标
        int Y = pointY;// 预判的Y坐标
        switch (direction) {// 判断方向得到预判的正确的x与y
            case "N":
                Y -= tankSpeed;
                if(Y < 0)
                    return false;
                break;
            case "S":
                Y += tankSpeed;
                if(Y > 520)
                    return false;
                break;
            case "W":
                X -= tankSpeed;
                if(X < 0)
                    return false;
                break;
            case "E":
                X += tankSpeed;
                if(X > 740)
                    return false;
                break;
        }
        Rectangle thisTank = new Rectangle(X, Y, weight, height);// 当前坦克
        Rectangle bossRec = new Rectangle(con.boss.pointX,con.boss.pointY,con.boss.weight,con.boss.height);

        // 道具判断检测
        for (int i = 0; i < con.itemList.size(); i++) {
            Rectangle itemRec = new Rectangle(con.itemList.get(i).pointX,con.itemList.get(i).pointY,40,40);
            if(thisTank.intersects(itemRec)){

                if(con.welMenuActLis.allMusic && con.welMenuActLis.addMusic) {// 吃到道具的音效
                    MusicData.addClip.start();
                    MusicData.addClip.setFramePosition(0);
                }

                switch (con.itemList.get(i).type){
                    case 0:
                        if(blood < 3)
                            blood++;
                        break;
                    case 1:
                        checkSteel = true;
                        break;
                    case 2:
                        for (int j = 0; j < con.enemyTanks.size(); j++) {
                            con.enemyTanks.get(j).blood = 0;
                        }
                        break;
                }
                con.itemList.remove(con.itemList.get(i));
            }
        }
        // 判断P2坦克是否存在，不存在则为单人模式
        if(con.tankP2 != null) {
            // 判断己方坦克
            if (this.equals(con.tankP1)) {// 判断当前坦克是否为P1
                // p2坦克
                Rectangle tank2Rec = new Rectangle(con.tankP2.pointX, con.tankP2.pointY, con.tankP2.weight, con.tankP2.height);
                if (thisTank.intersects(tank2Rec))
                    return false;
            } else {// 否则为为P2
                // p1坦克
                Rectangle tank1Rec = new Rectangle(con.tankP1.pointX, con.tankP1.pointY, con.tankP1.weight, con.tankP1.height);
                if (thisTank.intersects(tank1Rec))
                    return false;
            }
        }
        // 判断碰撞敌法坦克
        for (EnemyTank enemyTank : con.enemyTanks) {
            Rectangle en = new Rectangle(enemyTank.pointX, enemyTank.pointY, enemyTank.weight, enemyTank.height);
            if (thisTank.intersects(en)) {
                return false;
            }
        }
        // 碰撞地图判断
        for (int i = 0; i < con.map.gameMap[con.index].length; i++) {
            for (int j = 0; j < con.map.gameMap[con.index][i].length; j++) {
                Rectangle rec = new Rectangle(i * 20, j * 20, 20, 20);
                if (con.map.gameMap[con.index][i][j] < 5 && con.map.gameMap[con.index][i][j] != 0 && con.map.gameMap[con.index][i][j] != 3
                          && (thisTank.intersects(rec))) {
                    return false;
                }
            }
        }
        if(bossRec.intersects(thisTank) && con.index == 8){
            blood = 0;
            return false;
        }
        return true;
    }
}
