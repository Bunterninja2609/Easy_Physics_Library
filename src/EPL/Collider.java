package EPL;

import java.util.ArrayList;

public class Collider {
    ArrayList<Hitbox> hitboxes;
    private double x;
    private double y;
    private double leftMostX;
    private double upMostY;
    private double rightMostX;
    private double downMostY;
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public boolean collidesWith(Collider c) {
        for (Hitbox h : hitboxes) {
            for (Hitbox h2 : c.getHitboxes()) {
                if (h.collidesWith(h2)){
                    return true;
                };
            }
        }
        return false;
    }
    public ArrayList<Hitbox> getHitboxes() {
        return hitboxes;
    }
    private void calculateExtremePoints(){
        double tempLeftMostX = x;
        double tempRightMostX = x;
        double tempUpMostY = y;
        double tempDownMostY = y;
        for (Hitbox h : hitboxes) {
            tempLeftMostX = Math.min(h.getX(), tempLeftMostX);
            tempRightMostX = Math.max(h.getX(), tempRightMostX);
            tempUpMostY = Math.min(h.getY(), tempUpMostY);
            tempDownMostY = Math.max(h.getY(), tempDownMostY);
        }
        leftMostX = tempLeftMostX;
        upMostY = tempUpMostY;
        rightMostX = tempRightMostX;
        downMostY = tempDownMostY;
    }
    public double getLeftMostX() {
        return leftMostX;
    }
    public double getUpMostY() {
        return upMostY;
    }
    public double getRightMostX() {
        return rightMostX;
    }
    public double getDownMostY() {
        return downMostY;
    }
}
