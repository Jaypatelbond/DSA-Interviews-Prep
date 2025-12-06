package kt.graph

import java.util.ArrayDeque

class Graph(private val n: Int) {
    private val adj = Array(n) { mutableListOf<Int>() }

    fun addEdge(u: Int, v: Int, directed: Boolean = false) {
        adj[u].add(v)
        if (!directed) adj[v].add(u)
    }

    fun bfs(start: Int): List<Int> {
        val visited = BooleanArray(n)
        val order = mutableListOf<Int>()
        val q = ArrayDeque<Int>()
        q.add(start)
        visited[start] = true
        while (q.isNotEmpty()) {
            val u = q.removeFirst()
            order.add(u)
            for (v in adj[u]) {
                if (!visited[v]) {
                    visited[v] = true
                    q.add(v)
                }
            }
        }
        return order
    }
}

fun main() {
    val g = Graph(6)
    g.addEdge(0, 1)
    g.addEdge(0, 2)
    g.addEdge(1, 3)
    g.addEdge(2, 4)
    g.addEdge(3, 5)

    println("BFS from 0: ${g.bfs(0)}")
}
