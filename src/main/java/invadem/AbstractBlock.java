package invadem;

/**
 * the blocks of a barrier
 */
public abstract class AbstractBlock extends AbstractObject {
    public AbstractBlock(int x, int y) {
        super(x, y, 3, 8, 8);

    }

    /**
     * update the index of sprite when the blood changes
     */
    public void updateSprite() {
        currentSpriteIndex = blood;
    }

}
