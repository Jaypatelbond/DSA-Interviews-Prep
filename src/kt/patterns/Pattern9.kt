package kt.patterns

/**
 * # Pattern 9: Diamond Pattern (Star Diamond)
 *
 * Prints a diamond shape by combining an upward pyramid
 * with an inverted pyramid.
 *
 * ## Example Output (n = 3):
 * ```
 *   *
 *  ***
 * *****
 * *****
 *  ***
 *   *
 * ```
 *
 * ## Algorithm:
 * - Combines two patterns: upward pyramid + inverted pyramid
 * - Upper half: centered pyramid with increasing stars (1, 3, 5, ...)
 * - Lower half: inverted centered pyramid with decreasing stars
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - two pyramids, each O(n²)
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows for each half of the diamond
 */
fun pattern9(n: Int) {
    straightPyramid(n)
    invertedPyramid(n)
}

/**
 * Prints the upper half of the diamond (upward pyramid).
 *
 * @param n The number of rows
 */
private fun straightPyramid(n: Int) {
    for (i in 0..<n) {
        for (j in 1..n - i - 1) {
            print(" ")
        }

        for (j in 1..2 * i + 1) {
            print("*")
        }

        for (j in 1..n - i) {
            print(" ")
        }
        println()
    }
}

/**
 * Prints the lower half of the diamond (inverted pyramid).
 *
 * @param n The number of rows
 */
fun invertedPyramid(n: Int) {
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
    pattern9(n)
}