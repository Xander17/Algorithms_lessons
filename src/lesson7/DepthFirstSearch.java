package lesson7;

import lesson7.graphsearch.GraphSearch;

public class DepthFirstSearch extends GraphSearch {

    public DepthFirstSearch(Graph graph, int source) {
        super(graph, source);
    }

    @Override
    public void parseGraph(Graph graph, int vertex) {
        visited[vertex] = true;
        for (int nextVertex : graph.getAdjList(vertex)) {
            if (!visited[nextVertex]) {
                edgeTo[nextVertex] = vertex;
                parseGraph(graph, nextVertex);
            }
        }
    }
}
