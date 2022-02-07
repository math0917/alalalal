import sys

def sol(step, x1,y1, x2,y2):

    police_1 = abs(car[step][0] - x1) + abs(car[step][1] - y1) 
    police_2 = abs(car[step][0] - x2) + abs(car[step][1] - y2)

    dp[step][step[0]]
n = int(sys.stdin.readline())
w = int(sys.stdin.readline())

car = [list(map(lambda x : int(x)-1,sys.stdin.readline().split())) for _ in range(w)]
dp = [[[-1]*2 for _ in range(2)] for _ in range(w)]
sol(0,0,0,n-1,n-1)