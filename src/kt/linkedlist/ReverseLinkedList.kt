package kt.linkedlist

/**
 * Reverse Linked List
 *
 * Problem: Reverse a singly linked list.
 *
 * Time Complexity: O(n) - single pass through list
 * Space Complexity: O(1) for iterative, O(n) for recursive (call stack)
 *
 * @see <a href="https://leetcode.com/problems/reverse-linked-list/">LeetCode 206</a>
 */

/**
 * Iterative approach - most efficient
 * Uses three pointers: prev, current, next
 */
fun reverseListIterative(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var current = head

    while (current != null) {
        val next = current.next  // Save next
        current.next = prev       // Reverse link
        prev = current            // Move prev forward
        current = next            // Move current forward
    }

    return prev
}

/**
 * Recursive approach
 * Base case: empty or single node
 * Recursive case: reverse rest, then link current to end
 */
fun reverseListRecursive(head: ListNode?): ListNode? {
    // Base case: empty or single node
    if (head?.next == null) return head

    // Recursively reverse the rest
    val newHead = reverseListRecursive(head.next)

    // head.next is now the last node, point it back to head
    head.next!!.next = head
    head.next = null  // Prevent cycle

    return newHead
}

/**
 * Reverse a portion of the list from position m to n (1-indexed)
 * LeetCode 92: Reverse Linked List II
 */
fun reverseBetween(head: ListNode?, m: Int, n: Int): ListNode? {
    if (head == null || m == n) return head

    val dummy = ListNode(0).apply { next = head }
    var prev: ListNode? = dummy

    // Move to position before m
    repeat(m - 1) {
        prev = prev?.next
    }

    // Start reversing from position m
    val start = prev?.next
    var then = start?.next

    repeat(n - m) {
        start?.next = then?.next
        then?.next = prev?.next
        prev?.next = then
        then = start?.next
    }

    return dummy.next
}

fun main() {
    // Test Case 1: Reverse entire list (iterative)
    val list1 = ListNode.fromArray(intArrayOf(1, 2, 3, 4, 5))
    println("Original: $list1")
    val reversed1 = reverseListIterative(list1)
    println("Reversed (iterative): $reversed1")

    // Test Case 2: Reverse entire list (recursive)
    val list2 = ListNode.fromArray(intArrayOf(1, 2, 3, 4, 5))
    val reversed2 = reverseListRecursive(list2)
    println("Reversed (recursive): $reversed2")

    // Test Case 3: Single element
    val list3 = ListNode.fromArray(intArrayOf(1))
    println("\nSingle element: $list3")
    println("Reversed: ${reverseListIterative(list3)}")

    // Test Case 4: Empty list
    val list4: ListNode? = null
    println("\nEmpty list reversed: ${reverseListIterative(list4)}")

    // Test Case 5: Reverse between positions
    val list5 = ListNode.fromArray(intArrayOf(1, 2, 3, 4, 5))
    println("\nOriginal: $list5")
    val reversed5 = reverseBetween(ListNode.fromArray(intArrayOf(1, 2, 3, 4, 5)), 2, 4)
    println("Reversed between 2-4: $reversed5") // 1 -> 4 -> 3 -> 2 -> 5
}
