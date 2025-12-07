package kt.patterns

/**
 * # Pattern 5: Inverted Right-Angled Triangle (Decreasing Stars)
 *
 * Prints an inverted right-angled triangle where each row has
 * one fewer star than the previous row.
 *
 * ## Example Output (n = 5):
 * ```
 * *****
 * ****
 * ***
 * **
 * *
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs n times (for each row, 0 to n-1)
 * - Inner loop runs (n - i) times for row i
 * - Row 0 gets n stars, row 1 gets n-1 stars, ..., row n-1 gets 1 star
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - total iterations = n + (n-1) + ... + 1 = n(n+1)/2
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows in the inverted triangle
 */
fun pattern5(n: Int) {
    for (i in 0..<n) {
        for (j in 0..<n - i) {
            print("*")
        }
        println()
    }
}


fun main() {
    val n = 5
    pattern5(n)
}