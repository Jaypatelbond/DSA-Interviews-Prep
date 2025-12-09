package kt.patterns

/**
 * # Pattern 14: Alphabet Triangle (Increasing Letters)
 *
 * Prints a right-angled triangle where each row contains
 * letters starting from 'A' up to the corresponding letter for that row.
 *
 * ## Example Output (n = 5):
 * ```
 * A
 * AB
 * ABC
 * ABCD
 * ABCDE
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs from 0 to n-1 (for each row)
 * - Inner loop prints characters from 'A' to 'A' + i
 * - Using Kotlin's char arithmetic to generate sequential letters
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - total iterations = 1 + 2 + ... + n = n(n+1)/2
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows in the triangle
 */
fun pattern14(n: Int) {
    for (i in 0..<n) {
        for (j in 'A'..'A' + i) {
            print(j)
        }
        println()
    }
}

fun main() {
    print(pattern14(4))
}