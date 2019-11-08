package invadem;

/**
 * the blocks of a barrier
 */
public abstract class AbstractBlock extends AbstractObject {
    public AbstractBlock(int x, int y) {
        super(x, y, 3, 8, 8);

    }

    public void updateSprite() {
        currentSpriteIndex = blood;
    }

}
