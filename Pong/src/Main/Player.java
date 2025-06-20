package Main;

import java.awt.*;

public class Player extends Rectangle {
    private int points;
    private String movement;

    public Player() {
        points = 0;
        movement = "stop";
    }

    public void draw(Graphics2D g2) {
        g2.drawRect(x, y, width, height);
        g2.fillRect(x, y, width, height);
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    public String getMovement() {
        return movement;
    }
    public void setMovement(String movement) {
        this.movement = movement;
    }
}
