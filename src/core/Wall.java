package core;

import java.awt.*;

public class Wall extends GameObject{

    public int SIZE = 42;
    private Handler handler;

    public Wall(int x, int y, ID id, Handler handler){
        super(x, y, id, handler);
        this.handler = handler;
        velX = -1;
    }

    @Override
    public Rectangle getBounce() {
        return new Rectangle(x, y, SIZE, Game.HEIGHT);
    }

    @Override
    public void tick() {
        x += velX;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, SIZE, Game.HEIGHT);
        g.setColor(Color.WHITE);
        g.fillRect(x, 0, SIZE, y-160);
    }
}
