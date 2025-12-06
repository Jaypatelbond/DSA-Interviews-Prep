package kt.sorting

import kotlin.random.Random

/**
 * Quick Sort
 *
 * Divide and conquer sorting using partitioning.
 * Selects a pivot and partitions array so elements less than pivot
 * are on left, greater on right.
 *
 * Time Complexity:
 *   - Average: O(n log n)
 *   - Worst: O(n²) when pivot is always min/max
 *
 * Space Complexity: O(log n) for recursion stack
 *
 * Properties:
 * - In-place sorting
 * - Not stable
 * - Cache-friendly (good locality)
 *
 * @see <a href="https://leetcode.com/problems/sort-an-array/">LeetCode 912</a>
 */

/**
 * Standard Quick Sort with Lomuto partition scheme
 */
fun quickSort(arr: IntArray, low: Int = 0, high: Int = arr.size - 1) {
    if (low < high) {
        val pivotIndex = partition(arr, low, high)
        quickSort(arr, low, pivotIndex - 1)
        quickSort(arr, pivotIndex + 1, high)
    }
}

/**
 * Lomuto partition scheme
 * Pivot is always the last element
 */
private fun partition(arr: IntArray, low: Int, high: Int): Int {
    val pivot = arr[high]
    var i = low - 1  // Index of smaller element

    for (j in low until high) {
        if (arr[j] <= pivot) {
            i++
            swap(arr, i, j)
        }
    }

    swap(arr, i + 1, high)
    return i + 1
}

/**
 * Quick Sort with Hoare partition scheme
 * More efficient - fewer swaps on average
 */
fun quickSortHoare(arr: IntArray, low: Int = 0, high: Int = arr.size - 1) {
    if (low < high) {
        val pivotIndex = partitionHoare(arr, low, high)
        quickSortHoare(arr, low, pivotIndex)
        quickSortHoare(arr, pivotIndex + 1, high)
    }
}

private fun partitionHoare(arr: IntArray, low: Int, high: Int): Int {
    val pivot = arr[low + (high - low) / 2]
    var i = low - 1
    var j = high + 1

    while (true) {
        do { i++ } while (arr[i] < pivot)
        do { j-- } while (arr[j] > pivot)

        if (i >= j) return j

        swap(arr, i, j)
    }
}

/**
 * Quick Sort with random pivot
 * Avoids worst case for sorted arrays
 */
fun quickSortRandom(arr: IntArray, low: Int = 0, high: Int = arr.size - 1) {
    if (low < high) {
        val randomIndex = Random.nextInt(low, high + 1)
        swap(arr, randomIndex, high)

        val pivotIndex = partition(arr, low, high)
        quickSortRandom(arr, low, pivotIndex - 1)
        quickSortRandom(arr, pivotIndex + 1, high)
    }
}

/**
 * Three-way Quick Sort (Dutch National Flag)
 * Handles duplicates efficiently
 * Partitions into: [< pivot] [= pivot] [> pivot]
 */
fun quickSort3Way(arr: IntArray, low: Int = 0, high: Int = arr.size - 1) {
    if (low >= high) return

    val pivot = arr[low]
    var lt = low      // arr[low..lt-1] < pivot
    var i = low + 1   // arr[lt..i-1] == pivot
    var gt = high     // arr[gt+1..high] > pivot

    while (i <= gt) {
        when {
            arr[i] < pivot -> {
                swap(arr, lt, i)
                lt++
                i++
            }
            arr[i] > pivot -> {
                swap(arr, i, gt)
                gt--
            }
            else -> i++
        }
    }

    quickSort3Way(arr, low, lt - 1)
    quickSort3Way(arr, gt + 1, high)
}

private fun swap(arr: IntArray, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}

/**
 * Quick Select - Find kth smallest element in O(n) average
 *
 * @see <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">LeetCode 215</a>
 */
fun quickSelect(arr: IntArray, k: Int): Int {
    return quickSelectHelper(arr.copyOf(), 0, arr.size - 1, k - 1)
}

private fun quickSelectHelper(arr: IntArray, low: Int, high: Int, k: Int): Int {
    if (low == high) return arr[low]

    // Random pivot
    val randomIndex = Random.nextInt(low, high + 1)
    swap(arr, randomIndex, high)

    val pivotIndex = partition(arr, low, high)

    return when {
        k == pivotIndex -> arr[k]
        k < pivotIndex -> quickSelectHelper(arr, low, pivotIndex - 1, k)
        else -> quickSelectHelper(arr, pivotIndex + 1, high, k)
    }
}

/**
 * Sort Colors (Dutch National Flag Problem)
 * Sort array containing only 0, 1, 2
 *
 * @see <a href="https://leetcode.com/problems/sort-colors/">LeetCode 75</a>
 */
fun sortColors(nums: IntArray) {
    var low = 0
    var mid = 0
    var high = nums.size - 1

    while (mid <= high) {
        when (nums[mid]) {
            0 -> {
                swap(nums, low, mid)
                low++
                mid++
            }
            1 -> mid++
            2 -> {
                swap(nums, mid, high)
                high--
            }
        }
    }
}

fun main() {
    println("=== Quick Sort (Lomuto) ===")
    val arr1 = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    println("Original: ${arr1.toList()}")
    quickSort(arr1)
    println("Sorted:   ${arr1.toList()}")

    println("\n=== Quick Sort (Hoare) ===")
    val arr2 = intArrayOf(10, 80, 30, 90, 40, 50, 70)
    quickSortHoare(arr2)
    println("Sorted: ${arr2.toList()}")

    println("\n=== Quick Sort (Random Pivot) ===")
    val arr3 = intArrayOf(1, 2, 3, 4, 5) // Already sorted - would be worst case
    quickSortRandom(arr3)
    println("Sorted: ${arr3.toList()}")

    println("\n=== 3-Way Quick Sort (Duplicates) ===")
    val arr4 = intArrayOf(4, 9, 4, 4, 1, 9, 4, 4, 9, 4, 4, 1, 4)
    quickSort3Way(arr4)
    println("Sorted: ${arr4.toList()}")

    println("\n=== Quick Select ===")
    val arr5 = intArrayOf(3, 2, 1, 5, 6, 4)
    println("Array: ${arr5.toList()}")
    println("2nd smallest: ${quickSelect(arr5, 2)}") // 2
    println("4th smallest: ${quickSelect(arr5, 4)}") // 4

    println("\n=== Sort Colors ===")
    val colors = intArrayOf(2, 0, 2, 1, 1, 0)
    println("Before: ${colors.toList()}")
    sortColors(colors)
    println("After:  ${colors.toList()}")

    println("\n=== Performance Note ===")
    println("Average: O(n log n), Worst: O(n²)")
    println("Space: O(log n) for recursion")
    println("Not stable, but in-place")
}
