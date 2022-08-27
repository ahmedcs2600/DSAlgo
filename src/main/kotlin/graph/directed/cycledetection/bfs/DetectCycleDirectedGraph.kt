package graph

import java.util.*


/*
https://www.codingninjas.com/codestudio/problems/detect-cycle-in-a-directed-graph_1062626?leftPanelTab=0&utm_source=youtube&utm_medium=affiliate&utm_campaign=Lovebabbar
* */
fun main() {

}

fun detectCycleInDirectedGraphBFS(n: Int, edges: ArrayList<ArrayList<Int>>): Boolean {
    val adj: ArrayList<ArrayList<Int>> = createAdj(edges, n)
    val inDegree = IntArray(n)

    for (i in adj.indices) {
        for (j in adj[i].indices) {
            val idx = adj[i][j]
            inDegree[idx] += 1
        }
    }

    val queue: Queue<Int> = LinkedList()

    for (i in inDegree.indices) {
        if (inDegree[i] == 0) {
            queue.add(i)
        }
    }


    var cnt = 0

    while (!queue.isEmpty()) {
        val front = queue.poll()
        cnt++
        for (neighbour in adj[front]) {
            inDegree[neighbour] -= 1
            if (inDegree[neighbour] == 0) {
                queue.add(neighbour)
            }
        }
    }


    return n != cnt
}


private fun createAdj(
    edges: ArrayList<ArrayList<Int>>,
    vertices: Int
): ArrayList<ArrayList<Int>> {
    val adj = ArrayList<ArrayList<Int>>()
    for (i in 0 until vertices) adj.add(ArrayList())
    for (i in edges.indices) {
        val u = edges[i][0] - 1
        val v = edges[i][1] - 1
        adj[u].add(v)
    }
    return adj
}