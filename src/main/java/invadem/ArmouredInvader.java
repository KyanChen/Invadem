package invadem;

import processing.core.PImage;

public class ArmouredInvader extends Invader {
    public ArmouredInvader(int x, int y) {
        super(x, y);
        blood = 3;
    }

    @Override
    public void loadImages() {
        sprites = new PImage[2];
        sprites[0] = p.loadImage("src/main/resources/invader1_armoured.png");
        sprites[1] = p.loadImage("src/main/resources/invader2_armoured.png");
        currentSpriteIndex = 0;
        imageLoaded = true;
    }
}
