package kt.binarytree

/**
 * Generic Binary Tree Node
 *
 * A simple generic tree node that can hold any type of value.
 * Used for problems that don't require parent pointers.
 *
 * @param T the type of value stored in the node
 * @property value the value stored in this node
 * @property left reference to the left child node
 * @property right reference to the right child node
 */
data class TreeNode<T>(
    var value: T,
    var left: TreeNode<T>? = null,
    var right: TreeNode<T>? = null
) {
    companion object {
        /**
         * Create a tree from level-order array representation
         * null values represent missing nodes
         *
         * Example: [1, 2, 3, null, 4] creates:
         *       1
         *      / \
         *     2   3
         *      \
         *       4
         */
        fun fromArray(arr: Array<Int?>): TreeNode<Int>? {
            if (arr.isEmpty() || arr[0] == null) return null

            val root = TreeNode(arr[0]!!)
            val queue = ArrayDeque<TreeNode<Int>>()
            queue.addLast(root)

            var i = 1
            while (queue.isNotEmpty() && i < arr.size) {
                val node = queue.removeFirst()

                // Left child
                if (i < arr.size && arr[i] != null) {
                    node.left = TreeNode(arr[i]!!)
                    queue.addLast(node.left!!)
                }
                i++

                // Right child
                if (i < arr.size && arr[i] != null) {
                    node.right = TreeNode(arr[i]!!)
                    queue.addLast(node.right!!)
                }
                i++
            }

            return root
        }
    }

    /**
     * Convert tree to level-order string representation
     */
    override fun toString(): String {
        val result = mutableListOf<String>()
        val queue = ArrayDeque<TreeNode<T>?>()
        queue.addLast(this)

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (node != null) {
                result.add(node.value.toString())
                queue.addLast(node.left)
                queue.addLast(node.right)
            } else {
                result.add("null")
            }
        }

        // Trim trailing nulls
        while (result.isNotEmpty() && result.last() == "null") {
            result.removeAt(result.size - 1)
        }

        return "[${result.joinToString(", ")}]"
    }
}

fun main() {
    // Test manual creation
    val tree = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4)
            right = TreeNode(5)
        }
        right = TreeNode(3)
    }

    println("Manual tree: $tree")

    // Test from array
    val fromArray = TreeNode.fromArray(arrayOf(1, 2, 3, null, 4, 5, 6))
    println("From array: $fromArray")

    // Test with strings
    val stringTree = TreeNode("root").apply {
        left = TreeNode("left")
        right = TreeNode("right")
    }
    println("String tree: $stringTree")
}