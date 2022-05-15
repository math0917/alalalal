import sys
input = sys.stdin.readline

n = int(input())

matrix = [list(map(int,input().split())) for _ in range(n)]

dp = [[float('inf')]*n for _ in range(n)]

for idx, (x,y) in enumerate(matrix):
    dp[idx][idx] = [x,y,0]

for i in reversed(range(n-1)):
    diff = n-i-1
    for j in range(i+1):
        if diff == 1:
            dp[j][j+diff] = [dp[j][j][0],dp[j+diff][j+diff][1],dp[j][j][0]*dp[j][j][1]*dp[j+diff][j+diff][1]]
        else:
            for k in range(diff):
                first_section = dp[j][j+k]
                second_section = dp[j+k+1][j+diff]
                try:
                    compare = dp[j][j+diff][2]
                except:
                    compare = dp[j][j+diff]
                if first_section[2] + second_section[2] + first_section[0]*first_section[1]*second_section[1] < compare:
                    dp[j][j+diff] = [first_section[0],second_section[1],first_section[2] + second_section[2] + first_section[0]*first_section[1]*second_section[1]]

print(dp[0][-1][2])