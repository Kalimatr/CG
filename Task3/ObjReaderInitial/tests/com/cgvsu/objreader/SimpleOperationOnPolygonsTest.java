package com.cgvsu.objreader;

import com.cgvsu.SimpleOperationOnPolygons;
import com.cgvsu.model.Model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleOperationOnPolygonsTest {


    Path fileName = Path.of("Task3/ObjReaderInitial/tests/com/cgvsu/objreader/SimpleOperationTest.obj");
    String fileContent;
    {
        try {
            fileContent = Files.readString(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDeletePolygonButNoVertices() {
        Model model = ObjReader.read(fileContent);
        int verticesSizeOld = model.vertices.size();
        int polygonsSizeOld = model.polygons.size();

        SimpleOperationOnPolygons.removePolygonByIndex(model, 4, false);

        int verticesSizeNew = model.vertices.size();
        int polygonsSizeNew = model.polygons.size();

        Assertions.assertNotNull(verticesSizeOld);
        Assertions.assertNotNull(polygonsSizeOld);

        Assertions.assertEquals(verticesSizeNew, verticesSizeOld);
        Assertions.assertNotEquals(polygonsSizeOld, polygonsSizeNew);
    }

    @Test
    public void testDeletePolygonAndVertices() {
        Model model = ObjReader.read(fileContent);

        int verticesSizeOld = model.vertices.size();
        int polygonsSizeOld = model.polygons.size();

        SimpleOperationOnPolygons.removePolygonByIndex(model, 4, true);

        int verticesSizeNew = model.vertices.size();
        int polygonsSizeNew = model.polygons.size();

        Assertions.assertNotNull(verticesSizeOld);
        Assertions.assertNotNull(polygonsSizeOld);

        Assertions.assertNotEquals(verticesSizeNew, verticesSizeOld);
        Assertions.assertNotEquals(polygonsSizeOld, polygonsSizeNew);
    }
}
