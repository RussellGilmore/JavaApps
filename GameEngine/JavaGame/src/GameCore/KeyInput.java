package GameCore;

/*
 * Copyright 2016 Russell.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private final Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(-5);
                    Player.myUp = true;
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-5);
                    Player.myLeft = true;
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(5);
                    Player.myDown = true;
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(5);
                    Player.myRight = true;
                }
                if (key == KeyEvent.VK_SPACE) {      //this is where the attack happens
                    tempObject.attack = true;

                }
                if (key == KeyEvent.VK_R) {
                    GameObject.melee = !GameObject.melee;
                    GameObject.range = !GameObject.range;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(0);
                    Player.myUp = false;
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(0);
                    Player.myLeft = false;
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(0);
                    Player.myDown = false;
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(0);
                    Player.myRight = false;
                }
                if (key == KeyEvent.VK_SPACE) {
                    tempObject.attack = false;
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }

    }

}
