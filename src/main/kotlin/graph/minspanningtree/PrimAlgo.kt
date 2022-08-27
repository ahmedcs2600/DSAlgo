package graph.minspanningtree

import java.util.*



fun calculatePrimsMST(n: Int, m: Int, g: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>> {
    val adj: MutableMap<Int, MutableList<Pair<Int, Int>>> = HashMap()
    for (i in g.indices) {
        val u = g[i][0]
        val v = g[i][1]
        val w = g[i][2]
        adj.putIfAbsent(u, ArrayList())
        adj.putIfAbsent(v, ArrayList())
        adj[u]!!.add(Pair(v, w))
        adj[v]!!.add(Pair<Int, Int>(u, w))
    }
    val key = IntArray(n + 1)
    val mst = BooleanArray(n + 1)
    val parent = IntArray(n + 1)
    Arrays.fill(key, Int.MAX_VALUE)
    Arrays.fill(parent, -1)
    key[1] = 0

    //find min
    for (i in 1..n) {
        var min = Int.MAX_VALUE
        var u = -1
        for (j in 1..n) {
            if (!mst[j] && min > key[j]) {
                min = key[j]
                u = j
            }
        }
        mst[u] = true

        //check adj nodes
        for (neighbour in adj[u]!!) {
            val v = neighbour.first
            val w = neighbour.second
            if (key[v] > w && !mst[v]) {
                key[v] = w
                parent[v] = u
            }
        }
    }
    val result = ArrayList<ArrayList<Int>>()

    //create result
    for (i in 2..n) {
        val ll = ArrayList<Int>()
        ll.add(parent[i])
        ll.add(i)
        ll.add(key[i])
        result.add(ll)
    }
    return result
}
