package graphs.graphsearch;

import graphs.Graph;

import java.util.LinkedList;

public abstract class GraphSearch {
    private int source;
    protected boolean[] visited;
    protected int[] edgeTo;

    public GraphSearch(Graph graph, int source) {
        this.source = source;
        this.visited = new boolean[graph.getVertexCount()];
        this.edgeTo = new int[graph.getVertexCount()];
        parseGraph(graph, source);
    }

    public abstract void parseGraph(Graph graph, int source);

    public boolean hasPathTo(int dst) {
        return visited[dst];
    }

    public LinkedList<Integer> pathTo(int dst) {
        if (!hasPathTo(dst)) return null;
        LinkedList<Integer> stack = new LinkedList<>();
        int vertex = dst;
        while (vertex != source) {
            stack.push(vertex);
            vertex = edgeTo[vertex];
        }
        return stack;
    }
}
