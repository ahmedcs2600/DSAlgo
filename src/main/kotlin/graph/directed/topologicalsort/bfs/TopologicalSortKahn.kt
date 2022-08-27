package graph

import java.util.*

/*
* https://www.codingninjas.com/codestudio/problems/topological-sort_982938?topList=love-babbar-dsa-sheet-problems&leftPanelTab=1&utm_source=youtube&utm_medium=affiliate&utm_campaign=Lovebabbar
* */
fun main() {

}

fun topologicalSortBFS(edges: ArrayList<ArrayList<Int>>, v: Int, e: Int): ArrayList<Int> {
    val adj = createAdj(edges, v)

    //ArrayList<Integer> inDegree = new ArrayList<>();
    val inDegree = IntArray(v)
    val ans = ArrayList<Int>()
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
    while (!queue.isEmpty()) {
        val front = queue.poll()
        ans.add(front)
        for (neighbour in adj[front]) {
            inDegree[neighbour] -= 1
            if (inDegree[neighbour] == 0) {
                queue.add(neighbour)
            }
        }
    }
    return ans
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