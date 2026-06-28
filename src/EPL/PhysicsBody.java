package EPL;

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
    private void moveX(double x) {
        this.x += x;
        if(PhysicsManager.isColliding(this)){
            this.x -= x*0.1;
        }
    }
    private void moveY(double y) {
        this.y += y;
        if(PhysicsManager.isColliding(this)){
            this.y -= y*0.1;
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
}
