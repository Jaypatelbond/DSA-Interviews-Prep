package kt.dp

/**
 * Longest Common Subsequence (LCS)
 *
 * Problem: Find the length of the longest subsequence common to two strings.
 * A subsequence is a sequence that can be derived by deleting some/no elements
 * without changing the order of remaining elements.
 *
 * Time Complexity: O(m × n) where m, n are string lengths
 * Space Complexity: O(m × n) or O(min(m, n)) optimized
 *
 * @see <a href="https://leetcode.com/problems/longest-common-subsequence/">LeetCode 1143</a>
 */

/**
 * Standard DP solution with 2D table
 *
 * dp[i][j] = LCS of text1[0..i-1] and text2[0..j-1]
 *
 * If text1[i-1] == text2[j-1]: dp[i][j] = dp[i-1][j-1] + 1
 * Else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 */
fun longestCommonSubsequence(text1: String, text2: String): Int {
    val m = text1.length
    val n = text2.length
    val dp = Array(m + 1) { IntArray(n + 1) }

    for (i in 1..m) {
        for (j in 1..n) {
            dp[i][j] = if (text1[i - 1] == text2[j - 1]) {
                dp[i - 1][j - 1] + 1
            } else {
                maxOf(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    return dp[m][n]
}

/**
 * Space-optimized version using only two rows
 */
fun lcsOptimized(text1: String, text2: String): Int {
    val m = text1.length
    val n = text2.length

    // Ensure we use the shorter string for columns (less space)
    val (s1, s2) = if (m < n) text1 to text2 else text2 to text1
    val rows = s1.length
    val cols = s2.length

    var prev = IntArray(cols + 1)
    var curr = IntArray(cols + 1)

    for (i in 1..rows) {
        for (j in 1..cols) {
            curr[j] = if (s1[i - 1] == s2[j - 1]) {
                prev[j - 1] + 1
            } else {
                maxOf(prev[j], curr[j - 1])
            }
        }
        // Swap rows
        val temp = prev
        prev = curr
        curr = temp
    }

    return prev[cols]
}

/**
 * Get the actual LCS string, not just length
 */
fun lcsString(text1: String, text2: String): String {
    val m = text1.length
    val n = text2.length
    val dp = Array(m + 1) { IntArray(n + 1) }

    // Fill DP table
    for (i in 1..m) {
        for (j in 1..n) {
            dp[i][j] = if (text1[i - 1] == text2[j - 1]) {
                dp[i - 1][j - 1] + 1
            } else {
                maxOf(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    // Backtrack to find the LCS
    val lcs = StringBuilder()
    var i = m
    var j = n

    while (i > 0 && j > 0) {
        when {
            text1[i - 1] == text2[j - 1] -> {
                lcs.append(text1[i - 1])
                i--
                j--
            }
            dp[i - 1][j] > dp[i][j - 1] -> i--
            else -> j--
        }
    }

    return lcs.reverse().toString()
}

/**
 * Longest Common Substring (contiguous)
 * Different from subsequence - characters must be consecutive
 */
fun longestCommonSubstring(text1: String, text2: String): String {
    val m = text1.length
    val n = text2.length
    val dp = Array(m + 1) { IntArray(n + 1) }

    var maxLength = 0
    var endIndex = 0

    for (i in 1..m) {
        for (j in 1..n) {
            if (text1[i - 1] == text2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1

                if (dp[i][j] > maxLength) {
                    maxLength = dp[i][j]
                    endIndex = i
                }
            }
            // No else - consecutive chars must match
        }
    }

    return text1.substring(endIndex - maxLength, endIndex)
}

/**
 * Edit Distance (Levenshtein Distance)
 * Minimum operations (insert, delete, replace) to convert one string to another
 *
 * @see <a href="https://leetcode.com/problems/edit-distance/">LeetCode 72</a>
 */
fun editDistance(word1: String, word2: String): Int {
    val m = word1.length
    val n = word2.length
    val dp = Array(m + 1) { IntArray(n + 1) }

    // Base cases
    for (i in 0..m) dp[i][0] = i // Delete all chars
    for (j in 0..n) dp[0][j] = j // Insert all chars

    for (i in 1..m) {
        for (j in 1..n) {
            dp[i][j] = if (word1[i - 1] == word2[j - 1]) {
                dp[i - 1][j - 1] // No operation needed
            } else {
                1 + minOf(
                    dp[i - 1][j],     // Delete
                    dp[i][j - 1],     // Insert
                    dp[i - 1][j - 1]  // Replace
                )
            }
        }
    }

    return dp[m][n]
}

fun main() {
    println("=== Longest Common Subsequence ===")
    println("'abcde' vs 'ace': ${longestCommonSubsequence("abcde", "ace")}") // 3 (ace)
    println("'abc' vs 'abc': ${longestCommonSubsequence("abc", "abc")}")     // 3
    println("'abc' vs 'def': ${longestCommonSubsequence("abc", "def")}")     // 0

    println("\n=== LCS String ===")
    println("'abcde' vs 'ace': '${lcsString("abcde", "ace")}'")               // "ace"
    println("'AGGTAB' vs 'GXTXAYB': '${lcsString("AGGTAB", "GXTXAYB")}'")     // "GTAB"

    println("\n=== Space Optimized ===")
    println("'abcde' vs 'ace': ${lcsOptimized("abcde", "ace")}") // 3

    println("\n=== Longest Common Substring ===")
    println("'abcdxyz' vs 'xyzabcd': '${longestCommonSubstring("abcdxyz", "xyzabcd")}'") // "abcd"
    println("'zxabcdezy' vs 'yzabcdezx': '${longestCommonSubstring("zxabcdezy", "yzabcdezx")}'") // "abcdez"

    println("\n=== Edit Distance ===")
    println("'horse' -> 'ros': ${editDistance("horse", "ros")}")     // 3
    println("'intention' -> 'execution': ${editDistance("intention", "execution")}") // 5
    println("'' -> 'a': ${editDistance("", "a")}")                   // 1
    println("'a' -> 'a': ${editDistance("a", "a")}")                 // 0
}
