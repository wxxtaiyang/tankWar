package view;

import controller.Controller;
import view.game.BossJFrame;

public class Main {
    /*        界面拆分为20为一个单位
              子弹为10个像素单位
              游戏主界面可拆分为39*28的网格图
              即 x轴 0-38、y轴 0-27
     */
    /*
        判断AI坦克走动的位置，可根据下一个要到达的坐标与当前坐标的差值
        x坐标差为正，向右；为负，向左。
        y坐标差为正，向下；为负，向上。
    */
    public static void main(String[] args) {
        Controller con = new Controller();
    }
}
