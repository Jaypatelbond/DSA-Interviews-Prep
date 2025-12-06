package kt.arrays

/**
 * Valid Palindrome
 *
 * Problem: Determine if a string is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 *
 * Approach: Two-pointer technique from both ends, skip non-alphanumeric.
 *
 * Time Complexity: O(n) - single pass
 * Space Complexity: O(1) - constant extra space
 *
 * @see <a href="https://leetcode.com/problems/valid-palindrome/">LeetCode 125</a>
 */
fun isPalindrome(s: String): Boolean {
    var left = 0
    var right = s.length - 1

    while (left < right) {
        // Skip non-alphanumeric from left
        while (left < right && !s[left].isLetterOrDigit()) {
            left++
        }

        // Skip non-alphanumeric from right
        while (left < right && !s[right].isLetterOrDigit()) {
            right--
        }

        // Compare characters (case-insensitive)
        if (s[left].lowercaseChar() != s[right].lowercaseChar()) {
            return false
        }

        left++
        right--
    }

    return true
}

/**
 * Valid Palindrome II
 * Check if string can become palindrome by removing at most one character
 */
fun validPalindromeII(s: String): Boolean {
    var left = 0
    var right = s.length - 1

    while (left < right) {
        if (s[left] != s[right]) {
            // Try removing either left or right character
            return isPalindromeRange(s, left + 1, right) ||
                    isPalindromeRange(s, left, right - 1)
        }
        left++
        right--
    }

    return true
}

private fun isPalindromeRange(s: String, start: Int, end: Int): Boolean {
    var left = start
    var right = end

    while (left < right) {
        if (s[left] != s[right]) return false
        left++
        right--
    }

    return true
}

/**
 * Longest Palindromic Substring helper
 * Expand from center to find palindrome
 */
fun longestPalindrome(s: String): String {
    if (s.isEmpty()) return ""

    var start = 0
    var maxLen = 1

    fun expandFromCenter(left: Int, right: Int): Int {
        var l = left
        var r = right

        while (l >= 0 && r < s.length && s[l] == s[r]) {
            l--
            r++
        }

        return r - l - 1
    }

    for (i in s.indices) {
        // Odd length palindrome (single center)
        val len1 = expandFromCenter(i, i)
        // Even length palindrome (two centers)
        val len2 = expandFromCenter(i, i + 1)

        val len = maxOf(len1, len2)

        if (len > maxLen) {
            maxLen = len
            start = i - (len - 1) / 2
        }
    }

    return s.substring(start, start + maxLen)
}

fun main() {
    // Test Case 1: Valid palindrome with special chars
    println("Is 'A man, a plan, a canal: Panama' palindrome?")
    println("  ${isPalindrome("A man, a plan, a canal: Panama")}") // true

    // Test Case 2: Not a palindrome
    println("\nIs 'race a car' palindrome?")
    println("  ${isPalindrome("race a car")}") // false

    // Test Case 3: Empty or whitespace
    println("\nIs ' ' palindrome?")
    println("  ${isPalindrome(" ")}") // true

    // Test Valid Palindrome II
    println("\nCan 'abca' become palindrome by removing one char?")
    println("  ${validPalindromeII("abca")}") // true (remove 'c' or 'b')

    println("\nCan 'abc' become palindrome by removing one char?")
    println("  ${validPalindromeII("abc")}") // false

    // Test Longest Palindrome
    println("\nLongest palindrome in 'babad': ${longestPalindrome("babad")}") // "bab" or "aba"
    println("Longest palindrome in 'cbbd': ${longestPalindrome("cbbd")}") // "bb"
}
