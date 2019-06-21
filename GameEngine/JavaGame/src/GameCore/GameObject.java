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


import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    //Can only be accessed by the objects the inherit the game objects.
    protected int x, y;
    protected ID id;
    protected int velX, velY;
    protected boolean attack;       //add attack parameter to GameObject
    static boolean myUp = false;
    static boolean myDown = false;
    static boolean myRight = false;
    static boolean myLeft = false;
    protected static boolean melee = true; //What kind of weapon are we using
    protected static boolean range = false; //What kind of weapon are we using

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;

    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getVelY() {
        return velY;
    }

    public Integer[] ranged() {
        Integer[] project = {x * 3, y * 3};
        return project;
    }
}
