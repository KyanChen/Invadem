package invadem;

import processing.core.PImage;

public class RightBlock extends AbstractBlock {


    public RightBlock(int x, int y) {
        super(x, y);
    }

    @Override
    public void loadImages() {
        // left
        sprites = new PImage[4];
        sprites[3] = p.loadImage("src/main/resources/barrier_right1.png");
        sprites[2] = p.loadImage("src/main/resources/barrier_right2.png");
        sprites[1] = p.loadImage("src/main/resources/barrier_right3.png");
        sprites[0] = p.loadImage("src/main/resources/empty.png");
        currentSpriteIndex = 3;
        imageLoaded = true;

    }
}
