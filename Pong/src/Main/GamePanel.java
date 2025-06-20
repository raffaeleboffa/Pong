package Main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    int width, height;

    Player player1 = new Player(), player2 = new Player();
    Ball ball = new Ball();

    int player_width = 18, player_height = 125;
    int ball_r = 25;

    int touch = 0;
    boolean cicla = false;
    int keepwin = 0, winner = 0;

    public GamePanel(int width, int height) {
        setBackground(Color.BLACK);
        this.width = width;
        this.height = height;
    }

    public void start_thread() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        switch((int) (Math.random()*4)) {
            case 0: ball.setDirection("left_up"); break;
            case 1: ball.setDirection("left_down"); break;
            case 2: ball.setDirection("right_up"); break;
            case 3: ball.setDirection("right_down"); break;
        }

        player1.setBounds(50, (height/2)-(player_height/2), player_width, player_height);
        player2.setBounds(width-player_width-50, (height/2)-(player_height/2), player_width, player_height);
        ball.setBounds((width/2)-(ball_r/2), (height/2)-(ball_r/2), ball_r, ball_r);
        ball.setVelocity(8);

        cicla = true;

        while(cicla) {
            if (winner != 0) {
                keepwin++;
                if(keepwin == 20) {
                    winner = 0;
                    keepwin = 0;
                }
            }

            update();
            repaint();

            if(ball.x <= player1.x) {
                player2.setPoints(player2.getPoints()+1);
                winner = 2;
                cicla = false;
                break;
            } else if(ball.x >= player2.x) {
                player1.setPoints(player1.getPoints()+1);
                winner = 1;
                cicla = false;
                break;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);

        if(!cicla) {
            player1.setBounds(50, (height/2)-(player_height/2), player_width, player_height);
            player2.setBounds(width-player_width-50, (height/2)-(player_height/2), player_width, player_height);
            ball.setBounds((width/2)-(ball_r/2), (height/2)-(ball_r/2), ball_r, ball_r);
        }

        player1.draw(g2);
        player2.draw(g2);
        ball.draw(g2);

        g2.drawLine(width/2, 0, width/2, height);

        g2.setFont(new Font("Serif", Font.PLAIN, 20));

        String strPointPlayer1 = Integer.toString(player1.getPoints()), strPointPlayer2 = Integer.toString(player2.getPoints());
        if(winner == 1) {
            strPointPlayer1 += "       VINCITORE";
        } else if(winner == 2) {
            strPointPlayer2 += "       VINCITORE";
        }

        g2.drawString(strPointPlayer1, 50, 50);
        g2.drawString(strPointPlayer2, (width/2)+50, 50);

        g2.dispose();
    }

    public void update() {
        if(touch == 3) {
            touch = 0;
            if (ball.getVelocity() < player1.width -3) ball.setVelocity(ball.getVelocity()+1);
        }

        if(player1.getMovement().equals("up")) {
            if(player1.y > 10) player1.setLocation(player1.x, player1.y-10);
        }
        else if(player1.getMovement().equals("down")) if((player1.y+player1.height) < (height-50)) player1.setLocation(player1.x, player1.y+10);

        if(player2.getMovement().equals("up")) {
            if(player2.y > 10) player2.setLocation(player2.x, player2.y-10);
        }
        else if(player2.getMovement().equals("down")) if((player2.y+player2.height) < (height-50)) player2.setLocation(player2.x, player2.y+10);

        int ball_velocity = ball.getVelocity();
        switch(ball.getDirection()) {
            case "left_up": ball.setLocation(ball.x-ball_velocity, ball.y-ball_velocity-1); break;
            case "left_down": ball.setLocation(ball.x-ball_velocity, ball.y+ball_velocity-1); break;
            case "right_up": ball.setLocation(ball.x+ball_velocity, ball.y-ball_velocity-1); break;
            case "right_down": ball.setLocation(ball.x+ball_velocity, ball.y+ball_velocity-1); break;
        }

        if(ball.y <= 0) {
            switch(ball.getDirection()) {
                case "left_up": ball.setDirection("left_down"); break;
                case "right_up": ball.setDirection("right_down"); break;
            }
        }
        else if(ball.y >= height-ball.height-50) {
            switch(ball.getDirection()) {
                case "left_down": ball.setDirection("left_up"); break;
                case "right_down": ball.setDirection("right_up"); break;
            }
        }

        if(ball.intersects(player1) || ball.intersects(player2)) {
            touch++;
            switch(ball.getDirection()) {
                case "left_up": ball.setDirection("right_up"); break;
                case "left_down": ball.setDirection("right_down"); break;
                case "right_up": ball.setDirection("left_up"); break;
                case "right_down": ball.setDirection("left_down"); break;
            }
        }
    }
}
