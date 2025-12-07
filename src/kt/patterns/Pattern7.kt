package kt.patterns

/**
 * # Pattern 7: Pyramid Pattern (Centered Triangle)
 *
 * Prints a centered pyramid of stars with n rows. Each row is
 * centered using leading spaces, and stars increase as 1, 3, 5, 7...
 *
 * ## Example Output (n = 5):
 * ```
 *     *
 *    ***
 *   *****
 *  *******
 * *********
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs n times (for each row, 0 to n-1)
 * - First inner loop: prints (n - i - 1) leading spaces for centering
 * - Second inner loop: prints (2 * i + 1) stars (odd numbers: 1, 3, 5, ...)
 * - Third inner loop: prints trailing spaces (optional, for symmetry)
 *
 * ## Formula:
 * - Leading spaces per row i: (n - i - 1)
 * - Stars per row i: (2 * i + 1)
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - each row processes up to 2n characters
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows in the pyramid
 */
fun pattern7(n: Int) {
    for (i in 0..<n) {
        var j1 = 1
        while (j1 <= n - i - 1) {
            print(" ")
            j1++
        }

        var j2 = 1
        while (j2 <= 2 * i + 1) {
            print("*")
            j2++
        }

        var j3 = 1
        while (j3 <= n - i) {
            print(" ")
            j3++
        }
        println()
    }
}

fun main() {
    val n = 5
    pattern7(n)
}