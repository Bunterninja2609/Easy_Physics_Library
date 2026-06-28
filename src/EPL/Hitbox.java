package EPL;

public class Hitbox {
    private HITBOX_SHAPE shape;
    private Collider parent;
    private double offsetX;
    private double offsetY;
    private double width;
    private double height;
    private double radius;


    public boolean collidesWith(Hitbox b) {
        Hitbox a = this;
        if (a.shape == HITBOX_SHAPE.RECTANGLE) {
            if (b.getShape() == HITBOX_SHAPE.RECTANGLE) {
                //RECTANGLE TO RECTANGLE COLLISION            // If all 4 conditions are true a collision has occured
                return a.getX() < b.getX() + b.getWidth() &&  // a's left edge is left from b's right edge
                       a.getX() + a.getWidth() > b.getX() &&  // a's right edge is right from b's left edge
                       a.getY() < b.getY() + b.getHeight() && // a's top edge is higher than b's bottom edge
                       a.getY() + a.getHeight() > b.getY() ;  // a's bottom edge is lower than b's top edge

            } else if (b.getShape() == HITBOX_SHAPE.CIRCLE) {
                //RECTANGLE TO CIRCLE COLLISION
                double closestX = MathAddition.clamp(b.getX(), a.getX(), a.getX() + a.getWidth());
                double closestY = MathAddition.clamp(b.getY(), a.getY(), a.getY() + a.getHeight());
                return MathAddition.distance(closestX, closestY, b.getX(), b.getY()) < b.getRadius(); //When closest point on the rectangle a is within the radius of the circle b a collision has occured
            }
        }else if (a.shape == HITBOX_SHAPE.CIRCLE) {
            if (b.getShape() == HITBOX_SHAPE.RECTANGLE) {
                //CIRCLE TO RECTANGLE COLLISION
                double closestX = MathAddition.clamp(a.getX(), b.getX(), b.getX() + b.getWidth());
                double closestY = MathAddition.clamp(a.getY(), b.getY(), b.getY() + b.getHeight());
                return MathAddition.distance(closestX, closestY, a.getX(), a.getY()) < a.getRadius(); //When closest point on the rectangle b is within the radius of the circle a a collision has occured
            } else if (b.getShape() == HITBOX_SHAPE.CIRCLE) {
                //CIRCLE TO CIRCLE COLLISION
                return MathAddition.distance(a.getX(), a.getY(), b.getX(), b.getY()) < a.getRadius() + b.getRadius(); // If distance from a to b is smaller than their combined radius a collision has occured
            }
        }
        return false;
    }
    public double getRadius() {
        return radius;
    }
    public double getOffsetX() {
        return offsetX;
    }
    public double getOffsetY() {
        return offsetY;
    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    public double getX() {
        return offsetX + parent.getX();
    }
    public double getY() {
        return offsetY + parent.getY();
    }

    public HITBOX_SHAPE getShape() {
        return shape;
    }

    public double getLeftMostX(){
        return switch (shape) {
            case RECTANGLE -> getX();
            case CIRCLE -> getX() - getRadius();
        };
    }
    public double getTopMostY(){
        return switch (shape) {
            case RECTANGLE -> getY();
            case CIRCLE -> getY() - getRadius();
        };
    }
    public double getRightMostX(){
        return switch (shape) {
            case RECTANGLE -> getX() + getWidth();
            case CIRCLE -> getX() + getRadius();
        };
    }
    public double getDownMostY(){
        return switch (shape) {
            case RECTANGLE -> getY() + getHeight();
            case CIRCLE -> getY() + getRadius();
        };
    }
}
