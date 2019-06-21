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
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GameObject {

    private static final int ATTACK_COOLDOWN = 1000;

    private Handler handler;
    int PX, PY, xRange, yRange;
    int health = 100;
    // Weapon Logic
    private String weaponType = "";


    public boolean up = false;
    public boolean down = false;
    public boolean right = false;
    public boolean left = false;

    public int previousVerticle;
    public int previousHorizontal;

    //Used to time the number of attacks
    long lastAttack = 0;
    // First is verticle then horiz
    int[] justWork = {1, 1};

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        //Automated Movement
        velX = 1;
        velY = 1;
        PX = PY = -1;
        xRange = yRange = 0;
        makeType();
    }

    private void makeType() {
        Random rand = new Random();
        int type = rand.nextInt(2);
        if (type == 0) {
            this.weaponType = "melee";
        } else {
            this.weaponType = "range";
        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);

    }

    public void setPreviousLocation() {
        this.previousVerticle = y;
        this.previousHorizontal = x;

    }

    public void direction() {
        if (previousVerticle < y) {
            justWork[0] = 32;
        }
        if (previousVerticle > y) {
            justWork[0] = -32;
        }
        if (previousVerticle % y == 0) {
            justWork[0] = 0;

        }
        if (previousHorizontal < x) {
            justWork[1] = 32;
        }
        if (previousHorizontal > x) {
            justWork[1] = -32;
        }
        if (previousHorizontal % x == 0) {
            justWork[1] = 0;

        }
    }

    public void tick() {
        handler.addObject(new Trail(x, y, ID.Trail, Color.RED, 16, 16, 0.08f, handler, 0));
        setPreviousLocation();
        moveEnemy();
        direction();
        // range logic
        if ("range".equals(this.weaponType)) {
            final long currentTime = System.currentTimeMillis();
            if (currentTime - this.lastAttack > ATTACK_COOLDOWN) {
                this.lastAttack = currentTime;
                handler.addObject(new Projectile(handler, x, y, ID.Projectile, justWork[1], justWork[0], "Player"));
            }
        }
        // melee logic
        if ("melee".equals(this.weaponType)) {

        }

    }

    public void moveEnemy() {
        x += velX;
        y += velY;

        if (Game.user.x < x) {

            velX = -1;
        } else if (Game.user.x > x) {

            velX = 1;
        } else {
            velX = 0;
        }

        if (Game.user.y < y) {
            velY = -1;
        } else if (Game.user.y > y) {
            velY = 1;
        } else {
            velY = 0;
        }

    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Graphics fire = (Graphics) g;
        g2d.draw(getBounds());
        // Actual Object
        g.setColor(Color.white);
        g.fillRect(x, y, 16, 16);

        if ("melee".equals(this.weaponType)) {
            if (velX != 0 || velY != 0) {
                //logic
            }

        }

        if ("range".equals(this.weaponType)) {
            if (velX != 0 || velY != 0) {
                g.setColor(Color.PINK);
                //logic
            }
        }
    }
}
