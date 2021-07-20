import sys

def left_part(arr):
    n = len(arr)
    result = []
    for i in range(n):
        result.append(0)
    result[0] = 1
    
    for i in range(1,n):
        ptr = [j for j in range(i) if arr[j]<arr[i]]  #자기보다 작은놈들 다꺼내

        if len(ptr) == 0:
            result[i] = 1 
        else:
            max_val = 0
            for j in ptr:
                if result[j]>max_val:
                    max_val = result[j]
            
            result[i] = max_val+ 1
    return result[n-1]
n = int(sys.stdin.readline())

arr = list(map(int,sys.stdin.readline().split()))

result = []

for i in range(n):
    result.append(left_part(arr[:i+1])+left_part(list(reversed(arr[i:n+1]))))

print(max(result)-1)