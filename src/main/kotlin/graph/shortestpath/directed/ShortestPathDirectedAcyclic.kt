package graph.shortestpath.directed

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val graph = Graph()
    graph.addEdge(0, 1, 5)
    graph.addEdge(0, 2, 3)
    graph.addEdge(1, 2, 2)
    graph.addEdge(1, 3, 6)
    graph.addEdge(2, 3, 7)
    graph.addEdge(2, 4, 4)
    graph.addEdge(2, 5, 2)
    graph.addEdge(3, 4, -1)
    graph.addEdge(4, 5, -2)
    graph.printAdj()

    val n = 6
    val visited = hashMapOf<Int, Boolean>()
    val topo = Stack<Int>()

    for (i in 0 until n) {
        graph.dfs(i, visited, topo)
    }

    val src = 1
    val dist = IntArray(n) { Int.MAX_VALUE }

    graph.getShortestPath(src, dist, topo)

    println(dist.map {  it  })
}

class Graph {
    val adj = HashMap<Int, ArrayList<Pair<Int, Int>>>()

    fun addEdge(u: Int, v: Int, weight: Int) {
        adj.putIfAbsent(u, arrayListOf())
        adj[u]?.add(Pair(v, weight))
    }

    fun printAdj() {
        for ((key, children) in adj) {
            print("$key -> ")
            for (child in children) {
                val (value, weight) = child
                print("($value,$weight) ")
            }
            println()
        }
    }

    fun dfs(node: Int, visited: HashMap<Int, Boolean>, topo: Stack<Int>) {
        visited[node] = true

        for (neighbour in adj.getOrDefault(node, arrayListOf())) {
            if (!visited.getOrDefault(neighbour.first , false)) {
                dfs(neighbour.first, visited, topo)
            }
        }

        topo.push(node)
    }

    fun getShortestPath(src: Int, dist: IntArray, topo: Stack<Int>) {
        dist[src] = 0

        while(topo.isNotEmpty()) {
            val top = topo.pop()
            if (dist[top] != Int.MAX_VALUE) {
                for (neighbour in adj.getOrDefault(top, arrayListOf())) {
                    if (dist[top] + neighbour.second < dist[neighbour.first]) {
                        dist[neighbour.first] = dist[top] + neighbour.second
                    }
                }
            }
        }
    }
}