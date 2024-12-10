package com.cgvsu.protocurvefxapp;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static com.cgvsu.protocurvefxapp.Lagrange.createLagrangePolynomial;

public class ProtoCurveController {
    ArrayList<Double> x = new ArrayList<>();
    ArrayList<Double> y = new ArrayList<>();

    @FXML
    AnchorPane anchorPane;
    GraphicsContext graphicsContext;
    @FXML
    private Canvas canvas;
    private final List<Point> points = new ArrayList<>();
    private Point selectedPoint;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));
        graphicsContext = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(this::mousePressed);
        canvas.setOnMouseDragged(this::mouseDragged);
        canvas.setOnMouseReleased(this::mouseReleased);
    }

    private void mousePressed(MouseEvent event) {
        selectedPoint = findNearestPoint(event.getX(), event.getY());
        if (selectedPoint != null) {
            selectedPoint.setDragging(true);
        } else {
            points.add(new Point(event.getX(), event.getY()));
            DrawLagrange();
        }
    }

    private void DrawLagrange() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        PointComparator pointComparator = new PointComparator();
        Collections.sort(points, pointComparator);

        double[] X = new double[points.size()];
        double[] Y = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            X[i] = points.get(i).getX();
            Y[i] = points.get(i).getY();
        }
        final int POINT_RADIUS = 4;
        for (int i = 0; i < points.size(); i++) {
            graphicsContext.fillOval(points.get(i).getX() - POINT_RADIUS, points.get(i).getY() - POINT_RADIUS, 2 * POINT_RADIUS, 2 * POINT_RADIUS);
        }

        Function<Double, Double> lagrangePolynomial = createLagrangePolynomial(X, Y);

        for (double t = X[0]; t <= X[X.length - 1] - 0.01; t += 0.01) {
            double ysp = lagrangePolynomial.apply(t);
            double yp = lagrangePolynomial.apply(t + 0.01);
            graphicsContext.strokeLine((int) t ,ysp, (int) t + 0.01, (int) yp);
        }
    }

    private void mouseDragged(MouseEvent event) {
        if (selectedPoint != null && selectedPoint.isDragging()) {
            selectedPoint.setX(event.getX());
            selectedPoint.setY(event.getY());
            DrawLagrange();
        }
    }

    private void mouseReleased(MouseEvent event) {
        if (selectedPoint != null) {
            selectedPoint.setDragging(false);
            selectedPoint = null;
        }
    }

    private Point findNearestPoint(double x, double y) {
        for (Point point : points) {
            if (point.contains(x, y)) {
                return point;
            }
        }
        return null;
    }

}