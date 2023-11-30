package org.example;
import java.util.*;
public abstract class Graph<T>{
    private int numVertices; // Количество вершин в графе
    protected ArrayList<Edge<T>> edges;
    protected ArrayList<Vertex<T>> vertex;
    private List<List<Edge>> adjacencyList; // списки смежность
    private int[][] adjacencyMatrix; // Матрица смежности
    private int[][] incidenceMatrix; // Матрица инцидентности

/*
    public Graph(List<Edge> edges, int numVertices) {
        this.numVertices = numVertices;
        this.adjacencyList = new ArrayList<>();
        this.adjacencyMatrix = new int[numVertices][numVertices];
        this.incidenceMatrix = new int[numVertices][numVertices];

        int n = 0;
        for (Edge e : edges) {
            n = Integer.max(n, Integer.max(e.source, e.destination));
        }
        for (int i = 0; i <= n; i++) {
            adjacencyList.add(i, new ArrayList<>());
        }
        for (Edge e: edges) {
            adjacencyList.get(e.source).add(e.destination, e.weight);
        }
    }

 */
    public Graph(int numVertices) {
        this.numVertices = numVertices;
        edges = new ArrayList<>();
        this.adjacencyList = new ArrayList<>();
        this.adjacencyMatrix = new int[numVertices][numVertices];
        this.incidenceMatrix = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            this.adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edges.add(edge);

        adjacencyList.get(source).add(edge);
        adjacencyMatrix[source][destination] = weight;
        incidenceMatrix[source][source]++;
        incidenceMatrix[source][destination]--;
    }

    public void removeEdge(int source, int destination){
        Edge edgeIndex;
        for (int i = 0; i < adjacencyList.get(source).size(); i++) {
            Edge edge = adjacencyList.get(source).get(i);
            if (edge.getDestination() == destination) {
                edgeIndex = edge;
                break;
            }
        }

        adjacencyList.get(source).remove(edgeIndex);
        adjacencyMatrix[source][destination] = 0;
        incidenceMatrix[source][source]--;
        incidenceMatrix[source][destination]++;
    }

    public void addVertex() {
        adjacencyList.add(new ArrayList<Edge>());
        numVertices++;

        int[][] newAdjacencyMatrix = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices - 1; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, newAdjacencyMatrix[i], 0, numVertices - 1);
        }
        adjacencyMatrix = newAdjacencyMatrix;

        int[][] newIncidenceMatrix = new int[numVertices][edges.size()];
        for (int i = 0; i < numVertices - 1; i++) {
            System.arraycopy(incidenceMatrix[i], 0, newIncidenceMatrix[i], 0, edges.size());
        }
        incidenceMatrix = newIncidenceMatrix;
    }

    public void removeVertex(int vertex) {
        if (vertex < 0 || vertex >= adjacencyList.size()) {
            throw new IllegalArgumentException("Invalid vertex");
        }

        adjacencyList.remove(vertex);

        int[][] newAdjacencyMatrix = new int[numVertices][numVertices];
        for (int i = 0, newRow = 0; i < numVertices + 1; i++) {
            if (i == vertex) {
                continue;
            }

            for (int j = 0, newColumn = 0; j < numVertices + 1; j++) {
                if (j == vertex) {
                    continue;
                }

                newAdjacencyMatrix[newRow][newColumn] = adjacencyMatrix[i][j];
                newColumn++;
            }

            newRow++;
        }
        adjacencyMatrix = newAdjacencyMatrix;

        int[][] newIncidenceMatrix = new int[numVertices][edges.size()];
        for (int i = 0; i < numVertices; i++) {
            int newColumn = 0;
            for (int j = 0; j < edges.size(); j++) {
                if (edges.get(j).getSource() == vertex || edges.get(j).getDestination() == vertex) {
                    continue;
                }

                newIncidenceMatrix[i][newColumn] = incidenceMatrix[i][j];
                newColumn++;
            }
        }
        incidenceMatrix = newIncidenceMatrix;
        numVertices = adjacencyList.size();
    }



    public List<Edge> getAdjacentVertices(int source) {
        return this.adjacencyList.get(source);
    }

    public int getWeight(int source, int destination) {
        return adjacencyMatrix[source][destination];
    }

    public int getIncidentEdges(int vertex) {
        int incidentEdges = 0;

        for (int i = 0; i < numVertices; i++) {
            incidentEdges += incidenceMatrix[vertex][i];
        }

        return incidentEdges;
    }
}
