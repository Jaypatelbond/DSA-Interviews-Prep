package kt.arrays

class PrefixSum(private val nums: IntArray) {
    private val prefix = IntArray(nums.size + 1)

    init {
        for (i in nums.indices) {
            prefix[i + 1] = prefix[i] + nums[i]
        }
    }

    // sum of range [l, r]
    fun rangeSum(l: Int, r: Int): Int {
        if (l < 0 || r >= nums.size || l > r) throw IllegalArgumentException("invalid range")
        return prefix[r + 1] - prefix[l]
    }
}

fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5)
    val ps = PrefixSum(arr)
    println("sum(1..3) = ${ps.rangeSum(1, 3)}")
}
