import sys
import collections

def sort(start,finish,number):
    global max_length
    if start == finish:
        max_length = max(max_length, start+1)
        biggest_ever[start] = min(number, biggest_ever[start])
        return start
    mid = (start+finish)//2
    if biggest_ever[mid] == number:
        return mid
    elif biggest_ever[mid] < number:
        return sort(mid+1,finish,number)
    else:
        return sort(start,mid,number)


n = int(sys.stdin.readline())
arr = list(map(int,sys.stdin.readline().split()))

level = [0] * n

biggest_ever = [float('inf')]*n
max_length = 0
for i in range(n):
    level[i] = sort(0,max_length,arr[i])

stack = collections.deque([])
print(max_length)
idx = n-1

while max_length >0:
    if level[idx] == max_length-1:
        stack.append(arr[idx])
        max_length -= 1
    idx-=1
while stack:
    print(stack.pop(),'',end='')
