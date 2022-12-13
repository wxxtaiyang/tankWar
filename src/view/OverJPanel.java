package view;

import commen.ImageData;
import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class OverJPanel extends JPanel {
    public Controller con;

    public JLabel tankWhite;
    public JLabel tankYellow;
    public JLabel tankGreen;
    public JLabel tankRed;
    public JLabel tankBlue;

    public JLabel sumPoint;
    public JButton next;

    public OverJPanel(Controller con) {
        this.con = con;
        Font font = new Font("华文彩云",Font.BOLD,20);
        setLayout(null);

        // 各坦克得分情况
        // 白
        tankWhite = new JLabel("0");
        tankWhite.setFont(new Font("宋体",Font.BOLD,30));
        tankWhite.setForeground(Color.white);
        tankWhite.setBounds(230,320,200,40);
        add(tankWhite);
        // 黄
        tankYellow = new JLabel("0");
        tankYellow.setFont(new Font("宋体",Font.BOLD,30));
        tankYellow.setForeground(Color.white);
        tankYellow.setBounds(530,320,200,40);
        add(tankYellow);
        // 绿
        tankGreen = new JLabel("0");
        tankGreen.setFont(new Font("宋体",Font.BOLD,30));
        tankGreen.setForeground(Color.white);
        tankGreen.setBounds(830,320,200,40);
        add(tankGreen);
        // 蓝
        tankBlue = new JLabel("0");
        tankBlue.setFont(new Font("宋体",Font.BOLD,30));
        tankBlue.setForeground(Color.white);
        tankBlue.setBounds(380,370,200,40);
        add(tankBlue);
        // 红
        tankRed = new JLabel("0");
        tankRed.setFont(new Font("宋体",Font.BOLD,30));
        tankRed.setForeground(Color.white);
        tankRed.setBounds(680,370,200,40);
        add(tankRed);

        sumPoint = new JLabel("总积分:0");
        sumPoint.setFont(font);
        sumPoint.setForeground(Color.white);
        sumPoint.setBounds(400,420,200,20);
        add(sumPoint);
        next = new JButton("下一关");
        next.setFont(new Font("宋体",Font.BOLD,26));
        next.setBounds(425,460,150,40);
        add(next);
        next.addActionListener(con.overActLis);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageData.menu_end,0,0,1000,600,null);
        g.drawImage(ImageData.enemy1U,150,320,40,40,null);
        g.drawImage(ImageData.into,200,330,20,20,null);

        g.drawImage(ImageData.enemy3U,450,320,40,40,null);
        g.drawImage(ImageData.into,800,330,20,20,null);
        g.drawImage(ImageData.enemy2U,750,320,40,40,null);
        g.drawImage(ImageData.into,500,330,20,20,null);


        g.drawImage(ImageData.enemy5U,300,370,40,40,null);
        g.drawImage(ImageData.into,350,380,20,20,null);
        g.drawImage(ImageData.enemy4U,600,370,40,40,null);
        g.drawImage(ImageData.into,650,380,20,20,null);
    }
}
