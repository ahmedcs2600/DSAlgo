package graph

import java.util.*


fun main() {

}

fun topologicalSort(edges: ArrayList<ArrayList<Int>>, v: Int, e: Int): ArrayList<Int>? {
    val adj = createAdj(edges, v)
    val visited = BooleanArray(v)
    val stack = Stack<Int>()
    for (i in 0 until v) {
        if (!visited[i]) {
            dfs(adj, visited, stack, i)
        }
    }
    val result = ArrayList<Int>()
    while (!stack.isEmpty()) {
        result.add(stack.pop())
    }
    return result
}

private fun dfs(
    adj: ArrayList<ArrayList<Int>>,
    visited: BooleanArray,
    stack: Stack<Int>,
    vertex: Int
) {
    visited[vertex] = true
    for (neighbour in adj[vertex]) {
        if (!visited[neighbour]) {
            dfs(adj, visited, stack, neighbour)
        }
    }
    stack.push(vertex)
}

private fun createAdj(
    edges: ArrayList<ArrayList<Int>>,
    vertices: Int
): ArrayList<ArrayList<Int>> {
    val adj = ArrayList<ArrayList<Int>>()
    for (i in 0 until vertices) adj.add(ArrayList())
    for (i in edges.indices) {
        val u = edges[i][0]
        val v = edges[i][1]
        adj[u].add(v)
    }
    return adj
}