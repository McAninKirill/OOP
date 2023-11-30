package org.example;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyList<T> extends Graph<T>{
    private ArrayList<ArrayList<Vertex<T>>> graph = new ArrayList<>();

    public void adjaecncyList(ArrayList<Vertex<T>> vertex, ArrayList<Edge<T>> edges){
        this.vertex = vertex;
        this.edges = edges;

        int numVertices = vertex.size();
        for (int i = 0; i < numVertices; i++) {
            this.graph.add(new ArrayList<Vertex<T>>());
        }


    }
}
