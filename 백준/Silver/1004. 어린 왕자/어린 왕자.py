import sys
input = sys.stdin.readline

result = []
T = int(input())

for _ in range(T):
    x1, y1, x2, y2 = map(int,input().split())

    n = int(input())

    space = [list(map(int,input().split())) for _ in range(n)]
    count = 0
    for x,y,r in space:
        d1 = (x1-x)**2 + (y1-y)**2 
        d2 = (x2-x)**2 + (y2-y)**2

        if (d1 < r**2 and d2 > r**2) or (d1 > r**2 and d2 < r**2):
            count+=1
    result.append(count)
for i in result:
    print(i)
