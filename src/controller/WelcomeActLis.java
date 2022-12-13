package controller;

import commen.DataBoss;
import model.BossEnemy;
import model.BossTimer;
import model.Player;
import view.OverJFrame;
import view.game.BossJFrame;
import view.game.GameJFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeActLis implements ActionListener {
    public Controller con;

    public WelcomeActLis(Controller con) {
        this.con = con;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "boss":
                con.index = 8;
                con.boss = new BossEnemy(con,0,0,100,120, DataBoss.BOSS_JUMP3);
                con.bossJFrame = new BossJFrame(con);
                con.bossTimer = new BossTimer(con);// boss定时器
                con.welJFrame.setVisible(false);
                con.bossJFrame.setVisible(true);
                con.bossTimer.timer.start();
                break;
            case "begin":
                String inputValue = JOptionPane.showInputDialog("请输入名称");
                if(inputValue == null){
                    return;
                }
                if("".equals(inputValue)){
                    JOptionPane.showMessageDialog(null,
                            "用户名不能为空！！！","错误信息",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                con.player = new Player(inputValue,0);
                con.ctmJFrame.setVisible(false);
                con.welJFrame.setVisible(false);
                con.gameJFrame = new GameJFrame(con);
                con.overJFrame = new OverJFrame(con);
                con.gameJFrame.setVisible(true);
                break;
            case "end":
                int ans = JOptionPane.showConfirmDialog(null,
                        "确定要退出？","",JOptionPane.YES_NO_OPTION);
                if(ans == 0){
                    System.exit(0);
                }
                break;

        }
    }
}
