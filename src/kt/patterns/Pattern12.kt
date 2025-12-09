package kt.patterns

/**
 * # Pattern 12: Number Crown / Arrow Pattern
 *
 * Prints a symmetric pattern where numbers increase towards the center
 * and mirror back, forming an arrow or crown shape.
 *
 * ## Example Output (n = 5):
 * ```
 * 1        1
 * 12      21
 * 123    321
 * 1234  4321
 * 1234554321
 * ```
 *
 * ## Algorithm:
 * - Initialize spaces = 2 * (n - 1) for the gap in the middle
 * - Outer loop runs from 1 to n (for each row)
 * - First inner loop prints ascending numbers (1 to i)
 * - Second inner loop prints spaces (decreasing by 2 each row)
 * - Third inner loop prints descending numbers (i down to 1)
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - each row prints 2i numbers + spaces
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows in the pattern
 */
fun pattern12(n: Int) {
    var spaces = 2 * (n - 1)

    for (i in 1..n) {
        for (j in 1..i) {
            print(j)
        }

        for (j in 1..spaces) {
            print(" ")
        }

        for (j in i downTo 1) {
            print(j)
        }

        println()
        spaces -= 2
    }
}

fun main() {
    print(pattern12(5))
}