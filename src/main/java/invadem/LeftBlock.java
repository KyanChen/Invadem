package invadem;

import processing.core.PImage;

/**
 * the left block of the barrier
 */
public class LeftBlock extends AbstractBlock {


    public LeftBlock(int x, int y) {
        super(x, y);
    }

    /**
     * load images of the block
     */
    @Override
    public void loadImages() {
        // left
        sprites = new PImage[4];
        sprites[3] = p.loadImage("src/main/resources/barrier_left1.png");
        sprites[2] = p.loadImage("src/main/resources/barrier_left2.png");
        sprites[1] = p.loadImage("src/main/resources/barrier_left3.png");
        sprites[0] = p.loadImage("src/main/resources/empty.png");
        currentSpriteIndex = 3;


    }
}
