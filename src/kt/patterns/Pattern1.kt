package kt.patterns

/**
 * # Pattern 1: Square Pattern (n × n Grid)
 *
 * Prints a square grid of asterisks with n rows and n columns.
 *
 * ## Example Output (n = 4):
 * ```
 * ****
 * ****
 * ****
 * ****
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs n times (for each row)
 * - Inner loop runs n times (for each column in the row)
 * - Print '*' for each cell, then newline after each row
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - nested loops each run n times
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The size of the square (number of rows and columns)
 */
fun pattern1(n: Int) {
    for (i in 0..<n) {
        for (j in 0..<n) {
            print("*")
        }
        println()
    }
}

fun main() {
    val n = 4

    // Create an instance of the Solution class
    val sol = pattern1(n)

    print(sol)
}


