package ua.oledok.algorithms.part1;


import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;
    private int numberOfSegments;

    public BruteCollinearPoints(Point[] points) {
        Point[] copy = Arrays.copyOf(points, points.length);

        validate(copy);

        this.lineSegments = new LineSegment[points.length > 3 ? points.length - 3 : 1];

        find(copy);
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(lineSegments, numberOfSegments);
    }

    private void find(Point[] points) {
        int length = points.length;

        for (int i = 0; i < length; i++) {
            Point p = points[i];

            for (int j = i + 1; j < length; j++) {
                Point q = points[j];

                for (int k = j + 1; k < length; k++) {
                    Point r = points[k];

                    for (int l = k + 1; l < length; l++) {
                        Point s = points[l];
                        double pqSlope = p.slopeTo(q);
                        double prSlope = p.slopeTo(r);
                        double psSLope = p.slopeTo(s);

                        if (pqSlope == prSlope && pqSlope == psSLope) {
                            Point[] collinear = new Point[]{p, q, r, s};

                            Arrays.sort(collinear);

                            lineSegments[numberOfSegments++] = new LineSegment(collinear[0], collinear[3]);
                        }
                    }
                }
            }
        }
    }

    private void validate(Point[] points) {
        int length = points.length;

        Arrays.sort(points);

        for (int i = 1; i < length; i++) {
            if (points[i - 1].compareTo(points[i]) == 0) {
                throw new IllegalArgumentException("Points contain a repeated point");
            }
        }
    }
}
