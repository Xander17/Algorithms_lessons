package lesson7;

import lesson7.graphsearch.GraphSearch;

import java.util.LinkedList;

public class BreadthFirstSearch extends GraphSearch {

    public BreadthFirstSearch(Graph graph, int source) {
        super(graph, source);
    }

    @Override
    public void parseGraph(Graph graph, int source) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(source);
        visited[source] = true;
        while (!queue.isEmpty()) {
            int vertex = queue.removeFirst();
            for (int w : graph.getAdjList(vertex)) {
                if (!visited[w]) {
                    visited[w] = true;
                    edgeTo[w] = vertex;
                    queue.addLast(w);
                }
            }
        }
    }
}
