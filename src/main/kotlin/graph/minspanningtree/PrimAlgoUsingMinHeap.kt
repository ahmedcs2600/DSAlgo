package graph.minspanningtree

import java.util.*


fun calculatePrimsMSTUsingMinHeap(n: Int, m: Int, g: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>>? {
    val adj: MutableMap<Int, MutableList<Pair<Int, Int>>> = HashMap()
    for (i in g.indices) {
        val u = g[i][0]
        val v = g[i][1]
        val w = g[i][2]
        adj.putIfAbsent(u, ArrayList())
        adj.putIfAbsent(v, ArrayList())
        adj[u]!!.add(Pair(v, w))
        adj[v]!!.add(Pair(u, w))
    }
    val key = IntArray(n + 1)
    val mst = BooleanArray(n + 1)
    val parent = IntArray(n + 1)
    Arrays.fill(key, Int.MAX_VALUE)
    Arrays.fill(parent, -1)
    key[1] = 0
    val minHeap: PriorityQueue<Pair<Int, Int>> = PriorityQueue { (first), (first1) -> first - first1 }
    minHeap.add(Pair(0, 1))
    //find min
    while (!minHeap.isEmpty()) {
        val u: Int = minHeap.poll().second
        mst[u] = true

        //check adj nodes
        for ((v, w) in adj[u]!!) {
            if (key[v] > w && !mst[v]) {
                key[v] = w
                parent[v] = u
                minHeap.add(Pair(w, v))
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
