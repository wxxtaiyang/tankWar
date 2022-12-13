package commen;

import javax.swing.*;
import java.awt.*;

public class ImageData {
    public static Image into;
    // 自己坦克图片
    public static Image tank1U; // 一号玩家坦克
    public static Image tank1D;
    public static Image tank1L;
    public static Image tank1R;
    public static Image tank2U; // 二号玩家坦克
    public static Image tank2D;
    public static Image tank2L;
    public static Image tank2R;

    // 敌对坦克图片
    public static Image enemy1U;//   敌对坦克1
    public static Image enemy1D;
    public static Image enemy1L;
    public static Image enemy1R;
    public static Image enemy2U;//   敌对坦克2
    public static Image enemy2D;
    public static Image enemy2L;
    public static Image enemy2R;
    public static Image enemy3U;//   敌对坦克3
    public static Image enemy3D;
    public static Image enemy3L;
    public static Image enemy3R;
    public static Image enemy4U;//   敌对坦克4
    public static Image enemy4D;
    public static Image enemy4L;
    public static Image enemy4R;
    public static Image enemy5U;//   敌对坦克5
    public static Image enemy5D;
    public static Image enemy5L;
    public static Image enemy5R;
    // 爆炸图片
    public static Image blast;  // 爆炸大图，坦克和墙体使用
    // 营地图片
    public static Image symbol; //  老鹰图片
    public static Image destory;// 老鹰死亡图片
    // 墙体等图片
    public static Image wall;   // 土墙
    public static Image water;  //  河流
    public static Image steels; // 钢铁
    public static Image grass;  // 树林
    // 开始与结背景图片
    public static Image menu_begin;// 开始界面背景
    public static Image menu_end;   // 游戏结束界面背景
    // 游戏背景图片
    public static Image bg_desert;
    public static Image bg_green;
    public static Image bg_black;
    // 子弹照片
    public static Image bullet;     // 玩家坦克子弹
    public static Image enemy_bullet; // 敌对坦克子弹
    // 道具图片
    public static Image blood; // 加血道具
    public static Image bomb; //  爆炸道具
    public static Image timer; //  时间道具
    public static Image start; //  加强钢铁道具
    public static Image pistol;// 强化子弹道具
    // 血条
    public static Image bloodBar;
    // 生命数
    public static Image life;
    // 关卡数
    public static Image level;
    static{
        into = new ImageIcon("resource/image/into.png").getImage();
        // 玩家1的坦克四个方位图
        tank1U = new ImageIcon("resource/image/p1tankU.gif").getImage();
        tank1D = new ImageIcon("resource/image/p1tankD.gif").getImage();
        tank1L = new ImageIcon("resource/image/p1tankL.gif").getImage();
        tank1R = new ImageIcon("resource/image/p1tankR.gif").getImage();
        // 玩家1的坦克四个方位图
        tank2U = new ImageIcon("resource/image/p2tankU.gif").getImage();
        tank2D = new ImageIcon("resource/image/p2tankD.gif").getImage();
        tank2L = new ImageIcon("resource/image/p2tankL.gif").getImage();
        tank2R = new ImageIcon("resource/image/p2tankR.gif").getImage();
        //   敌对坦克1的四个方位图
        enemy1U = new ImageIcon("resource/image/enemy1U.gif").getImage();
        enemy1D = new ImageIcon("resource/image/enemy1D.gif").getImage();
        enemy1L = new ImageIcon("resource/image/enemy1L.gif").getImage();
        enemy1R = new ImageIcon("resource/image/enemy1R.gif").getImage();
        //   敌对坦克2的四个方位图
        enemy2U = new ImageIcon("resource/image/enemy3U.gif").getImage();
        enemy2D = new ImageIcon("resource/image/enemy3D.gif").getImage();
        enemy2L = new ImageIcon("resource/image/enemy3L.gif").getImage();
        enemy2R = new ImageIcon("resource/image/enemy3R.gif").getImage();
        //   敌对坦克3的四个方位图
        enemy3U = new ImageIcon("resource/image/enemy2U.gif").getImage();
        enemy3D = new ImageIcon("resource/image/enemy2D.gif").getImage();
        enemy3L = new ImageIcon("resource/image/enemy2L.gif").getImage();
        enemy3R = new ImageIcon("resource/image/enemy2R.gif").getImage();
        //   敌对坦克4的四个方位图
        enemy4U = new ImageIcon("resource/image/enemy4U.png").getImage();
        enemy4D = new ImageIcon("resource/image/enemy4D.png").getImage();
        enemy4L = new ImageIcon("resource/image/enemy4L.png").getImage();
        enemy4R = new ImageIcon("resource/image/enemy4R.png").getImage();
        //   敌对坦克5的四个方位图
        enemy5U = new ImageIcon("resource/image/enemy5U.png").getImage();
        enemy5D = new ImageIcon("resource/image/enemy5D.png").getImage();
        enemy5L = new ImageIcon("resource/image/enemy5L.png").getImage();
        enemy5R = new ImageIcon("resource/image/enemy5R.png").getImage();
        //爆炸图片
        blast = new ImageIcon("resource/image/blast8.gif").getImage();
        // 营地图片
        symbol = new ImageIcon("resource/image/symbol.gif").getImage();
        destory = new ImageIcon("resource/image/destory.gif").getImage();
        // 墙体等图片
        wall = new ImageIcon("resource/image/walls.gif").getImage();
        water = new ImageIcon("resource/image/water.gif").getImage();
        steels = new ImageIcon("resource/image/steel.gif").getImage();
        grass = new ImageIcon("resource/image/grass.gif").getImage();
        // 开始与结背景图片
        menu_begin = new ImageIcon("resource/image/menu_begin.gif").getImage();
        menu_end = new ImageIcon("resource/image/menu_end.png").getImage();
        // 游戏背景图片
        bg_desert = new ImageIcon("resource/image/bg_desert.jpg").getImage();
        bg_green = new ImageIcon("resource/image/bg_green.jpg").getImage();
        bg_black = new ImageIcon("resource/image/bg_black.png").getImage();
        // 子弹照片
        bullet = new ImageIcon("resource/image/bullet.gif").getImage();
        enemy_bullet = new ImageIcon("resource/image/enemy_bullet.gif").getImage();
        // 道具图片
        blood = new ImageIcon("resource/image/blood.png").getImage();
        bomb = new ImageIcon("resource/image/bomb.gif").getImage();
        timer = new ImageIcon("resource/image/timer.gif").getImage();
        start = new ImageIcon("resource/image/star.gif").getImage();
        pistol = new ImageIcon("resource/image/pistol.png").getImage();
        // 血条
        bloodBar = new ImageIcon("resource/image/blood_bar.png").getImage();
        // 命数
        life = new ImageIcon("resource/image/life.png").getImage();
        // 关卡
        level = new ImageIcon("resource/image/level.png").getImage();
    }

    public static void changeHomeImage(){
        symbol = destory;
    }
    public static void homeImage(){
        symbol = new ImageIcon("resource/image/symbol.gif").getImage();
    }
}
