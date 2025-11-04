package kt.linkedlist

fun reverseList(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var cur = head
    while (cur != null) {
        val next = cur.next
        cur.next = prev
        prev = cur
        cur = next
    }
    return prev
}

fun main() {
    val n1 = ListNode(1)
    val n2 = ListNode(2)
    val n3 = ListNode(3)
    n1.next = n2
    n2.next = n3

    println("original:")
    printList(n1)

    val rev = reverseList(n1)
    println("reversed:")
    printList(rev)
}
