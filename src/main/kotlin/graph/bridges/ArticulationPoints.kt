package graph.bridges

fun main() {
    val v = 5
    val adj = ArrayList<ArrayList<Int>>()

    for(i in 0 until v){
        adj.add(ArrayList())
    }
    addEdge(adj, 1, 0)
    addEdge(adj, 0, 4)
    addEdge(adj, 1, 4)
    addEdge(adj, 2, 3)
    addEdge(adj, 2, 4)
    addEdge(adj, 3, 4)
    AP(adj, v)
}

private fun addEdge(adj: ArrayList<ArrayList<Int>>, u: Int, v: Int) {
    adj[u].add(v)
    adj[v].add(u)
}

private class Time {
    var value: Int = 0
}

private fun AP(adj: ArrayList<ArrayList<Int>>, v: Int) {
    val discovery = IntArray(v) { -1 }
    val low = IntArray(v) { -1 }
    val visited = BooleanArray(v) { false }

    val ap = IntArray(v) { 0 }

    var timer = Time()

    for (vertex in 0 until v) {
        if(!visited[vertex]) dfs(adj, discovery, low, visited, ap, -1, vertex, timer)
    }

    for (i in 0 until v) {
        if (ap[i] == 1) {
            println(i)
        }
    }
}

private fun dfs(
    adj: ArrayList<ArrayList<Int>>,
    discovery: IntArray,
    low: IntArray,
    visited: BooleanArray,
    ap: IntArray,
    parent: Int,
    vertex: Int,
    t : Time
) {
    t.value++
    discovery[vertex]  = t.value
    low[vertex] = t.value

    visited[vertex] = true

    var child = 0
    for(neighbour in adj[vertex]) {

        if(neighbour == parent) continue

        if(!visited[neighbour]) {
            child++
            dfs(adj, discovery, low, visited, ap, vertex, neighbour, t)
            low[vertex] = Math.min(low[neighbour], low[vertex])

            if(low[neighbour] >= discovery[vertex] && parent != -1) {
                ap[vertex] = 1
            }
        } else {
            low[vertex] = Math.min(discovery[neighbour], low[vertex])
        }
    }

    if(parent != -1 && child > 1) {
        ap[vertex] = 1
    }
}