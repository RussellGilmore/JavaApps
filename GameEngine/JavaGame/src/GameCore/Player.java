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

import static GameCore.GameObject.myUp;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {

    private static final int ATTACK_COOLDOWN = 1000;
    private long damageThreshold;

    Handler handler;
    HUD hud;
    int xRange; //Projectile Square Start
    int yRange;//Projectile Square Start
    int PX;
    int PY;

    private int horL = 0; //starting point for block horizontally
    private int verL = 0; //starting point for block vertically
    private int wid = 32; //width
    private int hig = 32; //hight
    private int side = 0;
    private int up = 0;

    private final int upOffset = -32;
    private final int downOffset = 32;
    private final int rightOffset = 32;
    private final int leftOffset = -32;

    //Used to time the number of attacks
    long lastAttack = 0;

    public Player(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
        this.attack = false;
        this.xRange = -1;
        this.yRange = -1;
        this.PX = 0;
        this.PY = 0;

    }

    public int[] getDirection() {
        int[] offsets = {0, 0};
        //up, down, right, left
        if (myUp & myRight) {
            offsets[0] = upOffset;
            offsets[1] = rightOffset;
        } else if (myUp & myLeft) {
            offsets[0] = upOffset;
            offsets[1] = leftOffset;
        } else if (myDown & myLeft) {
            offsets[0] = downOffset;
            offsets[1] = leftOffset;
        } else if (myDown & myRight) {
            offsets[0] = downOffset;
            offsets[1] = rightOffset;
        } else if (myUp) {
            offsets[0] = upOffset;
        } else if (myDown) {
            offsets[0] = downOffset;
        } else if (myRight) {
            offsets[1] = rightOffset;
        } else if (myLeft) {
            offsets[1] = leftOffset;
        }
        return offsets;

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 38);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);
        position();
        if (melee == true && attack == true) {
            if (attack && velX != 0 || velY != 0) {
                position();
                killZone();
            }
        }

        if (range == true && attack == true) {
            if (attack && velX != 0 || velY != 0) {
                //killZone();
                final long currentTime = System.currentTimeMillis();
                //System.out.println(currentTime - this.lastAttack);
                if (currentTime - this.lastAttack > ATTACK_COOLDOWN) {
                    this.lastAttack = currentTime;
                    int[] offsets = getDirection();
                    handler.addObject(new Projectile(handler, x, y, ID.Projectile, offsets[1], offsets[0], "BasicEnemy"));
                }
            }
        }
        collision();
    }

    @Override
    public void render(Graphics g) {
        Graphics point = (Graphics) g;
        Graphics fire = (Graphics) g;
        if (id == ID.Player) {
            g.setColor(Color.white);
            g.fillRect(x, y, 32, 32);
            if (melee == true) {
                g.setColor(Color.PINK);
            } else {
                g.setColor(Color.BLUE);
            }
            point.fillRect(x + this.horL, y + this.verL, this.wid, this.hig);  //use info from if/then statements
            //Melee Render Logic
            if (melee == true && attack) {
                if (velX != 0 || velY != 0) {
                    //System.out.println("working");
                    g.setColor(Color.PINK);
                    int[] offsets = getDirection();
                    //System.out.println(Arrays.toString(offsets));
                    fire.fillRect(x + offsets[1], y + offsets[0], 32, 32);
                    position();
                }
            }
            // Range Render Logic
            if (range == true && attack) {
                if (velX != 0 || velY != 0) {

                }
            }
        }
    }

    private void collision() {

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy) {
                final long currentTime = System.currentTimeMillis();
                if (currentTime - this.damageThreshold > ATTACK_COOLDOWN) {
                    this.damageThreshold = currentTime;
                    if (getBounds().intersects(tempObject.getBounds())) {
                        //Colision
                        if (HUD.SHIELD == 0) {
                            HUD.HEALTH -= 10;
                        } else {
                            HUD.SHIELD -= 10;
                        }

                    }
                }
            }
        }
    }

    private void killZone() {
        //Drops should be handled here
        Random rand = new Random();
        int range1 = rand.nextInt(5);

        int drop = rand.nextInt(5);

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy) {

                if (getBounds().intersects(tempObject.getBounds())) {
                    //Colision
                    handler.object.remove(tempObject);
                    hud.setScore(hud.getScore() + 100);
                    if (drop == range1) {
                        HUD.SHIELD = 100;
                    }

                }
            }

        }

    }

    public void position() {
        if (velX > 0) {
            this.side = 1;
            this.horL = 22;
            this.wid = 10;
        } else if (velX < 0) {
            this.side = -1;
            this.horL = 0;
            this.wid = 10;
        } else {
            this.horL = 10;
            this.wid = 12;
        }
        if (velY > 0) {
            this.up = 1;
            this.verL = 22;
            this.hig = 10;
        } else if (velY < 0) {
            this.up = -1;
            this.verL = 0;
            this.hig = 10;
        } else {
            this.verL = 10;
            this.hig = 12;
        }
    }
}
