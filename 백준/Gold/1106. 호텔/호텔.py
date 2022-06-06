import sys
input = sys.stdin.readline

C, N = map(int,input().split())

cities = [list(map(int,input().split())) for _ in range(N)]

dp = [[0]*N]
cost = 1

while True:
    dp.append([])
    for col in range(N):
        
        if cost - cities[col][0] >= 0:
            if col - 1 >=0:
                this_turn = max(dp[cost][col-1], dp[cost-cities[col][0]][col] + cities[col][1])
            else:
                this_turn = dp[cost-cities[col][0]][col] + cities[col][1]
        else:
            if col - 1 >=0:
                this_turn = dp[cost][col-1]
            else:
                this_turn = 0
        if this_turn >= C:
            print(cost)
            sys.exit()
        dp[cost].append(this_turn)
    cost+=1