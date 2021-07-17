import sys

n = int(sys.stdin.readline())

arr = [1,2]
sum = 0
for i in range(1,(n+1)//2):
    arr[0] = (arr[0]+arr[1])%15746
    arr[1] = (arr[0]+arr[1])%15746

if n%2 == 0:
    print(arr[1])
else:
    print(arr[0])
    
