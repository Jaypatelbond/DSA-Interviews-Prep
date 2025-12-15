package kt.patterns

/**
 * # Pattern 16: Repeating Letter Triangle (Same Letter per Row)
 *
 * Prints a right-angled triangle where each row contains
 * the same letter repeated, with letters incrementing per row.
 *
 * ## Example Output (n = 5):
 * ```
 * A
 * BB
 * CCC
 * DDDD
 * EEEEE
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs from 0 to n-1 (for each row)
 * - Calculate the character for row i as 'A' + i
 * - Inner loop prints the same character (i + 1) times
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - total iterations = 1 + 2 + ... + n = n(n+1)/2
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows in the triangle
 */
fun pattern16(n: Int) {
    for (i in 0..<n) {
        val ch = ('A' + i).toChar()
        for (j in 0..i) {
            print(ch)
        }
        println()
    }
}

fun main() {
    print(pattern16(6))
}