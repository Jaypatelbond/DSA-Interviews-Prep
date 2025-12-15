package kt.patterns

/**
 * # Pattern 21: Hollow Square Pattern (Border Only)
 *
 * Prints a hollow square where only the border is filled with stars,
 * and the interior is empty (spaces).
 *
 * ## Example Output (n = 5):
 * ```
 * *****
 * *   *
 * *   *
 * *   *
 * *****
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs n times (for each row, 0 to n-1)
 * - Inner loop runs n times (for each column, 0 to n-1)
 * - Print '*' if on border: first/last row (i=0, i=n-1) OR first/last column (j=0, j=n-1)
 * - Print ' ' for interior cells
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - nested loops each run n times
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The size of the square (number of rows and columns)
 */
fun pattern21(n: Int) {
    for (i in 0..<n) {
        for (j in 0..<n) {
            if (i == 0 || j == 0 || i == n - 1 || j == n - 1) print("*")
            else print(" ")
        }
        // Move to the next row.
        println()
    }
}

fun main() {
    print(pattern21(5))
}