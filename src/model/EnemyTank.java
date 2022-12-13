package model;

import commen.ImageData;
import commen.MusicData;
import controller.Controller;

import java.awt.*;
import java.util.Random;

public class EnemyTank extends Role{

    public int HP;// 生命值
    public int blood; // 当前血量
    public String direction;// 方向 N上，S下，W左，E右
    public int tankSpeed; // 速度
    public int type;// 敌方坦克类型
    public int isFire;// 是否可以发射子弹
    public int updateTime = 0;// 敌方坦克移动间隔时间
    public Controller con;
    public int bSpeed;// 自定义时存在的子弹速度

    public EnemyTank(int pointX,int pointY,int height,int weight) {
        this.pointX = pointX;
        this.pointY = pointY;
        this.weight = weight;
        this.height = height;
    }

    public EnemyTank(int HP, int blood, String direction, int tankSpeed, int type,Image image,  Controller con) {
        this.image = image;
        this.HP = HP;
        this.blood = blood;
        this.direction = direction;
        this.tankSpeed = tankSpeed;
        this.type = type;
        this.isFire = 100;
        this.updateTime = 0;
        this.con = con;
        weight = 40;
        height = 40;
    }
    // 敌方坦克移动
    public boolean move() {
        if(con.welMenuActLis.moveMusic && con.welMenuActLis.allMusic) {
            MusicData.moveClip.start();// 播放音效
            MusicData.moveClip.setFramePosition(0);// 将介质重绕到剪辑的开头。
        }

        changeDirection();// 修改照片
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
    // 敌方坦克射击
    public void fire(){
        if(con.welMenuActLis.allMusic && con.welMenuActLis.fireMusic) {
            MusicData.fireClip.start();// 开火音效
            MusicData.fireClip.setFramePosition(0);// 将介质重绕到剪辑的开头
        }

        Bullet bullet= new Bullet(4, direction, type, 1, ImageData.enemy_bullet, con);
        if(type == -6){
            bullet.bulletSpeed = bSpeed;
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
        isFire = 0;
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
        // p1坦克
        Rectangle tank1Rec = new Rectangle(con.tankP1.pointX, con.tankP1.pointY, con.tankP1.weight, con.tankP1.height);
        // 判断碰撞P1
        if (thisTank.intersects(tank1Rec))
            return false;
        if(con.tankP2 != null) {
            // p2坦克
            Rectangle tank2Rec = new Rectangle(con.tankP2.pointX, con.tankP2.pointY, con.tankP2.weight, con.tankP2.height);
            // 判断碰撞P2
            if (thisTank.intersects(tank2Rec))
                return false;
        }
        // 敌方坦克碰撞
        for (EnemyTank enemyTank : con.enemyTanks) {
            Rectangle en = new Rectangle(enemyTank.pointX, enemyTank.pointY, enemyTank.weight, enemyTank.height);
            if (enemyTank.equals(this))// 判断敌方坦克中某一辆，则自身与自身不做碰撞处理
                continue;
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
        return true;
    }
    // 改变方法同时判断是否可以开火
    public void changeDirection(){
        int P1X = con.tankP1.pointX;
        int P1Y = con.tankP1.pointY;

        if(Collision() && updateTime == 200){
            if (pointY - 520 > 0) {
                direction = "N";
            } else if (pointY - 520 < 0) {
                direction = "S";
            } else if (pointX - 360 > 0) {
                direction = "W";
            } else if (pointX - 360 < 0) {
                direction = "E";
            }
            changImage(this);
            updateTime = 0;
            return;
        }
        // 发生碰撞后修改方向，当次不进行射击判断
        if(!Collision()){
            updateTime = 0;
            Random rd = new Random();
            int dir = rd.nextInt(4);
            switch (dir) {// 随机方向
                case 0:
                    direction = "N";
                    break;
                case 1:
                    direction = "S";
                    break;
                case 2:
                    direction = "W";
                    break;
                case 3:
                    direction = "E";
                    break;
            }
            return;
        }
        else if(isFire >= 100) {// 先判断是否可射击敌方坦克设计判断，当X轴或者区间在（-10，10）时进行射击
            if( (pointX - 360 > -20 && pointX - 360 < 20)){// 判断某一个X，Y是否在家的范围里面
                updateTime = 0;
                if( pointY - 540 > 0)
                    direction = "N";
                else
                    direction = "S";
                fire();
            }else if((pointY - 540 > -20 && pointY - 540 < 20)){
                updateTime = 0;
                if(pointX-390 > 0)
                    direction = "W";
                else
                    direction = "E";
            }else if((pointX - P1X > -10 && pointX - P1X < 10)){// 判断某一个X，Y是否在P1的范围里面
                updateTime = 0;
                if(pointY-P1Y > 0){
                    direction = "N";
                }else{
                    direction = "S";
                }
                fire();
            }else if((pointY-P1Y > -10 && pointY - P1Y < 10)){
                updateTime = 0;
                if(pointX-P1X > 0)
                    direction = "W";
                else
                    direction = "E";
                fire();
            }else if(con.tankP2 != null) {
                int P2X = con.tankP2.pointX;
                int P2Y = con.tankP2.pointY;
                if((pointX - P2X > -10 && pointX - P2X < 10)){// 判断某一个X，Y是否在P2的范围里面
                    updateTime = 0;
                    if(pointY-P2Y > 0)
                        direction = "N";
                    else
                        direction = "S";
                    fire();
                }else if( (pointY - P2Y > -10 && pointY - P2Y < 10)){
                    updateTime = 0;
                    if(pointX-P2X > 0)
                        direction = "W";
                    else
                        direction = "E";
                    fire();
                }
            }
        }
        changImage(this);
    }
    // 敌方坦克换图片
    public void changImage(EnemyTank tank) {
        if (tank.type == -1) {
            switch (tank.direction) {
                case "N":
                    tank.image = ImageData.enemy1U;
                    break;
                case "S":
                    tank.image = ImageData.enemy1D;
                    break;
                case "W":
                    tank.image = ImageData.enemy1L;
                    break;
                case "E":
                    tank.image = ImageData.enemy1R;
                    break;
            }
        } else if (tank.type == -2) {
            switch (tank.direction) {
                case "N":
                    tank.image = ImageData.enemy2U;
                    break;
                case "S":
                    tank.image = ImageData.enemy2D;
                    break;
                case "W":
                    tank.image = ImageData.enemy2L;
                    break;
                case "E":
                    tank.image = ImageData.enemy2R;
                    break;
            }
        } else if (tank.type == -3) {
            switch (tank.direction) {
                case "N":
                    tank.image = ImageData.enemy3U;
                    break;
                case "S":
                    tank.image = ImageData.enemy3D;
                    break;
                case "W":
                    tank.image = ImageData.enemy3L;
                    break;
                case "E":
                    tank.image = ImageData.enemy3R;
                    break;
            }
        } else if (tank.type == -4) {
            switch (tank.direction) {
                case "N":
                    tank.image = ImageData.enemy5U;
                    break;
                case "S":
                    tank.image = ImageData.enemy5D;
                    break;
                case "W":
                    tank.image = ImageData.enemy5L;
                    break;
                case "E":
                    tank.image = ImageData.enemy5R;
                    break;
            }
        } else {
            switch (tank.direction) {
                case "N":
                    tank.image = ImageData.enemy4U;
                    break;
                case "S":
                    tank.image = ImageData.enemy4D;
                    break;
                case "W":
                    tank.image = ImageData.enemy4L;
                    break;
                case "E":
                    tank.image = ImageData.enemy4R;
                    break;
            }
        }
    }
}
