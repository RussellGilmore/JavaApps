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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class HUD {

    public static int HEALTH = 200;
    public static int SHIELD = 0;
    static int score = 0;

    public void tick() {
//        HEALTH--;

        HEALTH = Game.clamp(HEALTH, 0, 200);
        SHIELD = Game.clamp(SHIELD, 0, 200);
        //score++;

    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(15, 15, 200, 32);
        g.setColor(Color.green);
        g.fillRect(15, 15, HEALTH, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);

        if (HEALTH == 0) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("b.png"));
            } catch (IOException ex) {
                Logger.getLogger(HUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            g.drawImage(image, Game.WIDTH / 2, Game.HEIGHT / 2, image.getWidth() * 2, image.getHeight() * 2, null);
            
            g.drawString("You Died", Game.WIDTH / 2 - "You Died".length() - 14, Game.HEIGHT / 2 - 30);
            g.dispose();
        } else {
            g.drawString("Score " + score, 15, 64);
        }

        //Shield 
        g.setColor(Color.BLUE);
        g.fillRect(15, 15, SHIELD * 2, 32);

    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }
}
