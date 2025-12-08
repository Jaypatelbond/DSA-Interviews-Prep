package kt.patterns

fun pattern11(n: Int) {
    for (i in 1..n) {
        var start = if (i % 2 != 0) 1 else 0

        for (j in 1..i) {
            print(start.toString() + " ")
            start = 1 - start // toggle between 0 and 1
        }

        println()
    }
}

fun main() {
    val n = 3
    pattern11(n)
}