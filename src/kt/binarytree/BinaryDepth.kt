package kt.binarytree


fun totalDepthOfLeafPaths(root: TreeNode<Int>?): Int {
    return sumDepths(root, 0) // Start at level 1 (root)
}

private fun sumDepths(node: TreeNode<Int>?, level: Int): Int {
    if (node == null) return 0

    if (node.left == null && node.right == null) {
        return level // Leaf node reached, return its depth
    }

    return sumDepths(node.left, level + 1) + sumDepths(node.right, level + 1)
}

fun main() {
    val root = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4).apply{
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

    val root1 = TreeNode(10).apply {
        left = TreeNode(1).apply {
        }
        right = TreeNode(5).apply {
            right = TreeNode(4).apply {
                left = TreeNode(1)
                right = TreeNode(2)
            }
        }
    }

    val depth = totalDepthOfLeafPaths(root1)
    println("Tree depth: $depth") // Output: 6
}