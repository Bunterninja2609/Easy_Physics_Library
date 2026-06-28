package EPL;

import java.util.ArrayList;

public class PhysicsManager {
    private static ArrayList<PhysicsBody> bodies = new ArrayList<PhysicsBody>();
    public static void reqister(PhysicsBody b) {
        bodies.add(b);
    }
    public static void unregister(PhysicsBody b) {
        bodies.remove(b);
    }
    public static ArrayList<PhysicsBody> getBodies() {
        return bodies;
    }
    public static void setBodies(ArrayList<PhysicsBody> bodies) {
        PhysicsManager.bodies = bodies;
    }
    public void reset(){
        bodies.clear();
    }
    public static ArrayList<PhysicsBody> getLeftFrom(double x) {
        ArrayList<PhysicsBody> leftBodies = new ArrayList<>();
        for (PhysicsBody b : bodies) {
            if (b.getCollider().getLeftMostX() <= x){
                leftBodies.add(b);
            }
        }
        return leftBodies;
    }
    public static ArrayList<PhysicsBody> getRightFrom(double x) {
        ArrayList<PhysicsBody> rightBodies = new ArrayList<>();
        for (PhysicsBody b : bodies) {
            if (b.getCollider().getRightMostX() >= x){
                rightBodies.add(b);
            }
        }
        return rightBodies;
    }
    public static ArrayList<PhysicsBody> getUpFrom(double y) {
        ArrayList<PhysicsBody> upBodies = new ArrayList<>();
        for (PhysicsBody b : bodies) {
            if (b.getCollider().getUpMostY() <= y){
                upBodies.add(b);
            }
        }
        return upBodies;
    }
    public static ArrayList<PhysicsBody> getDownFrom(double y) {
        ArrayList<PhysicsBody> downBodies = new ArrayList<>();
        for (PhysicsBody b : bodies) {
            if (b.getCollider().getDownMostY() >= y){
                downBodies.add(b);
            }
        }
        return downBodies;
    }

    public static ArrayList<PhysicsBody> getInRadius(double x, double y, double radius) {
        ArrayList<PhysicsBody> inRadiusBodies = new ArrayList<>();
        for (PhysicsBody b : bodies) {
            if(MathAddition.distance(x,y,b.getX(),b.getY())<=radius){
                inRadiusBodies.add(b);
            }
        }
        return inRadiusBodies;
    }
    public static ArrayList<PhysicsBody> getInArea(double x, double y, double width, double height) {
        ArrayList<PhysicsBody> inAreaBodies = new ArrayList<>();
        for (PhysicsBody b : bodies) {
            if(b.getCollider().getLeftMostX() <= x + width
            && b.getCollider().getRightMostX() >= x
            && b.getCollider().getUpMostY() <= y + height
            && b.getCollider().getDownMostY() >= y){
                inAreaBodies.add(b);
            }
        }
        return inAreaBodies;
    }

    public static ArrayList<PhysicsBody> getInsideBoundingBox(PhysicsBody b) {
        double x = b.getCollider().getLeftMostX();
        double y = b.getCollider().getUpMostY();
        double w = b.getCollider().getRightMostX()-b.getCollider().getLeftMostX();
        double h = b.getCollider().getDownMostY()-b.getCollider().getUpMostY();
        return getInArea(x, y, w, h);
    }
    public static ArrayList<PhysicsBody> getColliding(PhysicsBody b) {
        ArrayList<PhysicsBody> collidingBodies = new ArrayList<>();
        for (PhysicsBody cB : getInsideBoundingBox(b)) {
            if (b.collidesWith(cB)){
                collidingBodies.add(cB);
            }
        }
        return collidingBodies;
    }
    public static boolean isColliding(PhysicsBody b) {
        ArrayList<PhysicsBody> collidingBodies = new ArrayList<>();
        for (PhysicsBody cB : getInsideBoundingBox(b)) {
            if (b.collidesWith(cB)){
                return true;
            }
        }
        return false;
    }
}
