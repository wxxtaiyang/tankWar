package view;

import controller.AllWindowLis;
import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class WelcomeJFrame extends JFrame {
    public JMenuItem allMusic;
    public JMenuItem gameMusic;
    public JMenuItem runMusic;
    public JMenuItem bulletMusic;
    public JMenuItem blastMusic;
    public JMenuItem hitMusic;
    public JMenuItem propMusic;
    public JMenuItem explainItem;// 说明
    public JMenuItem authorItem; // 关于作者


    public WelcomeJPanel welJPanel;
    public Controller con;
    public WelcomeJFrame(Controller con) {
        this.con = con;
        setSize(1000,600); // 窗口大小
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// 设置啥都不干
        setLocationRelativeTo(null); // 居中
//        setLayout(null);// 自由布局
        setTitle("坦克大战"); // 标题
        setResizable(false); // 无法拉伸
        //菜单栏添加
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu custom = new JMenu("自定义面板");
        JMenu fit = new JMenu("设置");
        JMenu ranking = new JMenu("排行榜");
        JMenu help = new JMenu("帮助");
        menuBar.add(custom);
        menuBar.add(fit);
        menuBar.add(ranking);
        menuBar.add(help);
        // 菜单注册监听
        custom.addMenuListener(con.welMenuActLis);
        custom.setActionCommand("custom");
        ranking.addMenuListener(con.welMenuActLis);

        ranking.setActionCommand("ranking");
        // 设置菜单
        // 全部音效设置
        allMusic = new JMenuItem("开启全部声音\t√");
        allMusic.setActionCommand("allMusic");
        allMusic.addActionListener(con.welMenuActLis);
        // 开始音效设置
        gameMusic = new JMenuItem("开始音效\t√");
        gameMusic.setActionCommand("beginMusic");
        gameMusic.addActionListener(con.welMenuActLis);
        // 移动音效设置
        runMusic = new JMenuItem("移动音效\t√");
        runMusic.setActionCommand("runMusic");
        runMusic.addActionListener(con.welMenuActLis);
        // 开火音效设置
        bulletMusic = new JMenuItem("开火音效\t√");
        bulletMusic.setActionCommand("bulletMusic");
        bulletMusic.addActionListener(con.welMenuActLis);
        // 爆炸音效设置
        blastMusic = new JMenuItem("爆炸音效\t√");
        blastMusic.setActionCommand("blastMusic");
        blastMusic.addActionListener(con.welMenuActLis);
        // 击打音效设置
        hitMusic = new JMenuItem("击打音效\t√");
        hitMusic.setActionCommand("hitMusic");
        hitMusic.addActionListener(con.welMenuActLis);
        // 道具音效设置
        propMusic = new JMenuItem("道具音效\t√");
        propMusic.setActionCommand("propMusic");
        propMusic.addActionListener(con.welMenuActLis);
        // 将控件添加进菜单
        fit.add(allMusic);
        fit.add(gameMusic);
        fit.add(runMusic);
        fit.add(bulletMusic);
        fit.add(blastMusic);
        fit.add(hitMusic);
        fit.add(propMusic);
        // 帮助菜单
        explainItem = new JMenuItem("游戏说明");
        authorItem = new JMenuItem("作者介绍");
        help.add(explainItem);
        help.add(authorItem);
        // 添加菜单栏监听
        explainItem.addActionListener(con.welMenuActLis);
        explainItem.setActionCommand("explain");
        authorItem.addActionListener(con.welMenuActLis);
        authorItem.setActionCommand("author");
        // 窗口监听
        addWindowListener(con.allWindowLis);
        welJPanel = new WelcomeJPanel(con);
        add(welJPanel);

        setVisible(true);
    }

}
