package controller;

import commen.DataBoss;
import commen.ImageData;
import commen.MusicData;
import model.*;
import model.Map;
import view.CustomJFrame;
import view.OverJFrame;
import view.WelcomeJFrame;
import view.game.BossJFrame;
import view.game.GameJFrame;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    public List<Player> playList;
    public Player player;// 玩家,用于记录玩家名称与积分
    public Map map;// 地图
    public int index;// 关卡数
    public Tank tankP1;// P1玩家的坦克
    public Tank tankP2;// P2玩家的坦克
    public int[][] tankMap;// 关卡对应各类坦克数量
    public ArrayList<EnemyTank>[] tankLevel;// 敌方坦克数组
    public ArrayList<EnemyTank> enemyTanks;// 当前十辆坦克数组
    public ArrayList<Bullet> bullets;// 子弹列表
    public ArrayList<Role> blastTank;// 已经爆炸的坦克渲染
    public boolean isOver;// 是否结束游戏
    public ArrayList<Item> itemList;// 道具集合

    public BossEnemy boss;// 关卡boss

    public AllWindowLis allWindowLis; // 所有界面窗口监听
    public WelcomeMenuActLis welMenuActLis; // 登录界面菜单栏动作监听类
    public CustomAtcLis ctmLis;// 自定义面板动作监听类
    public WelcomeActLis welActLis; // 开始界面监听类
    public GameMenuActLis gMenuActLis;// 游戏菜单监听
    public OverActLis overActLis;// 结算界面监听器

    public GameKeyLis gameKeyLis;//游戏键盘监听
    public FrameTimer frameTimer;// 界面刷新定时器
    public BossTimer bossTimer;// boss的定时器

    public CustomJFrame ctmJFrame;// 自定义页面
    public WelcomeJFrame welJFrame;// 登录与欢迎页面

    public GameJFrame gameJFrame;// 游戏面板
    public OverJFrame overJFrame;// 结束界面
    public OverTimer overTimer;// 结束界面动画定时器
    // BOSS界面渲染
    public BossJFrame bossJFrame;

    public Controller() {
        playList = new ArrayList<Player>();
        playList.add(new Player("小明",6666));
        playList.add(new Player("小黄",9777));
        playList.add(new Player("小黑",8888));
        player = new Player();

        dataRestore();// 所需信息初始化

        boss = new BossEnemy(this,0,0,100,120,DataBoss.BOSS_JUMP3);
//        boss.blood = 0;
        // 监听器初始化
        allWindowLis = new AllWindowLis();
        welMenuActLis = new WelcomeMenuActLis(this);
        ctmLis = new CustomAtcLis(this);
        welActLis = new WelcomeActLis(this);
        gMenuActLis = new GameMenuActLis(this);
        gameKeyLis = new GameKeyLis(this);
        overActLis = new OverActLis(this);

        // 定时器初始化
        frameTimer = new FrameTimer(this);// 页面刷新定时器

        // 页面初始化
        welJFrame = new WelcomeJFrame(this);
        ctmJFrame = new CustomJFrame(this);



    }
//    排行榜实现，返回集合
    public List<Player> ranking(){
        playList = playList.stream().sorted(Comparator.comparing(Player::getPoint).reversed())
                .collect(Collectors.toList());
        return playList;
    }
    // 坦克刷新
    public void setTankP1() {
        tankP1 = new Tank(3,3,"N",2,ImageData.tank1U,this);
        tankP1.DrawRole(15,26);
    }
    public void setTankP2() {
        this.tankP2 = new Tank(3,3,"N",2,ImageData.tank2U,this);
        tankP2.DrawRole(22,26);
    }
    // 八个关卡各坦克实例化
    public void insertEnemyTank(){
        tankLevel = new ArrayList[8];
        ArrayList<EnemyTank> l1 = new ArrayList<>(20);
        ArrayList<EnemyTank> l2 = new ArrayList<>(20);
        ArrayList<EnemyTank> l3 = new ArrayList<>(20);
        ArrayList<EnemyTank> l4 = new ArrayList<>(20);
        ArrayList<EnemyTank> l5 = new ArrayList<>(20);
        ArrayList<EnemyTank> l6 = new ArrayList<>(20);
        ArrayList<EnemyTank> l7 = new ArrayList<>(20);
        ArrayList<EnemyTank> l8 = new ArrayList<>(20);
        for (int i = 0; i < 10; i++) {
            l1.add(new EnemyTank(1,1,"S",1,-1,ImageData.enemy1D,this));
            l8.add(new EnemyTank(3,3,"S",2,-5,ImageData.enemy4D,this));
        }
        for (int i = 0; i < 8; i++) {
            l2.add(new EnemyTank(1,1,"S",1,-1,ImageData.enemy1D,this));
            l7.add(new EnemyTank(2,2,"S",2,-4,ImageData.enemy5D,this));
        }
        for (int i = 0; i < 7; i++) {
            l1.add(new EnemyTank(1,1,"S",2,-2,ImageData.enemy2D,this));
            l7.add(new EnemyTank(3,3,"S",2,-5,ImageData.enemy4D,this));
        }
        for (int i = 0; i < 6; i++) {
            l2.add(new EnemyTank(1,1,"S",2,-2,ImageData.enemy2D,this));
            l3.add(new EnemyTank(1,1,"S",1,-1,ImageData.enemy1D,this));
            l3.add(new EnemyTank(2,2,"S",1,-3,ImageData.enemy3D,this));
            l4.add(new EnemyTank(2,2,"S",1,-3,ImageData.enemy3D,this));
            l5.add(new EnemyTank(2,2,"S",2,-4,ImageData.enemy5D,this));
            l6.add(new EnemyTank(3,3,"S",2,-5,ImageData.enemy4D,this));
            l8.add(new EnemyTank(2,2,"S",2,-4,ImageData.enemy5D,this));
        }
        for (int i = 0; i < 5; i++) {
            l3.add(new EnemyTank(1,1,"S",2,-2,ImageData.enemy2D,this));
            l5.add(new EnemyTank(2,2,"S",1,-3,ImageData.enemy3D,this));
        }
        for (int i = 0; i < 4; i++) {
            l2.add(new EnemyTank(2,2,"S",1,-3,ImageData.enemy3D,this));
            l4.add(new EnemyTank(1,1,"S",1,-1,ImageData.enemy1D,this));
            l4.add(new EnemyTank(1,1,"S",2,-2,ImageData.enemy2D,this));
            l4.add(new EnemyTank(2,2,"S",2,-4,ImageData.enemy5D,this));
            l5.add(new EnemyTank(3,3,"S",2,-5,ImageData.enemy4D,this));
            l6.add(new EnemyTank(2,2,"S",1,-3,ImageData.enemy3D,this));
            l7.add(new EnemyTank(2,2,"S",1,-3,ImageData.enemy3D,this));
            l8.add(new EnemyTank(2,2,"S",1,-3,ImageData.enemy3D,this));
        }
        for (int i = 0; i < 3; i++) {
            l5.add(new EnemyTank(1,1,"S",2,-2,ImageData.enemy2D,this));
        }
        for (int i = 0; i < 2; i++) {
            l1.add(new EnemyTank(2,2,"S",1,-3,ImageData.enemy3D,this));
            l2.add(new EnemyTank(2,2,"S",2,-4,ImageData.enemy5D,this));
            l3.add(new EnemyTank(2,2,"S",2,-4,ImageData.enemy5D,this));
            l4.add(new EnemyTank(3,3,"S",2,-5,ImageData.enemy4D,this));
            l5.add(new EnemyTank(1,1,"S",1,-1,ImageData.enemy1D,this));
            l6.add(new EnemyTank(1,1,"S",2,-2,ImageData.enemy2D,this));
        }
        l1.add(new EnemyTank(2,2,"S",2,-4,ImageData.enemy5D,this));
        l3.add(new EnemyTank(3,3,"S",2,-5,ImageData.enemy4D,this));
        l6.add(new EnemyTank(1,1,"S",1,-1,ImageData.enemy1D,this));
        l7.add(new EnemyTank(1,1,"S",2,-2,ImageData.enemy2D,this));
        tankLevel[0] = l1;
        tankLevel[1] = l2;
        tankLevel[2] = l3;
        tankLevel[3] = l4;
        tankLevel[4] = l5;
        tankLevel[5] = l6;
        tankLevel[6] = l7;
        tankLevel[7] = l8;
    }
    // 对应敌方坦克信息初始化
    public void setTankMap(){
        tankMap = new int[][]{{10,7,2,1,0}, {8,6,4,2,0},{6,5,6,2,1},{4,4,6,4,2}
                ,{2,3,5,6,4},{1,2,4,7,6},{0,1,4,8,7},{0,0,4,6,10}};
    }
    // 数据重新渲染
    public void dataRestore(){
        map = new Map();
        index = 0;
        insertEnemyTank();
        enemyTanks = new ArrayList<>(10);
        setTankMap();
        blastTank = new ArrayList<>();
        setTankP1();
        bullets = new ArrayList<>(20);
        isOver = false;
        ImageData.homeImage();
        if(tankP2 != null){
            setTankP2();
        }
        itemList = new ArrayList<>();
    }
}
