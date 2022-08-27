package graph

import java.util.*

/*
https://www.codingninjas.com/codestudio/problems/cycle-detection-in-undirected-graph_1062670?topList=love-babbar-dsa-sheet-problems&leftPanelTab=0&utm_source=youtube&utm_medium=affiliate&utm_campaign=Lovebabbar
* */
fun main() {

}


fun cycleDetectionDFS(edges: Array<IntArray>, n: Int, m: Int): String {
    val adj = createAdjList(n, edges)
    val parent: MutableMap<Int, Int> = HashMap()
    val visited = BooleanArray(n + 1)
    //System.out.println("adj -> " + adj.size());
    for (i in 0..n) {
        if (!visited[i]) {
            parent[i] = -1
            if (isCyclicDFS(adj, i, visited, parent)) {
                return "Yes"
            }
        }
    }
    return "No"
}

private fun isCyclicDFS(
    adj: ArrayList<ArrayList<Int>>,
    source: Int,
    visited: BooleanArray,
    parent: MutableMap<Int, Int>
): Boolean {
    visited[source] = true
    for (neighbour in adj[source]) {
        if (visited[neighbour] && neighbour != parent[source]) {
            return true
        } else if (!visited[neighbour]) {
            parent[neighbour] = source
            if (isCyclicDFS(adj, neighbour, visited, parent)) {
                return true
            }
        }
    }
    return false
}


private fun createAdjList(
    n: Int,
    edges: Array<IntArray>
): ArrayList<ArrayList<Int>> {
    val result = ArrayList<ArrayList<Int>>()
    for (i in 0..n) result.add(ArrayList())
    for (i in edges.indices) {
        val u = edges[i][0]
        val v = edges[i][1]
        result[u].add(v)
        result[v].add(u)
    }
    return result
}