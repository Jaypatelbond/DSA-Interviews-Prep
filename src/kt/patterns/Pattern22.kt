package kt.patterns

import kotlin.math.min

/**
 * # Pattern 22: Number Frame Pattern (Concentric Layers)
 *
 * Prints a (2n-1) x (2n-1) grid where numbers form concentric
 * rectangular layers from the outside (n) to the inside (1).
 *
 * ## Example Output (n = 4):
 * ```
 * 4 4 4 4 4 4 4
 * 4 3 3 3 3 3 4
 * 4 3 2 2 2 3 4
 * 4 3 2 1 2 3 4
 * 4 3 2 2 2 3 4
 * 4 3 3 3 3 3 4
 * 4 4 4 4 4 4 4
 * ```
 *
 * ## Algorithm:
 * - Grid size = (2n - 1) x (2n - 1)
 * - For each cell (i, j), calculate distances from all four edges:
 *   - top = i, bottom = (2n - 2) - i
 *   - left = j, right = (2n - 2) - j
 * - The value at cell = n - min(top, bottom, left, right)
 * - This creates concentric rectangular frames with values n down to 1
 *
 * ## Complexity Analysis:
 * - **Time Complexity**: O(n²) - processes (2n-1)² cells
 * - **Space Complexity**: O(1) - only uses loop variables
 *
 * @param n The number determining grid size and maximum value
 */
fun pattern22(n: Int) {
    for (i in 0..<2 * n - 1) {
        for (j in 0..<2 * n - 1) {
            val top = i
            val left = j
            val right = (2 * n - 2) - j
            val bottom = (2 * n - 2) - i
            print((n - min(min(top, right), min(left, bottom))).toString() + " ")
        }
        println()
    }
}

fun main() {
    print(pattern22(5))
}