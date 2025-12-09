package kt.patterns

/**
 * # Pattern 15: Reverse Alphabet Triangle (Decreasing Letters)
 *
 * Prints an inverted right-angled triangle where each row contains
 * letters starting from 'A', with decreasing count per row.
 *
 * ## Example Output (n = 5):
 * ```
 * ABCDE
 * ABCD
 * ABC
 * AB
 * A
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs from 0 to n-1 (for each row)
 * - Inner loop prints characters from 'A' to 'A' + (n - i - 1)
 * - First row prints n letters, last row prints 1 letter
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - total iterations = n + (n-1) + ... + 1 = n(n+1)/2
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows in the triangle
 */
fun pattern15(n: Int) {
    for (i in 0..<n) {
        for (j in 'A'..'A' + (n - i - 1)) {
            print(j)
        }
        println()
    }
}

fun main() {
    print(pattern15(5))
}