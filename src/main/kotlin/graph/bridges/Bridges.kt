package graph.bridges


fun findBridges(edges: Array<IntArray>, v: Int, e: Int): List<List<Int>> {
    val adj: MutableMap<Int, MutableList<Int>> = HashMap()
    val result: MutableList<List<Int>> = ArrayList()
    for (i in edges.indices) {
        val a = edges[i][0]
        val b = edges[i][1]
        adj.putIfAbsent(a, ArrayList())
        adj.putIfAbsent(b, ArrayList())
        adj[a]!!.add(b)
        adj[b]!!.add(a)
    }
    val timer = Timer()
    timer.value = 0
    val discovery = IntArray(v)
    val low = IntArray(v)
    val parent = -1
    val visited = BooleanArray(v)
    for (i in 0 until v) {
        if (!visited[i]) {
            dfs(i, adj, discovery, low, -1, visited, timer, result)
        }
    }
    return result
}

private fun dfs(
    node: Int,
    adj: Map<Int, MutableList<Int>>,
    discovery: IntArray,
    low: IntArray,
    parent: Int,
    visited: BooleanArray,
    timer: Timer,
    result: MutableList<List<Int>>
) {
    visited[node] = true
    low[node] = timer.value++
    discovery[node] = low[node]
    for (neighbour in adj[node] ?: ArrayList()) {
        if (neighbour == parent) continue
        if (!visited[neighbour]) {
            dfs(neighbour, adj, discovery, low, node, visited, timer, result)
            low[node] = Math.min(low[node], low[neighbour])
            //check edge is bridge
            if (low[neighbour] > discovery[node]) {
                val l: MutableList<Int> = ArrayList()
                l.add(node)
                l.add(neighbour)
                result.add(l)
            }
        } else {
            //Back edge exists
            low[node] = Math.min(low[node], low[neighbour])
        }
    }
}

private class Timer {
    var value = 0
}
