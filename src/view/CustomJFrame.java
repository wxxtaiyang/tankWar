package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class CustomJFrame extends JFrame {

    public CustomJPanel ctJPanel;

    public Controller con;
    public CustomJFrame(Controller con) {
        this.con = con;
        Point point = con.welJFrame.getLocation();
        setBounds(point.x-240,point.y,250,310);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ctJPanel = new CustomJPanel(con); // 创建中间容器
        add(ctJPanel);
        addWindowListener(con.ctmLis);// 添加窗口监听
//        setVisible(true);
    }



}
