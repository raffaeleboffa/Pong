package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main implements KeyListener {
    static int width = 1000, height = 700;

    static JFrame frame = new JFrame("Pong - Press enter to play");
    static GamePanel gp = new GamePanel(width, height);

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 20, 1000, 700);
        frame.setResizable(false);

        frame.addKeyListener(new Main());
        frame.add(gp);

        //gp.start_thread();

        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_W) gp.player1.setMovement("up");
        if(keyCode == KeyEvent.VK_S) gp.player1.setMovement("down");
        if(keyCode == KeyEvent.VK_O) gp.player2.setMovement("up");
        if(keyCode == KeyEvent.VK_L) gp.player2.setMovement("down");
        if(keyCode == KeyEvent.VK_ENTER && !gp.cicla) gp.start_thread();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_W) gp.player1.setMovement("stop");
        if(keyCode == KeyEvent.VK_S) gp.player1.setMovement("stop");
        if(keyCode == KeyEvent.VK_O) gp.player2.setMovement("stop");
        if(keyCode == KeyEvent.VK_L) gp.player2.setMovement("stop");
    }
}
