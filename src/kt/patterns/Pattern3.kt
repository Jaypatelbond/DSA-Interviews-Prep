package kt.patterns

/**
 * # Pattern 3: Number Triangle (Sequential Numbers per Row)
 *
 * Prints a right-angled triangle where each row contains
 * sequential numbers starting from 1 up to the row number.
 *
 * ## Example Output (n = 5):
 * ```
 * 1
 * 12
 * 123
 * 1234
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs from 1 to n-1 (for each row)
 * - Inner loop runs from 1 to i (prints numbers 1 to i)
 * - Each row starts with 1 and increments up to the row number
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - total iterations = 1 + 2 + ... + (n-1)
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows in the triangle (exclusive, so n-1 rows are printed)
 */
fun pattern3(n: Int) {
    for (i in 1..<n) {
        for (j in 1..i) {
            print(j)
        }
        println()
    }
}

fun main() {
    val n = 5
    pattern3(n)
}