package view;

import commen.ImageData;
import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class WelcomeJPanel extends JPanel {
    public JButton beginBtn;
    public JButton endBtn;
    public JButton bossBtn;

    public Controller con;
    public WelcomeJPanel(Controller con) {
        this.con = con;
        setSize(1000,600);
        setLayout(null);
        Font font = new Font("华文彩云",Font.BOLD,50);
        // 挑战BOSS按钮
        bossBtn = new JButton("挑战BOSS");
        bossBtn.setBounds(350,330,300,40);
        bossBtn.setForeground(new Color(255,255,255));
        bossBtn.setBorderPainted(false);// 去边框
        bossBtn.setContentAreaFilled(false);// 透明背景
        //  开始游戏按钮
        beginBtn = new JButton("开始游戏");
        beginBtn.setBounds(350,380,300,80);
        beginBtn.setForeground(new Color(255,255,255));
        beginBtn.setBorderPainted(false);// 去边框
        beginBtn.setContentAreaFilled(false);// 透明背景
        //   结束游戏按钮
        endBtn = new JButton("结束游戏");
        endBtn.setBounds(350,440,300,80);
        endBtn.setForeground(new Color(255,255,255));
        endBtn.setBorderPainted(false);// 去边框
        endBtn.setContentAreaFilled(false);// 透明背景
        // 设置字体
        bossBtn.setFont(new Font("华文彩云",Font.BOLD,30));
        beginBtn.setFont(font);
        endBtn.setFont(font);
        add(bossBtn);
        add(beginBtn);
        add(endBtn);
        //添加动作监听
        bossBtn.addActionListener(con.welActLis);
        bossBtn.setActionCommand("boss");
        beginBtn.addActionListener(con.welActLis);
        beginBtn.setActionCommand("begin");
        endBtn.addActionListener(con.welActLis);
        endBtn.setActionCommand("end");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageData.menu_begin,0,0,1000,600,null);
    }
}
