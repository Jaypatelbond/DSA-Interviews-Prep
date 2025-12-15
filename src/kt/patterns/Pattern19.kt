package kt.patterns

fun pattern19(n: Int) {
    printUpperHalf(n)
    printLowerHalf(n)
}

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