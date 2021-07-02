import sys

N = int(sys.stdin.readline())
arr = []
for i in range(N):
    arr.append(int(sys.stdin.readline()))
    
sum1 = 0
for i in arr:
    sum1+=i

print(round(sum1/len(arr)))
arr.sort()
print(arr[len(arr)//2])

count = dict()

for i in arr:
    if i in count.keys():
        count[i]+=1
    else:
        count[i] = 1


max_count = 0
max_idx = 0
max_idx_second = 0

for i in count.keys():
    if max_count<count[i]:
        max_count = count[i]
        max_idx = i
        max_idx_second = i
    elif max_count == count[i]:
        if max_idx == max_idx_second:
            max_idx_second = i
        
        elif max_idx_second>i:
            if max_idx>i:
                max_idx_second = max_idx
                max_idx = i
            else:
                max_idx_second = i

            
print(max_idx_second)

print(max(arr)-min(arr))