package model;

public class Player {
    public String name;
    public int point;
    public int life;

    public Player(String name, int point) {
        this.name = name;
        this.point = point;
        life = 3;
    }

    public Player() {
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
