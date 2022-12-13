package view;

import commen.ImageData;
import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class CustomJPanel extends JPanel {
    //  单人与双人选择按钮
    public JRadioButton oneBtn;
    public JRadioButton twoBtn;
    //  正常游戏与自定义游戏选择按钮
    public JRadioButton normalBtn;
    public JRadioButton customBtn;
    // 自定义的其中选项下拉框
    public JComboBox levelJC; //关卡选择
    public JComboBox tankSpeed; //坦克速度
    public JComboBox bulletSpeed; // 子弹速度
    public JComboBox numTank; // 坦克数量
    public JComboBox numBlood; // 坦克血量
    //  确认与取消选择按钮
    public JButton jTrue;
    public JButton jFalse;

    public Controller con;

    public CustomJPanel(Controller con) {
        Font font = new Font("宋体", Font.BOLD, 15);
        this.con = con;
        setLayout(null);

        oneBtn = new JRadioButton("单人模式", null, true);
        oneBtn.setBounds(10, 20, 100, 20);
        twoBtn = new JRadioButton("双人模式");
        twoBtn.setBounds(120, 20, 100, 20);
        ButtonGroup btnGroup1 = new ButtonGroup();
        btnGroup1.add(oneBtn);
        btnGroup1.add(twoBtn);
        add(oneBtn);
        add(twoBtn);
        // 游戏正常或者自定义游戏
        normalBtn = new JRadioButton("正常游戏", null, true);
        normalBtn.addActionListener(con.ctmLis);
        normalBtn.setActionCommand("normal");
        normalBtn.setBounds(10, 50, 100, 20);
        customBtn = new JRadioButton("自定义游戏");
        customBtn.setBounds(120, 50, 100, 20);
        customBtn.addActionListener(con.ctmLis);
        customBtn.setActionCommand("custom");
        ButtonGroup btnGroup2 = new ButtonGroup();
        btnGroup2.add(normalBtn);
        btnGroup2.add(customBtn);
        add(normalBtn);
        add(customBtn);
        // 选择关卡
        JLabel lLabel = new JLabel("选择关卡：");
        lLabel.setBounds(50, 80, 100, 20);
        add(lLabel);
        int[] levelAry = {1, 2, 3, 4, 5, 6, 7, 8};
        levelJC = new JComboBox();
        levelJC.setBounds(120, 80, 100, 20);
        for (int i : levelAry) {
            levelJC.addItem(i);
        }
        add(levelJC);
        // 坦克速度
        JLabel tLabel = new JLabel("坦克速度：");
        tLabel.setBounds(50, 110, 100, 20);
        add(tLabel);
        String[] speed = {"正常", "慢速", "快速"};
        tankSpeed = new JComboBox();
        tankSpeed.setBounds(120, 110, 100, 20);
        for (String s : speed) {
            tankSpeed.addItem(s);
        }
        add(tankSpeed);
        // 子弹速度
        JLabel bLabel = new JLabel("子弹速度：");
        bLabel.setBounds(50, 140, 100, 20);
        add(bLabel);
        bulletSpeed = new JComboBox();
        bulletSpeed.setBounds(120, 140, 100, 20);
        for (String s : speed) {
            bulletSpeed.addItem(s);
        }
        add(bulletSpeed);
        // 坦克数量
        JLabel nLabel = new JLabel("坦克数量：");
        nLabel.setBounds(50, 170, 100, 20);
        add(nLabel);
        numTank = new JComboBox();
        numTank.setBounds(120, 170, 100, 20);
        for (int i = 10; i < 51; i += 10) {
            numTank.addItem(i);
        }
        add(numTank);
        // 坦克血量
        JLabel tbLabel = new JLabel("坦克血量");
        tbLabel.setBounds(50, 200, 100, 20);
        add(tbLabel);
        numBlood = new JComboBox();
        numBlood.setBounds(120, 200, 100, 20);
        for (int i = 1; i < 5; i ++) {
            numBlood.addItem(i);
        }
        add(numBlood);
        // 设置不可选
        levelJC.setEnabled(false);
        tankSpeed.setEnabled(false);
        bulletSpeed.setEnabled(false);
        numTank.setEnabled(false);
        numBlood.setEnabled(false);
        // 确认与取消按钮
        jTrue = new JButton("确认");
        jTrue.setFont(font);
        jTrue.addActionListener(con.ctmLis);
        jTrue.setActionCommand("true");
        jTrue.setBounds(10, 230, 100, 30);
        add(jTrue);
        jFalse = new JButton("取消");
        jFalse.setFont(font);
        jFalse.addActionListener(con.ctmLis);
        jFalse.setActionCommand("false");
        jFalse.setBounds(120, 230, 100, 30);
        add(jFalse);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageData.bg_desert, 0, 0, 250, 310, null);
    }
}
