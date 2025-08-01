package kt.binarytree

fun branchSums(root: TreeNode<Int>?): List<Int> {
    val result = mutableListOf<Int>()
    calculateBranchSums(root, 0, result)
    return result
}

private fun calculateBranchSums(node: TreeNode<Int>?, runningSum: Int, result: MutableList<Int>) {
    if (node == null) return

    val newSum = runningSum + node.value

    if (node.left == null && node.right == null) {
        result.add(newSum)
        return
    }

    calculateBranchSums(node.left, newSum, result)
    calculateBranchSums(node.right, newSum, result)
}

fun main() {
    val root = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4).apply{
                left = TreeNode(8)
                right = TreeNode(9)
            }
            right = TreeNode(5).apply{
                left = TreeNode(10)
            }
        }
        right = TreeNode(3).apply {
            right = TreeNode(6)
            left = TreeNode(7)
        }
    }

    val sums = branchSums(root)
    println("Branch sums: $sums") // Output Branch sums: [15, 16, 18, 11, 10]
}