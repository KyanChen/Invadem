package invadem;

import processing.core.PImage;

public class TopBlock extends AbstractBlock {


    public TopBlock(int x, int y) {
        super(x, y);
    }

    @Override
    public void loadImages() {
        // left
        sprites = new PImage[4];
        sprites[3] = p.loadImage("src/main/resources/barrier_top1.png");
        sprites[2] = p.loadImage("src/main/resources/barrier_top2.png");
        sprites[1] = p.loadImage("src/main/resources/barrier_top3.png");
        sprites[0] = p.loadImage("src/main/resources/empty.png");
        currentSpriteIndex = 3;


    }
}
