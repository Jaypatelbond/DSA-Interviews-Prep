package kt.linkedlist

/**
 * Linked List Cycle Detection
 *
 * Problem: Detect if a linked list has a cycle.
 *
 * Approach: Floyd's Cycle Detection Algorithm (Tortoise and Hare)
 * - Use two pointers: slow (1 step) and fast (2 steps)
 * - If they meet, there's a cycle
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * @see <a href="https://leetcode.com/problems/linked-list-cycle/">LeetCode 141</a>
 */
fun hasCycle(head: ListNode?): Boolean {
    var slow = head
    var fast = head

    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next

        if (slow === fast) {
            return true
        }
    }

    return false
}

/**
 * Find the start of the cycle (if exists)
 *
 * After detecting cycle, reset one pointer to head.
 * Move both at same speed - they meet at cycle start.
 *
 * @see <a href="https://leetcode.com/problems/linked-list-cycle-ii/">LeetCode 142</a>
 */
fun detectCycleStart(head: ListNode?): ListNode? {
    var slow = head
    var fast = head

    // Phase 1: Detect if cycle exists
    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next

        if (slow === fast) {
            // Phase 2: Find cycle start
            var pointer = head

            while (pointer !== slow) {
                pointer = pointer?.next
                slow = slow?.next
            }

            return pointer
        }
    }

    return null
}

/**
 * Find the length of the cycle
 */
fun cycleLength(head: ListNode?): Int {
    var slow = head
    var fast = head

    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next

        if (slow === fast) {
            // Count the cycle length
            var count = 1
            var current = slow?.next

            while (current !== slow) {
                count++
                current = current?.next
            }

            return count
        }
    }

    return 0
}

fun main() {
    // Test Case 1: List with cycle
    val node1 = ListNode(3)
    val node2 = ListNode(2)
    val node3 = ListNode(0)
    val node4 = ListNode(-4)

    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node2  // Creates cycle back to node2

    println("List with cycle (3 -> 2 -> 0 -> -4 -> back to 2):")
    println("  Has cycle: ${hasCycle(node1)}") // true
    println("  Cycle starts at: ${detectCycleStart(node1)?.value}") // 2
    println("  Cycle length: ${cycleLength(node1)}") // 3

    // Test Case 2: List without cycle
    val list2 = ListNode.fromArray(intArrayOf(1, 2, 3, 4, 5))
    println("\nList without cycle (1 -> 2 -> 3 -> 4 -> 5):")
    println("  Has cycle: ${hasCycle(list2)}") // false
    println("  Cycle starts at: ${detectCycleStart(list2)}") // null

    // Test Case 3: Single node with self-loop
    val single = ListNode(1)
    single.next = single

    println("\nSingle node self-loop:")
    println("  Has cycle: ${hasCycle(single)}") // true
    println("  Cycle length: ${cycleLength(single)}") // 1

    // Test Case 4: Empty list
    println("\nEmpty list:")
    println("  Has cycle: ${hasCycle(null)}") // false
}
