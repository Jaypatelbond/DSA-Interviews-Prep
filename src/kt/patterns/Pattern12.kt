package kt.patterns

fun pattern12(n: Int) {
    // Initial no. of spaces in row 1.
    var spaces = 2 * (n - 1)

    // Outer loop for the number of rows.
    for (i in 1..n) {
        // For printing numbers in each row
        for (j in 1..i) {
            print(j)
        }

        // For printing spaces in each row
        for (j in 1..spaces) {
            print(" ")
        }

        // For printing numbers in each row
        for (j in i downTo 1) {
            print(j)
        }

        /* As soon as the numbers for each iteration
            are printed, we move to the next row and give
            a line break otherwise all numbers would get
            printed in 1 line*/
        println()

        /* After each iteration nos. increase by
            2, thus spaces will decrement by 2*/
        spaces -= 2
    }
}

fun main() {
    print(pattern12(5))
}