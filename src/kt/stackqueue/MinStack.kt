package kt.stackqueue

import java.util.Stack

/**
 * Min Stack
 *
 * Problem: Design a stack that supports push, pop, top, and retrieving
 * the minimum element in constant time.
 *
 * Approach: Use two stacks - one for values and one for minimums.
 * Each push to min stack stores the minimum at that point.
 *
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n) for the auxiliary min stack
 *
 * @see <a href="https://leetcode.com/problems/min-stack/">LeetCode 155</a>
 */
class MinStack {
    private val stack = Stack<Int>()
    private val minStack = Stack<Int>()

    fun push(value: Int) {
        stack.push(value)

        // Push to minStack if empty or new value is <= current min
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value)
        }
    }

    fun pop() {
        if (stack.isEmpty()) return

        val popped = stack.pop()

        // Pop from minStack if it was the minimum
        if (popped == minStack.peek()) {
            minStack.pop()
        }
    }

    fun top(): Int {
        return stack.peek()
    }

    fun getMin(): Int {
        return minStack.peek()
    }

    fun isEmpty(): Boolean = stack.isEmpty()
}

/**
 * Space-optimized MinStack using single stack with pairs
 * Stores (value, minAtThisPoint) for each element
 */
class MinStackOptimized {
    private val stack = Stack<Pair<Int, Int>>()

    fun push(value: Int) {
        val newMin = if (stack.isEmpty()) value else minOf(value, stack.peek().second)
        stack.push(value to newMin)
    }

    fun pop() {
        stack.pop()
    }

    fun top(): Int = stack.peek().first

    fun getMin(): Int = stack.peek().second
}

/**
 * Max Stack with popMax operation
 * More complex - uses TreeMap for efficient max retrieval
 */
class MaxStack {
    private val stack = Stack<Int>()
    private val maxStack = Stack<Int>()

    fun push(value: Int) {
        stack.push(value)
        val currentMax = if (maxStack.isEmpty()) value else maxOf(value, maxStack.peek())
        maxStack.push(currentMax)
    }

    fun pop(): Int {
        maxStack.pop()
        return stack.pop()
    }

    fun top(): Int = stack.peek()

    fun peekMax(): Int = maxStack.peek()

    // O(n) operation - removes and returns the max element
    fun popMax(): Int {
        val max = peekMax()
        val buffer = Stack<Int>()

        // Find and remove the max
        while (top() != max) {
            buffer.push(pop())
        }
        pop()

        // Restore elements
        while (buffer.isNotEmpty()) {
            push(buffer.pop())
        }

        return max
    }
}

fun main() {
    // Test MinStack
    println("=== MinStack Test ===")
    val minStack = MinStack()
    minStack.push(-2)
    minStack.push(0)
    minStack.push(-3)
    println("Min: ${minStack.getMin()}") // -3
    minStack.pop()
    println("Top: ${minStack.top()}")    // 0
    println("Min: ${minStack.getMin()}") // -2

    // Test MinStack with duplicates
    println("\n=== MinStack with Duplicates ===")
    val minStack2 = MinStack()
    minStack2.push(0)
    minStack2.push(1)
    minStack2.push(0)
    println("Min: ${minStack2.getMin()}") // 0
    minStack2.pop()
    println("Min after pop: ${minStack2.getMin()}") // 0

    // Test MaxStack
    println("\n=== MaxStack Test ===")
    val maxStack = MaxStack()
    maxStack.push(5)
    maxStack.push(1)
    maxStack.push(5)
    println("Top: ${maxStack.top()}")       // 5
    println("Max: ${maxStack.peekMax()}")   // 5
    println("PopMax: ${maxStack.popMax()}") // 5
    println("Top: ${maxStack.top()}")       // 1
    println("Max: ${maxStack.peekMax()}")   // 5
}
