package com.cgvsu;

import com.cgvsu.model.Model;
import com.cgvsu.objreader.ObjReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {

        Path fileName = Path.of("Task3/ObjReaderInitial/tests/com/cgvsu/objreader/SimpleOperationTest.obj");
        String fileContent = Files.readString(fileName);

        System.out.println("Loading model ...");
        Model model = ObjReader.read(fileContent);

        for(int i = 0; i < 16; i++){
            System.out.println(i + "  " + model.polygons.get(i).getVertexIndices() );
        }

        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());

        SimpleOperationOnPolygons.removePolygonByIndex(model,14,true);

        for(int i = 0; i < 16; i++){
            System.out.println(i + "  " + model.polygons.get(i).getVertexIndices() );
        }

        System.out.println("\n\nVertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());
    }
}
