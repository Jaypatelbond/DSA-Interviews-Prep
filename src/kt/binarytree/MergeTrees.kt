package kt.binarytree

/**
 * Merge Two Binary Trees
 *
 * Problem: Given two binary trees, merge them by adding overlapping node values.
 * If only one tree has a node at a position, use that node.
 *
 * Approach: Recursive traversal, creating new nodes with summed values.
 *
 * Time Complexity: O(min(n, m)) - visit overlapping nodes
 * Space Complexity: O(min(h1, h2)) - recursion depth
 *
 * @see <a href="https://leetcode.com/problems/merge-two-binary-trees/">LeetCode 617</a>
 */

/**
 * Merge two trees, creating a new tree
 */
fun mergeTrees(t1: TreeNode<Int>?, t2: TreeNode<Int>?): TreeNode<Int>? {
    // If both null, return null
    if (t1 == null && t2 == null) return null

    // Get values (0 if node is null)
    val val1 = t1?.value ?: 0
    val val2 = t2?.value ?: 0

    // Create new node with summed value
    val merged = TreeNode(val1 + val2)

    // Recursively merge children
    merged.left = mergeTrees(t1?.left, t2?.left)
    merged.right = mergeTrees(t1?.right, t2?.right)

    return merged
}

/**
 * Merge trees in-place (modifies t1)
 * More space efficient as it reuses existing nodes
 */
fun mergeTreesInPlace(t1: TreeNode<Int>?, t2: TreeNode<Int>?): TreeNode<Int>? {
    if (t1 == null) return t2
    if (t2 == null) return t1

    t1.value += t2.value
    t1.left = mergeTreesInPlace(t1.left, t2.left)
    t1.right = mergeTreesInPlace(t1.right, t2.right)

    return t1
}

/**
 * Iterative merge using stack
 */
fun mergeTreesIterative(t1: TreeNode<Int>?, t2: TreeNode<Int>?): TreeNode<Int>? {
    if (t1 == null) return t2
    if (t2 == null) return t1

    val stack = ArrayDeque<Pair<TreeNode<Int>, TreeNode<Int>>>()
    stack.addLast(t1 to t2)

    while (stack.isNotEmpty()) {
        val (node1, node2) = stack.removeLast()

        // Merge values
        node1.value += node2.value

        // Handle left children
        if (node1.left == null) {
            node1.left = node2.left
        } else if (node2.left != null) {
            stack.addLast(node1.left!! to node2.left!!)
        }

        // Handle right children
        if (node1.right == null) {
            node1.right = node2.right
        } else if (node2.right != null) {
            stack.addLast(node1.right!! to node2.right!!)
        }
    }

    return t1
}

/**
 * Helper: Print tree in pre-order
 */
fun printPreOrder(root: TreeNode<Int>?, prefix: String = "") {
    if (root == null) return
    print("$prefix${root.value} ")
    printPreOrder(root.left, prefix)
    printPreOrder(root.right, prefix)
}

/**
 * Helper: Print tree structure
 */
fun printTree(node: TreeNode<Int>?, level: Int = 0, prefix: String = "Root: ") {
    if (node == null) return
    println("${" ".repeat(level * 4)}$prefix${node.value}")
    if (node.left != null || node.right != null) {
        printTree(node.left, level + 1, "L: ")
        printTree(node.right, level + 1, "R: ")
    }
}

/**
 * Get tree as level-order list (with nulls for missing nodes)
 */
fun toLevelOrder(root: TreeNode<Int>?): List<Int?> {
    if (root == null) return emptyList()

    val result = mutableListOf<Int?>()
    val queue = ArrayDeque<TreeNode<Int>?>()
    queue.addLast(root)

    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        result.add(node?.value)
        if (node != null) {
            queue.addLast(node.left)
            queue.addLast(node.right)
        }
    }

    // Trim trailing nulls
    while (result.isNotEmpty() && result.last() == null) {
        result.removeAt(result.size - 1)
    }

    return result
}

fun main() {
    /*
     * Tree 1:      Tree 2:        Merged:
     *     1            1              2
     *    / \          / \            / \
     *   3   2        5   9          8  11
     *  / \            \   \        / \   \
     * 7   4            2   6      9  11   6
     */

    val tree1 = TreeNode(1).apply {
        left = TreeNode(3).apply {
            left = TreeNode(7)
            right = TreeNode(4)
        }
        right = TreeNode(2)
    }

    val tree2 = TreeNode(1).apply {
        left = TreeNode(5).apply {
            right = TreeNode(2)
        }
        right = TreeNode(9).apply {
            right = TreeNode(6)
        }
    }

    println("=== Tree 1 ===")
    printTree(tree1)
    println("Level order: ${toLevelOrder(tree1)}")

    println("\n=== Tree 2 ===")
    printTree(tree2)
    println("Level order: ${toLevelOrder(tree2)}")

    // Create copies for different merge methods
    val t1Copy = TreeNode(1).apply {
        left = TreeNode(3).apply { left = TreeNode(7); right = TreeNode(4) }
        right = TreeNode(2)
    }
    val t2Copy = TreeNode(1).apply {
        left = TreeNode(5).apply { right = TreeNode(2) }
        right = TreeNode(9).apply { right = TreeNode(6) }
    }

    println("\n=== Merged (new tree) ===")
    val merged = mergeTrees(tree1, tree2)
    printTree(merged)
    println("Level order: ${toLevelOrder(merged)}")

    println("\n=== Edge Cases ===")
    println("Merge null + tree: ${toLevelOrder(mergeTrees(null, TreeNode(5)))}")
    println("Merge tree + null: ${toLevelOrder(mergeTrees(TreeNode(3), null))}")
    println("Merge null + null: ${mergeTrees(null, null)}")
}