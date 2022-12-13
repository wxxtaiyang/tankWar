package model;

public class Map {
    public int gameMap[][][];

    public Map() {
        this.gameMap =new int[9][39][28];
        setAllMap();
    }
    //第一关地图
    public void setOneMap(){
        // 老家方格
        for (int i = 18; i < 21; i++) {
            gameMap[0][i][25] = 1;
        }
        for (int i = 27; i > 24 ; i--) {
            gameMap[0][17][i] = 1;
            gameMap[0][21][i] = 1;
        }
        //土墙绘制
        for (int a = 4; a < 39; a+=6) {
            for (int i = a; i < a+2; i++) {
                for (int j = 4; j < 15; j++) {
                    gameMap[0][i][j] = 1;
                }
            }
        }
        for (int a = 2; a < 39; a+=24) {
            for (int i = a; i < a+11; i++) {
                for (int j = 17; j < 19; j++) {
                    gameMap[0][i][j] = 1;
                }
            }
        }
        // 铁墙
        for (int i = 18; i < 22; i++) {
            for (int j = 9; j < 13; j++) {
                gameMap[0][i][j] = 2;
            }
        }
        // 树林
        for (int a = 6; a < 39; a+=24) {
            for (int i = a; i < a+4; i++) {
                for (int j = 6; j < 10; j++) {
                    gameMap[0][i][j] = 3;
                }
            }
        }
        // 水
        for (int i = 11; i < 27; i++) {
            for (int j = 20; j < 22; j++) {
                gameMap[0][i][j] = 4;
            }
        }
    }
    // 第二关地图
    public void setTwoMap(){
        // 老家方格
        for (int i = 18; i < 21; i++) {
            gameMap[1][i][25] = 1;
        }
        for (int i = 27; i > 24 ; i--) {
            gameMap[1][17][i] = 1;
            gameMap[1][21][i] = 1;
        }
        //土墙绘制
        for (int i = 3; i < 10; i++) {
            for (int j = 3; j < 5; j++) {
                gameMap[1][i][j] = 1;
            }
        }
        for (int i = 10; i < 12; i++) {
            for (int j = 3; j < 11; j++) {
                gameMap[1][i][j] = 1;
            }
        }
        for (int i = 38-3; i > 38-10; i--) {
            for (int j = 3; j < 5; j++) {
                gameMap[1][i][j] = 1;
            }
        }
        for (int i = 38-10; i > 38-12; i--) {
            for (int j = 3; j < 11; j++) {
                gameMap[1][i][j] = 1;
            }
        }
        for (int i = 18; i < 21; i+=2) {
            for (int j = 3; j < 9; j++) {
                gameMap[1][i][j] = 1;
            }
        }
        for (int i = 14; i < 25; i++) {
            for (int j = 9; j < 11; j++) {
                gameMap[1][i][j] = 1;
            }
        }
        // 铁墙
        for (int j = 3; j < 9; j++) {
            gameMap[1][19][j] = 2;
        }
        for (int i = 0; i < 39; i+=4) {
            for (int j = 15; j < 17; j++) {
                gameMap[1][i][j] = 2;
                gameMap[1][i+1][j] = 2;
            }
        }
        // 树林
        for (int i = 0; i < 39; i+=4) {
            for (int j = 17; j < 19; j++) {
                gameMap[1][i][j] = 3;
                gameMap[1][i+1][j] = 3;
            }
        }
        // 水
        for (int i = 11; i < 27; i++) {
            for (int j = 20; j < 22; j++) {
                gameMap[1][i][j] = 4;
            }
        }
    }
    // 第三关地图
    public void setThreeMap(){
        // 老家方格
        for (int i = 18; i < 21; i++) {
            gameMap[2][i][25] = 1;
        }
        for (int i = 27; i > 24 ; i--) {
            gameMap[2][17][i] = 1;
            gameMap[2][21][i] = 1;
        }
        //土墙绘制
        for (int i = 0; i < 39; i+=4) {
            for (int j = 3; j < 25; j+=4) {
                gameMap[2][i][j] = 1;
                gameMap[2][i+1][j] = 1;
                gameMap[2][i][j+1] = 1;
                gameMap[2][i+1][j+1] = 1;
            }
        }
        // 铁墙
        for (int i = 2; i < 37; i+=4) {
            for (int j = 5; j < 23; j+=4) {
                gameMap[2][i][j] = 2;
                gameMap[2][i+1][j] = 2;
                gameMap[2][i][j+1] = 2;
                gameMap[2][i+1][j+1] = 2;
            }
        }
        // 树林
        for (int a = 6; a < 39; a+=24) {
            for (int i = a; i < a+4; i++) {
                for (int j = 6; j < 10; j++) {
                    gameMap[2][i][j] = 3;
                }
            }
        }
        // 水
        for (int i = 11; i < 27; i++) {
            for (int j = 20; j < 22; j++) {
                gameMap[2][i][j] = 4;
            }
        }
    }
    // 第四关地图
    public void setFourMap(){
        // 老家方格
        for (int i = 18; i < 21; i++) {
            gameMap[3][i][25] = 1;
        }
        for (int i = 27; i > 24 ; i--) {
            gameMap[3][17][i] = 1;
            gameMap[3][21][i] = 1;
        }
        //土墙绘制
        for (int i = 2; i < 37; i+=4) {
            for (int j = 5; j < 23; j+=4) {
                gameMap[3][i][j] = 1;
                gameMap[3][i+1][j] = 1;
                gameMap[3][i][j+1] = 1;
                gameMap[3][i+1][j+1] = 1;
            }
        }
        // 铁墙
        for (int i = 0; i < 39; i+=4) {
            for (int j = 3; j < 25; j+=4) {
                gameMap[3][i][j] = 2;
                gameMap[3][i+1][j] = 2;
                gameMap[3][i][j+1] = 2;
                gameMap[3][i+1][j+1] = 2;
            }
        }
        // 树林
        for (int a = 6; a < 39; a+=24) {
            for (int i = a; i < a+4; i++) {
                for (int j = 6; j < 10; j++) {
                    gameMap[3][i][j] = 3;
                }
            }
        }
        // 水
        for (int i = 11; i < 27; i++) {
            for (int j = 20; j < 22; j++) {
                gameMap[3][i][j] = 4;
            }
        }
    }
    // 第五关地图
    public void setFiveMap(){
        // 老家方格
        for (int i = 18; i < 21; i++) {
            gameMap[4][i][25] = 1;
        }
        for (int i = 27; i > 24 ; i--) {
            gameMap[4][17][i] = 1;
            gameMap[4][21][i] = 1;
        }
        // 土墙绘制
        for (int i = 4; i < 16; i++) {
            for (int j = 4; j < 16; j++) {
                gameMap[4][i][j] = 1;
                gameMap[4][38-i][j] = 1;
            }
            for (int j = 6; j < 14; j++) {
                if(i > 5 && i < 14){
                    gameMap[4][38-i][j] = 0;
                    gameMap[4][i][j] = 0;
                }
            }
        }
        // 铁墙绘制
        for (int i = 6; i < 14; i++) {
            for (int j = 9; j < 11; j++) {
                gameMap[4][i][j] = 2;
                gameMap[4][38-i][j] = 2;
            }
        }
        // 树林绘制
        for (int i = 16; i < 23; i++) {
            for (int j = 8; j < 13; j++) {
                gameMap[4][i][j] = 3;
            }
        }
        // 河流绘制
        for (int i = 3; i < 17; i++) {
            for (int j = 19; j < 21; j++) {
                gameMap[4][i][j] = 4;
                gameMap[4][38-i][j] = 4;
            }
        }
    }
    // 第六关地图
    public void setSixMap(){
        // 老家方格
        for (int i = 18; i < 21; i++) {
            gameMap[5][i][25] = 1;
        }
        for (int i = 27; i > 24 ; i--) {
            gameMap[5][17][i] = 1;
            gameMap[5][21][i] = 1;
        }
        // 土墙绘制
        for (int i = 0; i < 39; i++) {
            for (int j = 4; j < 24; j++) {
                gameMap[5][i][j] = 1;
            }
        }
        // 铁墙
        for (int i = 2; i < 18; i++) {
            for (int j = 6; j < 13; j++) {
                gameMap[5][i][j] = 2;
                gameMap[5][38-i][27-j] = 2;
            }
        }
        //  树林
        for (int i = 21; i < 37; i++) {
            for (int j = 6; j < 13; j++) {
                gameMap[5][i][j] = 3;
            }
        }
        //  水
        for (int i = 2; i < 18; i++) {
            for (int j = 6; j < 13; j++) {
                gameMap[5][i][27-j] = 4;
            }
        }
    }
    // 第七关地图
    public void setSevenMap(){
        // 老家方格
        for (int i = 18; i < 21; i++) {
            gameMap[6][i][25] = 1;
        }
        for (int i = 27; i > 24 ; i--) {
            gameMap[6][17][i] = 1;
            gameMap[6][21][i] = 1;
        }
        // 土墙绘制
        for (int i = 2; i < 37; i++) {
            for (int j = 6; j < 22; j++) {
                gameMap[6][i][j] = 1;
            }
        }
        // 铁墙
        for (int i = 2; i < 18; i++) {
            for (int j = 6; j < 13; j++) {
                gameMap[6][i][j] = 2;
                gameMap[6][38-i][27-j] = 2;
            }
        }
        //  树林
        for (int i = 21; i < 37; i++) {
            for (int j = 6; j < 13; j++) {
                gameMap[6][i][j] = 3;
            }
        }
        //  水
        for (int i = 2; i < 18; i++) {
            for (int j = 6; j < 13; j++) {
                gameMap[6][i][27-j] = 4;
            }
        }
    }
    // 第八关地图
    public void setEightMap(){
        // 老家方格
        for (int i = 18; i < 21; i++) {
            gameMap[7][i][25] = 1;
        }
        for (int i = 27; i > 24 ; i--) {
            gameMap[7][17][i] = 1;
            gameMap[7][21][i] = 1;
        }
        // 土墙绘制
        for (int i = 9; i < 30; i++) {
            for (int j = 3; j < 5; j++) {
                gameMap[7][i][j] = 1;
            }
        }
        for (int i = 7; i < 32; i++) {
            for (int j = 22; j < 24; j++) {
                gameMap[7][i][j] = 1;
            }
        }
        //  铁墙绘制
        for (int i = 19; i < 21; i++) {
            for (int j = 5; j < 22; j++) {
                gameMap[7][i][j] = 2;
            }
        }
        //  树林绘制
        for (int i = 21; i < 29; i++) {
            for (int j = 11; j < 13; j++) {
                gameMap[7][i][j] = 3;
            }
        }
        //  水绘制
        for (int i = 13; i < 15; i++) {
            for (int j = 13; j < 22; j++) {
                gameMap[7][i][j] = 4;
            }
        }
    }
    // 初始化所有地图
    public void setAllMap(){
        setOneMap();
        setTwoMap();
        setThreeMap();
        setFourMap();
        setFiveMap();
        setSixMap();
        setSevenMap();
        setEightMap();
    }
}
