package kt.patterns

/**
 * # Pattern 8: Inverted Pyramid Pattern (Centered, Decreasing)
 *
 * Prints an inverted centered pyramid of stars. Each row has
 * decreasing stars centered with leading and trailing spaces.
 *
 * ## Example Output (n = 3):
 * ```
 * *****
 *  ***
 *   *
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs n times (for each row, 0 to n-1)
 * - First inner loop: prints i leading spaces for centering
 * - Second inner loop: prints (2*n - (2*i + 1)) stars
 *   - Row 0: 2n - 1 stars, Row 1: 2n - 3 stars, etc.
 * - Third inner loop: prints i trailing spaces (for symmetry)
 *
 * ## Formula:
 * - Leading/trailing spaces per row i: i
 * - Stars per row i: 2*n - 2*i - 1 = 2*(n-i) - 1
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - each row processes up to 2n characters
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows in the inverted pyramid
 */
fun pattern8(n: Int) {
    for (i in 0..<n) {
        for (j1 in 0..<i) {
            print(" ")
        }

        for (j2 in 0..<2 * n - (2 * i + 1)) {
            print("*")
        }

        for (j3 in 0..<i) {
            print(" ")
        }
        println()
    }
}

fun main() {
    val n = 3
    pattern8(n)
}