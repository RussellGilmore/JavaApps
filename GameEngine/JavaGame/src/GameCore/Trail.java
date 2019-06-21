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


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {

    private float alpha = 1;
    private final float life;

    private final Handler handler;
    private final Color color;
    private final int width;
    private final int height;

    public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler, int damage) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;

    }

    public void tick() {
        // The smaller the number the longer the life of the particle
        if (alpha > life) {
            alpha -= (life - 0.0001f);
        } else {
            handler.removeObject(this);
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect(x, y, width, height);

        g2d.setComposite(makeTransparent(1));

    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));

    }

    public Rectangle getBounds() {
        return null;
    }

}
