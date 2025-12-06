package kt.sorting

/**
 * Binary Search
 *
 * Efficient search algorithm for sorted arrays.
 * Repeatedly divides search interval in half.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1) iterative, O(log n) recursive
 *
 * @see <a href="https://leetcode.com/problems/binary-search/">LeetCode 704</a>
 */

/**
 * Standard binary search - returns index or -1
 */
fun binarySearch(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1

    while (left <= right) {
        val mid = left + (right - left) / 2 // Avoid overflow

        when {
            nums[mid] == target -> return mid
            nums[mid] < target -> left = mid + 1
            else -> right = mid - 1
        }
    }

    return -1
}

/**
 * Recursive binary search
 */
fun binarySearchRecursive(nums: IntArray, target: Int, left: Int = 0, right: Int = nums.size - 1): Int {
    if (left > right) return -1

    val mid = left + (right - left) / 2

    return when {
        nums[mid] == target -> mid
        nums[mid] < target -> binarySearchRecursive(nums, target, mid + 1, right)
        else -> binarySearchRecursive(nums, target, left, mid - 1)
    }
}

/**
 * Find first occurrence of target (leftmost)
 * Useful when there are duplicates
 */
fun findFirst(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1
    var result = -1

    while (left <= right) {
        val mid = left + (right - left) / 2

        when {
            nums[mid] == target -> {
                result = mid
                right = mid - 1 // Continue searching left
            }
            nums[mid] < target -> left = mid + 1
            else -> right = mid - 1
        }
    }

    return result
}

/**
 * Find last occurrence of target (rightmost)
 */
fun findLast(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1
    var result = -1

    while (left <= right) {
        val mid = left + (right - left) / 2

        when {
            nums[mid] == target -> {
                result = mid
                left = mid + 1 // Continue searching right
            }
            nums[mid] < target -> left = mid + 1
            else -> right = mid - 1
        }
    }

    return result
}

/**
 * Search in Rotated Sorted Array
 *
 * @see <a href="https://leetcode.com/problems/search-in-rotated-sorted-array/">LeetCode 33</a>
 */
fun searchRotated(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1

    while (left <= right) {
        val mid = left + (right - left) / 2

        if (nums[mid] == target) return mid

        // Determine which half is sorted
        if (nums[left] <= nums[mid]) {
            // Left half is sorted
            if (target >= nums[left] && target < nums[mid]) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        } else {
            // Right half is sorted
            if (target > nums[mid] && target <= nums[right]) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
    }

    return -1
}

/**
 * Find minimum in rotated sorted array
 *
 * @see <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/">LeetCode 153</a>
 */
fun findMin(nums: IntArray): Int {
    var left = 0
    var right = nums.size - 1

    while (left < right) {
        val mid = left + (right - left) / 2

        if (nums[mid] > nums[right]) {
            left = mid + 1
        } else {
            right = mid
        }
    }

    return nums[left]
}

/**
 * Search Insert Position
 * Find index where target should be inserted to maintain sorted order
 *
 * @see <a href="https://leetcode.com/problems/search-insert-position/">LeetCode 35</a>
 */
fun searchInsert(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size

    while (left < right) {
        val mid = left + (right - left) / 2

        if (nums[mid] < target) {
            left = mid + 1
        } else {
            right = mid
        }
    }

    return left
}

/**
 * Peak Element - find any local maximum
 *
 * @see <a href="https://leetcode.com/problems/find-peak-element/">LeetCode 162</a>
 */
fun findPeakElement(nums: IntArray): Int {
    var left = 0
    var right = nums.size - 1

    while (left < right) {
        val mid = left + (right - left) / 2

        if (nums[mid] > nums[mid + 1]) {
            right = mid
        } else {
            left = mid + 1
        }
    }

    return left
}

fun main() {
    println("=== Binary Search ===")
    val nums = intArrayOf(-1, 0, 3, 5, 9, 12)
    println("Array: ${nums.toList()}")
    println("Find 9: index ${binarySearch(nums, 9)}")       // 4
    println("Find 2: index ${binarySearch(nums, 2)}")       // -1

    println("\n=== First and Last Occurrence ===")
    val duplicates = intArrayOf(1, 2, 2, 2, 3, 4, 5)
    println("Array: ${duplicates.toList()}")
    println("First 2: ${findFirst(duplicates, 2)}")         // 1
    println("Last 2: ${findLast(duplicates, 2)}")           // 3

    println("\n=== Rotated Array Search ===")
    val rotated = intArrayOf(4, 5, 6, 7, 0, 1, 2)
    println("Rotated: ${rotated.toList()}")
    println("Find 0: ${searchRotated(rotated, 0)}")         // 4
    println("Find 3: ${searchRotated(rotated, 3)}")         // -1

    println("\n=== Find Minimum in Rotated ===")
    println("Min in ${rotated.toList()}: ${findMin(rotated)}") // 0
    println("Min in [3,4,5,1,2]: ${findMin(intArrayOf(3, 4, 5, 1, 2))}") // 1

    println("\n=== Search Insert Position ===")
    val sorted = intArrayOf(1, 3, 5, 6)
    println("Insert 5: ${searchInsert(sorted, 5)}")         // 2
    println("Insert 2: ${searchInsert(sorted, 2)}")         // 1
    println("Insert 7: ${searchInsert(sorted, 7)}")         // 4

    println("\n=== Find Peak Element ===")
    val peaks = intArrayOf(1, 2, 1, 3, 5, 6, 4)
    println("Peak in ${peaks.toList()}: index ${findPeakElement(peaks)}") // 1 or 5
}
