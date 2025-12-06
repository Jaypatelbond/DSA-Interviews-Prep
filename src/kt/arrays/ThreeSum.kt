package kt.arrays

/**
 * Three Sum
 *
 * Problem: Find all unique triplets in the array that sum to zero.
 *
 * Approach: Sort array, then for each element use two-pointer technique
 * to find pairs that sum to the negative of that element.
 *
 * Time Complexity: O(n²) - outer loop × two-pointer search
 * Space Complexity: O(n) - for result storage (O(1) auxiliary)
 *
 * @see <a href="https://leetcode.com/problems/3sum/">LeetCode 15</a>
 */
fun threeSum(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val sorted = nums.sortedArray()

    for (i in sorted.indices) {
        // Skip duplicates for first element
        if (i > 0 && sorted[i] == sorted[i - 1]) continue

        // Early termination: if smallest is positive, no solution possible
        if (sorted[i] > 0) break

        val target = -sorted[i]
        var left = i + 1
        var right = sorted.size - 1

        while (left < right) {
            val sum = sorted[left] + sorted[right]

            when {
                sum == target -> {
                    result.add(listOf(sorted[i], sorted[left], sorted[right]))

                    // Skip duplicates
                    while (left < right && sorted[left] == sorted[left + 1]) left++
                    while (left < right && sorted[right] == sorted[right - 1]) right--

                    left++
                    right--
                }
                sum < target -> left++
                else -> right--
            }
        }
    }

    return result
}

/**
 * Three Sum with target value
 * Generalized version that finds triplets summing to any target
 */
fun threeSumTarget(nums: IntArray, target: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val sorted = nums.sortedArray()

    for (i in sorted.indices) {
        if (i > 0 && sorted[i] == sorted[i - 1]) continue

        val remaining = target - sorted[i]
        var left = i + 1
        var right = sorted.size - 1

        while (left < right) {
            val sum = sorted[left] + sorted[right]

            when {
                sum == remaining -> {
                    result.add(listOf(sorted[i], sorted[left], sorted[right]))
                    while (left < right && sorted[left] == sorted[left + 1]) left++
                    while (left < right && sorted[right] == sorted[right - 1]) right--
                    left++
                    right--
                }
                sum < remaining -> left++
                else -> right--
            }
        }
    }

    return result
}

fun main() {
    // Test Case 1: Standard example
    val nums1 = intArrayOf(-1, 0, 1, 2, -1, -4)
    println("Three Sum [-1,0,1,2,-1,-4]:")
    threeSum(nums1).forEach { println("  $it") }
    // Output: [[-1,-1,2], [-1,0,1]]

    // Test Case 2: No triplets
    val nums2 = intArrayOf(0, 1, 1)
    println("\nThree Sum [0,1,1]:")
    threeSum(nums2).forEach { println("  $it") }
    // Output: (empty)

    // Test Case 3: All zeros
    val nums3 = intArrayOf(0, 0, 0, 0)
    println("\nThree Sum [0,0,0,0]:")
    threeSum(nums3).forEach { println("  $it") }
    // Output: [[0,0,0]]

    // Test with custom target
    val nums4 = intArrayOf(1, 2, 3, 4, 5)
    println("\nThree Sum Target=9 [1,2,3,4,5]:")
    threeSumTarget(nums4, 9).forEach { println("  $it") }
    // Output: [[1,3,5], [2,3,4]]
}
