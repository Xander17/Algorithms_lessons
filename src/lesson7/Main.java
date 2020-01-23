package lesson7;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdges(1, 2);
        graph.addEdges(1, 3);
        graph.addEdges(1, 4);
        graph.addEdges(2, 7);
        graph.addEdges(3, 5);
        graph.addEdges(4, 6);
        graph.addEdges(5, 7);
        graph.addEdges(5, 10);
        graph.addEdges(6, 8);
        graph.addEdges(7, 9);
        graph.addEdges(8, 9);
        graph.addEdges(9, 10);

        DepthFirstSearch dfs = new DepthFirstSearch(graph, 1);
        System.out.println(dfs.pathTo(10));

        BreadthFirstSearch bfs=new BreadthFirstSearch(graph,1);
        System.out.println(bfs.pathTo(10));
    }
}
