package invadem;

import processing.core.PImage;

public class Component extends AbstractObject {
    private PImage[][] components;

    public Component(ComponentEnum type, int x, int y) {
        super(ObjectEnum.BARRIER, x, y, 3, 8, 8);
        components = new PImage[4][4];
        loadComponents();
        sprites = components[type.getIndex()];
        currentSpriteIndex = 3;

    }

    private void loadComponents() {
        // left
        components[0][3]=p.loadImage("../resources/barrier_left1.png");
        components[0][2]= p.loadImage("../resources/barrier_left2.png");
        components[0][1]= p.loadImage("../resources/barrier_left3.png");
        components[0][0]= p.loadImage("../resources/empty.png");
        // top
        components[1][3]=p.loadImage("../resources/barrier_top1.png");
        components[1][2]= p.loadImage("../resources/barrier_top2.png");
        components[1][1]= p.loadImage("../resources/barrier_top3.png");
        components[1][0]= p.loadImage("../resources/empty.png");
        // right
        components[2][3]=p.loadImage("../resources/barrier_right1.png");
        components[2][2]= p.loadImage("../resources/barrier_right2.png");
        components[2][1]= p.loadImage("../resources/barrier_right3.png");
        components[2][0]= p.loadImage("../resources/empty.png");
        //solid
        components[3][3]=p.loadImage("../resources/barrier_solid1.png");
        components[3][2]= p.loadImage("../resources/barrier_solid2.png");
        components[3][1]= p.loadImage("../resources/barrier_solid3.png");
        components[3][0]= p.loadImage("../resources/empty.png");





    }

    @Override
    public void isHit() {
        if (blood > 0) {
            blood--;
            currentSpriteIndex = blood;
        }
    }

}
