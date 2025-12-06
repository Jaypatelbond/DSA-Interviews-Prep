package kt.binarytree

/**
 * Branch Sums
 *
 * Problem: Calculate the sum of values along every root-to-leaf path
 * and return all sums in left-to-right order.
 *
 * Approach: DFS traversal maintaining running sum, collect at leaf nodes.
 *
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(n) - result list + O(h) recursion stack
 *
 * @see <a href="https://www.algoexpert.io/questions/branch-sums">AlgoExpert</a>
 */

/**
 * Returns list of all branch sums from left to right
 */
fun branchSums(root: TreeNode<Int>?): List<Int> {
    val result = mutableListOf<Int>()
    calculateBranchSums(root, 0, result)
    return result
}

private fun calculateBranchSums(node: TreeNode<Int>?, runningSum: Int, result: MutableList<Int>) {
    if (node == null) return

    val newSum = runningSum + node.value

    // If leaf node, add sum to result
    if (node.left == null && node.right == null) {
        result.add(newSum)
        return
    }

    // Continue to children
    calculateBranchSums(node.left, newSum, result)
    calculateBranchSums(node.right, newSum, result)
}

/**
 * Iterative approach using stack
 */
fun branchSumsIterative(root: TreeNode<Int>?): List<Int> {
    if (root == null) return emptyList()

    val result = mutableListOf<Int>()
    val stack = ArrayDeque<Pair<TreeNode<Int>, Int>>()
    stack.addLast(root to root.value)

    while (stack.isNotEmpty()) {
        val (node, currentSum) = stack.removeLast()

        // If leaf, record sum
        if (node.left == null && node.right == null) {
            result.add(currentSum)
            continue
        }

        // Add children (right first for left-to-right order)
        node.right?.let { stack.addLast(it to currentSum + it.value) }
        node.left?.let { stack.addLast(it to currentSum + it.value) }
    }

    return result
}

/**
 * Path Sum - Check if any root-to-leaf path equals target
 *
 * @see <a href="https://leetcode.com/problems/path-sum/">LeetCode 112</a>
 */
fun hasPathSum(root: TreeNode<Int>?, targetSum: Int): Boolean {
    if (root == null) return false

    val remaining = targetSum - root.value

    // If leaf and remaining is 0, found path
    if (root.left == null && root.right == null) {
        return remaining == 0
    }

    return hasPathSum(root.left, remaining) || hasPathSum(root.right, remaining)
}

/**
 * Path Sum II - Return all paths that sum to target
 *
 * @see <a href="https://leetcode.com/problems/path-sum-ii/">LeetCode 113</a>
 */
fun pathSum(root: TreeNode<Int>?, targetSum: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val currentPath = mutableListOf<Int>()

    fun dfs(node: TreeNode<Int>?, remaining: Int) {
        if (node == null) return

        currentPath.add(node.value)
        val newRemaining = remaining - node.value

        if (node.left == null && node.right == null && newRemaining == 0) {
            result.add(currentPath.toList())
        } else {
            dfs(node.left, newRemaining)
            dfs(node.right, newRemaining)
        }

        currentPath.removeAt(currentPath.size - 1)
    }

    dfs(root, targetSum)
    return result
}

fun main() {
    /*
     * Build sample tree:
     *         1
     *        / \
     *       2   3
     *      / \  / \
     *     4   5 7  6
     *    / \  |
     *   8   9 10
     *
     * Branch sums: [15, 16, 18, 11, 10]
     *   1->2->4->8 = 15
     *   1->2->4->9 = 16
     *   1->2->5->10 = 18
     *   1->3->7 = 11
     *   1->3->6 = 10
     */
    val root = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4).apply {
                left = TreeNode(8)
                right = TreeNode(9)
            }
            right = TreeNode(5).apply {
                left = TreeNode(10)
            }
        }
        right = TreeNode(3).apply {
            left = TreeNode(7)
            right = TreeNode(6)
        }
    }

    println("=== Branch Sums ===")
    println("Recursive: ${branchSums(root)}")
    println("Iterative: ${branchSumsIterative(root)}")

    println("\n=== Path Sum ===")
    println("Has path sum 15? ${hasPathSum(root, 15)}") // true
    println("Has path sum 20? ${hasPathSum(root, 20)}") // false

    println("\n=== Path Sum II ===")
    println("Paths summing to 16:")
    pathSum(root, 16).forEach { println("  $it") }

    // Simple tree for clear example
    val simple = TreeNode(5).apply {
        left = TreeNode(4).apply {
            left = TreeNode(11).apply {
                left = TreeNode(7)
                right = TreeNode(2)
            }
        }
        right = TreeNode(8).apply {
            left = TreeNode(13)
            right = TreeNode(4).apply {
                right = TreeNode(1)
            }
        }
    }

    println("\n=== Simple Tree: Paths summing to 22 ===")
    pathSum(simple, 22).forEach { println("  $it") }
    // [5, 4, 11, 2]
}