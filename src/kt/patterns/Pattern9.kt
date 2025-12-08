package kt.patterns

fun pattern9(n: Int) {
    straightPyramid(n)
    invertedPyramid(n)
}

private fun straightPyramid(n: Int) {
    for (i in 0..<n) {
        for (j in 1..n - i - 1) {
            print(" ")
        }

        for (j in 1..2 * i + 1) {
            print("*")
        }

        for (j in 1..n - i) {
            print(" ")
        }
        println()
    }
}

fun invertedPyramid(n: Int) {
    for (i in 0..<n) {
        for (j1 in 0..<i) {
            print(" ")
        }

        for (j2 in 0..<2 * n - (2 * i + 1)) {
            print("*")
        }

        for (j3 in 0..<i) {
            print(" ")
        }
        println()
    }
}

fun main() {
    val n = 3
    pattern9(n)
}