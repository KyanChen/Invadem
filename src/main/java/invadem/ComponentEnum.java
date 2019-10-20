package invadem;

public enum ComponentEnum {
    LEFT(0),
    TOP(1),
    RIGHT(2),
    SOLID(3);

    private int index;
    ComponentEnum(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
