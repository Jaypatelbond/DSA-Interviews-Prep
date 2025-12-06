package kt.dp

/**
 * Fibonacci Numbers
 *
 * Classic introduction to dynamic programming.
 * F(n) = F(n-1) + F(n-2), with F(0) = 0, F(1) = 1
 *
 * Demonstrates the progression from naive recursion to optimized DP.
 *
 * @see <a href="https://leetcode.com/problems/fibonacci-number/">LeetCode 509</a>
 */

/**
 * Naive Recursive - O(2^n) time, O(n) space
 * Exponential due to overlapping subproblems
 */
fun fibRecursive(n: Int): Long {
    if (n <= 1) return n.toLong()
    return fibRecursive(n - 1) + fibRecursive(n - 2)
}

/**
 * Top-Down DP (Memoization) - O(n) time, O(n) space
 * Cache results to avoid recomputation
 */
fun fibMemoized(n: Int, memo: MutableMap<Int, Long> = mutableMapOf()): Long {
    if (n <= 1) return n.toLong()

    if (n in memo) return memo[n]!!

    memo[n] = fibMemoized(n - 1, memo) + fibMemoized(n - 2, memo)
    return memo[n]!!
}

/**
 * Bottom-Up DP (Tabulation) - O(n) time, O(n) space
 * Build solution from base cases up
 */
fun fibTabulation(n: Int): Long {
    if (n <= 1) return n.toLong()

    val dp = LongArray(n + 1)
    dp[0] = 0
    dp[1] = 1

    for (i in 2..n) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }

    return dp[n]
}

/**
 * Space-Optimized DP - O(n) time, O(1) space
 * Only need last two values at any point
 */
fun fibOptimized(n: Int): Long {
    if (n <= 1) return n.toLong()

    var prev2 = 0L  // F(n-2)
    var prev1 = 1L  // F(n-1)

    for (i in 2..n) {
        val current = prev1 + prev2
        prev2 = prev1
        prev1 = current
    }

    return prev1
}

/**
 * Matrix Exponentiation - O(log n) time, O(1) space
 * Uses the property: [F(n+1), F(n)] = [[1,1],[1,0]]^n × [F(1), F(0)]
 */
fun fibMatrix(n: Int): Long {
    if (n <= 1) return n.toLong()

    fun multiply(a: Array<LongArray>, b: Array<LongArray>): Array<LongArray> {
        return arrayOf(
            longArrayOf(
                a[0][0] * b[0][0] + a[0][1] * b[1][0],
                a[0][0] * b[0][1] + a[0][1] * b[1][1]
            ),
            longArrayOf(
                a[1][0] * b[0][0] + a[1][1] * b[1][0],
                a[1][0] * b[0][1] + a[1][1] * b[1][1]
            )
        )
    }

    fun power(matrix: Array<LongArray>, n: Int): Array<LongArray> {
        if (n == 1) return matrix

        val half = power(matrix, n / 2)
        val result = multiply(half, half)

        return if (n % 2 == 0) result
        else multiply(result, matrix)
    }

    val baseMatrix = arrayOf(
        longArrayOf(1, 1),
        longArrayOf(1, 0)
    )

    val result = power(baseMatrix, n)
    return result[0][1]
}

/**
 * Tribonacci - similar but with 3 previous terms
 * T(n) = T(n-1) + T(n-2) + T(n-3)
 *
 * @see <a href="https://leetcode.com/problems/n-th-tribonacci-number/">LeetCode 1137</a>
 */
fun tribonacci(n: Int): Int {
    if (n == 0) return 0
    if (n <= 2) return 1

    var t0 = 0
    var t1 = 1
    var t2 = 1

    for (i in 3..n) {
        val current = t0 + t1 + t2
        t0 = t1
        t1 = t2
        t2 = current
    }

    return t2
}

fun main() {
    println("=== Fibonacci Approaches ===")
    val n = 10

    println("F($n) using different methods:")
    println("  Recursive: ${fibRecursive(n)}")
    println("  Memoized:  ${fibMemoized(n)}")
    println("  Tabulation: ${fibTabulation(n)}")
    println("  Optimized:  ${fibOptimized(n)}")
    println("  Matrix:     ${fibMatrix(n)}")

    println("\n=== Performance Comparison ===")
    val largeN = 40

    var start = System.currentTimeMillis()
    fibMemoized(largeN)
    println("Memoized F($largeN): ${System.currentTimeMillis() - start}ms")

    start = System.currentTimeMillis()
    fibOptimized(largeN)
    println("Optimized F($largeN): ${System.currentTimeMillis() - start}ms")

    println("\n=== First 15 Fibonacci Numbers ===")
    val fibSequence = (0..15).map { fibOptimized(it) }
    println(fibSequence)

    println("\n=== Tribonacci ===")
    val tribSequence = (0..10).map { tribonacci(it) }
    println("First 11 Tribonacci: $tribSequence")
    // [0, 1, 1, 2, 4, 7, 13, 24, 44, 81, 149]
}
