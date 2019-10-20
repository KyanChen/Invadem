package invadem;

import processing.core.PImage;

/**
 * the blocks of a barrier
 */
public class Block extends AbstractObject {
    private PImage[][] blocks;

    public Block(BlockEnum type, int x, int y) {
        super(ObjectEnum.BARRIER, x, y, 3, 8, 8);
        blocks = new PImage[4][4];
        loadImages();
        sprites = blocks[type.getIndex()];
        currentSpriteIndex = 3;

    }

    /**
     * load the sprites of each block
     */
    private void loadImages() {
        // left
        blocks[0][3] = p.loadImage("../resources/barrier_left1.png");
        blocks[0][2] = p.loadImage("../resources/barrier_left2.png");
        blocks[0][1] = p.loadImage("../resources/barrier_left3.png");
        blocks[0][0] = p.loadImage("../resources/empty.png");
        // top
        blocks[1][3] = p.loadImage("../resources/barrier_top1.png");
        blocks[1][2] = p.loadImage("../resources/barrier_top2.png");
        blocks[1][1] = p.loadImage("../resources/barrier_top3.png");
        blocks[1][0] = p.loadImage("../resources/empty.png");
        // right
        blocks[2][3] = p.loadImage("../resources/barrier_right1.png");
        blocks[2][2] = p.loadImage("../resources/barrier_right2.png");
        blocks[2][1] = p.loadImage("../resources/barrier_right3.png");
        blocks[2][0] = p.loadImage("../resources/empty.png");
        //solid
        blocks[3][3] = p.loadImage("../resources/barrier_solid1.png");
        blocks[3][2] = p.loadImage("../resources/barrier_solid2.png");
        blocks[3][1] = p.loadImage("../resources/barrier_solid3.png");
        blocks[3][0] = p.loadImage("../resources/empty.png");


    }

    /**
     * deduct bloods and change it sprite
     */
    @Override
    public void isHit() {
        if (blood > 0) {
            blood--;
            currentSpriteIndex = blood;
        }
    }

}
