import sys
import collections
n = int(sys.stdin.readline())

dp = []

for i in range(10):
    dp.append([])
    for j in range(n):
        dp[i].append(collections.defaultdict(int))
    
for i in range(1,10):
    dp[i][0][1<<i]+=1


for col in range(n-1):
    for row in range(10):
        if row-1 >=0 :
            for i in dp[row][col].keys():
                dp[row-1][col+1][i | (1<<(row-1))] += dp[row][col][i]
        if row+1 <=9:
            for i in dp[row][col].keys():
                dp[row+1][col+1][i | (1<<(row+1))] += dp[row][col][i]
    

result = 0
for i in range(10):
    result += dp[i][n-1][1023]
    result %= 1000000000
print(result)
 