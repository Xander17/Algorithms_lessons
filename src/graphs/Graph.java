package graphs;

import java.util.LinkedList;

public class Graph {
    private LinkedList<Integer>[] adjList;
    private int vertexCount;
    private int edgeCount;

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount + 1;
        this.edgeCount = 0;
        adjList = new LinkedList[vertexCount + 1];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public LinkedList<Integer> getAdjList(int v) {
        return (LinkedList<Integer>) adjList[v].clone();
    }

    public void addEdges(int v1, int v2) {
        if (v1 <= 0 || v2 <= 0 || v1 > vertexCount || v2 > vertexCount) throw new IllegalArgumentException();
        edgeCount++;
        if (v1 == v2) {
            adjList[v1].add(v1);
            return;
        }
        adjList[v1].add(v2);
        adjList[v2].add(v1);
    }
}
