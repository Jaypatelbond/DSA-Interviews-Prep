# Archived: merge_sort.py

This file is an archived Python implementation moved from the codebase when the repository switched to Kotlin-only.

Original content (legacy):

```python
def merge_sort(arr, start, end):
    if start >= end:
        return arr

    mid = (start + end) // 2
    left = merge_sort(arr, start, mid)  #arr, 0, 1
    right = merge_sort(arr, mid + 1, end)  #arr, 2, 3
    merge(arr, start, mid, end)
    return arr


def merge(arr, start, mid, end):
    i = 0
    j = 0
    k = start
    left = arr[start:mid + 1]
    right = arr[mid + 1:end]
    n1 = len(left)
    n2 = len(right)
    while i < n1 and j < n2:
        if left[i] < right[j]:
            arr[k] = left[i]
            i += 1
            k += 1
        else:
            arr[k] = right[j]
            j += 1
            k += 1

    while i < n1:
        arr[k] = left[i]
        i += 1
        k += 1

    while j < n2:
        arr[k] = right[j]
        j += 1
        k += 1


print(merge_sort([8, 5, 2, 9, 5, 6, 3], 0, 6))
```

If you need this code, copy it from this file. Future work: fully remove archived files from repository history if desired.
