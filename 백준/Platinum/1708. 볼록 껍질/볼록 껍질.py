import sys
input = sys.stdin.readline
from functools import cmp_to_key
import collections
def compare(point1, point2):
    value = min_y_x * point1[1] + point1[0] * point2[1] + point2[0] * min_y - (min_y * point1[0] + point1[1]*point2[0] + point2[1]*min_y_x)
    
    if value > 0:
        return -1
    elif value < 0:
        return 1
    else:
        if (point1[0] - min_y_x) ** 2 + (point1[1] - min_y)** 2 < (point2[0] - min_y_x) ** 2 + (point2[1] - min_y) ** 2:
            return -1
        else :
            return 1
def ccw(point1,point2,point3):
    value = point1[0] * point2[1] + point2[0] * point3[1] + point3[0] *point1[1] - (point1[1] * point2[0] + point2[1] * point3[0] + point3[1] * point1[0] )
    if value > 0 :
        return 1
    elif value == 0 :
        return 0
    else:
        return -1
n = int(input())

point = [list(map(int,input().split())) for _ in range(n)]

min_y = float('inf')
min_y_x = 0
for x, y in point:
    if y < min_y:
        min_y = y
        min_y_x = x
    elif y == min_y:
        min_y_x = min(min_y_x,x)
point = sorted(point, key = cmp_to_key(compare))

stack = collections.deque([point[0]])
stack.append(point[1])
idx = 2

while idx < n:
    if len(stack) < 2:
        stack.append(point[idx])
        idx += 1
        continue
    result = ccw(stack[-2], stack[-1], point[idx])
    if result == 0:
        stack.pop()
        stack.append(point[idx])
        idx += 1
    elif result == 1:
        stack.append(point[idx])
        idx += 1
    else:
        stack.pop()
        
    
    
print(len(stack))