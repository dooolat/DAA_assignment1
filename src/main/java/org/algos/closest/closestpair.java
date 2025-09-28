package org.algos.closest;

import org.algos.metrics.metrics;
import java.util.Arrays;
import java.util.Comparator;

public class closestpair {

    public static double closest(point[] pts, metrics metrics) {
        if (pts == null || pts.length < 2) return Double.POSITIVE_INFINITY;

        point[] xs = pts.clone();
        Arrays.sort(xs, Comparator.comparingDouble(p -> p.x));

        if (metrics != null) metrics.addAllocations(xs.length);
        point[] aux = new point[pts.length];

        return Math.sqrt(rec(xs, aux, 0, pts.length - 1, metrics));
    }

    private static double rec(point[] xs, point[] aux, int left, int right, metrics metrics) {
        if (left >= right) return Double.POSITIVE_INFINITY;
        if (metrics != null) metrics.enterRecursion();

        int n = right - left + 1;
        if (n <= 3) {
            double best = Double.POSITIVE_INFINITY;
            for (int i = left; i <= right; i++) {
                for (int j = i+1; j <= right; j++) {
                    if (metrics != null) metrics.incComparisons();
                    best = Math.min(best, xs[i].dist2(xs[j]));
                }
            }
            if (metrics != null) metrics.exitRecursion();
            return best;
        }

        int mid = left + (right - left) / 2;
        double midx = xs[mid].x;

        double dl = rec(xs, aux, left, mid, metrics);
        double dr = rec(xs, aux, mid+1, right, metrics);
        double d = Math.min(dl, dr);

        double sqrtD = Math.sqrt(d);
        int k = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(xs[i].x - midx) <= sqrtD) {
                aux[k++] = xs[i];
            }
        }

        Arrays.sort(aux, 0, k, Comparator.comparingDouble(p -> p.y));
        if (metrics != null) metrics.addAllocations(k);

        for (int i = 0; i < k; i++) {
            for (int j = i+1; j < k && (aux[j].y - aux[i].y) <= sqrtD; j++) {
                if (metrics != null) metrics.incComparisons();
                d = Math.min(d, aux[i].dist2(aux[j]));
                sqrtD = Math.sqrt(d);
            }
        }

        if (metrics != null) metrics.exitRecursion();
        return d;
    }

    public static double bruteforce(point[] pts) {
        if (pts == null || pts.length < 2) return Double.POSITIVE_INFINITY;
        double best = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i+1; j < pts.length; j++) {
                best = Math.min(best, pts[i].dist2(pts[j]));
            }
        }
        return Math.sqrt(best);
    }
}
