package kt.patterns

/**
 * # Pattern 13: Continuous Number Triangle (Floyd's Triangle)
 *
 * Prints a right-angled triangle where numbers are printed
 * continuously from 1, incrementing across all rows.
 *
 * ## Example Output (n = 5):
 * ```
 * 1
 * 2 3
 * 4 5 6
 * 7 8 9 10
 * 11 12 13 14 15
 * ```
 *
 * ## Algorithm:
 * - Maintain a running counter `start` initialized to 1
 * - Outer loop runs from 1 to n (for each row)
 * - Inner loop prints `i` numbers, incrementing `start` after each print
 * - The counter continues across rows without resetting
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - total numbers printed = n(n+1)/2
 * - **Space Complexity**: O(1) - only uses loop variables and counter
 *
 * @param n The number of rows in the triangle
 */
fun pattern13(n: Int) {
    var start = 1
    for (i in 1..n) {
        for (j in 1..i) {
            print((start++).toString() + " ")
        }
        println()
    }
}

fun main() {
    print(pattern13(5))
}