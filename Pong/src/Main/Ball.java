package Main;

import java.awt.*;

public class Ball extends Rectangle {
    private String direction;
    private int velocity;

    public Ball() {
        velocity = 8;
    }

    public void draw(Graphics2D g2) {
        g2.drawOval(x, y, width, height);
        g2.fillOval(x, y, width, height);
    }

    public int getVelocity() {
        return velocity;
    }
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
}
