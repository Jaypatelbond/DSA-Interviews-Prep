package kt.patterns

/**
 * # Pattern 4: Repeating Row Number Triangle
 *
 * Prints a right-angled triangle where each row contains
 * the row number repeated 'row number' times.
 *
 * ## Example Output (n = 5):
 * ```
 * 1
 * 22
 * 333
 * 4444
 * 55555
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs from 1 to n (for each row)
 * - Inner loop runs i times for row i
 * - Each iteration of inner loop prints the row number i
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - total iterations = 1 + 2 + ... + n = n(n+1)/2
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows in the triangle
 */
fun pattern4(n: Int) {
    for (i in 1..n) {
        for (j in 1..i) {
            print(i)
        }
        println()
    }
}

fun main() {
    val n = 5
    pattern4(n)
}