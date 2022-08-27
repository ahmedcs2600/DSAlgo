package graph.disjointsets;

import java.util.*;

public class KruskalAlgorithm {

    public static int minimumSpanningTree(ArrayList<ArrayList<Integer>> edges, int n) {
        Collections.sort(edges, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o1.get(2) - o2.get(2);
            }
        });
        int[] parent = new int[n];
        int[] rank = new int[n];

        makeSet(parent, rank, n);

        int minWeight = 0;

        for(int i = 0; i < edges.size(); i++) {
            //System.out.println("edges.get(i).get(0) -> " + edges.get(i).get(0));
            //System.out.println("edges.get(i).get(1) -> " + edges.get(i).get(1));
            int u = findParent(parent, edges.get(i).get(0));
            int v = findParent(parent, edges.get(i).get(1));
            int wt = edges.get(i).get(2);
            // System.out.println("u -> " + u);
            // System.out.println("v -> " + v);
            if(u != v) {
                minWeight += wt;
                unionSet(u, v, parent, rank);
            }
        }

        return minWeight;
    }

    private static void makeSet(int[] parent, int[] rank, int n) {
        for(int i = 0 ; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    private static int findParent(int[] parent, int node) {
        if(parent[node] == node) return node;

        parent[node] = findParent(parent, parent[node]);

        return parent[node];
    }

    private static void unionSet(int u, int v, int[] parent, int[] rank) {
        u = findParent(parent, u);
        v = findParent(parent, v);

        if(rank[u] < rank[v]) {
            parent[u] = v;
        } else if(rank[v] < rank[u]) {
            parent[v] = u;
        } else {
            parent[v] = u;
            rank[u]++;
        }
    }
}