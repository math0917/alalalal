import sys

n = int(sys.stdin.readline())

rgb = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

#n번방에 m색으로 1번이r인경우
dp = [[[float('inf'), float('inf'), float('inf')] for _ in range(3)] for _ in range(n)]

dp[0][0][0] = rgb[0][0]
dp[0][1][1] = rgb[0][1]
dp[0][2][2] = rgb[0][2]

for i in range(1,n-1):
    for j in range(3):
        dp[i][0][j] = min(dp[i-1][1][j], dp[i-1][2][j]) + rgb[i][0]
        dp[i][1][j] = min(dp[i-1][2][j], dp[i-1][0][j]) + rgb[i][1]
        dp[i][2][j] = min(dp[i-1][0][j], dp[i-1][1][j]) + rgb[i][2]
result = float('inf')
result = min(min(dp[n-2][1][1],dp[n-2][2][1],dp[n-2][1][2],dp[n-2][2][2]) + rgb[n-1][0], result)
result = min(min(dp[n-2][0][0], dp[n-2][2][0], dp[n-2][0][2], dp[n-2][2][2]) + rgb[n-1][1], result)
result = min(min(dp[n-2][0][0], dp[n-2][1][0], dp[n-2][0][1], dp[n-2][1][1])  + rgb[n-1][2], result)

print(result)