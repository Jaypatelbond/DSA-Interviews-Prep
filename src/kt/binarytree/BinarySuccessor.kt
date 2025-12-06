package kt.binarytree

/**
 * Find Successor (In-Order Successor)
 *
 * Problem: Given a node in a binary tree with parent pointers,
 * find its in-order successor (the next node in in-order traversal).
 *
 * Approach:
 * - If node has right child: successor is leftmost node in right subtree
 * - If no right child: go up until we find an ancestor we're in the left subtree of
 *
 * Time Complexity: O(h) where h is tree height
 * Space Complexity: O(1) - no recursion, just pointer traversal
 *
 * @see <a href="https://www.algoexpert.io/questions/find-successor">AlgoExpert</a>
 * @see <a href="https://leetcode.com/problems/inorder-successor-in-bst-ii/">LeetCode 510</a>
 */

/**
 * Find in-order successor of given node
 *
 * @param tree The root of the tree (unused but typical in API)
 * @param node The node to find successor of
 * @return The successor node, or null if node is the last in in-order
 */
fun findSuccessor(tree: BinaryTree?, node: BinaryTree): BinaryTree? {
    // Case 1: Node has right child
    // Successor is the leftmost node in the right subtree
    if (node.right != null) {
        return findLeftmost(node.right!!)
    }

    // Case 2: No right child
    // Go up to find ancestor where node is in left subtree
    return findAncestorSuccessor(node.parent, node)
}

/**
 * Find the leftmost (smallest) node in a subtree
 */
private fun findLeftmost(node: BinaryTree): BinaryTree {
    var current = node
    while (current.left != null) {
        current = current.left!!
    }
    return current
}

/**
 * Find ancestor successor by traversing up the tree
 */
private fun findAncestorSuccessor(current: BinaryTree?, previous: BinaryTree): BinaryTree? {
    if (current == null) return null

    // If previous is left child of current, current is the successor
    if (current.left == previous) return current

    // Keep going up
    return findAncestorSuccessor(current.parent, current)
}

/**
 * Find in-order predecessor (reverse of successor)
 */
fun findPredecessor(tree: BinaryTree?, node: BinaryTree): BinaryTree? {
    // Case 1: Node has left child
    // Predecessor is the rightmost node in left subtree
    if (node.left != null) {
        return findRightmost(node.left!!)
    }

    // Case 2: No left child
    // Go up to find ancestor where node is in right subtree
    return findAncestorPredecessor(node.parent, node)
}

private fun findRightmost(node: BinaryTree): BinaryTree {
    var current = node
    while (current.right != null) {
        current = current.right!!
    }
    return current
}

private fun findAncestorPredecessor(current: BinaryTree?, previous: BinaryTree): BinaryTree? {
    if (current == null) return null
    if (current.right == previous) return current
    return findAncestorPredecessor(current.parent, current)
}

/**
 * Get full in-order traversal for verification
 */
fun inOrderTraversal(node: BinaryTree?): List<Int> {
    val result = mutableListOf<Int>()

    fun traverse(n: BinaryTree?) {
        if (n == null) return
        traverse(n.left)
        result.add(n.value)
        traverse(n.right)
    }

    traverse(node)
    return result
}

fun main() {
    /*
     * Build sample tree:
     *         1
     *        / \
     *       2   3
     *      / \
     *     4   5
     *    /
     *   6
     *
     * In-order: 6, 4, 2, 5, 1, 3
     */
    val tree = BinaryTree(1)
    val node2 = BinaryTree(2)
    val node3 = BinaryTree(3)
    val node4 = BinaryTree(4)
    val node5 = BinaryTree(5)
    val node6 = BinaryTree(6)

    // Build tree structure with parent pointers
    tree.left = node2
    tree.right = node3
    node2.parent = tree
    node3.parent = tree

    node2.left = node4
    node2.right = node5
    node4.parent = node2
    node5.parent = node2

    node4.left = node6
    node6.parent = node4

    println("=== In-Order Traversal ===")
    println("Order: ${inOrderTraversal(tree)}")  // [6, 4, 2, 5, 1, 3]

    println("\n=== Find Successor ===")
    val nodes = listOf(node6, node4, node2, node5, tree, node3)
    for (node in nodes) {
        val successor = findSuccessor(tree, node)
        println("Successor of ${node.value}: ${successor?.value ?: "None"}")
    }

    println("\n=== Find Predecessor ===")
    for (node in nodes) {
        val predecessor = findPredecessor(tree, node)
        println("Predecessor of ${node.value}: ${predecessor?.value ?: "None"}")
    }

    /*
     * Expected output:
     * Successor of 6: 4
     * Successor of 4: 2
     * Successor of 2: 5
     * Successor of 5: 1
     * Successor of 1: 3
     * Successor of 3: None
     */
}