package model;

import commen.ImageData;
import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class Item extends Role {
    public String ItemName;// 道具名称
    public int type;// 道具类别
    public int dieTime;// 道具消失时间
    public Controller con;
    public Image blackImg;// 背景黑色图片
    public Image itemImg;// 接收的图片
    public int changeTime;// 图片切换时间

    public Item(String ItemName, int type, Image image, int dieTime, Controller con) {
        this.ItemName = ItemName;
        this.type = type;
        this.image = image;
        this.itemImg = image;
        this.blackImg = new ImageIcon("resource/image/nothing.png").getImage();
        this.dieTime = dieTime;
        this.con = con;
    }
    // 放置道具的碰撞检测
    public boolean setItem(int x, int y) {
        Rectangle rec = new Rectangle(x,y,40,40);
        for (int i = 0; i < con.map.gameMap[con.index].length; i++) {
            for (int j = 0; j < con.map.gameMap[con.index][i].length; j++) {
                if(con.map.gameMap[con.index][i][j] == 1 || con.map.gameMap[con.index][i][j] == 2
                    || con.map.gameMap[con.index][i][j] == 4) {
                    Rectangle mapRec = new Rectangle(i * 20, j * 20, 20, 20);
                    if (rec.intersects(mapRec))
                        return false;
                }
            }
        }
        for (int i = 0; i < con.itemList.size(); i++) {
            Rectangle itemRec = new Rectangle(con.itemList.get(i).pointX,con.itemList.get(i).pointY,40,40);
            if(rec.intersects(itemRec))
                return false;
        }
        return true;
    }
    // 图片切换
    public void changeImage(){
        changeTime++;
        if(blackImg != null && changeTime == 5){
            this.blackImg = null;
            this.image = itemImg;
            changeTime = 0;
        }else{
            this.blackImg = new ImageIcon("resource/image/nothing.png").getImage();
            this.image = blackImg;
        }
    }
}
