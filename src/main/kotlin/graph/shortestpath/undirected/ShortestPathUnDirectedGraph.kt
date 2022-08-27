package graph.shortestpath.undirected
import java.util.*

fun main() {
}

fun shortestPath(edges: Array<IntArray>, n: Int, m: Int, s: Int, t: Int): LinkedList<Int> {
    val adj = createAdj(edges, n)
    val visited = BooleanArray(n)
    val parent: MutableMap<Int, Int> = HashMap()
    bfs(s - 1, adj, visited, parent)
    var currVertex = t - 1
    val ans = LinkedList<Int>()
    while (currVertex != -1) {
        //System.out.println("currVertex -> " + currVertex);
        ans.addFirst(currVertex + 1)
        currVertex = parent.getOrDefault(currVertex, -1)
    }
    return ans
}

private fun bfs(vertex: Int, adj: ArrayList<ArrayList<Int>>, visited: BooleanArray, parent: MutableMap<Int, Int>) {
    val queue: Queue<Int> = LinkedList()
    queue.add(vertex)
    visited[vertex] = true
    parent[vertex] = -1
    while (!queue.isEmpty()) {
        val front = queue.poll()
        for (neighbour in adj[front]) {
            if (!visited[neighbour]) {
                queue.add(neighbour)
                parent[neighbour] = front
                visited[neighbour] = true
            }
        }
    }
}

private fun createAdj(edges: Array<IntArray>, n: Int): ArrayList<ArrayList<Int>> {
    val adj = ArrayList<ArrayList<Int>>()
    for (i in 0 until n) {
        adj.add(ArrayList())
    }
    for (i in edges.indices) {
        val u = edges[i][0] - 1
        val v = edges[i][1] - 1
        adj[u].add(v)
        adj[v].add(u)
    }
    return adj
}