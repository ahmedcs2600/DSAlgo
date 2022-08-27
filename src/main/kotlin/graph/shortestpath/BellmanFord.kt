package graph.shortestpath

import java.util.*


fun main() {

}
fun bellmonFord(n: Int, m: Int, src: Int, dest: Int, edges: ArrayList<ArrayList<Int>>): Int {
    val distance = IntArray(n + 1)
    val maxN = 1e9.toInt()
    Arrays.fill(distance, maxN)
    distance[src] = 0
    for (i in 1..n) {
        for (j in 0 until m) {
            val u = edges[j][0]
            val v = edges[j][1]
            val wt = edges[j][2]
            if (distance[u] != maxN && distance[u] + wt < distance[v]) {
                distance[v] = distance[u] + wt
            }
        }
    }
    var flag = 0

    //check for negative cycle
    for (i in 0 until m) {
        val u = edges[i][0]
        val v = edges[i][1]
        val wt = edges[i][2]
        if (distance[u] != maxN && distance[u] + wt < distance[v]) {
            flag = 1
            break
        }
    }
    return if (flag == 1) -1 else distance[dest]
}