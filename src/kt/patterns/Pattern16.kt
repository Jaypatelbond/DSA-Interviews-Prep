package kt.patterns

fun pattern16(n: Int) {
    for (i in 0..<n) {
        val ch = ('A' + i).toChar()
        for (j in 0..i) {
            print(ch)
        }
        println()
    }
}

fun main() {
    print(pattern16(6))
}