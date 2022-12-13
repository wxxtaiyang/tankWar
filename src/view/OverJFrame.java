package view;

import controller.AllWindowLis;
import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class OverJFrame extends JFrame {
    public OverJPanel overJPanel;

    public Controller con;

    public OverJFrame(Controller con){
        this.con = con;
        setSize(1000,600); // 窗口大小
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// 设置啥都不干
        setLocationRelativeTo(null); // 居中
        setTitle("坦克大战"); // 标题
        setResizable(false); // 无法拉伸

        addWindowListener(con.allWindowLis);

        overJPanel = new OverJPanel(con);
        add(overJPanel);

//        setVisible(true);
    }

}
