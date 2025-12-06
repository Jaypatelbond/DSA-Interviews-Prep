package kt.binarytree

import kotlin.math.abs

/**
 * Height-Balanced Binary Tree
 *
 * Problem: Determine if a binary tree is height-balanced.
 * A tree is balanced if the height of left and right subtrees
 * of every node differ by at most 1.
 *
 * Time Complexity: O(n) optimized, O(n²) naive
 * Space Complexity: O(h) - recursion stack
 *
 * @see <a href="https://leetcode.com/problems/balanced-binary-tree/">LeetCode 110</a>
 */

/**
 * Naive approach - O(n²) due to repeated height calculations
 */
fun isBalancedNaive(tree: BinaryTree?): Boolean {
    if (tree == null) return true

    val leftHeight = height(tree.left)
    val rightHeight = height(tree.right)

    return abs(leftHeight - rightHeight) <= 1 &&
            isBalancedNaive(tree.left) &&
            isBalancedNaive(tree.right)
}

/**
 * Calculate height of a tree
 */
fun height(node: BinaryTree?): Int {
    if (node == null) return 0
    return 1 + maxOf(height(node.left), height(node.right))
}

/**
 * Optimized approach - O(n)
 * Check balance and calculate height in single pass
 * Returns -1 if subtree is unbalanced (early termination)
 */
fun isBalanced(tree: BinaryTree?): Boolean {
    return checkHeight(tree) != -1
}

private fun checkHeight(node: BinaryTree?): Int {
    if (node == null) return 0

    val leftHeight = checkHeight(node.left)
    if (leftHeight == -1) return -1  // Left subtree unbalanced

    val rightHeight = checkHeight(node.right)
    if (rightHeight == -1) return -1  // Right subtree unbalanced

    if (abs(leftHeight - rightHeight) > 1) return -1  // Current node unbalanced

    return 1 + maxOf(leftHeight, rightHeight)
}

/**
 * Alternative using sealed class for cleaner code
 */
sealed class BalanceResult {
    data class Balanced(val height: Int) : BalanceResult()
    data object Unbalanced : BalanceResult()
}

fun isBalancedClean(tree: BinaryTree?): Boolean {
    return checkBalanced(tree) is BalanceResult.Balanced
}

private fun checkBalanced(node: BinaryTree?): BalanceResult {
    if (node == null) return BalanceResult.Balanced(0)

    val left = checkBalanced(node.left)
    if (left is BalanceResult.Unbalanced) return left

    val right = checkBalanced(node.right)
    if (right is BalanceResult.Unbalanced) return right

    val leftHeight = (left as BalanceResult.Balanced).height
    val rightHeight = (right as BalanceResult.Balanced).height

    return if (abs(leftHeight - rightHeight) <= 1) {
        BalanceResult.Balanced(1 + maxOf(leftHeight, rightHeight))
    } else {
        BalanceResult.Unbalanced
    }
}

/**
 * Get the balance factor of each node (for debugging)
 */
fun getBalanceFactors(tree: BinaryTree?): Map<Int, Int> {
    val factors = mutableMapOf<Int, Int>()

    fun traverse(node: BinaryTree?) {
        if (node == null) return
        val leftH = height(node.left)
        val rightH = height(node.right)
        factors[node.value] = leftH - rightH
        traverse(node.left)
        traverse(node.right)
    }

    traverse(tree)
    return factors
}

fun main() {
    // Unbalanced tree
    val unbalanced = BinaryTree(1).apply {
        left = BinaryTree(2).apply {
            left = BinaryTree(4).apply {
                left = BinaryTree(7)
                right = BinaryTree(8)
            }
            right = BinaryTree(5)
        }
        right = BinaryTree(3)
    }

    println("=== Unbalanced Tree ===")
    println("Is balanced (naive): ${isBalancedNaive(unbalanced)}")   // false
    println("Is balanced (optimized): ${isBalanced(unbalanced)}")   // false
    println("Balance factors: ${getBalanceFactors(unbalanced)}")

    // Balanced tree
    val balanced = BinaryTree(1).apply {
        left = BinaryTree(2).apply {
            left = BinaryTree(4)
            right = BinaryTree(5)
        }
        right = BinaryTree(3).apply {
            left = BinaryTree(6)
            right = BinaryTree(7)
        }
    }

    println("\n=== Balanced Tree ===")
    println("Is balanced: ${isBalanced(balanced)}")  // true
    println("Balance factors: ${getBalanceFactors(balanced)}")

    // Edge cases
    println("\n=== Edge Cases ===")
    println("Empty tree: ${isBalanced(null)}")  // true
    println("Single node: ${isBalanced(BinaryTree(1))}")  // true

    // Just barely unbalanced
    val barelyUnbalanced = BinaryTree(1).apply {
        left = BinaryTree(2).apply {
            left = BinaryTree(3).apply {
                left = BinaryTree(4)
            }
        }
    }
    println("Chain of 4: ${isBalanced(barelyUnbalanced)}")  // false
}