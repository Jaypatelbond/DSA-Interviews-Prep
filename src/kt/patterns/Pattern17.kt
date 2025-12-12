package kt.patterns

/**
 * # Pattern 17: Alphabet Pyramid (Mirrored Letters)
 *
 * Prints a centered pyramid where each row contains letters that
 * increase to the middle and then decrease (mirror pattern).
 *
 * ## Example Output (n = 5):
 * ```
 *     A
 *    ABA
 *   ABCBA
 *  ABCDCBA
 * ABCDEDCBA
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs from 0 to n-1 (for each row)
 * - First inner loop prints (n - i - 1) leading spaces for centering
 * - Second inner loop prints (2 * i + 1) characters:
 *   - First half: letters increase from 'A' (A, B, C, ...)
 *   - Second half: letters decrease back to 'A' (..., C, B, A)
 * - The midpoint character appears once (not duplicated)
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - each row processes up to 2n characters
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows in the pyramid
 */
fun pattern17(n: Int) {
    for (i in 0..<n) {
        for (j in 0..<n - i - 1) {
            print(" ")
        }
        var ch = 'A'
        val noOfChar = (2 * i + 1) / 2
        for (j in 1..2 * i + 1) {
            print(ch)
            if (j <= noOfChar) ch++
            else ch--
        }
        println()
    }
}

fun main() {
    print(pattern17(5))
}