package kt.patterns

/**
 * # Pattern 20: Butterfly Pattern (Symmetric Diamond)
 *
 * Prints a butterfly/symmetric diamond pattern where stars expand
 * from corners towards the center, then contract back.
 *
 * ## Example Output (n = 4):
 * ```
 * *      *
 * **    **
 * ***  ***
 * ********
 * ***  ***
 * **    **
 * *      *
 * ```
 *
 * ## Algorithm:
 * - Total rows = 2n - 1
 * - Upper half (rows 1 to n): stars increase, spaces decrease
 * - Lower half (rows n+1 to 2n-1): stars decrease, spaces increase
 * - Each row has: stars + spaces + stars
 * - Spaces management: start at 2*(n-1), decrease by 2 until middle, then increase
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - processes 2n rows with up to 2n characters each
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number of rows for each half
 */
fun pattern20(n: Int) {
    // Initialising the spaces.
    var spaces = 2 * n - 2


    // Outer loop to print the row.
    for (i in 1..2 * n - 1) {
        // Stars for first half
        var stars = i


        // Stars for the second half.
        if (i > n) stars = 2 * n - i


        // For printing the stars
        for (j in 1..stars) {
            print("*")
        }


        // For printing the spaces
        for (j in 1..spaces) {
            print(" ")
        }


        // For printing the stars
        for (j in 1..stars) {
            print("*")
        }


        // Give a line break for new row.
        println()

        if (i < n) spaces -= 2
        else spaces += 2
    }
}

fun main() {
    print(pattern20(5))
}