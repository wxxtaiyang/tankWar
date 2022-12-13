package view.game;

import controller.Controller;

import javax.swing.*;

public class BossJFrame extends JFrame {
    public Controller con;
    public BossJPanel bossJPanel;

    public BossJFrame(Controller con) {
        this.con = con;
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(false);
        bossJPanel = new BossJPanel(con);

        addWindowListener(con.allWindowLis);
        addKeyListener(con.gameKeyLis);
        add(bossJPanel);


//        setVisible(true);
    }
}
