package controller;

import model.Player;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeMenuActLis implements ActionListener,MenuListener {
    public Controller con;
    public WelcomeMenuActLis(Controller con) {
        this.con = con;
    }
    private static final String EXPLAIN = "玩家1操作：w\\s\\a\\d:上\\下\\左\\右、j:开火。\n" +
            "玩家2操作：↑\\↓\\←\\→:上\\下\\左\\右、回车:开火。\n" +
            "按R开始和暂停游戏\n" +
            "按P关闭全部音效和打开全部音效";
    public boolean allMusic = true; // 全部音效
    public boolean beginMusic = true;// 开始音效
    public boolean moveMusic = true;// 移动音效
    public boolean fireMusic = true;// 开火音效
    public boolean blastMusic = true;// 爆炸音效
    public boolean hitMusic = true;// 打中音效
    public boolean addMusic = true;// 道具音效

    // 菜单栏监听
    @Override
    public void actionPerformed(ActionEvent e) {
        // 修改提示的字体
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("宋体", Font.BOLD, 15)));
        switch (e.getActionCommand()){
            case "explain":
                JOptionPane.showMessageDialog(null,
                        EXPLAIN,"游戏操作",JOptionPane.PLAIN_MESSAGE);
                break;
            case "author":
                JOptionPane.showMessageDialog(null,
                        "作者很懒啥都没写","关于",JOptionPane.PLAIN_MESSAGE);
                break;
            case "allMusic":
                if(allMusic){
                    allMusic = false;
                    con.welJFrame.allMusic.setText("开启全部声音\tX");
                }else {
                    allMusic = true;
                    con.welJFrame.allMusic.setText("开启全部声音\t√");
                }
                break;
            case "beginMusic":
                if(beginMusic){
                    beginMusic = false;
                    con.welJFrame.gameMusic.setText("开始音效\tX");
                }else{
                    beginMusic = true;
                    con.welJFrame.gameMusic.setText("开始音效\t√");
                }
                break;
            case "runMusic":
                if (moveMusic){
                    moveMusic = false;
                    con.welJFrame.runMusic.setText("移动音效\tX");
                }else{
                    moveMusic = true;
                    con.welJFrame.runMusic.setText("移动音效\t√");
                }
                break;
            case "bulletMusic":
                if(fireMusic){
                    fireMusic = false;
                    con.welJFrame.bulletMusic.setText("开火音效\tX");
                }else{
                    fireMusic = true;
                    con.welJFrame.bulletMusic.setText("开火音效\t√");
                }
                break;
            case "blastMusic":
                if(blastMusic){
                    blastMusic = false;
                    con.welJFrame.blastMusic.setText("爆炸音效\tX");
                }else{
                    blastMusic = true;
                    con.welJFrame.blastMusic.setText("爆炸音效\t√");
                }
                break;
            case "hitMusic":
                if(hitMusic){
                    hitMusic = false;
                    con.welJFrame.hitMusic.setText("击打音效\tX");
                }else{
                    hitMusic = true;
                    con.welJFrame.hitMusic.setText("击打音效\t√");
                }
                break;
            case "propMusic":
                if(addMusic){
                    addMusic = false;
                    con.welJFrame.propMusic.setText("道具音效\tX");
                }else{
                    addMusic = true;
                    con.welJFrame.propMusic.setText("道具音效\t√");
                }
                break;
        }
    }

    // 菜单监听
    @Override
    public void menuSelected(MenuEvent e) {
        JMenu menu = (JMenu) e.getSource();
        if(menu.getText().equals("排行榜")){
            String text = "";
            con.ranking();
            for (Player p : con.playList) {
                text += p.name +"得分："+p.point +"\n";
            }
            JOptionPane.showMessageDialog(null,
                    text,"排行榜",JOptionPane.PLAIN_MESSAGE);
        }else {
            con.ctmJFrame.setVisible(true);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
