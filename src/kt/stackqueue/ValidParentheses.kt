package kt.stackqueue

import java.util.Stack

/**
 * Valid Parentheses
 *
 * Problem: Determine if a string containing only '(){}[]' is valid.
 * A string is valid if brackets are closed in the correct order.
 *
 * Approach: Use a stack to match opening and closing brackets.
 *
 * Time Complexity: O(n) - single pass
 * Space Complexity: O(n) - stack size in worst case
 *
 * @see <a href="https://leetcode.com/problems/valid-parentheses/">LeetCode 20</a>
 */
fun isValid(s: String): Boolean {
    val stack = Stack<Char>()
    val pairs = mapOf(')' to '(', '}' to '{', ']' to '[')

    for (char in s) {
        if (char in "({[") {
            stack.push(char)
        } else if (char in ")]}") {
            if (stack.isEmpty() || stack.pop() != pairs[char]) {
                return false
            }
        }
    }

    return stack.isEmpty()
}

/**
 * Minimum Add to Make Parentheses Valid
 * Count minimum insertions to make string valid
 *
 * @see <a href="https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/">LeetCode 921</a>
 */
fun minAddToMakeValid(s: String): Int {
    var openCount = 0   // Unmatched '('
    var closeCount = 0  // Unmatched ')'

    for (char in s) {
        when (char) {
            '(' -> openCount++
            ')' -> {
                if (openCount > 0) {
                    openCount--
                } else {
                    closeCount++
                }
            }
        }
    }

    return openCount + closeCount
}

/**
 * Longest Valid Parentheses
 * Find the length of the longest valid parentheses substring
 *
 * @see <a href="https://leetcode.com/problems/longest-valid-parentheses/">LeetCode 32</a>
 */
fun longestValidParentheses(s: String): Int {
    var maxLen = 0
    val stack = Stack<Int>()
    stack.push(-1)  // Base for length calculation

    for (i in s.indices) {
        if (s[i] == '(') {
            stack.push(i)
        } else {
            stack.pop()

            if (stack.isEmpty()) {
                stack.push(i)  // New base
            } else {
                maxLen = maxOf(maxLen, i - stack.peek())
            }
        }
    }

    return maxLen
}

/**
 * Generate Parentheses
 * Generate all valid combinations of n pairs of parentheses
 *
 * @see <a href="https://leetcode.com/problems/generate-parentheses/">LeetCode 22</a>
 */
fun generateParenthesis(n: Int): List<String> {
    val result = mutableListOf<String>()

    fun backtrack(current: String, open: Int, close: Int) {
        if (current.length == 2 * n) {
            result.add(current)
            return
        }

        if (open < n) {
            backtrack(current + "(", open + 1, close)
        }
        if (close < open) {
            backtrack(current + ")", open, close + 1)
        }
    }

    backtrack("", 0, 0)
    return result
}

fun main() {
    // Test Case 1: Valid parentheses
    println("Is '()[]{}' valid? ${isValid("()[]{}")}") // true
    println("Is '(]' valid? ${isValid("(]")}") // false
    println("Is '([)]' valid? ${isValid("([)]")}") // false
    println("Is '{[]}' valid? ${isValid("{[]}")}") // true

    // Test Case 2: Min additions needed
    println("\nMin adds for '())': ${minAddToMakeValid("())")}")   // 1
    println("Min adds for '(((': ${minAddToMakeValid("(((")}") // 3
    println("Min adds for '()': ${minAddToMakeValid("()")}")   // 0

    // Test Case 3: Longest valid substring
    println("\nLongest valid in '(()': ${longestValidParentheses("(()")}")   // 2
    println("Longest valid in ')()())': ${longestValidParentheses(")()())")}") // 4
    println("Longest valid in '': ${longestValidParentheses("")}")       // 0

    // Test Case 4: Generate parentheses
    println("\nGenerate n=3 pairs:")
    generateParenthesis(3).forEach { println("  $it") }
    // ((())), (()()), (())(), ()(()), ()()()
}
