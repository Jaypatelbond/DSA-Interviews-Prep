package kt.sorting

fun mergeSort(arr: IntArray) {
    fun sort(l: Int, r: Int) {
        if (l >= r) return
        val m = (l + r) / 2
        sort(l, m)
        sort(m + 1, r)
        merge(l, m, r)
    }

    fun merge(l: Int, m: Int, r: Int) {
        val left = arr.sliceArray(l..m)
        val right = arr.sliceArray(m + 1..r)
        var i = 0
        var j = 0
        var k = l
        while (i < left.size && j < right.size) {
            if (left[i] <= right[j]) arr[k++] = left[i++] else arr[k++] = right[j++]
        }
        while (i < left.size) arr[k++] = left[i++]
        while (j < right.size) arr[k++] = right[j++]
    }

    sort(0, arr.size - 1)
}

fun main() {
    val arr = intArrayOf(8, 5, 2, 9, 5, 6, 3)
    println("before: ${arr.joinToString()}")
    mergeSort(arr)
    println("after: ${arr.joinToString()}")
}
