# DSA Interview Prep 🚀

![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?style=flat&logo=kotlin&logoColor=white)
![Status](https://img.shields.io/badge/Status-In%20Progress-yellow?style=flat)
![Contributions](https://img.shields.io/badge/Contributions-Welcome-brightgreen?style=flat)

A collaborative repository for mastering **Data Structures & Algorithms** for coding interviews. Solutions are written in Kotlin with detailed explanations and complexity analysis.

---

## 📊 Progress Tracker

| Topic | Problems | Status |
|-------|----------|--------|
| [Binary Tree](#-binary-tree) | 6 | ✅ Complete |
| [Arrays & Strings](#-arrays--strings) | 4 | ✅ Complete |
| [Linked Lists](#-linked-lists) | 4 | ✅ Complete |
| [Stacks & Queues](#-stacks--queues) | 3 | ✅ Complete |
| [Graphs](#-graphs) | 4 | ✅ Complete |
| [Dynamic Programming](#-dynamic-programming) | 4 | ✅ Complete |
| [Sorting & Searching](#-sorting--searching) | 3 | ✅ Complete |
| [Recursion](#-recursion) | 1 | ✅ Complete |

**Total: 29 problems**

---

## 📚 Problem Index

### 🌳 Binary Tree

| Problem | Difficulty | Time | Space | File |
|---------|------------|------|-------|------|
| Branch Sums | 🟢 Easy | O(n) | O(n) | [BinarySum.kt](src/kt/binarytree/BinarySum.kt) |
| Node Depths | 🟢 Easy | O(n) | O(h) | [BinaryDepth.kt](src/kt/binarytree/BinaryDepth.kt) |
| Find Successor | 🟡 Medium | O(h) | O(1) | [BinarySuccessor.kt](src/kt/binarytree/BinarySuccessor.kt) |
| Height Balanced | 🟡 Medium | O(n) | O(h) | [HeightBalancedTree.kt](src/kt/binarytree/HeightBalancedTree.kt) |
| Merge Binary Trees | 🟢 Easy | O(n) | O(h) | [MergeTrees.kt](src/kt/binarytree/MergeTrees.kt) |

---

### 📦 Arrays & Strings

| Problem | Difficulty | Time | Space | File |
|---------|------------|------|-------|------|
| Two Sum | 🟢 Easy | O(n) | O(n) | [TwoSum.kt](src/kt/arrays/TwoSum.kt) |
| Three Sum | 🟡 Medium | O(n²) | O(n) | [ThreeSum.kt](src/kt/arrays/ThreeSum.kt) |
| Sliding Window Maximum | 🔴 Hard | O(n) | O(k) | [SlidingWindowMax.kt](src/kt/arrays/SlidingWindowMax.kt) |
| Valid Palindrome | 🟢 Easy | O(n) | O(1) | [ValidPalindrome.kt](src/kt/arrays/ValidPalindrome.kt) |

---

### 🔗 Linked Lists

| Problem | Difficulty | Time | Space | File |
|---------|------------|------|-------|------|
| Reverse Linked List | 🟢 Easy | O(n) | O(1) | [ReverseLinkedList.kt](src/kt/linkedlist/ReverseLinkedList.kt) |
| Linked List Cycle | 🟢 Easy | O(n) | O(1) | [LinkedListCycle.kt](src/kt/linkedlist/LinkedListCycle.kt) |
| Merge Two Sorted Lists | 🟢 Easy | O(n+m) | O(1) | [MergeTwoLists.kt](src/kt/linkedlist/MergeTwoLists.kt) |

---

### 📚 Stacks & Queues

| Problem | Difficulty | Time | Space | File |
|---------|------------|------|-------|------|
| Valid Parentheses | 🟢 Easy | O(n) | O(n) | [ValidParentheses.kt](src/kt/stackqueue/ValidParentheses.kt) |
| Min Stack | 🟡 Medium | O(1) | O(n) | [MinStack.kt](src/kt/stackqueue/MinStack.kt) |
| Queue Using Stacks | 🟢 Easy | O(1)* | O(n) | [QueueUsingStacks.kt](src/kt/stackqueue/QueueUsingStacks.kt) |

---

### 🕸️ Graphs

| Problem | Difficulty | Time | Space | File |
|---------|------------|------|-------|------|
| BFS Traversal | 🟡 Medium | O(V+E) | O(V) | [BreadthFirstSearch.kt](src/kt/graph/BreadthFirstSearch.kt) |
| DFS Traversal | 🟡 Medium | O(V+E) | O(V) | [DepthFirstSearch.kt](src/kt/graph/DepthFirstSearch.kt) |
| Topological Sort | 🟡 Medium | O(V+E) | O(V) | [TopologicalSort.kt](src/kt/graph/TopologicalSort.kt) |

---

### 💡 Dynamic Programming

| Problem | Difficulty | Time | Space | File |
|---------|------------|------|-------|------|
| Fibonacci | 🟢 Easy | O(n) | O(1) | [Fibonacci.kt](src/kt/dp/Fibonacci.kt) |
| Climbing Stairs | 🟢 Easy | O(n) | O(1) | [ClimbingStairs.kt](src/kt/dp/ClimbingStairs.kt) |
| Coin Change | 🟡 Medium | O(n×m) | O(n) | [CoinChange.kt](src/kt/dp/CoinChange.kt) |
| Longest Common Subsequence | 🟡 Medium | O(n×m) | O(n×m) | [LongestCommonSubsequence.kt](src/kt/dp/LongestCommonSubsequence.kt) |

---

### 🔍 Sorting & Searching

| Problem | Difficulty | Time | Space | File |
|---------|------------|------|-------|------|
| Binary Search | 🟢 Easy | O(log n) | O(1) | [BinarySearch.kt](src/kt/sorting/BinarySearch.kt) |
| Merge Sort | 🟡 Medium | O(n log n) | O(n) | [MergeSort.kt](src/kt/sorting/MergeSort.kt) |
| Quick Sort | 🟡 Medium | O(n log n) | O(log n) | [QuickSort.kt](src/kt/sorting/QuickSort.kt) |

---

### 🔄 Recursion

| Problem | Difficulty | Time | Space | File |
|---------|------------|------|-------|------|
| Power Set | 🟡 Medium | O(n·2ⁿ) | O(n·2ⁿ) | [PowerSet.kt](src/kt/recursion/PowerSet.kt) |

---

## 🗂️ Project Structure

```
src/kt/
├── arrays/          # Array manipulation & string problems
├── binarytree/      # Binary tree traversals & operations
├── dp/              # Dynamic programming patterns
├── graph/           # Graph traversals & algorithms
├── linkedlist/      # Linked list operations
├── recursion/       # Recursive problem solving
├── sorting/         # Sorting algorithms & binary search
└── stackqueue/      # Stack & queue implementations
```

---

## 🚀 Getting Started

### Prerequisites
- Kotlin 1.9+ or IntelliJ IDEA

### Running Solutions
```bash
# Compile and run any solution
kotlinc src/kt/arrays/TwoSum.kt -include-runtime -d TwoSum.jar
java -jar TwoSum.jar
```

Or simply open in IntelliJ IDEA and run the `main()` function in any file.

---

## 🤝 Contributing

Contributions are welcome! Feel free to:
- Add new problems with solutions
- Improve existing implementations
- Add test cases
- Fix bugs or typos

---

## 📖 Resources

- [LeetCode](https://leetcode.com/)
- [AlgoExpert](https://www.algoexpert.io/)
- [NeetCode Roadmap](https://neetcode.io/roadmap)

---

*Happy Coding! 🎯*
