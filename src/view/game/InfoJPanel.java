package view.game;

import commen.ImageData;
import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class InfoJPanel extends JPanel {
    public JLabel enemy1;
    public JLabel enemy2;
    public JLabel enemy3;
    public JLabel enemy4;
    public JLabel enemy5;
    public JLabel level;
    public JLabel playName;
    public JLabel p1Point;
    public JLabel p2Point;
    public JLabel allPoint;

    public Controller con;
    public InfoJPanel(Controller con) {
        this.con = con;
        Font font = new Font("华文琥珀",Font.BOLD,20);
        setLayout(null);
        setPreferredSize(new Dimension(200,600));
        setBackground(Color.white);

        JLabel label1 = new JLabel("敌方坦克还有：");
        label1.setFont(font);
        label1.setBounds(20,5,200,20);
        add(label1);
        // 坦克1
        enemy1 = new JLabel("×  "+con.tankMap[con.index][0]);
        enemy1.setFont(font);
        enemy1.setBounds(70,30,150,30);
        add(enemy1);
        // 坦克2
        enemy2 = new JLabel("×  " + con.tankMap[con.index][1]);
        enemy2.setFont(font);
        enemy2.setBounds(70, 70, 150, 30);
        add(enemy2);
        // 坦克3
        enemy3 = new JLabel("×  "+con.tankMap[con.index][2]);
        enemy3.setFont(font);
        enemy3.setBounds(70,110,150,30);
        add(enemy3);
        // 坦克4
        enemy4 = new JLabel("×  "+con.tankMap[con.index][3]);
        enemy4.setFont(font);
        enemy4.setBounds(70,150,150,30);
        add(enemy4);
        // 坦克5
        enemy5 = new JLabel("×  "+con.tankMap[con.index][4]);
        enemy5.setFont(font);
        enemy5.setBounds(70,190,150,30);
        add(enemy5);
        // 关卡
        level = new JLabel("第"+(con.index+1)+"关");
        level.setFont(font);
        level.setBounds(90,230,110,40);
        add(level);
        // 生命
        JLabel life1 = new JLabel("P1:");
        life1.setBounds(10,280,90,40);
        add(life1);
        // 名称
        playName = new JLabel("欢迎玩家："+con.player.name);
        playName.setFont(font);
        playName.setBounds(30,320,200,40);
        add(playName);
        // P1得分
        p1Point = new JLabel(String.valueOf(con.tankP1.point));
        p1Point.setFont(new Font("华文琥珀",Font.BOLD,15));
        p1Point.setBounds(35,380,60,40);
        add(p1Point);
        // P2得分
        if(con.tankP2 != null) {
            JLabel life2 = new JLabel("P2:");// 生命
            life2.setBounds(110,280,90,40);
            add(life2);
            p2Point = new JLabel(String.valueOf(con.tankP2.point));
            p2Point.setFont(new Font("华文琥珀", Font.BOLD, 15));
            p2Point.setBounds(135, 380, 60, 40);
            add(p2Point);
        }
        JLabel label2 = new JLabel("总积分:");
        label2.setFont(new Font("华文琥珀",Font.BOLD,30));
        label2.setBounds(45,420,150,40);
        add(label2);
        allPoint = new JLabel(String.valueOf(con.player.point));
        allPoint.setFont(font);
        allPoint.setBounds(50,480,150,30);
        add(allPoint);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageData.enemy1U,30,30,30,30,null);
        g.drawImage(ImageData.enemy2U,30,70,30,30,null);
        g.drawImage(ImageData.enemy3U,30,110,30,30,null);
        g.drawImage(ImageData.enemy5U,30,150,30,30,null);
        g.drawImage(ImageData.enemy4U,30,190,30,30,null);
        g.drawImage(ImageData.level,30,230,40,40,null);
        g.drawImage(ImageData.tank1U,0,380,30,30,null);
        for (int i = 0; i < con.tankP1.life; i++) {
            g.drawImage(ImageData.life,35+i*20,290,15,15,null);
        }
        if(con.tankP2 != null) {
            g.drawImage(ImageData.tank2U,100,380,30,30,null);
            for (int i = 0; i < con.tankP2.life; i++) {
                g.drawImage(ImageData.life, 135 + i * 20, 290, 15, 15, null);
            }
        }
    }
}
