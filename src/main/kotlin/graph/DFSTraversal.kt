package graph


/*

* */
fun main() {
   // val result = depthFirstSearch(5, )
}

fun depthFirstSearch(v: Int, e: Int, edges: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>>? {
    val ans = ArrayList<ArrayList<Int>>()
    val visited = BooleanArray(v)
    val adjList = createAdjList(v, edges)
    for (vertex in 0 until v) {
        if (!visited[vertex]) {
            val subAns = ArrayList<Int>()
            ans.add(subAns)
            dfs(adjList, vertex, subAns, visited)
        }
    }
    return ans
}

private fun createAdjList(vertices: Int, edges: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>> {
    val result = ArrayList<ArrayList<Int>>()
    for (i in 0 until vertices) result.add(ArrayList())
    for (i in edges.indices) {
        val u = edges[i][0]
        val v = edges[i][1]
        result[u].add(v)
        result[v].add(u)
    }
    return result
}

private fun dfs(edges: ArrayList<ArrayList<Int>>, vertex: Int, ans: ArrayList<Int>, visited: BooleanArray) {
    visited[vertex] = true
    for (newVertex in edges[vertex]) {
        if (!visited[newVertex]) {
            dfs(edges, newVertex, ans, visited)
        }
    }
    ans.add(vertex)
}