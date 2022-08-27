package graph.stronglyconnected

import java.util.*

/**
 * https://www.codingninjas.com/codestudio/problems/count-strongly-connected-components-kosaraju-s-algorithm_1171151?leftPanelTab=1&utm_source=youtube&utm_medium=affiliate&utm_campaign=Lovebabbar
 * */
fun main() {

}

fun stronglyConnectedComponents(v: Int, edges: ArrayList<ArrayList<Int>>): Int {
    val adj: MutableMap<Int, MutableList<Int>> = HashMap()
    for (i in edges.indices) {
        val vert = edges[i][0]
        val u = edges[i][1]
        adj.putIfAbsent(vert, ArrayList())
        adj[vert]!!.add(u)
    }
    val visited = BooleanArray(v)
    val stack = Stack<Int>()
    for (i in 0 until v) {
        if (!visited[i]) {
            topoHelper(adj, i, visited, stack)
        }
    }
    val transpose: MutableMap<Int, MutableList<Int>> = HashMap()
    for (vert in 0 until v) {
        visited[vert] = false
        for (u in adj.getOrDefault(vert, ArrayList())) {
            transpose.putIfAbsent(u, ArrayList())
            transpose[u]!!.add(vert)
        }
    }
    var connectedComponents = 0
    while (!stack.isEmpty()) {
        val top = stack.pop()
        if (!visited[top]) {
            connectedComponents++
            dfs(transpose, visited, top)
        }
    }
    return connectedComponents
}

private fun dfs(
    adj: Map<Int, MutableList<Int>>,
    visited: BooleanArray,
    vertex: Int
) {
    visited[vertex] = true
    for (neighbour in adj[vertex] ?: ArrayList()) {
        if (!visited[neighbour]) {
            dfs(adj, visited, neighbour)
        }
    }
}

private fun topoHelper(
    adj: Map<Int, MutableList<Int>>,
    vertex: Int,
    visited: BooleanArray,
    stack: Stack<Int>
) {
    visited[vertex] = true
    for (neighbour in adj[vertex] ?: ArrayList()) {
        if (!visited[neighbour]) {
            topoHelper(adj, neighbour, visited, stack)
        }
    }
    stack.push(vertex)
}