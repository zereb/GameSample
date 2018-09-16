package core;

import java.awt.*;

public class Player extends GameObject{

    private long lastTime, cooldown;
    private Handler handler;
    public int THRUST = -4;
    public int SIZE = 24;
    public int score = 0;



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
    public void tick() {
        long delta = System.currentTimeMillis() - lastTime;
        if(delta> 100){
            velY = velY + 1;
            score++;
            System.out.println(" Score: " + score);
            cooldown -= delta;
            lastTime = System.currentTimeMillis();
        }
        y += velY;
        collision();

        if(y > Game.HEIGHT + SIZE){
           // Game.newGame();
        }
    }



    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
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
                if (getBounce().intersects(object.getBounce())){
                    System.out.println("dead");
                }
            }
        }
    }
}

