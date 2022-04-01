import sys

str1 = sys.stdin.readline().strip()
str2 = sys.stdin.readline().strip()

dp = [[0] * len(str1) for _ in range(len(str2))]
if str1[0] == str2[0]:
    dp[0][0] = 1
for col in range(1,len(str1)):
    dp[0][col] = 1 if dp[0][col-1] or str2[0] == str1[col] else 0

for row in range(1, len(str2)):
    dp[row][0] = 1 if dp[row-1][0] or str1[row] == str2[0] else 0

for row in range(1,len(str2)):
    for col in range(1,len(str1)):
        cmp = 1 if str2[row] == str1[col] else 0
        dp[row][col] = max(dp[row][col-1], dp[row-1][col] + cmp)


result = max(dp[-1])
print(result)


