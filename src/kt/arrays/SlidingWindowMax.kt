package kt.arrays

import java.util.ArrayDeque

/**
 * Sliding Window Maximum
 *
 * Problem: Given an array and window size k, find the maximum element
 * in each sliding window position.
 *
 * Approach: Use a monotonic deque to maintain candidates for maximum.
 * Elements in deque are kept in decreasing order.
 *
 * Time Complexity: O(n) - each element added/removed from deque once
 * Space Complexity: O(k) - deque stores at most k elements
 *
 * @see <a href="https://leetcode.com/problems/sliding-window-maximum/">LeetCode 239</a>
 */
fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    if (nums.isEmpty() || k <= 0) return intArrayOf()
    if (k == 1) return nums.copyOf()

    val result = IntArray(nums.size - k + 1)
    val deque = ArrayDeque<Int>() // Stores indices

    for (i in nums.indices) {
        // Remove elements outside current window
        while (deque.isNotEmpty() && deque.peekFirst() < i - k + 1) {
            deque.pollFirst()
        }

        // Remove smaller elements (they can never be maximum)
        while (deque.isNotEmpty() && nums[deque.peekLast()] < nums[i]) {
            deque.pollLast()
        }

        deque.offerLast(i)

        // Start recording results once we have a full window
        if (i >= k - 1) {
            result[i - k + 1] = nums[deque.peekFirst()]
        }
    }

    return result
}

/**
 * Brute Force approach for comparison
 * Time: O(n × k), Space: O(1)
 */
fun maxSlidingWindowBruteForce(nums: IntArray, k: Int): IntArray {
    if (nums.isEmpty() || k <= 0) return intArrayOf()

    val result = IntArray(nums.size - k + 1)

    for (i in 0..nums.size - k) {
        var max = nums[i]
        for (j in i until i + k) {
            max = maxOf(max, nums[j])
        }
        result[i] = max
    }

    return result
}

fun main() {
    // Test Case 1: Standard example
    val nums1 = intArrayOf(1, 3, -1, -3, 5, 3, 6, 7)
    val k1 = 3
    val result1 = maxSlidingWindow(nums1, k1)
    println("Sliding Window Max [1,3,-1,-3,5,3,6,7], k=3:")
    println("  ${result1.toList()}") // [3, 3, 5, 5, 6, 7]

    // Test Case 2: Single element window
    val nums2 = intArrayOf(1, 2, 3, 4, 5)
    val result2 = maxSlidingWindow(nums2, 1)
    println("\nSliding Window Max [1,2,3,4,5], k=1:")
    println("  ${result2.toList()}") // [1, 2, 3, 4, 5]

    // Test Case 3: Window equals array size
    val nums3 = intArrayOf(9, 11)
    val result3 = maxSlidingWindow(nums3, 2)
    println("\nSliding Window Max [9,11], k=2:")
    println("  ${result3.toList()}") // [11]

    // Test Case 4: Decreasing array
    val nums4 = intArrayOf(7, 6, 5, 4, 3, 2, 1)
    val result4 = maxSlidingWindow(nums4, 3)
    println("\nSliding Window Max [7,6,5,4,3,2,1], k=3:")
    println("  ${result4.toList()}") // [7, 6, 5, 4, 3]

    // Verify against brute force
    val bruteResult = maxSlidingWindowBruteForce(nums1, k1)
    println("\nBrute force verification: ${result1.contentEquals(bruteResult)}")
}
