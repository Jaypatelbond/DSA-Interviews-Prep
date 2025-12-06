package kt.graph

/**
 * Graph Representation
 *
 * Adjacency List implementation for directed/undirected graphs.
 * Supports both weighted and unweighted edges.
 */
class Graph<T>(private val directed: Boolean = false) {
    private val adjacencyList = mutableMapOf<T, MutableList<Edge<T>>>()

    data class Edge<T>(val to: T, val weight: Int = 1)

    /**
     * Add a vertex to the graph
     */
    fun addVertex(vertex: T) {
        adjacencyList.putIfAbsent(vertex, mutableListOf())
    }

    /**
     * Add an edge between two vertices
     */
    fun addEdge(from: T, to: T, weight: Int = 1) {
        addVertex(from)
        addVertex(to)

        adjacencyList[from]!!.add(Edge(to, weight))

        if (!directed) {
            adjacencyList[to]!!.add(Edge(from, weight))
        }
    }

    /**
     * Get all neighbors of a vertex
     */
    fun getNeighbors(vertex: T): List<Edge<T>> {
        return adjacencyList[vertex] ?: emptyList()
    }

    /**
     * Get all vertices in the graph
     */
    fun getVertices(): Set<T> = adjacencyList.keys

    /**
     * Check if vertex exists
     */
    fun hasVertex(vertex: T): Boolean = vertex in adjacencyList

    /**
     * Check if edge exists
     */
    fun hasEdge(from: T, to: T): Boolean {
        return adjacencyList[from]?.any { it.to == to } ?: false
    }

    /**
     * Get number of vertices
     */
    fun vertexCount(): Int = adjacencyList.size

    /**
     * Get number of edges
     */
    fun edgeCount(): Int {
        val count = adjacencyList.values.sumOf { it.size }
        return if (directed) count else count / 2
    }

    /**
     * Remove an edge
     */
    fun removeEdge(from: T, to: T) {
        adjacencyList[from]?.removeIf { it.to == to }
        if (!directed) {
            adjacencyList[to]?.removeIf { it.to == from }
        }
    }

    /**
     * Remove a vertex and all its edges
     */
    fun removeVertex(vertex: T) {
        adjacencyList.remove(vertex)
        adjacencyList.values.forEach { edges ->
            edges.removeIf { it.to == vertex }
        }
    }

    /**
     * Get in-degree of a vertex (for directed graphs)
     */
    fun getInDegree(vertex: T): Int {
        return adjacencyList.values.sumOf { edges ->
            edges.count { it.to == vertex }
        }
    }

    /**
     * Get out-degree of a vertex
     */
    fun getOutDegree(vertex: T): Int {
        return adjacencyList[vertex]?.size ?: 0
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.appendLine("Graph (${if (directed) "directed" else "undirected"}):")
        adjacencyList.forEach { (vertex, edges) ->
            val neighbors = edges.joinToString { "${it.to}(${it.weight})" }
            sb.appendLine("  $vertex -> [$neighbors]")
        }
        return sb.toString()
    }
}

/**
 * Simple adjacency list representation using just maps
 * For when you don't need the full Graph class
 */
typealias SimpleGraph = MutableMap<Int, MutableList<Int>>

fun createSimpleGraph(): SimpleGraph = mutableMapOf()

fun SimpleGraph.addEdge(from: Int, to: Int, directed: Boolean = false) {
    getOrPut(from) { mutableListOf() }.add(to)
    if (!directed) {
        getOrPut(to) { mutableListOf() }.add(from)
    }
}

fun main() {
    // Create an undirected graph
    println("=== Undirected Graph ===")
    val graph = Graph<String>()
    graph.addEdge("A", "B")
    graph.addEdge("A", "C")
    graph.addEdge("B", "D")
    graph.addEdge("C", "D")
    graph.addEdge("D", "E")
    println(graph)

    println("Neighbors of A: ${graph.getNeighbors("A").map { it.to }}")
    println("Vertices: ${graph.getVertices()}")
    println("Edge count: ${graph.edgeCount()}")

    // Create a directed graph
    println("\n=== Directed Graph ===")
    val directedGraph = Graph<Int>(directed = true)
    directedGraph.addEdge(0, 1)
    directedGraph.addEdge(0, 2)
    directedGraph.addEdge(1, 2)
    directedGraph.addEdge(2, 0)
    directedGraph.addEdge(2, 3)
    println(directedGraph)

    println("In-degree of 2: ${directedGraph.getInDegree(2)}")
    println("Out-degree of 2: ${directedGraph.getOutDegree(2)}")

    // Create a weighted graph
    println("\n=== Weighted Graph ===")
    val weightedGraph = Graph<String>()
    weightedGraph.addEdge("NYC", "LA", 2800)
    weightedGraph.addEdge("NYC", "Chicago", 790)
    weightedGraph.addEdge("LA", "Chicago", 2000)
    println(weightedGraph)
}
