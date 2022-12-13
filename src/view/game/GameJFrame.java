package view.game;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class GameJFrame extends JFrame {
    public InfoJPanel infoJPanel; // 右侧面板信息
    public GameJPanel gameJPanel; // 主要游戏界面

    public JMenuItem startGame; // 开始按钮
    public JMenuItem restart; // 重新开始按钮
    public JMenuItem suspend; // 暂停按钮

    public Controller con;
    public GameJFrame(Controller con){
        this.con = con;
//        con.frameTimer.timer.start();// 启动页面刷新定时器

        setLayout(new BorderLayout());// 设置边界布局
        setSize(1000,625);
        setTitle("坦克大战--------按R-开始/暂停-游戏-----------------姓名：吴子南    学号：JX220914     班级：JX2209");
        setResizable(false);// 不可更改大小
        setLocationRelativeTo(null);// 居中
        infoJPanel = new InfoJPanel(con);
        add(infoJPanel,BorderLayout.EAST);// 信息面板右侧显示
        gameJPanel = new GameJPanel(con);
        add(gameJPanel);
        // 添加窗口监听
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(con.allWindowLis);
        // 添加菜单栏
        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(1000,20));
        setJMenuBar(menuBar);
        JMenu menuGame = new JMenu("游戏");
        JMenu menuHelp = new JMenu("帮助");
        menuBar.add(menuGame);
        menuBar.add(menuHelp);
        // 游戏菜单项
        startGame = new JMenuItem("开始游戏");
        startGame.addActionListener(con.gMenuActLis);
        startGame.setActionCommand("startGame");// 开始--添加动作监听
        restart = new JMenuItem("重新开始");
        restart.addActionListener(con.gMenuActLis);
        restart.setActionCommand("restart");// 重新开始--添加动作监听
        suspend = new JMenuItem("暂停");
        suspend.addActionListener(con.gMenuActLis);
        suspend.setActionCommand("suspend");// 暂停--添加动作监听
        JMenuItem exit = new JMenuItem("退出 ");
        exit.addActionListener(con.gMenuActLis);
        exit.setActionCommand("exit");
        menuGame.add(startGame);
        menuGame.add(restart);
        menuGame.add(suspend);
        menuGame.add(exit);
        // 帮助菜单项
        JMenuItem gameExplain = new JMenuItem("游戏说明");
        gameExplain.addActionListener(con.gMenuActLis);
        gameExplain.setActionCommand("explain");
        JMenuItem about = new JMenuItem("关于");
        about.addActionListener(con.gMenuActLis);
        about.setActionCommand("about");
        menuHelp.add(gameExplain);
        menuHelp.add(about);

        addKeyListener(con.gameKeyLis);

    }
}
