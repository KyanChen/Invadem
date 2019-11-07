package invadem;

/**
 * assemble the blocks of barriers
 */
public class Barrier {
    private static int width = 24;
    private static int height = 24;
    private AbstractBlock[] blocks;
    private int x;
    private int y;

    public Barrier(int x, int y) {
        // (x,y) is the top-left corner
        this.x = x;
        this.y = y;
        blocks = new AbstractBlock[7];
        loadBlocks();

    }

    /**
     * Initialize objects of Barriers
     */
    public static Barrier[] loadBarriers(int LEFT_BOUNDARY, int RIGHT_BOUNDARY) {
        Barrier[] barriers = new Barrier[3];
        barriers[0] = new Barrier(LEFT_BOUNDARY + 20, 416);
        barriers[1] = new Barrier(320 - Barrier.getWidth() / 2, 416);
        barriers[2] = new Barrier(RIGHT_BOUNDARY - 20 - width, 416);
        return barriers;
    }

    /**
     * @return width of the barrier
     */
    public static int getWidth() {
        return width;
    }

    /**
     * create the objects of blocks
     */
    private void loadBlocks() {
        blocks[0] = new SolidBlock(x, y + 16);
        blocks[1] = new SolidBlock(x, y + 8);
        blocks[2] = new LeftBlock(x, y);
        blocks[3] = new TopBlock(x + 8, y);
        blocks[4] = new RightBlock(x + 16, y);
        blocks[5] = new SolidBlock(x + 16, y + 8);
        blocks[6] = new SolidBlock(x + 16, y + 16);
    }

    /**
     * display the barrier
     */
    public void display() {
        for (AbstractBlock bc : blocks) {
            if (bc.isAlive()) {
                bc.display();
            }
        }
    }

    /**
     * @return height of the barrier
     */
    public static int getHeight() {
        return height;
    }

    /**
     * @return the array of blocks
     */
    public AbstractBlock[] getBlocks() {
        return blocks;
    }
}
