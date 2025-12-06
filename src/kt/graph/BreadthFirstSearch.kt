package kt.graph

import java.util.LinkedList
import java.util.Queue

/**
 * Breadth-First Search (BFS)
 *
 * Explores all neighbors at current depth before moving to next level.
 * Uses a queue (FIFO) for traversal.
 *
 * Applications:
 * - Shortest path in unweighted graphs
 * - Level-order traversal
 * - Finding connected components
 *
 * Time Complexity: O(V + E) where V = vertices, E = edges
 * Space Complexity: O(V) for visited set and queue
 */

/**
 * BFS traversal returning nodes in order visited
 */
fun <T> bfsTraversal(graph: Graph<T>, start: T): List<T> {
    val visited = mutableSetOf<T>()
    val result = mutableListOf<T>()
    val queue: Queue<T> = LinkedList()

    queue.offer(start)
    visited.add(start)

    while (queue.isNotEmpty()) {
        val current = queue.poll()
        result.add(current)

        for (edge in graph.getNeighbors(current)) {
            if (edge.to !in visited) {
                visited.add(edge.to)
                queue.offer(edge.to)
            }
        }
    }

    return result
}

/**
 * BFS with level tracking
 * Returns list of levels, where each level contains nodes at that depth
 */
fun <T> bfsLevels(graph: Graph<T>, start: T): List<List<T>> {
    val visited = mutableSetOf<T>()
    val levels = mutableListOf<List<T>>()
    var currentLevel = listOf(start)

    visited.add(start)

    while (currentLevel.isNotEmpty()) {
        levels.add(currentLevel)
        val nextLevel = mutableListOf<T>()

        for (node in currentLevel) {
            for (edge in graph.getNeighbors(node)) {
                if (edge.to !in visited) {
                    visited.add(edge.to)
                    nextLevel.add(edge.to)
                }
            }
        }

        currentLevel = nextLevel
    }

    return levels
}

/**
 * Shortest path using BFS (unweighted graph)
 * Returns the shortest path from start to end, or empty if no path exists
 */
fun <T> bfsShortestPath(graph: Graph<T>, start: T, end: T): List<T> {
    if (start == end) return listOf(start)

    val visited = mutableSetOf<T>()
    val parent = mutableMapOf<T, T>()
    val queue: Queue<T> = LinkedList()

    queue.offer(start)
    visited.add(start)

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        for (edge in graph.getNeighbors(current)) {
            if (edge.to !in visited) {
                visited.add(edge.to)
                parent[edge.to] = current
                queue.offer(edge.to)

                if (edge.to == end) {
                    // Reconstruct path
                    return reconstructPath(parent, start, end)
                }
            }
        }
    }

    return emptyList() // No path found
}

/**
 * Helper to reconstruct path from parent map
 */
private fun <T> reconstructPath(parent: Map<T, T>, start: T, end: T): List<T> {
    val path = mutableListOf<T>()
    var current: T? = end

    while (current != null) {
        path.add(current)
        current = if (current == start) null else parent[current]
    }

    return path.reversed()
}

/**
 * Check if graph is bipartite using BFS
 * A graph is bipartite if nodes can be colored with 2 colors such that
 * no adjacent nodes have the same color
 *
 * @see <a href="https://leetcode.com/problems/is-graph-bipartite/">LeetCode 785</a>
 */
fun isBipartite(graph: Array<IntArray>): Boolean {
    val n = graph.size
    val colors = IntArray(n) { -1 } // -1: unvisited, 0 or 1: color

    for (i in 0 until n) {
        if (colors[i] != -1) continue

        val queue: Queue<Int> = LinkedList()
        queue.offer(i)
        colors[i] = 0

        while (queue.isNotEmpty()) {
            val node = queue.poll()
            val nextColor = 1 - colors[node]

            for (neighbor in graph[node]) {
                if (colors[neighbor] == -1) {
                    colors[neighbor] = nextColor
                    queue.offer(neighbor)
                } else if (colors[neighbor] != nextColor) {
                    return false
                }
            }
        }
    }

    return true
}

fun main() {
    // Build a sample graph
    //     A --- B
    //     |     |
    //     C --- D --- E
    val graph = Graph<String>()
    graph.addEdge("A", "B")
    graph.addEdge("A", "C")
    graph.addEdge("B", "D")
    graph.addEdge("C", "D")
    graph.addEdge("D", "E")

    println("=== BFS Traversal ===")
    println("Starting from A: ${bfsTraversal(graph, "A")}")
    // [A, B, C, D, E]

    println("\n=== BFS Levels ===")
    val levels = bfsLevels(graph, "A")
    levels.forEachIndexed { i, level ->
        println("Level $i: $level")
    }
    // Level 0: [A]
    // Level 1: [B, C]
    // Level 2: [D]
    // Level 3: [E]

    println("\n=== Shortest Path ===")
    println("A to E: ${bfsShortestPath(graph, "A", "E")}")
    // [A, B, D, E] or [A, C, D, E]

    println("A to A: ${bfsShortestPath(graph, "A", "A")}")
    // [A]

    // Test bipartite check
    println("\n=== Bipartite Check ===")
    val bipartiteGraph = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(0, 2),
        intArrayOf(1, 3),
        intArrayOf(0, 2)
    )
    println("Is bipartite (square): ${isBipartite(bipartiteGraph)}") // true

    val nonBipartite = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(0, 2),
        intArrayOf(0, 1, 3),
        intArrayOf(0, 2)
    )
    println("Is bipartite (with triangle): ${isBipartite(nonBipartite)}") // false
}
