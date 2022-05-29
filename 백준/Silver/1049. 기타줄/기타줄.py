import sys

n, m = map(int,sys.stdin.readline().split())

cost = [list(map(int,sys.stdin.readline().split())) for _ in range(m)]

cost.sort(key = lambda x : x[0])

package = n//6

money = cost[0][0]*package
compare = cost[0][0]
cost.sort(key = lambda x :x[1])

sole = n%6

if cost[0][1]*sole <= compare:
    money += cost[0][1]*sole
else:
    money += compare
if money > cost[0][1]*n:
    money = cost[0][1]*n
print(money)