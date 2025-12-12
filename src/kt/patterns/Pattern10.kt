package kt.patterns

/**
 * # Pattern 10: Hourglass Pattern (Double Triangle)
 *
 * Prints an hourglass/bowtie shape by combining a right-angled
 * triangle with an inverted right-angled triangle.
 *
 * ## Example Output (n = 4):
 * ```
 * *
 * **
 * ***
 * ****
 * ***
 * **
 * *
 * ```
 *
 * ## Algorithm:
 * - Upper half: right-angled triangle with increasing stars
 * - Lower half: inverted right-angled triangle with decreasing stars
 * - Combines forward pyramid + bottom pyramid helpers
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - two triangles, each O(n²)
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows for each half
 */
fun pattern10(n: Int) {
    forwardTopPyramid(n)
    bottomPyramid(n)
}

/**
 * Prints the upper expanding triangle.
 *
 * @param n The number of rows
 */
fun forwardTopPyramid(n: Int) {
    // Outer loop which will loop for the rows.

    for (i in 0..<n) {
        // Inner loop which loops for the columns.

        for (j in 0..i) {
            print("*")
        }
        /* As soon as stars for each iteration are printed,
             move to the next row and give a line break */
        println()
    }
}

/**
 * Prints the lower contracting triangle.
 *
 * @param n The number of rows
 */
fun bottomPyramid(n: Int) {
    for (i in 1..<n) {
        for (j in 0..<n - i) {
            print("*")
        }
        println()
    }
}

fun main() {
    val n = 3
    pattern10(n)
}