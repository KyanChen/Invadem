package invadem;

import processing.core.PImage;

public class Tank2 extends AbstractTank {

    public Tank2(int x, int y) {
        super(x, y);
    }

    @Override
    public void loadHearts() {
        hearts[0] = new Heart(50, 40);
        hearts[1] = new Heart(90, 40);
        hearts[2] = new Heart(130, 40);
    }

    @Override
    public void loadImages() {
        sprites = new PImage[2];
        sprites[0] = p.loadImage("src/main/resources/tank2.png");
        sprites[1] = p.loadImage("src/main/resources/tank2_boom.png");
        currentSpriteIndex = 0;
        imageLoaded = true;
    }


}
