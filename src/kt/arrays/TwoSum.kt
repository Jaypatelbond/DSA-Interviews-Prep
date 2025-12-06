package kt.arrays

/**
 * Two Sum
 *
 * Problem: Given an array of integers and a target sum, return indices of
 * the two numbers that add up to the target.
 *
 * Approach: Use a hash map to store complement values for O(n) lookup.
 *
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(n) - hash map storage
 *
 * @see <a href="https://leetcode.com/problems/two-sum/">LeetCode 1</a>
 */
fun twoSum(nums: IntArray, target: Int): IntArray {
    val seen = mutableMapOf<Int, Int>() // value -> index

    for ((index, num) in nums.withIndex()) {
        val complement = target - num

        if (complement in seen) {
            return intArrayOf(seen[complement]!!, index)
        }

        seen[num] = index
    }

    return intArrayOf() // No solution found
}

/**
 * Alternative: Two-pointer approach (requires sorted array)
 * Time: O(n log n) due to sorting
 * Space: O(1) if sorting in place
 */
fun twoSumSorted(nums: IntArray, target: Int): IntArray {
    val sorted = nums.mapIndexed { index, value -> value to index }
        .sortedBy { it.first }

    var left = 0
    var right = sorted.size - 1

    while (left < right) {
        val sum = sorted[left].first + sorted[right].first

        when {
            sum == target -> return intArrayOf(sorted[left].second, sorted[right].second)
            sum < target -> left++
            else -> right--
        }
    }

    return intArrayOf()
}

fun main() {
    // Test Case 1: Basic example
    val nums1 = intArrayOf(2, 7, 11, 15)
    val result1 = twoSum(nums1, 9)
    println("Two Sum [2,7,11,15], target=9: [${result1.joinToString()}]") // [0, 1]

    // Test Case 2: Target in middle
    val nums2 = intArrayOf(3, 2, 4)
    val result2 = twoSum(nums2, 6)
    println("Two Sum [3,2,4], target=6: [${result2.joinToString()}]") // [1, 2]

    // Test Case 3: Same element
    val nums3 = intArrayOf(3, 3)
    val result3 = twoSum(nums3, 6)
    println("Two Sum [3,3], target=6: [${result3.joinToString()}]") // [0, 1]

    // Test sorted approach
    val result4 = twoSumSorted(nums1, 9)
    println("Two Sum Sorted [2,7,11,15], target=9: [${result4.joinToString()}]")
}
