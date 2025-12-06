package kt.dp

fun knapsack(weights: IntArray, values: IntArray, capacity: Int): Int {
    val n = weights.size
    val dp = IntArray(capacity + 1)
    for (i in 0 until n) {
        val w = weights[i]
        val v = values[i]
        for (c in capacity downTo w) {
            dp[c] = maxOf(dp[c], dp[c - w] + v)
        }
    }
    return dp[capacity]
}

fun main() {
    val weights = intArrayOf(1, 3, 4)
    val values = intArrayOf(15, 20, 30)
    val cap = 4
    println("Max value = ${knapsack(weights, values, cap)}")
}
