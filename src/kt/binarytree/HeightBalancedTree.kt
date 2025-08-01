package kt.binarytree

import kotlin.math.abs

fun isBalanced(tree: BinaryTree?): Boolean {
    // Base case: empty tree is balanced
    if (tree == null) return true

    // Calculate heights of left and right subtrees
    val leftHeight = height(tree.left)
    val rightHeight = height(tree.right)

    // Check current node's balance and recurse for subtrees
    return abs(leftHeight - rightHeight) <= 1 && isBalanced(tree.left) && isBalanced(tree.right)
}

fun height(node: BinaryTree?): Int {
    if (node == null) return 0
    return 1 + Math.max(height(node.left), height(node.right))
}

fun main() {
    // Build the sample tree
    val tree = BinaryTree(1).apply {
        left = BinaryTree(2).apply {
            left = BinaryTree(4).apply {
                left = BinaryTree(7)
                right = BinaryTree(8)
            }
            right = BinaryTree(5)
        }
        right = BinaryTree(3)
    }

    // Check if balanced
    val result = isBalanced(tree)
    println("Is the tree balanced? $result") // Output: Is the tree balanced? false
}