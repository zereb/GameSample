package core;

import java.awt.*;
import java.util.Random;

public class Wall extends GameObject{

    private int SIZE = 42;
    private int GAP = 180;
    private Handler handler;

    public Wall(int x, int y, ID id, Handler handler){
        super(x, y, id, handler);
        this.handler = handler;
        velX = -3;
    }

    @Override
    public Rectangle getBounce(int pos) {
        if (pos == Game.BOTTOM_WALL)
            return new Rectangle(x, y, SIZE, Game.HEIGHT);
        if (pos == Game.TOP_WALL)
            return  new Rectangle(x, 0, SIZE, y-GAP);
        return null;
    }

    @Override
    public Rectangle getBounce() {
        return null;
    }

    @Override
    public void tick() {
        x += velX;
        if (x < -100)
            handler.removeObject(this);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, SIZE, Game.HEIGHT);
        g.fillRect(x, 0, SIZE, y-GAP);
    }
}
