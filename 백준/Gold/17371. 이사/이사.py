import sys
input = sys.stdin.readline
n = int(input())

point = [list(map(int,input().split())) for _ in range(n)]

min_length = float('inf')

result_x, result_y = point[0][0], point[0][1]
for i in range(n):
    max_length = 0
    x, y =point[i]
    for j in range(n):
        if i!= j:
           
            Hx, Hy = point[j]
            max_length = max(max_length, ((Hx - x) ** 2 + (Hy - y) ** 2)**(0.5))
    if min_length > max_length:
        min_length = max_length
        result_x, result_y = x, y
print(result_x, result_y)