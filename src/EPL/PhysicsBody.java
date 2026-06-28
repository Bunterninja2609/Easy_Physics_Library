package EPL;

/** @author joshuabecker
 *
 */
public class PhysicsBody {
    private Collider collider;
    private double x, y;
    private double vx, vy;

    public PhysicsBody(Collider collider, double x, double y) {
        this.collider = collider;
        this.x = x;
        this.y = y;
        PhysicsManager.reqister(this);
    }

    public void setVelocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }
    /** Moves the PhysicsBody on the X-Axis, then checks for collisions and moves the Body back until it doesn't collide anymore
     *
     * @param x Amount to move on the X-Axis by
     */
    private void moveX(double x) {
        if (x == 0) return;
        this.x += x;

        if (this.collider == null) return;

        collider.setX(this.x);
        double dir = Math.signum(x);
        double stepFraction = 0.5;
        if(PhysicsManager.isColliding(this)){

            while (stepFraction * x >= PhysicsManager.PRECISION) {
                //Use Binary Search to find the point of no collision
                if (PhysicsManager.isColliding(this)) {
                    //move backwards in case of collision
                    this.x -= stepFraction * x;
                } else {
                    //move forwards in case of no collision
                    this.x += stepFraction * x;
                }
                collider.setX(this.x);
                stepFraction /= 2;
            }
            if(PhysicsManager.isColliding(this)){
                //in case a collision still occurs move backwards one step
                this.x -= dir*PhysicsManager.PRECISION;
            }
        }
    }

    /** Moves the PhysicsBody on the Y-Axis, then checks for collisions and moves the Body back until it doesn't collide anymore
     *
     * @param y Amount to move on the Y-Axis by
     */
    private void moveY(double y) {
        if (y == 0) return;
        this.y += y;

        if (this.collider == null) return;

        collider.setY(this.y);
        double dir = Math.signum(y);
        double stepFraction = 0.5;
        if(PhysicsManager.isColliding(this)){
            //Use Binary Search to find the point of no collision
            while (stepFraction * y >= PhysicsManager.PRECISION) {
                if (PhysicsManager.isColliding(this)) {
                    //move backwards in case of collision
                    this.y -= stepFraction * y;
                } else {
                    //move forwards in case of no collision
                    this.y += stepFraction * y;
                }
                stepFraction /= 2;
            }
            if(PhysicsManager.isColliding(this)){
                //in case a collision still occurs move backwards one step
                this.y -= dir*PhysicsManager.PRECISION;
            }
        }
    }
    public void update(double dt) {
        moveX(vx*dt);
        moveY(vy*dt);
    }

    public boolean collidesWith(PhysicsBody b) {
        return this.collider.collidesWith(b.getCollider());
    }
    public Collider getCollider() {
        return collider;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setCollider(Collider collider) {
        this.collider = collider;
        collider.setX(x);
        collider.setY(y);
    }
}
