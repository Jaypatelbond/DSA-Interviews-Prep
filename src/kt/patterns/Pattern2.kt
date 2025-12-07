package kt.patterns

/**
 * # Pattern 2: Right-Angled Triangle (Increasing Stars)
 *
 * Prints a right-angled triangle with stars, where each row has
 * one more star than the previous row.
 *
 * ## Example Output (n = 5):
 * ```
 * *
 * **
 * ***
 * ****
 * *****
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs n times (for each row, 0 to n-1)
 * - Inner loop runs (i+1) times for row i (0 to i)
 * - Row 0 gets 1 star, row 1 gets 2 stars, ..., row n-1 gets n stars
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - total iterations = 1 + 2 + ... + n = n(n+1)/2
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows in the triangle
 */
fun pattern2(n: Int) {
    for (i in 0..<n) {
        for (j in 0..i) {
            print("*")
        }
        println()
    }
}

fun main() {
    val n = 5
    pattern2(n)
}