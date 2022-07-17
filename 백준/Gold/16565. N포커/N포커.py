import sys

input = sys.stdin.readline

n = int(input())

nck = [[0]*53 for _ in range(53)]
for i in range(53):
    nck[i][0] = 1
kindCount = n // 4
restCount = n % 4

for N in range(1,53):
    for k in range(1,n+1):
        nck[N][k] = (nck[N-1][k] + nck[N-1][k-1])%10007

fourCard = 0
for i in range(kindCount):
    fourCard += (((-1)**i)*nck[13][i+1]*nck[52-(i+1)*4][n-(i+1)*4]) 
print((fourCard)%10007)