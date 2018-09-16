package core;

import java.awt.*;

public class Player extends GameObject{

    private long lastTime, cooldown;
    private Handler handler;
    public int THRUST = -4;
    public int SIZE = 24;
    public boolean dead = false;



    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id, handler);
        this.handler = handler;
        velX = 0;
        velY = 0;
        lastTime = System.currentTimeMillis();
    }


    @Override
    public Rectangle getBounce() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    @Override
    public Rectangle getBounce(int pos) {
        return null;
    }

    @Override
    public void tick() {
        long delta = System.currentTimeMillis() - lastTime;
        if(delta> 100){
            velY = velY + 1;
            cooldown -= delta;
            lastTime = System.currentTimeMillis();
        }
        y += velY;
        collision();

        if(y > Game.HEIGHT + SIZE){
           dead = true;
        }
    }



    @Override
    public void render(Graphics g) {
        Color color = Color.RED;
        if (velY <  0)
            color = new Color(Game.clamp(150 + velY*2, 0, 255), 0 , 0);
        if (velY > 0)
            color = new Color(Game.clamp(150 - velY*2, 0, 255), 0 , 0);

        g.setColor(color);
        g.fillRect(x, y, SIZE, SIZE);
    }

    @Override
    public void setVelY(int velY){
       if (cooldown < 1){
           this.velY = THRUST;
           cooldown = 500;
       }
    }

    public void collision(){
        for (GameObject object : handler.getGameObjects()){
            if (object.getId() == ID.Enemy){
                if (getBounce().intersects(object.getBounce(Game.TOP_WALL)))
                   dead = true;
                if (getBounce().intersects(object.getBounce(Game.BOTTOM_WALL)))
                    dead = true;
            }
        }
    }
}

