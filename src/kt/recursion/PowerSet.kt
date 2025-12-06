package kt.recursion

/**
 * Power Set (All Subsets)
 *
 * Problem: Generate all possible subsets of a given set.
 * For a set of size n, there are 2^n subsets.
 *
 * Time Complexity: O(n × 2^n) - 2^n subsets, each taking O(n) to copy
 * Space Complexity: O(n × 2^n) - to store all subsets
 *
 * @see <a href="https://leetcode.com/problems/subsets/">LeetCode 78</a>
 */

/**
 * Backtracking approach (most intuitive)
 * For each element, we have two choices: include or exclude
 */
fun powerSetBacktrack(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val current = mutableListOf<Int>()

    fun backtrack(index: Int) {
        // Add current subset (at every step, not just leaves)
        result.add(current.toList())

        for (i in index until nums.size) {
            current.add(nums[i])
            backtrack(i + 1)
            current.removeAt(current.size - 1)
        }
    }

    backtrack(0)
    return result
}

/**
 * Iterative approach
 * Start with empty set, for each number add it to all existing subsets
 */
fun powerSetIterative(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    result.add(emptyList()) // Start with empty set

    for (num in nums) {
        val newSubsets = mutableListOf<List<Int>>()

        for (existing in result) {
            newSubsets.add(existing + num)
        }

        result.addAll(newSubsets)
    }

    return result
}

/**
 * Bit manipulation approach
 * Use binary numbers 0 to 2^n-1 as masks
 * Each bit represents whether to include corresponding element
 */
fun powerSetBitwise(nums: IntArray): List<List<Int>> {
    val n = nums.size
    val totalSubsets = 1 shl n  // 2^n
    val result = mutableListOf<List<Int>>()

    for (mask in 0 until totalSubsets) {
        val subset = mutableListOf<Int>()

        for (i in 0 until n) {
            if ((mask and (1 shl i)) != 0) {
                subset.add(nums[i])
            }
        }

        result.add(subset)
    }

    return result
}

/**
 * Recursive divide and conquer
 * Subsets = (subsets without last) + (subsets without last + last element)
 */
fun powerSetRecursive(nums: IntArray): List<List<Int>> {
    if (nums.isEmpty()) return listOf(emptyList())

    val last = nums.last()
    val rest = nums.dropLast(1).toIntArray()
    val subsetsWithoutLast = powerSetRecursive(rest)

    val subsetsWithLast = subsetsWithoutLast.map { it + last }

    return subsetsWithoutLast + subsetsWithLast
}

/**
 * Subsets II - with duplicates
 * Generate all unique subsets when input may contain duplicates
 *
 * @see <a href="https://leetcode.com/problems/subsets-ii/">LeetCode 90</a>
 */
fun subsetsWithDup(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val current = mutableListOf<Int>()
    val sorted = nums.sortedArray()

    fun backtrack(index: Int) {
        result.add(current.toList())

        for (i in index until sorted.size) {
            // Skip duplicates at the same level
            if (i > index && sorted[i] == sorted[i - 1]) continue

            current.add(sorted[i])
            backtrack(i + 1)
            current.removeAt(current.size - 1)
        }
    }

    backtrack(0)
    return result
}

/**
 * Combinations - subsets of specific size k
 *
 * @see <a href="https://leetcode.com/problems/combinations/">LeetCode 77</a>
 */
fun combinations(n: Int, k: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val current = mutableListOf<Int>()

    fun backtrack(start: Int) {
        if (current.size == k) {
            result.add(current.toList())
            return
        }

        // Optimization: prune if not enough elements remaining
        val remaining = n - start + 1
        val needed = k - current.size
        if (remaining < needed) return

        for (i in start..n) {
            current.add(i)
            backtrack(i + 1)
            current.removeAt(current.size - 1)
        }
    }

    backtrack(1)
    return result
}

fun main() {
    println("=== Power Set (Backtracking) ===")
    val nums = intArrayOf(1, 2, 3)
    println("Input: ${nums.toList()}")
    powerSetBacktrack(nums).forEach { println("  $it") }

    println("\n=== Power Set (Iterative) ===")
    powerSetIterative(nums).forEach { println("  $it") }

    println("\n=== Power Set (Bitwise) ===")
    powerSetBitwise(nums).forEach { println("  $it") }

    println("\n=== Power Set (Recursive) ===")
    powerSetRecursive(nums).forEach { println("  $it") }

    println("\n=== Subsets with Duplicates ===")
    val numsWithDup = intArrayOf(1, 2, 2)
    println("Input: ${numsWithDup.toList()}")
    subsetsWithDup(numsWithDup).forEach { println("  $it") }

    println("\n=== Combinations (n=4, k=2) ===")
    combinations(4, 2).forEach { println("  $it") }

    println("\n=== Statistics ===")
    for (size in 0..5) {
        val arr = (1..size).toList().toIntArray()
        val count = powerSetIterative(arr).size
        println("n=$size: $count subsets (2^$size = ${1 shl size})")
    }
}