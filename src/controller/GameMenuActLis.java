package controller;


import view.CustomJFrame;
import view.game.GameJFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenuActLis implements ActionListener {
    private static final String EXPLAIN = "玩家1操作：w\\s\\a\\d:上\\下\\左\\右、j:开火。\n" +
            "玩家2操作：↑\\↓\\←\\→:上\\下\\左\\右、空格:开火。\n" +
            "按R开始和暂停游戏\n" +
            "按P关闭全部音效和打开全部音效\n" +
            "点击重新开始从当前关卡重新开始，坦克血量生命回到刚到达关卡的状态。\n" +
            "";


    public Controller con;

    public GameMenuActLis(Controller con) {
        this.con = con;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "explain":
                JOptionPane.showMessageDialog(null,
                        EXPLAIN,"游戏操作",JOptionPane.PLAIN_MESSAGE);
                break;
            case "about":
                JOptionPane.showMessageDialog(null,
                        "作者很懒啥都没写","关于",JOptionPane.PLAIN_MESSAGE);
                break;
            case "startGame":
                con.frameTimer.timer.start();
                con.gameJFrame.startGame.setEnabled(false);
                break;
            case "suspend":
                if(con.gameJFrame.suspend.getText().equals("暂停")){
                    con.frameTimer.timer.stop();
                    con.gameJFrame.suspend.setText("继续");
                }else{
                    con.frameTimer.timer.start();
                    con.gameJFrame.startGame.setEnabled(false);
                    con.gameJFrame.suspend.setText("暂停");
                }
                break;
            case "restart":
                int a = JOptionPane.showConfirmDialog(null,
                        "是否重新開始？","",JOptionPane.YES_NO_OPTION);
                if(a == 0) {
                    con.frameTimer.timer.stop();
                    con.gameJFrame.setVisible(false);
                    int index = con.index;
                    if(con.ctmLis.normalOrCustom){
                        con.ctmLis.setData();
                        con.map.setAllMap();
                        con.setTankP1();
                        con.bullets.clear();
                        con.enemyTanks.clear();
                        con.itemList.clear();
                        con.blastTank.clear();
                    }else {
                        con.dataRestore();
                    }
                    con.index = index;
                    con.tankP1.life = con.frameTimer.oldTank1.life;// P1生命重置
                    con.tankP1.blood = con.frameTimer.oldTank1.blood;// P1血条重置
                    if (con.tankP2 != null) {
                        con.tankP2.life = con.frameTimer.oldTank2.life;// P2生命重置
                        con.tankP2.blood = con.frameTimer.oldTank2.blood;// P2血条重置
                    }
                    con.gameJFrame = new GameJFrame(con);
                    con.gameJFrame.setVisible(true);
                    con.gameJFrame.startGame.setEnabled(false);// 自动开启，开启游戏不可用
                    con.frameTimer.timer.start();
                }
                break;
            case "exit":
                int ans = JOptionPane.showConfirmDialog(null,
                        "确定要退出？","",JOptionPane.YES_NO_OPTION);
                if(ans == 0) {
                    con.frameTimer.timer.stop();
                    con.ctmLis = new CustomAtcLis(con); // 初始化自定义界面的动作监听事件
                    con.ctmJFrame = new CustomJFrame(con);// 初始化自定义界面面板
                    con.dataRestore();
                    con.gameJFrame.setVisible(false);// 关闭原先的游戏界面
                    con.welJFrame.setVisible(true);
                    break;
                }
        }
    }

}
