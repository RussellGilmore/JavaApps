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


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile extends GameObject {

    static int damage = 10;
    String target;
    int horizontal;
    int verticle;
    Handler handle;
    HUD hud;
    int[] directionsP = new int[2];

    public Projectile(Handler handle, int x, int y, ID id, int horizontal, int verticle, String target) {
        super(x, y, id);
        this.horizontal = horizontal;
        this.verticle = verticle;
        this.handle = handle;
        this.target = target;

    }

    @Override
    public void tick() {
        fireingSolution();
        if ("Player".equals(this.target)) {
            if (x > Game.WIDTH || y > Game.HEIGHT || x < 0 || y < 0) {
                handle.object.remove(this);
            }
            x += directionsP[0];
            y += directionsP[1];

        } else {
            if (x > Game.WIDTH || y > Game.HEIGHT || x < 0 || y < 0) {
                handle.object.remove(this);
            }
            x += velX + directionsP[0];
            y += velY + directionsP[1];
            // System.out.println("Firing");
        }
        killZone();
    }

    private void killZone() {
        for (int i = 0; i < handle.object.size(); i++) {
            GameObject tempObject = handle.object.get(i);
            if ("BasicEnemy".equals(target)) {
                if (tempObject.getId() == ID.BasicEnemy) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        handle.object.remove(tempObject);
                        HUD.score = HUD.score + 100;
                    }
                }
            } else if (tempObject.getId() == ID.Player) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (HUD.SHIELD == 0) {
                        HUD.HEALTH -= 2;
                    } else {
                        HUD.SHIELD -= 2;
                    }
                }
            }
        }
    }

    public void fireingSolution() {
        if (horizontal == 32 && verticle == 32) {
            int dirX = 8;
            int dirY = 8;
            directionsP[0] = dirX;
            directionsP[1] = dirY;

        } else if (horizontal == -32 && verticle == 32) {
            int dirX = - 8;
            int dirY = 8;
            directionsP[0] = dirX;
            directionsP[1] = dirY;
        } else if (horizontal == 32 && verticle == -32) {
            int dirX = 8;
            int dirY = -8;
            directionsP[0] = dirX;
            directionsP[1] = dirY;
        } else if (horizontal == -32 && verticle == -32) {
            int dirX = - 8;
            int dirY = - 8;
            directionsP[0] = dirX;
            directionsP[1] = dirY;
        } else if (horizontal == -32 && verticle == 0) {
            int dirX = - 8;
            int dirY = 0;
            directionsP[0] = dirX;
            directionsP[1] = dirY;
        } else if (horizontal == 0 && verticle == -32) {
            int dirX = 0;
            int dirY = -8;
            directionsP[0] = dirX;
            directionsP[1] = dirY;
        } else if (horizontal == 0 && verticle == 32) {
            int dirX = 0;
            int dirY = 8;
            directionsP[0] = dirX;
            directionsP[1] = dirY;
        } else if (horizontal == 32 && verticle == 0) {
            int dirX = 8;
            int dirY = 0;
            directionsP[0] = dirX;
            directionsP[1] = dirY;
        }

    }

    @Override
    public void render(Graphics g) {
        Graphics bullet = (Graphics) g;
        bullet.setColor(Color.BLUE);
        bullet.fillRect(x, y, 8, 8);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 8);
    }

}
