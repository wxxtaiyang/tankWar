package model;

import commen.DataBoss;
import commen.ImageData;
import controller.Controller;

import java.awt.*;
import java.util.Random;

public class BossEnemy extends Role {
    public int blood;// 当前血量
    public int HP;// 总血量
    public int jumpTime; // 跳跃动画渲染时间
    public int runTime;// 奔跑动画渲染时间
    public int dieTime;// 死亡动画渲染时间
    public int moveTime;// 移动时间
    public Controller con;

    public BossEnemy(Controller con, int pointX, int pointY, int weight, int height, Image image) {
        this.con = con;
        this.pointX = pointX;
        this.pointY = pointY;
        this.weight = weight;
        this.height = height;
        this.image = image;
        HP = 18;
        blood = HP;
        jumpTime = 0;
        runTime = 0;
        dieTime = 0;
    }

    // 默认展示
    public void defaultShow() {
        runTime++;
        if (runTime / 10 == 0) {
            image = DataBoss.BOSS_RUN3;
        } else if (runTime / 20 == 0) {
            image = DataBoss.BOSS_RUN4;
        } else if (runTime / 30 == 0) {
            image = DataBoss.BOSS_RUN5;
        } else if (runTime / 40 == 0) {
            image = DataBoss.BOSS_RUN6;
        } else if (runTime / 50 == 0) {
            image = DataBoss.BOSS_RUN7;
        } else if (runTime / 60 == 0) {
            image = DataBoss.BOSS_RUN8;
        } else if (runTime / 70 == 0) {
            image = DataBoss.BOSS_RUN9;
        } else if (runTime / 80 == 0) {
            image = DataBoss.BOSS_RUN10;
            runTime = 0;
        }
    }
    // 开始动画
    public boolean beginShow() {
        Rectangle rec = new Rectangle(pointX,pointY,weight,height);
        Rectangle tank1Rec = new Rectangle(con.tankP1.pointX,con.tankP1.pointY,con.tankP1.weight,con.tankP1.height);
        if(rec.intersects(tank1Rec)){
            con.tankP1.blood = 0;
        }
        if(con.tankP2 != null) {
            Rectangle tank2Rec = new Rectangle(con.tankP2.pointX, con.tankP2.pointY, con.tankP2.weight, con.tankP2.height);
            if(rec.intersects(tank2Rec)){
                con.tankP2.blood = 0;
            }
        }
        if (pointY < 440) {
            pointY += 5;
            return false;
        }
        return true;
    }
    // 跳跃展示
    public void jumpShow() {
        if (pointY > 0 && jumpTime < 20) {
            image = DataBoss.BOSS_JUMP5;
        } else if (pointY > 0 && jumpTime <= 75) {
            image = DataBoss.BOSS_JUMP3;
            pointY -= 4;
        } else {
            jumpTime = 0;
            con.bossTimer.time = 100;
        }
        jumpTime++;
        Rectangle rec = new Rectangle(pointX,pointY,weight,height);
        Rectangle tank1Rec = new Rectangle(con.tankP1.pointX,con.tankP1.pointY,con.tankP1.weight,con.tankP1.height);
        if(rec.intersects(tank1Rec)){
            con.tankP1.blood = 0;
        }
        if(con.tankP2 != null) {
            Rectangle tank2Rec = new Rectangle(con.tankP2.pointX, con.tankP2.pointY, con.tankP2.weight, con.tankP2.height);
            if(rec.intersects(tank2Rec)){
                con.tankP2.blood = 0;
            }
        }
    }
    // 死亡展示
    public void dieShow() {
        for (int i = 0; i < con.enemyTanks.size(); i++) {
            con.enemyTanks.get(i).blood = 0;
        }
        if (dieTime == 20) {
            pointY = 440;
            weight = 100;
            height = 120;
            image = DataBoss.BOSS_RUN5;
        } else if (dieTime == 40) {
            image = DataBoss.BOSS_RUN6;
        } else if (dieTime == 60) {
            image = DataBoss.BOSS_DIE1;
        } else if (dieTime == 80) {
            image = DataBoss.BOSS_DIE2;
        } else if (dieTime == 100) {
            weight = 120;
            height = 120;
            image = DataBoss.BOSS_DIE3;
        } else if (dieTime == 120) {
            weight = 140;
            height = 90;
            pointY += 20;
            image = DataBoss.BOSS_DIE4;
        } else if (dieTime == 140) {
            weight = 140;
            height = 70;
            pointY += 20;
            image = DataBoss.BOSS_DIE5;
        }else if(dieTime > 140 && dieTime < 250){
            if(dieTime % 3 == 0) {
                int x = new Random().nextInt(weight) + pointX;
                int y = new Random().nextInt(height) + pointY;
                con.blastTank.add(new EnemyTank(x, y, 0, 0));
            }
        }
        dieTime++;
    }
    // 移动方法
    public void move() {
        if (pointX < 780 && pointX >= 0) {
            image = DataBoss.BOSS_CHARGE;
            pointX += 10;
        } else if (pointX >= 780) {
            pointX = -100;
        } else if(pointX < 0){
            pointX += 5;
        }
        Rectangle rec = new Rectangle(pointX,pointY,weight,height);
        Rectangle tank1Rec = new Rectangle(con.tankP1.pointX,con.tankP1.pointY,con.tankP1.weight,con.tankP1.height);
        if(rec.intersects(tank1Rec)){
            con.tankP1.blood = 0;
        }
        if(con.tankP2 != null) {
            Rectangle tank2Rec = new Rectangle(con.tankP2.pointX, con.tankP2.pointY, con.tankP2.weight, con.tankP2.height);
            if(rec.intersects(tank2Rec)){
                con.tankP2.blood = 0;
            }
        }

    }
    // 发射子弹
    public void fireBullet() {
        if (blood > 12) {
            Bullet b1 = new Bullet(4, "E", -10, 1, ImageData.enemy_bullet, con);
            Bullet b2 = new Bullet(4, "NE", -10, 1, ImageData.enemy_bullet, con);
            Bullet b3 = new Bullet(4, "SE", -10, 1, ImageData.enemy_bullet, con);
            b1.weight = 15;     b1.height = 15;
            b2.weight = 15;     b2.height = 15;
            b3.weight = 15;     b3.height = 15;
            b1.DrawRole(pointX+110,pointY+height/2);
            b2.DrawRole(pointX+110,pointY+height/2);
            b3.DrawRole(pointX+110,pointY+height/2);
            con.bullets.add(b1);
            con.bullets.add(b2);
            con.bullets.add(b3);
        } else if (blood > 6) {
            Bullet b1 = new Bullet(6, "E", -10, 1, ImageData.enemy_bullet, con);
            Bullet b2 = new Bullet(6, "NE", -10, 1, ImageData.enemy_bullet, con);
            Bullet b3 = new Bullet(6, "SE", -10, 1, ImageData.enemy_bullet, con);
            b1.weight = 15;     b1.height = 15;
            b2.weight = 15;     b2.height = 15;
            b3.weight = 15;     b3.height = 15;
            b1.DrawRole(pointX+110,pointY+height/2);
            b2.DrawRole(pointX+110,pointY+height/2);
            b3.DrawRole(pointX+110,pointY+height/2);
            con.bullets.add(b1);
            con.bullets.add(b2);
            con.bullets.add(b3);
        } else {
            Bullet b1 = new Bullet(8, "E", -10, 1, ImageData.enemy_bullet, con);
            b1.reflex = 1;// 设置弹射次数
            Bullet b2 = new Bullet(8, "NE", -10, 1, ImageData.enemy_bullet, con);
            b2.reflex = 1;// 设置弹射次数
            Bullet b3 = new Bullet(8, "SE", -10, 1, ImageData.enemy_bullet, con);
            b3.reflex = 1;// 设置弹射次数
            b1.weight = 15;     b1.height = 15;
            b2.weight = 15;     b2.height = 15;
            b3.weight = 15;     b3.height = 15;
            b1.DrawRole(pointX+110,pointY+height/2);
            b2.DrawRole(pointX+110,pointY+height/2);
            b3.DrawRole(pointX+110,pointY+height/2);
            con.bullets.add(b1);
            con.bullets.add(b2);
            con.bullets.add(b3);
            EnemyTank tank;
            switch (new Random().nextInt(5)) {
                case 0:
                    tank = new EnemyTank(1, 1, "S", 1, -1, ImageData.enemy1D, con);
                    do {
                        tank.pointX = new Random().nextInt(560) + 180;
                        tank.pointY = new Random().nextInt(519) + 1;
                    }while(!tank.Collision());
                    con.enemyTanks.add(tank);
                    break;
                case 1:
                    tank = new EnemyTank(1, 1, "S", 2, -2, ImageData.enemy2D, con);
                    do {
                        tank.pointX = new Random().nextInt(560) + 180;
                        tank.pointY = new Random().nextInt(519) + 1;
                    }while(!tank.Collision());
                    con.enemyTanks.add(tank);
                    break;
                case 2:
                    tank = new EnemyTank(2, 2, "S", 1, -3, ImageData.enemy3D, con);
                    do {
                        tank.pointX = new Random().nextInt(560) + 180;
                        tank.pointY = new Random().nextInt(519) + 1;
                    }while(!tank.Collision());
                    con.enemyTanks.add(tank);
                    break;
                case 3:
                    tank = new EnemyTank(2,2,"S",2,-4,ImageData.enemy5D,con);
                    do {
                        tank.pointX = new Random().nextInt(560) + 180;
                        tank.pointY = new Random().nextInt(519) + 1;
                    }while(!tank.Collision());
                    con.enemyTanks.add(tank);
                    break;
                case 4:
                    tank = new EnemyTank(3,3,"S",2,-5,ImageData.enemy4D,con);
                    do {
                        tank.pointX = new Random().nextInt(560) + 180;
                        tank.pointY = new Random().nextInt(519) + 1;
                    }while(!tank.Collision());
                    con.enemyTanks.add(tank);
                    break;
            }

        }
    }
}
