package model;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverTimer {
    public Timer timer;
    public Controller con;
    public int[] enemyTankNum;// 击杀坦克的数量
    public int showTime;// 渲染时间

    public OverTimer( Controller  con) {
        this.timer = new Timer(5,act);
        this.con = con;
        enemyTankNum = new int[5];// 定义一个集合，用于接收打死的坦克类别与数量
        for (int i = 0; i < con.tankMap[con.index].length; i++) {// 获取当前关卡结束的剩余坦克数量
            enemyTankNum[i] = con.tankMap[con.index][i];
        }
        con.setTankMap();// 重新得到全部坦克数量
        for (int i = 0; i < con.tankMap[con.index].length; i++) {//  通过差值得到打死的坦克数量
            enemyTankNum[i] = con.tankMap[con.index][i] - enemyTankNum[i];
        }
//        System.out.println(enemyTankNum);
        showTime = -500;
    }

    public ActionListener act = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(showTime >= 0) {
                if (enemyTankNum[0]*100 >= showTime) {
                    con.overJFrame.overJPanel.tankWhite.setText(String.valueOf(showTime));
                }
                if (enemyTankNum[1]*200 >= showTime) {
                    con.overJFrame.overJPanel.tankGreen.setText(String.valueOf(showTime));
                }
                if (enemyTankNum[2]*200 >= showTime) {
                    con.overJFrame.overJPanel.tankYellow.setText(String.valueOf(showTime));
                }
                if (enemyTankNum[3]*300 >= showTime) {
                    con.overJFrame.overJPanel.tankBlue.setText(String.valueOf(showTime));
                }
                if (enemyTankNum[4]*500 >= showTime) {
                    con.overJFrame.overJPanel.tankRed.setText(String.valueOf(showTime));
                }
                if (con.tankP2 != null && con.tankP1.point + con.tankP2.point >= showTime) {
                    con.overJFrame.overJPanel.sumPoint.setText("总积分:  " + (con.player.point+showTime));
                }else if(con.tankP1.point >= showTime){
                    con.overJFrame.overJPanel.sumPoint.setText("总积分:  " + (con.player.point+showTime));
                }else {
                    showTime = -500;
                    timer.stop();
                }
            }
            showTime+=5;
        }
    };
}
