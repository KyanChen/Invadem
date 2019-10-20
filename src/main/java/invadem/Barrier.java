package invadem;

public class Barrier {
    private static int width = 24;
    private static int height = 24;
    private Component[] components;
    private int x;
    private int y;

    public Barrier(int x, int y) {
        // (x,y) is the top-left corner
        this.x = x;
        this.y = y;
        components = new Component[7];
        loadComponents();

    }

    private void loadComponents() {
        components[0] = new Component(ComponentEnum.SOLID, x, y + 16);
        components[1] = new Component(ComponentEnum.SOLID, x, y + 8);
        components[2] = new Component(ComponentEnum.LEFT, x, y);
        components[3] = new Component(ComponentEnum.TOP, x + 8, y);
        components[4] = new Component(ComponentEnum.RIGHT, x + 16, y);
        components[5] = new Component(ComponentEnum.SOLID, x + 16, y + 8);
        components[6] = new Component(ComponentEnum.SOLID, x + 16, y + 16);
    }

    public void display() {
        for (Component bc : components) {
            if (bc.isAlive()) {
                bc.display();
            }
        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public Component[] getComponents() {
        return components;
    }
}
