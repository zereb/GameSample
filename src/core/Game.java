package core;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    public Random random;



    public Game(){
        random = new Random();
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        new Window(WIDTH, HEIGHT, "core.Game", this);
        handler.addObject(new Player(10, 10, ID.player, handler));

        for (int i = 0; i < 10; i++){
            handler.addObject(new Wall(Game.WIDTH + i*100,  Game.HEIGHT - random.nextInt(200), ID.Enemy, handler));
        }



    }


    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        int tiks = 0;
        long lastTime = System.nanoTime();
        double ammountOfTicks = 60.0;
        double ns = 1000000000 / ammountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
           long now = System.nanoTime();
           delta += (now - lastTime) / ns;
           lastTime = now;
           while (delta >= 1){
               tick();
               delta --;
               tiks++;

           }
           if(running)
               render();
           frames++;

           if(System.currentTimeMillis() - timer > 1000){
               timer += 1000;
               System.out.print("Fps: " + frames);
               System.out.print(" Tiks: " + tiks);
               frames = 0;
               tiks = 0;
           }
        }
        stop();
    }

   private void tick(){
        handler.tick();
   }

   private void render(){
       BufferStrategy bs = this.getBufferStrategy();
       if(bs == null) {
           this.createBufferStrategy(3);
           return;
       }

       Graphics g = bs.getDrawGraphics();

       g.setColor(Color.BLACK);
       g.fillRect(0, 0, WIDTH, HEIGHT);

       handler.render(g);

       g.dispose();
       bs.show();
   }

    public static void main(String[] args) {
        new Game();
    }

    public static void newGame(){
        new Game();
    }
}
