package kt.binarytree

/**
 * Node Depths / Sum of Depths
 *
 * Problem: Calculate the sum of depths of all nodes in a binary tree.
 * Each node's depth is defined as its distance from the root.
 *
 * Approach: Recursive DFS, passing current depth to each call.
 *
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(h) - recursion depth equals tree height
 *
 * @see <a href="https://www.algoexpert.io/questions/node-depths">AlgoExpert</a>
 */

/**
 * Calculate total sum of all node depths
 */
fun nodeDepths(root: TreeNode<Int>?): Int {
    return calculateDepths(root, 0)
}

private fun calculateDepths(node: TreeNode<Int>?, depth: Int): Int {
    if (node == null) return 0

    return depth + calculateDepths(node.left, depth + 1) + calculateDepths(node.right, depth + 1)
}

/**
 * Alternative: Sum of leaf node depths only
 * Returns total depth of paths to all leaf nodes
 */
fun totalDepthOfLeafPaths(root: TreeNode<Int>?): Int {
    return sumLeafDepths(root, 0)
}

private fun sumLeafDepths(node: TreeNode<Int>?, level: Int): Int {
    if (node == null) return 0

    // Only count leaf nodes
    if (node.left == null && node.right == null) {
        return level
    }

    return sumLeafDepths(node.left, level + 1) + sumLeafDepths(node.right, level + 1)
}

/**
 * Iterative approach using stack with (node, depth) pairs
 */
fun nodeDepthsIterative(root: TreeNode<Int>?): Int {
    if (root == null) return 0

    var totalDepth = 0
    val stack = ArrayDeque<Pair<TreeNode<Int>, Int>>()
    stack.addLast(root to 0)

    while (stack.isNotEmpty()) {
        val (node, depth) = stack.removeLast()
        totalDepth += depth

        node.left?.let { stack.addLast(it to depth + 1) }
        node.right?.let { stack.addLast(it to depth + 1) }
    }

    return totalDepth
}

/**
 * Maximum Depth of Binary Tree
 *
 * @see <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/">LeetCode 104</a>
 */
fun maxDepth(root: TreeNode<Int>?): Int {
    if (root == null) return 0
    return 1 + maxOf(maxDepth(root.left), maxDepth(root.right))
}

/**
 * Minimum Depth of Binary Tree
 *
 * @see <a href="https://leetcode.com/problems/minimum-depth-of-binary-tree/">LeetCode 111</a>
 */
fun minDepth(root: TreeNode<Int>?): Int {
    if (root == null) return 0

    val left = minDepth(root.left)
    val right = minDepth(root.right)

    // If one subtree is empty, we must go through the other
    return when {
        left == 0 -> right + 1
        right == 0 -> left + 1
        else -> minOf(left, right) + 1
    }
}

fun main() {
    /*
     * Build sample tree:
     *         1
     *        / \
     *       2   3
     *      / \   \
     *     4   5   6
     *    / \
     *   8   9
     */
    val root = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4).apply {
                left = TreeNode(8)
                right = TreeNode(9)
            }
            right = TreeNode(5)
        }
        right = TreeNode(3).apply {
            right = TreeNode(6)
            left = TreeNode(7)
        }
    }

    println("=== Node Depths ===")
    println("Sum of all node depths (recursive): ${nodeDepths(root)}")
    println("Sum of all node depths (iterative): ${nodeDepthsIterative(root)}")

    println("\n=== Leaf Depths ===")
    println("Sum of leaf depths: ${totalDepthOfLeafPaths(root)}")

    println("\n=== Tree Depth ===")
    println("Maximum depth: ${maxDepth(root)}")
    println("Minimum depth: ${minDepth(root)}")

    // Second example
    val root2 = TreeNode(10).apply {
        left = TreeNode(1)
        right = TreeNode(5).apply {
            right = TreeNode(4).apply {
                left = TreeNode(1)
                right = TreeNode(2)
            }
        }
    }

    println("\n=== Second Tree ===")
    println("Sum of leaf depths: ${totalDepthOfLeafPaths(root2)}") // 1 + 3 + 3 = 7
    println("Max depth: ${maxDepth(root2)}")
}