package EPL;

import java.util.ArrayList;

public class Physics {
    private static ArrayList<PhysicsBody> bodies = new ArrayList<PhysicsBody>();
    public static void reqister(PhysicsBody b) {
        bodies.add(b);
    }
    public static void unregister(PhysicsBody b) {
        bodies.remove(b);
    }
}
