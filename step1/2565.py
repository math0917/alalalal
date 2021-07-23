import sys


n = int(sys.stdin.readline())

arr= [list(map(int,sys.stdin.readline().split())) for _ in range(n)]
ptr = [j[0] for j in arr]

max_val = max(ptr)

result = []

for i in range(max_val+1):
    result.append(501)


for i in range(n):
    index = arr[i][0]
    val = arr[i][1]

    result[index] = val

final = [[0,1]]

for i in range(1,max_val+1):
    ptr=result[:i]
    small_count = [j for j in range(len(ptr)) if ptr[j] < result[i]]
 
    if len(small_count) == 0:
        final.append([0,1])
    else:
        max_val = [final[j][1] for j in small_count]
        max_val1 = max(max_val)
        final.append([max_val1,max_val1+1])


max_v = [max(final[j]) for j in range(len(final)) if result[j] != 501]
print(n-max(max_v))