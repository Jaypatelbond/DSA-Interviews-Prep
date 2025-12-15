package kt.patterns

/**
 * # Pattern 19: Hourglass Star Pattern (Double Arrow)
 *
 * Prints an hourglass/bowtie shape using stars, with
 * upper half contracting and lower half expanding.
 *
 * ## Example Output (n = 4):
 * ```
 * ****  ****
 * ***    ***
 * **      **
 * *        *
 * **      **
 * ***    ***
 * ****  ****
 * ```
 *
 * ## Algorithm:
 * - Upper half: stars decrease from n to 1, spaces increase in middle
 * - Lower half: stars increase from 1 to n, spaces decrease in middle
 * - Each row has two star sections (left and right) with spaces between
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - two halves, each O(n²)
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows for each half
 */
fun pattern19(n: Int) {
    printUpperHalf(n)
    printLowerHalf(n)
}

/**
 * Prints the upper contracting half of the hourglass.
 *
 * @param n The number of rows
 */
private fun printUpperHalf(n: Int) {
    for (i in 0..<n) {
        for (j in 0..<n - i) {
            print("*")
        }
        for (j in 0..<2 * i) {
            print(" ")
        }

        for (j in 0..<n - i) {
            print("*")
        }
        println()
    }
}

/**
 * Prints the lower expanding half of the hourglass.
 *
 * @param n The number of rows
 */
private fun printLowerHalf(n: Int) {
    for (i in 0..<n) {
        for (j in 0..i) {
            print("*")
        }
        for (j in 1..(2 * n - 2) - (2 * i)) {
            print(" ")
        }

        for (j in 0..i) {
            print("*")
        }


        println()
    }
}

fun main() {
    print(pattern19(5))
}