package kt.dp

/**
 * Climbing Stairs
 *
 * Problem: You can climb 1 or 2 steps at a time. How many distinct ways
 * to reach the top of n stairs?
 *
 * This is essentially Fibonacci because:
 * ways(n) = ways(n-1) + ways(n-2)
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) optimized, O(n) naive
 *
 * @see <a href="https://leetcode.com/problems/climbing-stairs/">LeetCode 70</a>
 */

/**
 * Space-optimized solution
 */
fun climbStairs(n: Int): Int {
    if (n <= 2) return n

    var prev2 = 1  // ways(n-2)
    var prev1 = 2  // ways(n-1)

    for (i in 3..n) {
        val current = prev1 + prev2
        prev2 = prev1
        prev1 = current
    }

    return prev1
}

/**
 * DP with tabulation for clarity
 */
fun climbStairsDP(n: Int): Int {
    if (n <= 2) return n

    val dp = IntArray(n + 1)
    dp[1] = 1
    dp[2] = 2

    for (i in 3..n) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }

    return dp[n]
}

/**
 * Min Cost Climbing Stairs
 *
 * Each stair has a cost. Find minimum cost to reach the top.
 * You can start from stair 0 or 1.
 *
 * @see <a href="https://leetcode.com/problems/min-cost-climbing-stairs/">LeetCode 746</a>
 */
fun minCostClimbingStairs(cost: IntArray): Int {
    val n = cost.size

    var prev2 = cost[0]
    var prev1 = cost[1]

    for (i in 2 until n) {
        val current = cost[i] + minOf(prev1, prev2)
        prev2 = prev1
        prev1 = current
    }

    return minOf(prev1, prev2)
}

/**
 * Climbing Stairs with k steps
 *
 * Generalized: You can take 1 to k steps at a time.
 */
fun climbStairsK(n: Int, k: Int): Int {
    val dp = IntArray(n + 1)
    dp[0] = 1

    for (i in 1..n) {
        for (step in 1..minOf(k, i)) {
            dp[i] += dp[i - step]
        }
    }

    return dp[n]
}

/**
 * Climbing Stairs with allowed steps array
 *
 * You can only take steps of sizes in the allowed array.
 * Example: steps = [1, 3, 5] means you can take 1, 3, or 5 steps.
 */
fun climbStairsWithAllowed(n: Int, allowedSteps: IntArray): Int {
    val dp = IntArray(n + 1)
    dp[0] = 1

    for (i in 1..n) {
        for (step in allowedSteps) {
            if (i >= step) {
                dp[i] += dp[i - step]
            }
        }
    }

    return dp[n]
}

/**
 * Count unique paths to reach stair n, printing all paths
 */
fun climbStairsPaths(n: Int): List<List<Int>> {
    val allPaths = mutableListOf<List<Int>>()
    val currentPath = mutableListOf<Int>()

    fun backtrack(remaining: Int) {
        if (remaining == 0) {
            allPaths.add(currentPath.toList())
            return
        }

        for (step in 1..minOf(2, remaining)) {
            currentPath.add(step)
            backtrack(remaining - step)
            currentPath.removeAt(currentPath.size - 1)
        }
    }

    backtrack(n)
    return allPaths
}

fun main() {
    println("=== Climbing Stairs ===")
    for (n in 1..10) {
        println("n=$n: ${climbStairs(n)} ways")
    }

    println("\n=== Min Cost Climbing Stairs ===")
    val cost1 = intArrayOf(10, 15, 20)
    println("Cost [10,15,20]: ${minCostClimbingStairs(cost1)}") // 15

    val cost2 = intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1)
    println("Cost [1,100,1,1,1,100,1,1,100,1]: ${minCostClimbingStairs(cost2)}") // 6

    println("\n=== Climbing with K steps ===")
    println("n=5, k=3 (can take 1,2,3 steps): ${climbStairsK(5, 3)} ways") // 13

    println("\n=== Climbing with allowed steps [1,3,5] ===")
    val allowed = intArrayOf(1, 3, 5)
    for (n in 1..6) {
        println("n=$n: ${climbStairsWithAllowed(n, allowed)} ways")
    }

    println("\n=== All paths for n=4 ===")
    val paths = climbStairsPaths(4)
    println("${paths.size} paths:")
    paths.forEach { path ->
        println("  ${path.joinToString(" + ")} = ${path.sum()}")
    }
}
