package org.algos.closest;

public class point {
    public final double x;
    public final double y;

    public point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double dist2(point other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return dx*dx + dy*dy;
    }
}
