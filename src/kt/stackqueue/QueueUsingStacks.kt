package kt.stackqueue

import java.util.Stack

/**
 * Implement Queue using Stacks
 *
 * Problem: Implement a FIFO queue using only two stacks.
 *
 * Approach: Use two stacks - one for push operations, one for pop/peek.
 * Transfer elements lazily from input to output stack when needed.
 *
 * Time Complexity:
 *   - push: O(1)
 *   - pop/peek: Amortized O(1)
 *
 * Space Complexity: O(n)
 *
 * @see <a href="https://leetcode.com/problems/implement-queue-using-stacks/">LeetCode 232</a>
 */
class MyQueue {
    private val inputStack = Stack<Int>()  // For push operations
    private val outputStack = Stack<Int>() // For pop/peek operations

    /**
     * Push element to the back of queue
     * Always O(1)
     */
    fun push(x: Int) {
        inputStack.push(x)
    }

    /**
     * Remove and return the front element
     * Amortized O(1) - transfer only when output is empty
     */
    fun pop(): Int {
        ensureOutputHasElements()
        return outputStack.pop()
    }

    /**
     * Return the front element without removing
     */
    fun peek(): Int {
        ensureOutputHasElements()
        return outputStack.peek()
    }

    /**
     * Check if queue is empty
     */
    fun empty(): Boolean {
        return inputStack.isEmpty() && outputStack.isEmpty()
    }

    /**
     * Transfer elements from input to output stack if output is empty
     * This reverses the order, converting LIFO to FIFO
     */
    private fun ensureOutputHasElements() {
        if (outputStack.isEmpty()) {
            while (inputStack.isNotEmpty()) {
                outputStack.push(inputStack.pop())
            }
        }
    }

    fun size(): Int = inputStack.size + outputStack.size
}

/**
 * Implement Stack using Queues
 * The reverse problem - LIFO using FIFO
 *
 * @see <a href="https://leetcode.com/problems/implement-stack-using-queues/">LeetCode 225</a>
 */
class MyStack {
    private val queue = ArrayDeque<Int>()

    /**
     * Push element - O(n)
     * Add new element, then rotate all previous elements to back
     */
    fun push(x: Int) {
        queue.addLast(x)
        // Rotate to bring new element to front for pop
        repeat(queue.size - 1) {
            queue.addLast(queue.removeFirst())
        }
    }

    /**
     * Pop element - O(1)
     */
    fun pop(): Int {
        return queue.removeFirst()
    }

    /**
     * Peek at top - O(1)
     */
    fun top(): Int {
        return queue.first()
    }

    fun empty(): Boolean = queue.isEmpty()
}

/**
 * Circular Queue implementation
 *
 * @see <a href="https://leetcode.com/problems/design-circular-queue/">LeetCode 622</a>
 */
class MyCircularQueue(private val capacity: Int) {
    private val data = IntArray(capacity)
    private var front = 0
    private var rear = -1
    private var size = 0

    fun enQueue(value: Int): Boolean {
        if (isFull()) return false
        rear = (rear + 1) % capacity
        data[rear] = value
        size++
        return true
    }

    fun deQueue(): Boolean {
        if (isEmpty()) return false
        front = (front + 1) % capacity
        size--
        return true
    }

    fun Front(): Int = if (isEmpty()) -1 else data[front]

    fun Rear(): Int = if (isEmpty()) -1 else data[rear]

    fun isEmpty(): Boolean = size == 0

    fun isFull(): Boolean = size == capacity
}

fun main() {
    // Test Queue using Stacks
    println("=== Queue using Stacks ===")
    val queue = MyQueue()
    queue.push(1)
    queue.push(2)
    println("Peek: ${queue.peek()}")   // 1
    println("Pop: ${queue.pop()}")     // 1
    println("Empty: ${queue.empty()}") // false
    println("Pop: ${queue.pop()}")     // 2
    println("Empty: ${queue.empty()}") // true

    // Test Stack using Queues
    println("\n=== Stack using Queues ===")
    val stack = MyStack()
    stack.push(1)
    stack.push(2)
    println("Top: ${stack.top()}")     // 2
    println("Pop: ${stack.pop()}")     // 2
    println("Empty: ${stack.empty()}") // false

    // Test Circular Queue
    println("\n=== Circular Queue ===")
    val circularQueue = MyCircularQueue(3)
    println("EnQueue 1: ${circularQueue.enQueue(1)}") // true
    println("EnQueue 2: ${circularQueue.enQueue(2)}") // true
    println("EnQueue 3: ${circularQueue.enQueue(3)}") // true
    println("EnQueue 4: ${circularQueue.enQueue(4)}") // false (full)
    println("Rear: ${circularQueue.Rear()}")          // 3
    println("Is Full: ${circularQueue.isFull()}")     // true
    println("DeQueue: ${circularQueue.deQueue()}")    // true
    println("EnQueue 4: ${circularQueue.enQueue(4)}") // true
    println("Rear: ${circularQueue.Rear()}")          // 4
}
