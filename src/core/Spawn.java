package core;

import java.util.Random;

public class Spawn {

    private Handler handler;
    private Player player;
    private int score;
    private Random random;
    private int lvl = 0;
    private  int enemies = 0;
    private int maxEnemies = 7;


    public Spawn(Handler handler){
        this.handler = handler;
        random = new Random();
    }

    public void tick(){
        if (lvl == 0){
            handler.clear();
            handler.addObject(player = new Player(10, 10, ID.player, handler));
            for (int i = 0; i < maxEnemies; i++){
                handler.addObject(new Wall(Game.WIDTH + i*100,  Game.HEIGHT - random.nextInt(200), ID.Enemy, handler));
            }
            lvl++;
        }

        if (player.dead) {
            lvl = 0;
            score = 0;
        }

        for (GameObject object : handler.getGameObjects()){
            if (object.getId() == ID.Enemy)
                enemies++;
        }

        if (enemies < maxEnemies)
            for (int i = 0; i < maxEnemies - enemies; i++)
                handler.addObject(new Wall(Game.WIDTH + i * 100, Game.HEIGHT - random.nextInt(200), ID.Enemy, handler));

        score++;
        System.out.println("Score: " + score + " Enemies; " + enemies);
        enemies = 0;
    }
}
