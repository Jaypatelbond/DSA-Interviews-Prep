package kt.binarytree

fun findSuccessor(tree: BinaryTree?, node: BinaryTree): BinaryTree? {
    // Case 1: If node has right child, find leftmost in right subtree recursively
    if (node.right != null) {
        return findLeftmost(node.right!!)
    }

    // Case 2: If no right child, find ancestor where node is in left subtree
    return findAncestorSuccessor(node.parent, node)
}

// Helper function to find leftmost node recursively
fun findLeftmost(node: BinaryTree): BinaryTree {
    return if (node.left == null) node else findLeftmost(node.left!!)
}

// Helper function to find ancestor successor recursively
fun findAncestorSuccessor(current: BinaryTree?, previous: BinaryTree): BinaryTree? {
    if (current == null) return null
    if (current.left == previous) return current
    return findAncestorSuccessor(current.parent, current)
}

fun main() {
    // Construct the sample tree
    val tree = BinaryTree(1)
    val node2 = BinaryTree(2)
    val node3 = BinaryTree(3)
    val node4 = BinaryTree(4)
    val node5 = BinaryTree(5)
    val node6 = BinaryTree(6)

    // Build the tree structure
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

    // Test the function
    val successor = findSuccessor(tree, node5)
    println("Successor of node ${node5.value}: ${successor?.value ?: "None"}")
}