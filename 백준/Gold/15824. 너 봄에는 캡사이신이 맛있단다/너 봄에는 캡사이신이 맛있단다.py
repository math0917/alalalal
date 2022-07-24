import sys
input = sys.stdin.readline
MOD = 1000000007

def pow(a, b):
    if b == 0:
        return 1
    elif b == 1:
        return a
    
    result = pow(a,b//2)
    if b%2 == 1:
        return (result * result * a)%MOD
    else:
        return (result * result)%MOD
n = int(input())
arr = sorted(list(map(int,input().split())))
sum = 0
for idx, value in enumerate(arr):
    maxCount = idx - 0
    minCount = n - 1 - idx
    
    sum += value*pow(2,maxCount) 
    sum -= value*pow(2,minCount)
    
sum %= MOD
print(sum)
    