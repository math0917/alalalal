import sys
sys.setrecursionlimit(10**6)
def from_to(before,after):
    if before == 0 :
        return 2
    if before == after:
        return 1
    if abs(before-after) == 2:
        return 4
    return 3

def sol(count,left,right):
    if count >= length-1:
        return 0
    if dp[count][left][right] != -1:
        return dp[count][left][right]
    
    left_val = from_to(left, arr[count]) + sol(count+1, arr[count], right)
    right_val = from_to(right,arr[count]) + sol(count+1, left, arr[count])
    dp[count][left][right] = min(left_val,right_val)
    return dp[count][left][right]

arr = list(map(int,sys.stdin.readline().split()))

length = len(arr)

dp = [[[-1]*5 for _ in range(5)] for _ in range(length)]

# 0: 중점, 1: 위, 2: 왼, 3: 아래, 4: 오

print(sol(0,0,0))
