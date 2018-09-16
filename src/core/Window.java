package core;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    public Window(int width, int heigh, String title, Game game){
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, heigh));
        frame.setMaximumSize(new Dimension(width, heigh));
        frame.setMinimumSize(new Dimension(width, heigh));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        frame.toFront();
        game.start();

    }

}
