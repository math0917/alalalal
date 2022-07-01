import sys
input = sys.stdin.readline
import collections

n = int(input())

A = list(map(int,input().split()))

result = [-1]*n

dictionary = collections.defaultdict(int)
for i in A:
    dictionary[i] += 1

stack = collections.deque([[dictionary[A[0]],0]])

idx = 1

while idx < n:

    this_val,this_idx = stack.pop()
    # 오등큰수 발견
    if this_val < dictionary[A[idx]]:
        result[this_idx] = A[idx]
        if len(stack) == 0:
            stack.append([dictionary[A[idx]], idx])
    else:
        stack.append([this_val,this_idx])
        stack.append([dictionary[A[idx]],idx])
        idx += 1
for i in result:
    print(i,'',end='')