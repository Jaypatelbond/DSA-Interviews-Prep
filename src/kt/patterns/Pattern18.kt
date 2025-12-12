package kt.patterns

/**
 * # Pattern 18: Reverse Alphabet Triangle (Starting from End)
 *
 * Prints a right-angled triangle where each row starts from a
 * decreasing letter and goes up to the last letter of the alphabet range.
 *
 * ## Example Output (n = 5):
 * ```
 * E
 * D E
 * C D E
 * B C D E
 * A B C D E
 * ```
 *
 * ## Algorithm:
 * - Outer loop runs from 0 to n-1 (for each row)
 * - For row i, starting letter = 'A' + (n - 1 - i)
 * - Ending letter = 'A' + (n - 1) (constant for all rows)
 * - Inner loop prints from starting letter to ending letter
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - total iterations = 1 + 2 + ... + n = n(n+1)/2
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows in the triangle
 */
fun pattern18(n: Int) {
    for (i in 0..<n) {
        for (ch in (('A' + n - 1) - i).toChar()..('A' + n - 1)) {
            print(ch.toString() + " ")
        }

        println()
    }
}

fun main() {
    print(pattern18(5))
}