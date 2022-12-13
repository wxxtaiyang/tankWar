package controller;

import commen.ImageData;
import model.Tank;
import view.CustomJFrame;
import view.game.GameJFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OverActLis implements ActionListener {
    public Controller con;

    public OverActLis(Controller con) {
        this.con = con;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "下一关":
                con.index++;
                con.player.point += con.tankP1.point;
                con.tankP1 = new Tank(con.frameTimer.oldTank1.life,con.frameTimer.oldTank1.blood,"N",2,ImageData.tank1U,con);
                con.tankP1.DrawRole(15,26);// 位置初始化
                if(con.tankP2 != null) {
                    con.player.point += con.tankP2.point;
                    con.tankP2 = new Tank(con.frameTimer.oldTank2.life, con.frameTimer.oldTank2.blood, "N", 2, ImageData.tank2U, con);
                    con.tankP2.DrawRole(22, 26);// 位置初始化
                }
                con.blastTank = new ArrayList<>();// 爆炸初始化
                con.itemList = new ArrayList<>();// 道具列表初始化
                con.bullets = new ArrayList<>();// 子弹列表初始化
                con.gameJFrame = new GameJFrame(con);
                con.overJFrame.setVisible(false);
                con.gameJFrame.setVisible(true);
                con.frameTimer.timer.start();
                con.gameJFrame.startGame.setEnabled(false);
                con.gameJFrame.repaint();
                break;
            case "结束游戏":
                con.overJFrame.setVisible(false);
                con.player.point += con.tankP1.point;
                if(con.tankP2 != null){
                    con.player.point += con.tankP2.point;
                }
                con.playList.add(con.player);
                con.overJFrame.setVisible(false);
                con.ctmLis = new CustomAtcLis(con); // 初始化自定义界面的动作监听事件
                con.ctmJFrame = new CustomJFrame(con);// 初始化自定义界面面板
                con.dataRestore();
                con.welJFrame.setVisible(true);
                break;
        }
    }
}
