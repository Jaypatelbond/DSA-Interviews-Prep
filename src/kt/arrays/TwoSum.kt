package kt.arrays

fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = HashMap<Int, Int>()
    for (i in nums.indices) {
        val complement = target - nums[i]
        if (map.containsKey(complement)) {
            return intArrayOf(map[complement]!!, i)
        }
        map[nums[i]] = i
    }
    return intArrayOf()
}

fun main() {
    val nums = intArrayOf(2, 7, 11, 15)
    val res = twoSum(nums, 9)
    println("TwoSum result: ${res.joinToString()}")
}
