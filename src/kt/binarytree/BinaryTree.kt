package kt.binarytree

/**
 * Binary Tree Node with Parent Pointer
 *
 * Used for problems that require traversing up the tree,
 * such as finding in-order successor without access to root.
 *
 * @property value the integer value stored in this node
 * @property left reference to the left child node
 * @property right reference to the right child node
 * @property parent reference to the parent node (null for root)
 */
class BinaryTree(var value: Int) {
    var left: BinaryTree? = null
    var right: BinaryTree? = null
    var parent: BinaryTree? = null

    companion object {
        /**
         * Build a tree from level-order array with parent pointers set
         */
        fun fromArray(arr: Array<Int?>): BinaryTree? {
            if (arr.isEmpty() || arr[0] == null) return null

            val root = BinaryTree(arr[0]!!)
            val queue = ArrayDeque<BinaryTree>()
            queue.addLast(root)

            var i = 1
            while (queue.isNotEmpty() && i < arr.size) {
                val node = queue.removeFirst()

                // Left child
                if (i < arr.size && arr[i] != null) {
                    node.left = BinaryTree(arr[i]!!)
                    node.left!!.parent = node
                    queue.addLast(node.left!!)
                }
                i++

                // Right child
                if (i < arr.size && arr[i] != null) {
                    node.right = BinaryTree(arr[i]!!)
                    node.right!!.parent = node
                    queue.addLast(node.right!!)
                }
                i++
            }

            return root
        }
    }

    /**
     * Check if this node is a leaf
     */
    fun isLeaf(): Boolean = left == null && right == null

    /**
     * Check if this node is the root
     */
    fun isRoot(): Boolean = parent == null

    /**
     * Get the depth (distance from root)
     */
    fun depth(): Int {
        var count = 0
        var current = parent
        while (current != null) {
            count++
            current = current.parent
        }
        return count
    }

    /**
     * Get the height of subtree rooted at this node
     */
    fun height(): Int {
        val leftH = left?.height() ?: 0
        val rightH = right?.height() ?: 0
        return 1 + maxOf(leftH, rightH)
    }

    override fun toString(): String = "BinaryTree($value)"
}

fun main() {
    // Manual construction with parent pointers
    val root = BinaryTree(1)
    val node2 = BinaryTree(2)
    val node3 = BinaryTree(3)

    root.left = node2
    root.right = node3
    node2.parent = root
    node3.parent = root

    println("Root: ${root.value}")
    println("Node 2 parent: ${node2.parent?.value}")
    println("Node 2 is root: ${node2.isRoot()}")
    println("Node 2 depth: ${node2.depth()}")
    println("Root height: ${root.height()}")

    // From array
    val tree = BinaryTree.fromArray(arrayOf(1, 2, 3, 4, 5))
    println("\nTree from array: root = ${tree?.value}")
    println("Left child: ${tree?.left?.value}, parent = ${tree?.left?.parent?.value}")
}
