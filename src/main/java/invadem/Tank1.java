package invadem;

import processing.core.PImage;

public class Tank1 extends AbstractTank {

    public Tank1(int x, int y) {
        super(x, y);
    }

    @Override
    public void loadHearts() {

        hearts[0] = new Heart(50, 10);
        hearts[1] = new Heart(90, 10);
        hearts[2] = new Heart(130, 10);
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
