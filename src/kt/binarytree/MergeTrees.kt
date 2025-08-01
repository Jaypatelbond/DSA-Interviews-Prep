package kt.binarytree

class TreeNodes(var value: Int) {
    var left: TreeNodes? = null
    var right: TreeNodes? = null
}

fun mergeTrees(t1: TreeNodes?, t2: TreeNodes?): TreeNodes? {
    // If both nodes are null, return null
    if (t1 == null && t2 == null) return null

    // Get values (0 if node is null)
    val val1 = t1?.value ?: 0
    val val2 = t2?.value ?: 0

    // Create new node with sum
    val newNode = TreeNodes(val1 + val2)

    // Recursively merge left and right subtrees
    newNode.left = mergeTrees(t1?.left, t2?.left)
    newNode.right = mergeTrees(t1?.right, t2?.right)

    return newNode
}

// Simple way to print the tree (pre-order)
fun printTree(root: TreeNodes?) {
    if (root == null) return
    print("${root.value} ")
    printTree(root.left)
    printTree(root.right)
}

fun main() {
    // Tree 1
    val tree1 = TreeNodes(1).apply {
        left = TreeNodes(3).apply {
            left = TreeNodes(7)
            right = TreeNodes(4)
        }
        right = TreeNodes(2)
    }

    // Tree 2 (fixed structure)
    val tree2 = TreeNodes(1).apply {
        left = TreeNodes(5).apply {
            left = TreeNodes(2)
            right = TreeNodes(7)
        }
        right = TreeNodes(9).apply {
            right = TreeNodes(6)
        }
    }

    // Merge the trees
    val merged = mergeTrees(tree1, tree2)

    // Print the result
    print("Merged Tree: ")
    printTree(merged)
    // Output: 2 8 9 11 11 6
}