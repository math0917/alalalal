import sys

n = int(sys.stdin.readline())

arr = [int(sys.stdin.readline()) for _ in range(n)]

ptr = []
for i in range(n):
    ptr.append([])
    for j in range(3):
        ptr[i].append(0)
if len(arr) == 1:
    print(arr[0])
    exit()
ptr[0][1] = arr[0]
ptr[1][0] = arr[0]
ptr[1][1] = arr[1]
ptr[1][2] = arr[0]+arr[1]

for i in range(2,n):
    ptr[i][0] = max(ptr[i-1])
    ptr[i][1] = max(max(ptr[i-1][0],ptr[i-2][1])+arr[i],max(ptr[i-2][1],ptr[i-2][2])+arr[i])
    ptr[i][2] = ptr[i-1][1]+arr[i]

ptr2 = [max(i) for i in ptr]
print(max(ptr2))