import sys
import math
import itertools

def cal (num):
    this_turn = [0,0]
    for i in range(n):
        if num &(1<<i):
            this_turn[0] += arr[i][0]
            this_turn[1] += arr[i][1]
        else:
            this_turn[0] -= arr[i][0]
            this_turn[1] -= arr[i][1]
    return this_turn[0]**2 + this_turn[1]**2
t = int(sys.stdin.readline())

for _ in range(t):
    n = int(sys.stdin.readline())
    arr = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]
    
    min_value = float('inf') 
    
 
    perm = itertools.combinations(range(n), n//2)
    for i in perm:
        num = 0
        for j in i:
            num ^= (1<<j)
        min_value = min(min_value,cal(num))
    print(math.sqrt(min_value))