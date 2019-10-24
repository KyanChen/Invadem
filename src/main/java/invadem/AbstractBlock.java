package invadem;

/**
 * the blocks of a barrier
 */
public abstract class AbstractBlock extends AbstractObject {
    public AbstractBlock(int x, int y) {
        super(x, y, 3, 8, 8);

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
