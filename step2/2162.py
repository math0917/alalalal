import sys
import collections
def ccw(point1,point2,point3):
    if (point2[0]-point1[0])*(point3[1]-point1[1]) - (point2[1]-point1[1])*(point3[0]-point1[0]) > 0:
        return 1
    elif (point2[0]-point1[0])*(point3[1]-point1[1]) - (point2[1]-point1[1])*(point3[0]-point1[0]) < 0:
        return -1
    else:
        return 0
def find(idx):
    if parent[idx] == idx:
        return idx
    else:
        parent[idx] = find(parent[idx])
        return parent[idx]
        
def union(child, par):
    parent[find(child)] = find(par)
def compare(a,b,c,d,a1,b1,c1,d1):
    # a,b = 1 a1, b1 = 2 c, d = 3 c1, d1 = 4
    # 1-3 2-4의 만나는지에 대한 판단
    # by 123과 143이 반시계 방향에 있는지 판단 +
    # by 214과 234가 반시계 방향에 있는지 판단
    point1 = (a,b)
    point2 = (a1,b1)
    point3 = (c,d)
    point4 = (c1,d1)
    
    if ccw(point1,point2,point3) * ccw(point1,point4,point3) == 0 and ccw(point2,point1,point4) * ccw(point2,point3,point4) == 0:
        if min(point1[0], point3[0])<=max(point4[0],point2[0]) and max(point1[0], point3[0])>= min(point2[0],point4[0]) and min(point1[1], point3[1])<=max(point4[1],point2[1]) and max(point1[1], point3[1])>= min(point2[1],point4[1]):
            return True
    elif ccw(point1,point2,point3) * ccw(point1,point4,point3) <= 0 and ccw(point2,point1,point4) * ccw(point2,point3,point4) <= 0:
        return True
    
    return False

n = int(sys.stdin.readline())

point = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

parent = [i for i in range(n)]

for i in range(n):
    x1, y1, x2, y2 = point[i]
    for j in reversed(range(i)):
        if compare(x1,y1,x2,y2,point[j][0],point[j][1],point[j][2],point[j][3]):
            union(i,j)
    

for i in reversed(range(n)):
    parent[i] = find(parent[i])

max_count = 0
set_count = 0
parent_dict = collections.defaultdict(int)
for i in parent:
    parent_dict[i] += 1

for i in parent_dict.keys():
    set_count+=1
    max_count = max(max_count, parent_dict[i])
print(set_count)
print(max_count)