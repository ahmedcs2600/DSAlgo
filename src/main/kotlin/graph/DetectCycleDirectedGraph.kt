package graph

import java.util.*

/*
https://www.codingninjas.com/codestudio/problems/detect-cycle-in-a-directed-graph_1062626?leftPanelTab=0&utm_source=youtube&utm_medium=affiliate&utm_campaign=Lovebabbar
* */
fun main() {

}

fun detectCycleInDirectedGraph(n: Int, edges: ArrayList<ArrayList<Int>>): Boolean {
    val adj: ArrayList<ArrayList<Int>> = createAdj(n, edges)
    val visited = IntArray(n + 1)
    for (i in 1..n) {
        if (visited[i] == 0 && bfs(n, adj, i, visited)) {
            return true
        }
    }
    return false
}

private fun createAdj(
    n: Int,
    edges: ArrayList<ArrayList<Int>>
): ArrayList<ArrayList<Int>> {
    val adj = ArrayList<ArrayList<Int>>()
    for (i in 0..n) {
        adj.add(ArrayList<Int>())
    }
    for (i in edges.indices) {
        val u = edges[i][0]
        val v = edges[i][1]
        adj[u].add(v)
        //adj.get(v).add(u);
    }
    return adj
}

private fun bfs(
    n: Int,
    adj: ArrayList<ArrayList<Int>>,
    vertex: Int,
    visited: IntArray
): Boolean {
    val queue: Queue<Int> = LinkedList()
    queue.add(vertex)
    visited[vertex] = 2
    while (!queue.isEmpty()) {
        val front = queue.poll()
        for (neighbour in adj[front]) {
            if (visited[neighbour] == 1) continue
            if (visited[neighbour] == 2) {
                return true
            }
            visited[neighbour] = 2
            queue.add(neighbour)
        }
        visited[front] = 1
    }
    return false
}

private fun dfs(
    n: Int,
    adj: ArrayList<ArrayList<Int>>,
    vertex: Int,
    visited: IntArray
): Boolean {
    if (visited[vertex] == 2) return true
    if (visited[vertex] == 1) return false
    visited[vertex] = 2
    for (neighbour in adj[vertex]) {
        if (dfs(n, adj, neighbour, visited)) {
            return true
        }
    }
    visited[vertex] = 1
    return false
}