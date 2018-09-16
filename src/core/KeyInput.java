package core;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        for(GameObject object : handler.getGameObjects()){
            if(object.getId() == ID.player){
                if (key == KeyEvent.VK_SPACE) object.setVelY(0);
                if (key == KeyEvent.VK_ESCAPE) System.exit(0);
            }
        }
    }



    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        for(GameObject object : handler.getGameObjects()){
            if(object.getId() == ID.player){
            }
        }
    }

}
