package kt.sorting

/**
 * Merge Sort
 *
 * Divide and conquer sorting algorithm.
 * Recursively divides array in half, sorts, then merges.
 *
 * Time Complexity: O(n log n) - always
 * Space Complexity: O(n) - for temporary arrays
 *
 * Properties:
 * - Stable sort (preserves relative order of equal elements)
 * - Not in-place (requires extra memory)
 * - Consistent performance regardless of input
 *
 * @see <a href="https://leetcode.com/problems/sort-an-array/">LeetCode 912</a>
 */

/**
 * Standard merge sort implementation
 */
fun mergeSort(arr: IntArray): IntArray {
    if (arr.size <= 1) return arr

    val mid = arr.size / 2
    val left = mergeSort(arr.sliceArray(0 until mid))
    val right = mergeSort(arr.sliceArray(mid until arr.size))

    return merge(left, right)
}

/**
 * Merge two sorted arrays into one sorted array
 */
private fun merge(left: IntArray, right: IntArray): IntArray {
    val result = IntArray(left.size + right.size)
    var i = 0
    var j = 0
    var k = 0

    while (i < left.size && j < right.size) {
        if (left[i] <= right[j]) {
            result[k++] = left[i++]
        } else {
            result[k++] = right[j++]
        }
    }

    // Copy remaining elements
    while (i < left.size) result[k++] = left[i++]
    while (j < right.size) result[k++] = right[j++]

    return result
}

/**
 * In-place merge sort (still uses O(n) temp space for merge)
 */
fun mergeSortInPlace(arr: IntArray, left: Int = 0, right: Int = arr.size - 1) {
    if (left >= right) return

    val mid = left + (right - left) / 2
    mergeSortInPlace(arr, left, mid)
    mergeSortInPlace(arr, mid + 1, right)
    mergeInPlace(arr, left, mid, right)
}

private fun mergeInPlace(arr: IntArray, left: Int, mid: Int, right: Int) {
    val leftArr = arr.sliceArray(left..mid)
    val rightArr = arr.sliceArray((mid + 1)..right)

    var i = 0
    var j = 0
    var k = left

    while (i < leftArr.size && j < rightArr.size) {
        if (leftArr[i] <= rightArr[j]) {
            arr[k++] = leftArr[i++]
        } else {
            arr[k++] = rightArr[j++]
        }
    }

    while (i < leftArr.size) arr[k++] = leftArr[i++]
    while (j < rightArr.size) arr[k++] = rightArr[j++]
}

/**
 * Bottom-up merge sort (iterative)
 * Avoids recursion overhead
 */
fun mergeSortBottomUp(arr: IntArray): IntArray {
    if (arr.size <= 1) return arr

    val result = arr.copyOf()
    val temp = IntArray(arr.size)

    var size = 1
    while (size < arr.size) {
        var left = 0
        while (left < arr.size - size) {
            val mid = left + size - 1
            val right = minOf(left + 2 * size - 1, arr.size - 1)
            mergeBottomUp(result, temp, left, mid, right)
            left += 2 * size
        }
        size *= 2
    }

    return result
}

private fun mergeBottomUp(arr: IntArray, temp: IntArray, left: Int, mid: Int, right: Int) {
    for (i in left..right) temp[i] = arr[i]

    var i = left
    var j = mid + 1
    var k = left

    while (i <= mid && j <= right) {
        if (temp[i] <= temp[j]) {
            arr[k++] = temp[i++]
        } else {
            arr[k++] = temp[j++]
        }
    }

    while (i <= mid) arr[k++] = temp[i++]
}

/**
 * Count inversions using merge sort
 * An inversion is when arr[i] > arr[j] where i < j
 *
 * @see <a href="https://leetcode.com/problems/count-of-smaller-numbers-after-self/">LeetCode 315</a>
 */
fun countInversions(arr: IntArray): Long {
    val temp = IntArray(arr.size)
    return mergeSortCount(arr, temp, 0, arr.size - 1)
}

private fun mergeSortCount(arr: IntArray, temp: IntArray, left: Int, right: Int): Long {
    if (left >= right) return 0L

    val mid = left + (right - left) / 2
    var count = mergeSortCount(arr, temp, left, mid)
    count += mergeSortCount(arr, temp, mid + 1, right)
    count += mergeCount(arr, temp, left, mid, right)

    return count
}

private fun mergeCount(arr: IntArray, temp: IntArray, left: Int, mid: Int, right: Int): Long {
    for (i in left..right) temp[i] = arr[i]

    var i = left
    var j = mid + 1
    var k = left
    var inversions = 0L

    while (i <= mid && j <= right) {
        if (temp[i] <= temp[j]) {
            arr[k++] = temp[i++]
        } else {
            arr[k++] = temp[j++]
            inversions += (mid - i + 1) // All remaining left elements form inversions
        }
    }

    while (i <= mid) arr[k++] = temp[i++]

    return inversions
}

fun main() {
    println("=== Merge Sort ===")
    val arr1 = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    println("Original: ${arr1.toList()}")
    println("Sorted:   ${mergeSort(arr1).toList()}")

    println("\n=== In-Place Merge Sort ===")
    val arr2 = intArrayOf(5, 2, 4, 6, 1, 3)
    mergeSortInPlace(arr2)
    println("Sorted: ${arr2.toList()}")

    println("\n=== Bottom-Up Merge Sort ===")
    val arr3 = intArrayOf(38, 27, 43, 3, 9, 82, 10)
    println("Sorted: ${mergeSortBottomUp(arr3).toList()}")

    println("\n=== Count Inversions ===")
    val arr4 = intArrayOf(2, 4, 1, 3, 5)
    println("Array: ${arr4.toList()}")
    println("Inversions: ${countInversions(arr4.copyOf())}") // 3 (2>1, 4>1, 4>3)

    val arr5 = intArrayOf(5, 4, 3, 2, 1)
    println("\nArray: ${arr5.toList()}")
    println("Inversions: ${countInversions(arr5.copyOf())}") // 10 (completely reversed)

    println("\n=== Stability Test ===")
    // Pairs of (value, original_index)
    data class Item(val value: Int, val index: Int)
    val items = listOf(Item(3, 0), Item(1, 1), Item(3, 2), Item(2, 3))
    println("Original: $items")
    val sorted = items.sortedWith(compareBy { it.value })
    println("Sorted (stable): $sorted")
    // Note: Kotlin's sortedWith uses a stable sort
}
