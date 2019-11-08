package invadem;

import processing.core.PImage;

public class Tank1 extends AbstractTank {

    public Tank1(int x, int y) {
        super(x, y);
    }

    @Override
    public void loadHearts() {
        hearts[0] = new Heart(20, 50);
        hearts[1] = new Heart(60, 50);
        hearts[2] = new Heart(100, 50);
    }

    @Override
    public void loadImages() {
        sprites = new PImage[2];
        sprites[0] = p.loadImage("src/main/resources/tank1.png");
        sprites[1] = p.loadImage("src/main/resources/tank1_boom.png");
        currentSpriteIndex = 0;
        imageLoaded = true;
    }


}
