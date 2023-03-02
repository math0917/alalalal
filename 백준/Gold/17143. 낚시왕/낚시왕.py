import sys
#r = row 개수
#c = col 개수
#m = shark 개수
r, c, m = map(int,sys.stdin.readline().split())
"""
(0,1) : [row-1,col-1]
2 : 속력
3 : 이동방향 (1:위 2:아래 3:오른쪽 4:왼쪽)
4 : 크기
"""
shark = [list(map(int,sys.stdin.readline().split())) for _ in range(m)]

direction = [[-1,0],[1,0],[0,1],[0,-1]]

graph = [[0]*c for _ in range(r)]
alive = set(range(m))

for i in range(m):
    shark_x = shark[i][0] - 1
    shark_y = shark[i][1] - 1
    graph[shark_x][shark_y] = i + 1


result = 0
for i in range(c):
   
    for j in range(r):
    
        if graph[j][i] != 0:
            alive.remove(graph[j][i]-1)
          
            result += shark[graph[j][i]-1][4]
            graph[j][i] = 0
            break
    del_list = set()
    for k in alive:
        graph[shark[k][0]-1][shark[k][1]-1] = 0
    for k in alive:
        (loc_x, loc_y, velocity, direct, size) = shark[k]
        loc_x -= 1
        loc_y -= 1
        if direct == 1 or direct == 2:
            velocity = velocity % ((r-1)*2)
        else:
            velocity = velocity % ((c-1)*2)
        for _ in range(velocity):
            row = loc_x + direction[shark[k][3]-1][0]
            col = loc_y + direction[shark[k][3]-1][1]

            if 0 <= row < r and 0 <= col < c:
                loc_x = row
                loc_y = col
            else:
                if shark[k][3] == 1:
                    shark[k][3] = 2
                elif shark[k][3] == 2:
                    shark[k][3] = 1
                elif shark[k][3] == 3:
                    shark[k][3] = 4
                else:
                    shark[k][3] = 3
                
                loc_x = loc_x + direction[shark[k][3]-1][0]
                loc_y = loc_y + direction[shark[k][3]-1][1]
        
        shark[k][0] = loc_x + 1
        shark[k][1] = loc_y + 1
    
        if graph[loc_x][loc_y]:
            already_have = graph[loc_x][loc_y] - 1
            if shark[already_have][4] < size:
                graph[loc_x][loc_y] = k +1
                del_list.add(already_have)
            else:
                del_list.add(k)
                
        else:
            graph[loc_x][loc_y] = k+1
    alive = alive - del_list
print(result)

            
    