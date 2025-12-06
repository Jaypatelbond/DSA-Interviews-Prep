package kt.linkedlist

/**
 * Singly Linked List Node
 *
 * Basic building block for linked list problems.
 * Contains value and reference to next node.
 */
data class ListNode(
    var value: Int,
    var next: ListNode? = null
) {
    /**
     * Helper function to create a linked list from array
     */
    companion object {
        fun fromArray(arr: IntArray): ListNode? {
            if (arr.isEmpty()) return null

            val dummy = ListNode(0)
            var current = dummy

            for (value in arr) {
                current.next = ListNode(value)
                current = current.next!!
            }

            return dummy.next
        }
    }

    /**
     * Convert linked list to string for display
     */
    override fun toString(): String {
        val sb = StringBuilder()
        var current: ListNode? = this
        val visited = mutableSetOf<ListNode>()

        while (current != null) {
            if (current in visited) {
                sb.append("... (cycle detected)")
                break
            }
            visited.add(current)
            sb.append(current.value)
            if (current.next != null) sb.append(" -> ")
            current = current.next
        }

        return sb.toString()
    }

    /**
     * Convert linked list to array
     */
    fun toArray(): IntArray {
        val list = mutableListOf<Int>()
        var current: ListNode? = this

        while (current != null) {
            list.add(current.value)
            current = current.next
        }

        return list.toIntArray()
    }
}

fun main() {
    // Test creating from array
    val list = ListNode.fromArray(intArrayOf(1, 2, 3, 4, 5))
    println("Created list: $list")

    // Test converting back to array
    println("As array: ${list?.toArray()?.toList()}")

    // Test manual creation
    val head = ListNode(10).apply {
        next = ListNode(20).apply {
            next = ListNode(30)
        }
    }
    println("Manual list: $head")
}
