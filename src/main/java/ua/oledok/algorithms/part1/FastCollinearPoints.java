package ua.oledok.algorithms.part1;


import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private Point[][] segments;
    private LineSegment[] lineSegments;
    private int numberOfSegments;

    public FastCollinearPoints(Point[] points) {
        Point[] copy = Arrays.copyOf(points, points.length);

        validate(copy);

        this.segments = new Point[points.length * points.length][];

        find(copy);
        trim();
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        if (lineSegments == null) {
            LineSegment[] segments = new LineSegment[numberOfSegments];

            for (int i = 0; i < numberOfSegments; i++) {
                segments[i] = new LineSegment(this.segments[i][0], this.segments[i][1]);
            }

            lineSegments = segments;
            this.segments = null;
        }

        return Arrays.copyOf(lineSegments, numberOfSegments);
    }

    private void find(Point[] points) {
        int length = points.length;

        Point[] copy = Arrays.copyOfRange(points, 0, length);

        for (int i = 1; i < length; i++) {
            Point p = points[i - 1];

            Arrays.sort(copy, p.slopeOrder());

            double slope = p.slopeTo(copy[0]);
            int connected = 1;
            Point max = p;
            Point min = p;

            for (int j = 1; j < length; j++) {
                Point that = copy[j];
                double pjSlope = p.slopeTo(that);

                if (pjSlope == Double.NEGATIVE_INFINITY) {
                    continue;
                }

                if (slope == pjSlope) {
                    connected++;

                    if (max.compareTo(that) < 0) {
                        max = that;
                    }

                    if (min.compareTo(that) > 0) {
                        min = that;
                    }
                } else {
                    if (connected > 2) {
                        segments[numberOfSegments++] = new Point[]{min, max};
                    }

                    max = p.compareTo(that) > 0 ? p : that;
                    min = p.compareTo(that) < 0 ? p : that;
                    connected = 1;
                    slope = pjSlope;
                }
            }

            if (connected > 2) {
                segments[numberOfSegments++] = new Point[]{min, max};
            }
        }
    }

    private void trim() {
        if (numberOfSegments == 0) {
            return;
        }

        Point[][] pointsSegments = new Point[numberOfSegments][];
        Comparator<Point[]> segmentComparator = Comparator.nullsLast(Comparator.<Point[], Point>comparing(p -> p[0])
                .thenComparing(p -> p[1]));

        Arrays.sort(segments, segmentComparator);

        pointsSegments[0] = segments[0];

        int j = 1;

        for (int i = 1; i < numberOfSegments; i++) {
            if (segmentComparator.compare(segments[i - 1], segments[i]) != 0) {
                pointsSegments[j++] = segments[i];
            }
        }

        segments = pointsSegments;
        numberOfSegments = j;
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

    public static void main(String[] args) {
        LineSegment[] result = new FastCollinearPoints(new Point[]{
                new Point(10000, 0),
                new Point(0, 10000),
                new Point(3000, 10000),
//                new Point(3000, 8000),
//                new Point(3000, 6000),
//                new Point(3000, 5000),
                new Point(3000, 7000),
                new Point(7000, 3000),
                new Point(20000, 21000),
                new Point(3000, 4000),
                new Point(14000, 15000),
                new Point(6000, 7000)

                /////////
//                new Point(10000, 0),
//                new Point(8000, 2000),
//                new Point(2000, 8000),
//                new Point(0, 10000),
//
//                new Point(20000, 0),
//                new Point(18000, 2000),
//                new Point(2000, 18000),
//                new Point(10000, 20000),
//                new Point(30000, 0),
//                new Point(0, 30000),
//
//                new Point(20000, 10000),
//                new Point(13000, 0),
//                new Point(11000, 3000),
//                new Point(5000, 12000),
//                new Point(9000, 6000)
/////////////////
//                new Point(1000, 17000),
//                new Point(4000, 24000),
//                new Point(26000, 8000),
//                new Point(10000, 28000),
//                new Point(18000, 5000),
//                new Point(1000, 27000),
//                new Point(14000, 14000),
//                new Point(11000, 16000),
//                new Point(29000, 17000),
//                new Point(5000, 21000),
//                new Point(19000, 26000),
//                new Point(28000, 21000),
//                new Point(25000, 24000),
//                new Point(30000, 10000),
//                new Point(25000, 14000),
//                new Point(31000, 16000),
//                new Point(5000, 12000),
//                new Point(1000, 31000),
//                new Point(2000, 24000),
//                new Point(13000, 17000),
//                new Point(1000, 28000),
//                new Point(14000, 16000),
//                new Point(26000, 26000),
//                new Point(10000, 31000),
//                new Point(12000, 4000),
//                new Point(9000, 24000),
//                new Point(28000, 29000),
//                new Point(12000, 20000),
//                new Point(13000, 11000),
//                new Point(4000, 26000),
//                new Point(8000, 10000),
//                new Point(15000, 12000),
//                new Point(22000, 29000),
//                new Point(7000, 15000),
//                new Point(10000, 4000),
//                new Point(2000, 29000),
//                new Point(17000, 17000),
//                new Point(3000, 15000),
//                new Point(4000, 29000),
//                new Point(19000, 2000)
                /////////////////
//                new Point(26000, 27000),
//                new Point(24000, 23000),
//                new Point(18000, 23000),
//                new Point(22000, 9000),
//                new Point(25000, 25000),
//                new Point(1000, 2000),
//                new Point(12000, 10000),
//                new Point(22000, 17000),
//                new Point(25000, 1000),
//                new Point(15000, 1000),
//                new Point(19000, 28000),
//                new Point(12000, 3000),
//                new Point(4000, 15000),
//                new Point(2000, 7000),
//                new Point(18000, 27000),
//                new Point(9000, 26000),
//                new Point(11000, 26000),
//                new Point(6000, 16000),
//                new Point(18000, 26000),
//                new Point(24000, 30000),
//                new Point(10000, 25000),
//                new Point(7000, 10000),
//                new Point(19000, 24000),
//                new Point(6000, 0),
//                new Point(26000, 15000),
//                new Point(1000, 23000),
//                new Point(23000, 29000),
//                new Point(15000, 7000),
//                new Point(15000, 19000),
//                new Point(17000, 31000),
//                new Point(6000, 2000),
//                new Point(17000, 16000),
//                new Point(1000, 26000),
//                new Point(11000, 19000),
//                new Point(25000, 0),
//                new Point(17000, 30000),
//                new Point(16000, 22000),
//                new Point(18000, 13000),
//                new Point(3000, 23000),
//                new Point(10000, 13000),
//                new Point(1000, 9000),
//                new Point(11000, 21000),
//                new Point(29000, 19000),
//                new Point(9000, 29000),
//                new Point(30000, 3000),
//                new Point(9000, 1000),
//                new Point(5000, 29000),
//                new Point(26000, 6000)
                //////////////////
//                new Point(7453, 14118),
//                new Point(2682, 14118),
//                new Point(7821, 14118),
//                new Point(5067, 14118),
//                new Point(9972, 4652),
//                new Point(16307, 4652),
//                new Point(5766, 4652),
//                new Point(4750, 4652),
//                new Point(13291, 7996),
//                new Point(20547, 7996),
//                new Point(10411, 7996),
//                new Point(8934, 7996),
//                new Point(1888, 7657),
//                new Point(7599, 7657),
//                new Point(12772, 7657),
//                new Point(13832, 7657),
//                new Point(10375, 12711),
//                new Point(14226, 12711),
//                new Point(20385, 12711),
//                new Point(18177, 12711)
        }).segments();

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-20, 32768);
        StdDraw.setYscale(-20, 32768);

        for (LineSegment segmet : result) {
            segmet.draw();
        }

        StdDraw.show();
    }
}
