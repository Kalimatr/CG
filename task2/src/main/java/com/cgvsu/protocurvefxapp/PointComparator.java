package com.cgvsu.protocurvefxapp;

import java.util.Comparator;

public class PointComparator implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
        return (int) (o1.getX() - o2.getX());
    }
}
