package kt.graph

import java.util.LinkedList
import java.util.Queue
import java.util.Stack

/**
 * Topological Sort
 *
 * Linear ordering of vertices in a DAG (Directed Acyclic Graph) such that
 * for every directed edge (u, v), vertex u comes before v.
 *
 * Applications:
 * - Build systems (dependency resolution)
 * - Course scheduling
 * - Task scheduling
 *
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 *
 * @see <a href="https://leetcode.com/problems/course-schedule-ii/">LeetCode 210</a>
 */

/**
 * Kahn's Algorithm (BFS-based)
 *
 * 1. Calculate in-degree of all vertices
 * 2. Add all vertices with in-degree 0 to queue
 * 3. Process queue: remove vertex, add to result, decrease neighbor in-degrees
 * 4. If result size != vertex count, graph has a cycle
 */
fun <T> topologicalSortKahn(graph: Graph<T>): List<T>? {
    val inDegree = mutableMapOf<T, Int>()
    val vertices = graph.getVertices()

    // Initialize in-degrees
    vertices.forEach { inDegree[it] = 0 }

    // Calculate in-degrees
    for (vertex in vertices) {
        for (edge in graph.getNeighbors(vertex)) {
            inDegree[edge.to] = inDegree.getOrDefault(edge.to, 0) + 1
        }
    }

    // Add all vertices with in-degree 0 to queue
    val queue: Queue<T> = LinkedList()
    for ((vertex, degree) in inDegree) {
        if (degree == 0) {
            queue.offer(vertex)
        }
    }

    val result = mutableListOf<T>()

    while (queue.isNotEmpty()) {
        val current = queue.poll()
        result.add(current)

        // Decrease in-degree of neighbors
        for (edge in graph.getNeighbors(current)) {
            inDegree[edge.to] = inDegree[edge.to]!! - 1
            if (inDegree[edge.to] == 0) {
                queue.offer(edge.to)
            }
        }
    }

    // If not all vertices are in result, there's a cycle
    return if (result.size == vertices.size) result else null
}

/**
 * DFS-based Topological Sort
 *
 * Uses post-order DFS and reverses the result.
 * A vertex is added to result only after all its dependencies are processed.
 */
fun <T> topologicalSortDFS(graph: Graph<T>): List<T>? {
    val visited = mutableSetOf<T>()
    val inProgress = mutableSetOf<T>()  // For cycle detection
    val stack = Stack<T>()

    fun dfs(node: T): Boolean {
        if (node in inProgress) return false // Cycle detected
        if (node in visited) return true

        inProgress.add(node)

        for (edge in graph.getNeighbors(node)) {
            if (!dfs(edge.to)) return false
        }

        inProgress.remove(node)
        visited.add(node)
        stack.push(node)

        return true
    }

    // Run DFS from all vertices (handles disconnected components)
    for (vertex in graph.getVertices()) {
        if (vertex !in visited) {
            if (!dfs(vertex)) return null // Cycle detected
        }
    }

    return stack.toList().reversed()
}

/**
 * Course Schedule - Can finish all courses?
 *
 * @see <a href="https://leetcode.com/problems/course-schedule/">LeetCode 207</a>
 */
fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    val graph = Graph<Int>(directed = true)

    // Add all courses as vertices
    repeat(numCourses) { graph.addVertex(it) }

    // Add prerequisites as edges: [course, prereq] -> prereq -> course
    for ((course, prereq) in prerequisites) {
        graph.addEdge(prereq, course)
    }

    return topologicalSortKahn(graph) != null
}

/**
 * Course Schedule II - Return order to take courses
 *
 * @see <a href="https://leetcode.com/problems/course-schedule-ii/">LeetCode 210</a>
 */
fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
    val graph = Graph<Int>(directed = true)

    repeat(numCourses) { graph.addVertex(it) }

    for ((course, prereq) in prerequisites) {
        graph.addEdge(prereq, course)
    }

    val order = topologicalSortKahn(graph)
    return order?.toIntArray() ?: intArrayOf()
}

/**
 * Alien Dictionary - Derive alphabet order from sorted words
 *
 * @see <a href="https://leetcode.com/problems/alien-dictionary/">LeetCode 269</a>
 */
fun alienOrder(words: Array<String>): String {
    val graph = Graph<Char>(directed = true)

    // Add all characters as vertices
    for (word in words) {
        for (char in word) {
            graph.addVertex(char)
        }
    }

    // Compare adjacent words to derive order
    for (i in 0 until words.size - 1) {
        val word1 = words[i]
        val word2 = words[i + 1]

        // Check for invalid case: prefix is longer
        if (word1.length > word2.length && word1.startsWith(word2)) {
            return ""
        }

        // Find first different character
        for (j in 0 until minOf(word1.length, word2.length)) {
            if (word1[j] != word2[j]) {
                graph.addEdge(word1[j], word2[j])
                break
            }
        }
    }

    val order = topologicalSortKahn(graph)
    return order?.joinToString("") ?: ""
}

fun main() {
    println("=== Topological Sort (Kahn's Algorithm) ===")
    // Course prerequisites: 0 <- 1 <- 2 <- 3
    //                      0 <- 2
    val courseGraph = Graph<Int>(directed = true)
    courseGraph.addEdge(0, 1)
    courseGraph.addEdge(1, 2)
    courseGraph.addEdge(2, 3)
    courseGraph.addEdge(0, 2)

    val order = topologicalSortKahn(courseGraph)
    println("Course order: $order") // [0, 1, 2, 3]

    println("\n=== Topological Sort (DFS) ===")
    val dfsOrder = topologicalSortDFS(courseGraph)
    println("Course order: $dfsOrder")

    println("\n=== Cycle Detection ===")
    val cyclicGraph = Graph<Int>(directed = true)
    cyclicGraph.addEdge(0, 1)
    cyclicGraph.addEdge(1, 2)
    cyclicGraph.addEdge(2, 0) // Creates cycle

    println("Cyclic graph order: ${topologicalSortKahn(cyclicGraph)}") // null

    println("\n=== Course Schedule ===")
    val prereqs1 = arrayOf(intArrayOf(1, 0), intArrayOf(2, 1))
    println("Can finish 3 courses: ${canFinish(3, prereqs1)}") // true

    val prereqs2 = arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))
    println("Can finish (cycle): ${canFinish(2, prereqs2)}") // false

    println("\n=== Course Order ===")
    val prereqs3 = arrayOf(intArrayOf(1, 0), intArrayOf(2, 0), intArrayOf(3, 1), intArrayOf(3, 2))
    println("Order for 4 courses: ${findOrder(4, prereqs3).toList()}") // [0, 1, 2, 3] or [0, 2, 1, 3]

    println("\n=== Alien Dictionary ===")
    val words = arrayOf("wrt", "wrf", "er", "ett", "rftt")
    println("Alien alphabet: ${alienOrder(words)}") // "wertf"
}
