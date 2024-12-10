package com.cgvsu.model;

import java.util.ArrayList;

public class Polygon {

    private ArrayList<Integer> vertexIndices;
    private ArrayList<Integer> textureVertexIndices;
    private ArrayList<Integer> normalIndices;


    public Polygon() {
        vertexIndices = new ArrayList<Integer>();
        textureVertexIndices = new ArrayList<Integer>();
        normalIndices = new ArrayList<Integer>();
    }

    public void setVertexIndices(ArrayList<Integer> vertexIndices) {
        try {
            assert vertexIndices.size() >= 3;
        }catch (AssertionError e){
            throw new AssertionError("Too few vertx indexes");
        }
        this.vertexIndices = vertexIndices;
    }

    public void setTextureVertexIndices(ArrayList<Integer> textureVertexIndices) {

        try {
            assert textureVertexIndices.size() >= 3 || textureVertexIndices.isEmpty();
        }catch (AssertionError e){
            throw new AssertionError("Not all vertex have texture");
        }
        this.textureVertexIndices = textureVertexIndices;
    }

    public void setNormalIndices(ArrayList<Integer> normalIndices) {
        try {
            assert normalIndices.size() >= 3 || normalIndices.isEmpty();
        }catch (AssertionError e){
            throw new AssertionError("Not all polygon have normal");
        }

        this.normalIndices = normalIndices;
    }

    public ArrayList<Integer> getVertexIndices() {
        return vertexIndices;
    }

    public ArrayList<Integer> getTextureVertexIndices() {
        return textureVertexIndices;
    }

    public ArrayList<Integer> getNormalIndices() {
        return normalIndices;
    }
}
