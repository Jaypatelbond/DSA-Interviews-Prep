package kt.linkedlist

/**
 * Merge Two Sorted Linked Lists
 *
 * Problem: Merge two sorted linked lists into one sorted list.
 *
 * Approach: Use a dummy head and compare nodes from both lists.
 *
 * Time Complexity: O(n + m) where n, m are lengths of the lists
 * Space Complexity: O(1) - reusing existing nodes
 *
 * @see <a href="https://leetcode.com/problems/merge-two-sorted-lists/">LeetCode 21</a>
 */

/**
 * Iterative approach with dummy head
 */
fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    val dummy = ListNode(0)
    var current = dummy
    var p1 = l1
    var p2 = l2

    while (p1 != null && p2 != null) {
        if (p1.value <= p2.value) {
            current.next = p1
            p1 = p1.next
        } else {
            current.next = p2
            p2 = p2.next
        }
        current = current.next!!
    }

    // Attach remaining nodes
    current.next = p1 ?: p2

    return dummy.next
}

/**
 * Recursive approach
 */
fun mergeTwoListsRecursive(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null) return l2
    if (l2 == null) return l1

    return if (l1.value <= l2.value) {
        l1.next = mergeTwoListsRecursive(l1.next, l2)
        l1
    } else {
        l2.next = mergeTwoListsRecursive(l1, l2.next)
        l2
    }
}

/**
 * Merge K Sorted Lists using divide and conquer
 * Time: O(N log k) where N is total nodes and k is number of lists
 *
 * @see <a href="https://leetcode.com/problems/merge-k-sorted-lists/">LeetCode 23</a>
 */
fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    if (lists.isEmpty()) return null
    return mergeKListsHelper(lists, 0, lists.size - 1)
}

private fun mergeKListsHelper(lists: Array<ListNode?>, start: Int, end: Int): ListNode? {
    if (start == end) return lists[start]
    if (start > end) return null

    val mid = start + (end - start) / 2
    val left = mergeKListsHelper(lists, start, mid)
    val right = mergeKListsHelper(lists, mid + 1, end)

    return mergeTwoLists(left, right)
}

fun main() {
    // Test Case 1: Merge two sorted lists (iterative)
    val list1 = ListNode.fromArray(intArrayOf(1, 2, 4))
    val list2 = ListNode.fromArray(intArrayOf(1, 3, 4))
    println("List 1: $list1")
    println("List 2: $list2")
    val merged = mergeTwoLists(list1, list2)
    println("Merged (iterative): $merged") // 1 -> 1 -> 2 -> 3 -> 4 -> 4

    // Test Case 2: Merge with recursive approach
    val list3 = ListNode.fromArray(intArrayOf(1, 3, 5))
    val list4 = ListNode.fromArray(intArrayOf(2, 4, 6))
    val mergedRecursive = mergeTwoListsRecursive(list3, list4)
    println("\nMerged (recursive): $mergedRecursive") // 1 -> 2 -> 3 -> 4 -> 5 -> 6

    // Test Case 3: One empty list
    val list5 = ListNode.fromArray(intArrayOf(1, 2, 3))
    val mergedWithEmpty = mergeTwoLists(list5, null)
    println("\nMerge with empty: $mergedWithEmpty")

    // Test Case 4: Both empty
    println("Both empty: ${mergeTwoLists(null, null)}")

    // Test Case 5: Merge K sorted lists
    val lists = arrayOf(
        ListNode.fromArray(intArrayOf(1, 4, 5)),
        ListNode.fromArray(intArrayOf(1, 3, 4)),
        ListNode.fromArray(intArrayOf(2, 6))
    )
    println("\nMerge K Lists:")
    lists.forEachIndexed { i, list -> println("  List $i: $list") }
    val mergedK = mergeKLists(lists)
    println("Merged: $mergedK") // 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
}
