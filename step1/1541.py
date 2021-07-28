import sys

arr = sys.stdin.readline()
arr = arr[:-1]
arr= arr.split('-')
result = []
for i in arr:
    ptr = i.split('+')
    num = 0
    for j in ptr:
        num+=int(j)
    result.append(num)
num = result[0]

for i in range(1, len(result)):
    num-=result[i]
print(num)