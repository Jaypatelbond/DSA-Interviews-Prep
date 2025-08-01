package kt.binarytree

data class TreeNode<T>(
    var value: T,
    var left: TreeNode<T>? = null,
    var right: TreeNode<T>? = null
)