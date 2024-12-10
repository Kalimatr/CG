package com.cgvsu.protocurvefxapp;

import javafx.scene.canvas.GraphicsContext;

public class Point {
    private double x;
    private double y;
    private boolean dragging;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.dragging = false;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isDragging() {
        return dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public void draw(GraphicsContext gc) {
        gc.fillOval(x - 5, y - 5, 10, 10);
    }

    public boolean contains(double x, double y) {
        return Math.abs(this.x - x) <= 5 && Math.abs(this.y - y) <= 5;
    }

    public void drawSmallCircle(GraphicsContext gc) {
        gc.fillOval(x - 2, y - 2, 4, 4);
    }
}

