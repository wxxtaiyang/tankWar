package controller;

import commen.ImageData;
import model.EnemyTank;
import model.Tank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class CustomAtcLis extends WindowAdapter implements ActionListener {

    public Controller con;
    // 存储确定之后的值
    public boolean oneOrTwo;
    public boolean normalOrCustom;
    public int levelJC; //关卡选择
    public int tankSpeed; //坦克速度
    public int bulletSpeed; // 子弹速度
    public int numTank; // 坦克数量
    public int numBlood; // 坦克血量
//    public
    public CustomAtcLis(Controller con) {
        this.con = con;
        oneOrTwo = false;
        con.tankP2 = null;
        normalOrCustom = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {// 动作监听
        switch (e.getActionCommand()){
            case "normal":
                con.ctmJFrame.ctJPanel.levelJC.setEnabled(false);
                con.ctmJFrame.ctJPanel.tankSpeed.setEnabled(false);
                con.ctmJFrame.ctJPanel.bulletSpeed.setEnabled(false);
                con.ctmJFrame.ctJPanel.numTank.setEnabled(false);
                con.ctmJFrame.ctJPanel.numBlood.setEnabled(false);
                break;
            case "custom":
                con.ctmJFrame.ctJPanel.levelJC.setEnabled(true);
                con.ctmJFrame.ctJPanel.tankSpeed.setEnabled(true);
                con.ctmJFrame.ctJPanel.bulletSpeed.setEnabled(true);
                con.ctmJFrame.ctJPanel.numTank.setEnabled(true);
                con.ctmJFrame.ctJPanel.numBlood.setEnabled(true);
                break;
            case "true":
                // 自定义关卡选择确定后
                if(con.ctmJFrame.ctJPanel.customBtn.isSelected()){
                    normalOrCustom = true;
                    con.ctmJFrame.ctJPanel.customBtn.setSelected(true);
                    setData();
                }else {
                    normalOrCustom = false;
                    con.ctmJFrame.ctJPanel.normalBtn.setSelected(true);
                    con.index = 0;
                    con.insertEnemyTank();
                    con.enemyTanks = new ArrayList<>(10);
                    con.setTankMap();
                    con.blastTank = new ArrayList<>();
                    con.setTankP1();
                    con.bullets = new ArrayList<>(20);
                }
                // 单人或者双人模式确定后
                if(con.ctmJFrame.ctJPanel.oneBtn.isSelected()){
                    con.tankP2 = null;
                    oneOrTwo = false;
                }else{
                    con.setTankP2();
                    oneOrTwo = true;
                }
                con.ctmJFrame.setVisible(false);
                break;
            case "false":
                if(oneOrTwo){
                    con.ctmJFrame.ctJPanel.twoBtn.setSelected(true);
                }else{
                    con.ctmJFrame.ctJPanel.oneBtn.setSelected(true);
                }
                if(normalOrCustom){
                    con.ctmJFrame.ctJPanel.customBtn.setSelected(true);
                    con.ctmJFrame.ctJPanel.levelJC.setEnabled(true);
                    con.ctmJFrame.ctJPanel.tankSpeed.setEnabled(true);
                    con.ctmJFrame.ctJPanel.bulletSpeed.setEnabled(true);
                    con.ctmJFrame.ctJPanel.numTank.setEnabled(true);
                    con.ctmJFrame.ctJPanel.numBlood.setEnabled(true);
                    dataRestore();
                }else{
                    con.ctmJFrame.ctJPanel.normalBtn.setSelected(true);
                    con.ctmJFrame.ctJPanel.levelJC.setEnabled(false);
                    con.ctmJFrame.ctJPanel.tankSpeed.setEnabled(false);
                    con.ctmJFrame.ctJPanel.bulletSpeed.setEnabled(false);
                    con.ctmJFrame.ctJPanel.numTank.setEnabled(false);
                    con.ctmJFrame.ctJPanel.numBlood.setEnabled(false);
                }
                con.ctmJFrame.setVisible(false);
                break;
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {// 页面监听
        if(oneOrTwo){
            con.ctmJFrame.ctJPanel.twoBtn.setSelected(true);
        }else{
            con.ctmJFrame.ctJPanel.oneBtn.setSelected(true);
        }
        if(normalOrCustom){
            con.ctmJFrame.ctJPanel.customBtn.setSelected(true);
            con.ctmJFrame.ctJPanel.levelJC.setEnabled(true);
            con.ctmJFrame.ctJPanel.tankSpeed.setEnabled(true);
            con.ctmJFrame.ctJPanel.bulletSpeed.setEnabled(true);
            con.ctmJFrame.ctJPanel.numTank.setEnabled(true);
            con.ctmJFrame.ctJPanel.numBlood.setEnabled(true);
            dataRestore();
        }else{
            con.ctmJFrame.ctJPanel.normalBtn.setSelected(true);
            con.ctmJFrame.ctJPanel.levelJC.setEnabled(false);
            con.ctmJFrame.ctJPanel.tankSpeed.setEnabled(false);
            con.ctmJFrame.ctJPanel.bulletSpeed.setEnabled(false);
            con.ctmJFrame.ctJPanel.numTank.setEnabled(false);
            con.ctmJFrame.ctJPanel.numBlood.setEnabled(false);
        }
    }
//  数据复原；用于点击取消或者页面关闭时复原之前选择的数据
    public void dataRestore(){
        con.ctmJFrame.ctJPanel.levelJC.setSelectedIndex(levelJC); // 关卡选择
        con.ctmJFrame.ctJPanel.numTank.setSelectedIndex((numTank/10-1)); //坦克数量
        switch (tankSpeed){
            case 1:
                con.ctmJFrame.ctJPanel.tankSpeed.setSelectedIndex(1);
                break;
            case 2:
                con.ctmJFrame.ctJPanel.tankSpeed.setSelectedIndex(0);
                break;
            case 4:
                con.ctmJFrame.ctJPanel.tankSpeed.setSelectedIndex(2);
                break;
        } // 坦克速度
        switch (bulletSpeed){
            case 4:
                con.ctmJFrame.ctJPanel.bulletSpeed.setSelectedIndex(1);
                break;
            case 2:
                con.ctmJFrame.ctJPanel.bulletSpeed.setSelectedIndex(0);
                break;
            case 8:
                con.ctmJFrame.ctJPanel.bulletSpeed.setSelectedIndex(2);
                break;
        } // 子弹速度
        con.ctmJFrame.ctJPanel.numBlood.setSelectedIndex(numBlood-1);// 坦克血量
    }
//    数据输入,在点击完确定之后执行
    public void setData(){
        levelJC = con.ctmJFrame.ctJPanel.levelJC.getSelectedIndex();// 关卡选择
        numTank = 10*(con.ctmJFrame.ctJPanel.numTank.getSelectedIndex()+1);// 坦克数量选择
        switch (con.ctmJFrame.ctJPanel.tankSpeed.getSelectedIndex()) {// 坦克速度
            case 0:
                tankSpeed = 2;
                break;
            case 1:
                tankSpeed = 1;
                break;
            case 2:
                tankSpeed = 4;
        }
        switch (con.ctmJFrame.ctJPanel.bulletSpeed.getSelectedIndex()) {// 子弹速度
            case 0:
                bulletSpeed = 4;
                break;
            case 1:
                bulletSpeed = 2;
                break;
            case 2:
                bulletSpeed = 8;
        }
        numBlood = con.ctmJFrame.ctJPanel.numBlood.getSelectedIndex()+1;
        con.index = levelJC;
        con.tankLevel[con.index] = new ArrayList<>();
        for (int i = 0; i < numTank; i++) {
            EnemyTank tank = new EnemyTank(numBlood,numBlood,"S",tankSpeed,-6, ImageData.enemy4D,con);
            tank.bSpeed = bulletSpeed;
            con.tankLevel[con.index].add(tank);
        }
        con.tankMap[con.index] =new int[]{0,0,0,0,numTank};
    }
}
