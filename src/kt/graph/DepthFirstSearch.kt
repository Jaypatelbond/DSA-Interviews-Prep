package kt.graph

import java.util.Stack

/**
 * Depth-First Search (DFS)
 *
 * Explores as far as possible along each branch before backtracking.
 * Can be implemented iteratively (stack) or recursively.
 *
 * Applications:
 * - Cycle detection
 * - Path finding
 * - Topological sorting
 * - Connected components
 *
 * Time Complexity: O(V + E)
 * Space Complexity: O(V) for visited set + recursion stack
 */

/**
 * DFS traversal - Recursive approach
 */
fun <T> dfsRecursive(graph: Graph<T>, start: T): List<T> {
    val visited = mutableSetOf<T>()
    val result = mutableListOf<T>()

    fun dfs(node: T) {
        if (node in visited) return

        visited.add(node)
        result.add(node)

        for (edge in graph.getNeighbors(node)) {
            dfs(edge.to)
        }
    }

    dfs(start)
    return result
}

/**
 * DFS traversal - Iterative approach using stack
 */
fun <T> dfsIterative(graph: Graph<T>, start: T): List<T> {
    val visited = mutableSetOf<T>()
    val result = mutableListOf<T>()
    val stack = Stack<T>()

    stack.push(start)

    while (stack.isNotEmpty()) {
        val current = stack.pop()

        if (current in visited) continue

        visited.add(current)
        result.add(current)

        // Add neighbors in reverse for consistent order with recursive
        for (edge in graph.getNeighbors(current).reversed()) {
            if (edge.to !in visited) {
                stack.push(edge.to)
            }
        }
    }

    return result
}

/**
 * Find all paths from start to end using DFS
 */
fun <T> findAllPaths(graph: Graph<T>, start: T, end: T): List<List<T>> {
    val allPaths = mutableListOf<List<T>>()
    val currentPath = mutableListOf<T>()

    fun dfs(node: T) {
        currentPath.add(node)

        if (node == end) {
            allPaths.add(currentPath.toList())
        } else {
            for (edge in graph.getNeighbors(node)) {
                if (edge.to !in currentPath) { // Avoid cycles in path
                    dfs(edge.to)
                }
            }
        }

        currentPath.removeAt(currentPath.size - 1)
    }

    dfs(start)
    return allPaths
}

/**
 * Detect cycle in directed graph using DFS
 * Uses three states: unvisited, visiting, visited
 */
fun hasCycleDirected(graph: Graph<Int>): Boolean {
    val unvisited = 0
    val visiting = 1
    val visited = 2

    val state = mutableMapOf<Int, Int>()
    graph.getVertices().forEach { state[it] = unvisited }

    fun dfs(node: Int): Boolean {
        state[node] = visiting

        for (edge in graph.getNeighbors(node)) {
            when (state[edge.to]) {
                visiting -> return true // Back edge found = cycle
                unvisited -> if (dfs(edge.to)) return true
            }
        }

        state[node] = visited
        return false
    }

    for (vertex in graph.getVertices()) {
        if (state[vertex] == unvisited) {
            if (dfs(vertex)) return true
        }
    }

    return false
}

/**
 * Detect cycle in undirected graph using DFS
 */
fun hasCycleUndirected(graph: Graph<Int>): Boolean {
    val visited = mutableSetOf<Int>()

    fun dfs(node: Int, parent: Int?): Boolean {
        visited.add(node)

        for (edge in graph.getNeighbors(node)) {
            if (edge.to !in visited) {
                if (dfs(edge.to, node)) return true
            } else if (edge.to != parent) {
                return true // Found a back edge
            }
        }

        return false
    }

    for (vertex in graph.getVertices()) {
        if (vertex !in visited) {
            if (dfs(vertex, null)) return true
        }
    }

    return false
}

/**
 * Count connected components in undirected graph
 */
fun <T> countComponents(graph: Graph<T>): Int {
    val visited = mutableSetOf<T>()
    var count = 0

    fun dfs(node: T) {
        visited.add(node)
        for (edge in graph.getNeighbors(node)) {
            if (edge.to !in visited) {
                dfs(edge.to)
            }
        }
    }

    for (vertex in graph.getVertices()) {
        if (vertex !in visited) {
            dfs(vertex)
            count++
        }
    }

    return count
}

fun main() {
    // Build sample graph
    //     A --- B
    //     |     |
    //     C --- D --- E
    //           |
    //           F
    val graph = Graph<String>()
    graph.addEdge("A", "B")
    graph.addEdge("A", "C")
    graph.addEdge("B", "D")
    graph.addEdge("C", "D")
    graph.addEdge("D", "E")
    graph.addEdge("D", "F")

    println("=== DFS Recursive ===")
    println("From A: ${dfsRecursive(graph, "A")}")

    println("\n=== DFS Iterative ===")
    println("From A: ${dfsIterative(graph, "A")}")

    println("\n=== All Paths ===")
    println("A to E:")
    findAllPaths(graph, "A", "E").forEach { println("  $it") }

    // Test cycle detection - directed
    println("\n=== Cycle Detection (Directed) ===")
    val directedWithCycle = Graph<Int>(directed = true)
    directedWithCycle.addEdge(0, 1)
    directedWithCycle.addEdge(1, 2)
    directedWithCycle.addEdge(2, 0) // Creates cycle
    println("Has cycle: ${hasCycleDirected(directedWithCycle)}") // true

    val directedNoCycle = Graph<Int>(directed = true)
    directedNoCycle.addEdge(0, 1)
    directedNoCycle.addEdge(1, 2)
    directedNoCycle.addEdge(0, 2)
    println("No cycle: ${hasCycleDirected(directedNoCycle)}") // false

    // Test connected components
    println("\n=== Connected Components ===")
    val disconnected = Graph<Int>()
    disconnected.addEdge(0, 1)
    disconnected.addEdge(1, 2)
    disconnected.addEdge(3, 4) // Separate component
    disconnected.addVertex(5)   // Isolated vertex
    println("Components: ${countComponents(disconnected)}") // 3
}
