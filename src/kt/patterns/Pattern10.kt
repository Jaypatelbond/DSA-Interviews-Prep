package kt.patterns

fun pattern10(n: Int) {
    forwardTopPyramid(n)
    bottomPyramid(n)
}

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