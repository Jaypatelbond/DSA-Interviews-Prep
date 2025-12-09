package kt.patterns

/**
 * # Pattern 11: Binary Number Triangle (Alternating 0s and 1s)
 *
 * Prints a right-angled triangle where each row alternates between 0 and 1.
 * Odd rows start with 1, even rows start with 0.
 *
 * ## Example Output (n = 5):
 * ```
 * 1
 * 0 1
 * 1 0 1
 * 0 1 0 1
 * 1 0 1 0 1
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs from 1 to n (for each row)
 * - Start value is 1 for odd rows, 0 for even rows
 * - Inner loop prints numbers, toggling between 0 and 1 using `1 - start`
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - total iterations = 1 + 2 + ... + n = n(n+1)/2
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows in the triangle
 */
fun pattern11(n: Int) {
    for (i in 1..n) {
        var start = if (i % 2 != 0) 1 else 0

        for (j in 1..i) {
            print(start.toString() + " ")
            start = 1 - start // toggle between 0 and 1
        }

        println()
    }
}

fun main() {
    val n = 3
    pattern11(n)
}