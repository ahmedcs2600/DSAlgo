package graph.shortestpath;

import java.util.*;
/*
    Time complexity: O(E*log(V))
    Space complexity: O(V^2)

    Where 'E' is the number of edges and 'V' is
    the number of vertices in a graph.
*/

/**
 * https://www.codingninjas.com/codestudio/problems/dijkstra-s-shortest-path_920469?leftPanelTab=0&utm_source=youtube&utm_medium=affiliate&utm_campaign=Lovebabbar
 * */
public class Dijkstra {

    public static ArrayList < Integer > dijkstra(ArrayList< ArrayList < Integer > > vec, int vertices, int edges, int source) {
        ArrayList<ArrayList<Pair<Integer, Integer>>> adj = new ArrayList<>();

        for(int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < vec.size(); i++) {
            int u = vec.get(i).get(0);
            int v = vec.get(i).get(1);
            int w = vec.get(i).get(2);

            adj.get(u).add(new Pair(v, w));
            adj.get(v).add(new Pair(u, w));
        }

        PriorityQueue < Pair < Integer, Integer > > pq = new PriorityQueue < Pair < Integer, Integer >>(vertices, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.first - o2.first;
            }
        });

        boolean[] visited = new boolean[vertices];

        ArrayList<Integer> distances = new ArrayList<>(vertices);

        for(int i = 0; i < vertices; i++) {
            distances.add(Integer.MAX_VALUE);
        }

        distances.set(source, 0);

        pq.add(new Pair(0, source));

        while(!pq.isEmpty()) {
            int u = pq.poll().second;
            visited[u] = true;

            for(Pair<Integer, Integer> neighbour : adj.get(u)) {
                int v = neighbour.first;
                int distance = neighbour.second;

                if(!visited[v] && distances.get(v) > distances.get(u) + distance) {
                    distances.set(v, distances.get(u) + distance);
                    pq.add(new Pair(distances.get(u) + distance, v));
                }
            }
        }

        return distances;
    }

    static class Pair<A, B> {
        A first;
        B second;

        Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }
}