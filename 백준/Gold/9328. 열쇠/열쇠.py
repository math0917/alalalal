import sys
import collections
input = sys.stdin.readline

n = int(input())
for _ in range(n):
    row, col = map(int,input().split())

    maze = [list(input().strip())for _ in range(row)]
    dx = [-1,0,1,0]
    dy = [0,1,0,-1]
    lst = input().strip()
    keys = []
    if lst[0] != '0':
        for i in lst:
            keys.append(i)
    stack = collections.deque([])
    visited = [[False]*col for _ in range(row)]
    for i in range(col):
        if maze[0][i] != '*':

            stack.append((0,i))
        if maze[-1][i] != '*':
 
            stack.append((row-1,i))
    for i in range(1,row-1):
        if maze[i][0] != '*':

            stack.append((i,0))
        if maze[i][-1] != '*':
          
            stack.append((i,col-1))
    flag = 0
    count = 0
    stack.append(('#','#'))
    #열쇠획득이나 문을 열게되면 flag = 1
    while stack:
        this_turn_row, this_turn_col = stack.popleft()
        
        if this_turn_row == '#':
            if flag:
                stack.append(('#','#'))
                flag = 0
            else:
                print(count)
                break
        elif maze[this_turn_row][this_turn_col] =='$':
            visited[this_turn_row][this_turn_col] = True
            maze[this_turn_row][this_turn_col] = '.'
            count += 1
            for i in range(4):
                this_row = this_turn_row + dx[i]
                this_col = this_turn_col + dy[i]
                if 0<=this_row<row and 0<=this_col < col and maze[this_row][this_col] != '*' and not visited[this_row][this_col]:
                    stack.appendleft((this_row,this_col))
        elif maze[this_turn_row][this_turn_col].isalpha():
            if maze[this_turn_row][this_turn_col].islower():
                flag = 1
                visited[this_turn_row][this_turn_col] = True
                keys.append(maze[this_turn_row][this_turn_col])
                maze[this_turn_row][this_turn_col] = '.'
                
                for i in range(4):
                    this_row = this_turn_row + dx[i]
                    this_col = this_turn_col + dy[i]
                    if 0<=this_row<row and 0<=this_col < col and maze[this_row][this_col] != '*' and not visited[this_row][this_col]:
                        stack.appendleft((this_row,this_col))
            else:
                
                if maze[this_turn_row][this_turn_col].lower() in keys:
                    visited[this_turn_row][this_turn_col] = True
                    maze[this_turn_row][this_turn_col] = '.'
                    for i in range(4):
                        this_row = this_turn_row + dx[i]
                        this_col = this_turn_col + dy[i]
                        if 0<=this_row<row and 0<=this_col < col and maze[this_row][this_col] != '*' and not visited[this_row][this_col]:
                            stack.appendleft((this_row,this_col))
                else:
                    stack.append((this_turn_row,this_turn_col))
        else:
            visited[this_turn_row][this_turn_col] = True
            for i in range(4):
                this_row = this_turn_row + dx[i]
                this_col = this_turn_col + dy[i]
                if 0<=this_row<row and 0<=this_col < col and maze[this_row][this_col] != '*' and not visited[this_row][this_col]:
                    stack.appendleft((this_row,this_col))
    
        