import sys

n = int(sys.stdin.readline())

arr= [list(map(int,sys.stdin.readline().split())) for _ in range(n)]
ptr = [j[0] for j in arr]
N = max(ptr)
result = []
for i in range(N):
    result.append(0)
while(True):
    for i in range(n):
        count = 0
        for j in range(n):
            if arr[i][1] > arr[j][1] and arr[i][0] < arr[j][0]:
                
                count+=1
            if arr[i][0] > arr[j][0] and arr[j][1]> arr[i][1]:
                count+=1

        result[arr[i][0]-1] = count
    if len(set(result)) == 1:
        break
    else:
        max_idx = 
