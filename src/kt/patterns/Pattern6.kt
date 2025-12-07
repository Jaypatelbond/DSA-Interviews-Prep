package kt.patterns

/**
 * # Pattern 6: Inverted Number Triangle (Sequential Numbers, Decreasing Count)
 *
 * Prints an inverted right-angled triangle where each row contains
 * sequential numbers starting from 1, with one fewer number per row.
 *
 * ## Example Output (n = 5):
 * ```
 * 1234
 * 123
 * 12
 * 1
 *
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs n times (for each row, 0 to n-1)
 * - Inner loop runs from 1 to (n - i - 1) for row i
 * - Row 0 prints 1 to n-1, row 1 prints 1 to n-2, etc.
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - total iterations = (n-1) + (n-2) + ... + 0
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows in the inverted triangle
 */
fun pattern6(n: Int) {
    for (i in 0..<n) {
        for (j in 1..<n - i) {
            print(j)
        }
        println()
    }
}


fun main() {
    val n = 5
    pattern6(n)
}