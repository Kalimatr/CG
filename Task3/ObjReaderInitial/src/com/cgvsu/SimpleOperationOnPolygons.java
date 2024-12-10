package com.cgvsu;

import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;

import java.util.ArrayList;
import java.util.List;

public class SimpleOperationOnPolygons {

    public static void removePolygonByIndex(Model model, int polygonIndex, boolean DeleteVertices) {
        if (polygonIndex >= model.polygons.size() || polygonIndex < 0) {
            throw new IndexOutOfBoundsException("Полигон с таким индексом не существует");
        }

        Polygon removedPolygon = model.polygons.remove(polygonIndex);

        if (checkFreeVertices(model, removedPolygon)) {
            if (DeleteVertices) {
                deleteFreeVertices(model);
            }
        }
        else {System.out.println("Cвободных вершин, связанных с удаленным полигоном, нет");}
    }

    private static boolean checkFreeVertices(Model model, Polygon removedPolygon) {
        ArrayList<Integer> usedVertexIndices = getUsedVertexIndices(model);
        ArrayList<Integer> removedVertexIndices = new ArrayList<>(removedPolygon.getVertexIndices());

        for (int index : removedVertexIndices) {
            if (!usedVertexIndices.contains(index)) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<Integer> getUsedVertexIndices(Model model) {
        ArrayList<Integer> usedVertexIndices = new ArrayList<>();

        for (Polygon polygon : model.polygons) {
            usedVertexIndices.addAll(polygon.getVertexIndices());
        }

        return usedVertexIndices;
    }

    private static void deleteFreeVertices(Model model) {
        List<Integer> usedVertexIndices = getUsedVertexIndices(model);
        List<Vector3f> verticesToRemove = new ArrayList<>();

        for (int i = 0; i < model.vertices.size(); i++) {
            Vector3f vertex = model.vertices.get(i);
            if (!usedVertexIndices.contains(i)) {
                verticesToRemove.add(vertex);
            }
        }

        for (Vector3f vertex : verticesToRemove) {
            model.vertices.remove(vertex);
        }
    }
}
