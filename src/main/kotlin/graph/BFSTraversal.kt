package graph

import java.util.*
import kotlin.collections.ArrayList

/*
https://www.codingninjas.com/codestudio/problems/bfs-in-graph_973002?topList=love-babbar-dsa-sheet-problems&leftPanelTab=0&utm_source=youtube&utm_medium=affiliate&utm_campaign=Lovebabbar
 */
fun main() {
    val result = BFS(
        4,
        arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, 3),
            intArrayOf(1, 2),
            intArrayOf(2, 3),
        )
    )
}

fun BFS(vertex: Int, edges: Array<IntArray>): ArrayList<Int> {
    val adj = createAdjList(vertex, edges)
    val visited = BooleanArray(vertex)
    val ans = ArrayList<Int>()
    //printAdjList(adj);
    for (v in 0 until vertex) {
        if (!visited[v]) bfs(adj, visited, v, ans)
    }
    return ans
}

private fun printAdjList(adj: List<List<Int>>) {
    for (i in adj.indices) {
        //System.out.print(i + " -> ");
        for (item in adj[i]) {
            print("$item ")
        }
        println()
    }
}

private fun createAdjList(vertex: Int, edges: Array<IntArray>): List<MutableList<Int>> {
    val adj: MutableList<MutableList<Int>> = ArrayList()
    for (i in 0 until vertex) adj.add(ArrayList())
    for (i in edges[0].indices) {
        val u = edges[0][i]
        val v = edges[1][i]
        //System.out.println(Arrays.toString(edges[0]));
        adj[u].add(v)
        adj[v].add(u)
    }
    for (i in 0 until vertex) {
        adj[i].sort()
    }
    return adj
}

private fun bfs(adj: List<MutableList<Int>>, visited: BooleanArray, v: Int, ans: ArrayList<Int>) {
    val queue: Queue<Int> = LinkedList()
    queue.add(v)
    visited[v] = true
    while (!queue.isEmpty()) {
        val ele = queue.poll()
        ans.add(ele)
        for (newV in adj[ele]) {
            if (!visited[newV]) {
                queue.add(newV)
                visited[newV] = true
            }
        }
    }
}