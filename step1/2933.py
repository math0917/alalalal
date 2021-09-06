import sys
import collections
r, c = map(int,sys.stdin.readline().split())

mineral = []

dx = [-1,0,1,0]
dy = [0,1,0,-1]

stack = collections.deque([])

for i in range(r):
    mineral.append(sys.stdin.readline().strip())

arr = []
for i in range(r):
    arr.append([])
    for j in mineral[i]:
        arr[i].append(j)

how_many = int(sys.stdin.readline())

to_do = list(map(int,sys.stdin.readline().split()))
'''
홀수의 차례의 경우 왼쪽에서 공격을 하도록 하고 짝수의 경우 오른쪽에서 공격을 하도록 한다. 

'''
for i in range(len(to_do)):
    this_count = 0
    this_turn_row = r-to_do[i]
    if i%2: #오른쪽 부터 x 찾아가는 상황
        for j in reversed(range(c)):
            if arr[this_turn_row][j] == 'x':
                arr[this_turn_row][j] = '.'
                this_count+=1
                break
    else:
        #왼쪽부터 x 찾아가는 상황
        for j in range(c):
            if arr[this_turn_row][j] == 'x':
                arr[this_turn_row][j] = '.'
                this_count +=1
                break
    if this_count:
        visited = [[1]*c for _ in range(r)]
        for j in range(this_turn_row):
            for k in range(c):
                if arr[j][k] == 'x' and visited[j][k]:
                    visited[j][k] = 0
                    stack.append((j,k))
                    