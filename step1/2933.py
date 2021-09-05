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
    if i%2: #오른쪽 부터 x 찾아가는 상황
        for j in reversed(range(c)):
            if arr[r - to_do[i]][j] == 'x':
                arr[r - to_do[i]][j] = '.'
                this_count+=1
                break
    else:
        #왼쪽부터 x 찾아가는 상황
        for j in range(c):
            if arr[r - to_do[i]][j] == 'x':
                arr[r - to_do[i]][j] = '.'
                this_count +=1
                break
    if this_count:
        cluster_visited = [[1]*c for _ in range(r)]
        for y in range(c):
            if arr[r-1][y] == 'x' and cluster_visited[r-1][y]:
                cluster_visited[r-1][y] = 0
                stack.append((r-1,y))
                while stack:
                    this_row,this_col = stack.pop()
                    for t in range(4):
                        row = this_row + dx[t]
                        col = this_col + dy[t]
                        if 0<= row <r and 0<= col < c and cluster_visited[row][col] and arr[row][col] == 'x':
                            cluster_visited[row][col] = 0
                            stack.append((row,col))
        for x in range(r):
            for y in range(c):
                if arr[x][y] == 'x' and cluster_visited[x][y]:
                    
        
        
    
for i in range(r):
    for j in range(c):
        print(arr[i][j],end='')
  
    print('')