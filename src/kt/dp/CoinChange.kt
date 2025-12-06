package kt.dp

/**
 * Coin Change
 *
 * Problem: Given coins of different denominations and a total amount,
 * find the minimum number of coins needed to make up that amount.
 * Return -1 if it's not possible.
 *
 * Time Complexity: O(amount × coins.length)
 * Space Complexity: O(amount)
 *
 * @see <a href="https://leetcode.com/problems/coin-change/">LeetCode 322</a>
 */

/**
 * Bottom-up DP approach
 *
 * dp[i] = minimum coins needed to make amount i
 * dp[i] = min(dp[i], dp[i - coin] + 1) for each coin
 */
fun coinChange(coins: IntArray, amount: Int): Int {
    if (amount == 0) return 0

    val dp = IntArray(amount + 1) { amount + 1 } // Initialize with impossible value
    dp[0] = 0

    for (i in 1..amount) {
        for (coin in coins) {
            if (coin <= i) {
                dp[i] = minOf(dp[i], dp[i - coin] + 1)
            }
        }
    }

    return if (dp[amount] > amount) -1 else dp[amount]
}

/**
 * Top-down DP with memoization
 */
fun coinChangeMemo(coins: IntArray, amount: Int): Int {
    val memo = mutableMapOf<Int, Int>()

    fun dp(remaining: Int): Int {
        if (remaining == 0) return 0
        if (remaining < 0) return -1
        if (remaining in memo) return memo[remaining]!!

        var minCoins = Int.MAX_VALUE

        for (coin in coins) {
            val result = dp(remaining - coin)
            if (result >= 0) {
                minCoins = minOf(minCoins, result + 1)
            }
        }

        memo[remaining] = if (minCoins == Int.MAX_VALUE) -1 else minCoins
        return memo[remaining]!!
    }

    return dp(amount)
}

/**
 * Coin Change II - Count number of combinations
 *
 * Different from minimum coins - here we count all possible ways.
 * Order doesn't matter: [1,1,2] and [2,1,1] are same combination.
 *
 * @see <a href="https://leetcode.com/problems/coin-change-2/">LeetCode 518</a>
 */
fun coinChangeII(amount: Int, coins: IntArray): Int {
    val dp = IntArray(amount + 1)
    dp[0] = 1 // One way to make amount 0: use no coins

    // Process each coin - this ensures combinations, not permutations
    for (coin in coins) {
        for (i in coin..amount) {
            dp[i] += dp[i - coin]
        }
    }

    return dp[amount]
}

/**
 * Get the actual coins used (not just count)
 */
fun coinChangeWithCoins(coins: IntArray, amount: Int): List<Int> {
    if (amount == 0) return emptyList()

    val dp = IntArray(amount + 1) { amount + 1 }
    val parent = IntArray(amount + 1) { -1 } // Track which coin was used
    dp[0] = 0

    for (i in 1..amount) {
        for (coin in coins) {
            if (coin <= i && dp[i - coin] + 1 < dp[i]) {
                dp[i] = dp[i - coin] + 1
                parent[i] = coin
            }
        }
    }

    if (dp[amount] > amount) return emptyList()

    // Reconstruct the coins used
    val result = mutableListOf<Int>()
    var remaining = amount
    while (remaining > 0) {
        result.add(parent[remaining])
        remaining -= parent[remaining]
    }

    return result
}

/**
 * Perfect Squares - minimum squares that sum to n
 * Special case of coin change where coins = [1, 4, 9, 16, ...]
 *
 * @see <a href="https://leetcode.com/problems/perfect-squares/">LeetCode 279</a>
 */
fun numSquares(n: Int): Int {
    val dp = IntArray(n + 1) { n + 1 }
    dp[0] = 0

    for (i in 1..n) {
        var j = 1
        while (j * j <= i) {
            dp[i] = minOf(dp[i], dp[i - j * j] + 1)
            j++
        }
    }

    return dp[n]
}

fun main() {
    println("=== Coin Change (Minimum Coins) ===")
    val coins1 = intArrayOf(1, 2, 5)
    println("Coins [1,2,5], amount=11: ${coinChange(coins1, 11)}") // 3 (5+5+1)
    println("Coins used: ${coinChangeWithCoins(coins1, 11)}")

    val coins2 = intArrayOf(2)
    println("\nCoins [2], amount=3: ${coinChange(coins2, 3)}") // -1 (impossible)

    val coins3 = intArrayOf(1)
    println("Coins [1], amount=0: ${coinChange(coins3, 0)}") // 0

    println("\n=== Coin Change (Memoization) ===")
    println("Coins [1,2,5], amount=11: ${coinChangeMemo(coins1, 11)}") // 3

    println("\n=== Coin Change II (Count Ways) ===")
    println("Coins [1,2,5], amount=5: ${coinChangeII(5, coins1)} ways") // 4
    // Ways: [1,1,1,1,1], [1,1,1,2], [1,2,2], [5]

    println("Coins [2], amount=3: ${coinChangeII(3, coins2)} ways") // 0
    println("Coins [10], amount=10: ${coinChangeII(10, intArrayOf(10))} ways") // 1

    println("\n=== Perfect Squares ===")
    println("n=12: ${numSquares(12)} squares") // 3 (4+4+4)
    println("n=13: ${numSquares(13)} squares") // 2 (4+9)
    println("n=1: ${numSquares(1)} square")    // 1
}
