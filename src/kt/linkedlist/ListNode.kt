package kt.linkedlist

data class ListNode(var value: Int, var next: ListNode? = null)

fun printList(head: ListNode?) {
    var cur = head
    val sb = StringBuilder()
    while (cur != null) {
        sb.append(cur.value).append(" -> ")
        cur = cur.next
    }
    sb.append("null")
    println(sb.toString())
}
