import sys

n = int(sys.stdin.readline())

ptr = [int(sys.stdin.readline()) for _ in range(n)]

arr= [1,1,1]

n = max(ptr)
for i in range(3,n+1):
    arr.append(arr[i-2]+arr[i-3]) 

for i in ptr:
    print(arr[i-1])