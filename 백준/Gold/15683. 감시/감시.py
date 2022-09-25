from re import L
import sys
input = sys.stdin.readline
import collections
import copy
n, m = map(int,input().split())

maze = [list(map(int,input().split())) for _ in range(n)]

dx = [0,-1,0,1]
dy = [1,0,-1,0]

stack = collections.deque([maze])
min_count = float('inf')
while stack:

  this_turn_maze = stack.popleft()
  
  flag = False
  for i in range(n):
    for j in range(m):
      if this_turn_maze[i][j] == 1:
        flag = True
        for k in range(4):
          new_maze = copy.deepcopy(this_turn_maze)
          new_maze[i][j] = 7
          idx = 1
          row = i
          col = j
          while 0 <= row + dx[k]*idx < n and 0 <= col + dy[k]*idx < m:
            # 벽
            if new_maze[row+dx[k]*idx][col+dy[k]*idx] == 6:
              break
            elif 1<= new_maze[row+dx[k]*idx][col+dy[k]*idx]<=5:
              idx +=1
              
            else:
              new_maze[row+dx[k]*idx][col+dy[k]*idx] = 7
              idx += 1
          stack.append(new_maze)  
        break
      elif this_turn_maze[i][j] == 2:
        flag = True
        for t in range(2):
          new_maze = copy.deepcopy(this_turn_maze)
          new_maze[i][j] = 7
          for k in range(2):
            idx = 1
            row = i
            col = j
            while 0 <= row + dx[k*2+t]*idx < n and 0 <= col + dy[k*2+t]*idx < m:
              # 벽
              if new_maze[row+dx[k*2+t]*idx][col+dy[k*2+t]*idx] == 6:
                break
              elif 1<= new_maze[row+dx[k*2+t]*idx][col+dy[k*2+t]*idx]<=5:
                idx +=1
              else:
                new_maze[row+dx[k*2+t]*idx][col+dy[k*2+t]*idx] = 7
                idx += 1
          stack.append(new_maze)  
  
        break
      elif this_turn_maze[i][j] == 3:
        flag = True
        for t in range(4):
          new_maze = copy.deepcopy(this_turn_maze)
          new_maze[i][j] = 7
          
          for k in range(2):
            idx = 1
            row = i
            col = j
            while 0 <= row + dx[(t+k)%4]*idx < n and 0 <= col + dy[(t+k)%4]*idx < m:
              # 벽
              if new_maze[row+dx[(t+k)%4]*idx][col+dy[(t+k)%4]*idx] == 6:
                break
              elif 1<= new_maze[row+dx[(t+k)%4]*idx][col+dy[(t+k)%4]*idx]<=5:
                idx +=1
              else:
                new_maze[row+dx[(t+k)%4]*idx][col+dy[(t+k)%4]*idx] = 7
                idx += 1
          stack.append(new_maze)  
          
        break
      elif this_turn_maze[i][j] == 4:
        flag = True
        for t in range(4):
          new_maze = copy.deepcopy(this_turn_maze)
          new_maze[i][j] = 7
          
          for k in range(3):
            idx = 1
            row = i
            col = j
            while 0 <= row + dx[(t+k)%4]*idx < n and 0 <= col + dy[(t+k)%4]*idx < m:
              # 벽
              if new_maze[row+dx[(t+k)%4]*idx][col+dy[(t+k)%4]*idx] == 6:
                break
              elif 1<= new_maze[row+dx[(t+k)%4]*idx][col+dy[(t+k)%4]*idx]<=5:
                idx +=1
              else:
                new_maze[row+dx[(t+k)%4]*idx][col+dy[(t+k)%4]*idx] = 7
                idx += 1
          stack.append(new_maze)  
          
        break
      elif this_turn_maze[i][j] == 5:
        flag = True
       
        new_maze = copy.deepcopy(this_turn_maze)
        new_maze[i][j] = 7
        t = 0
        for k in range(4):
          idx = 1
          row = i
          col = j
          while 0 <= row + dx[(t+k)%4]*idx < n and 0 <= col + dy[(t+k)%4]*idx < m:
            # 벽
            if new_maze[row+dx[(t+k)%4]*idx][col+dy[(t+k)%4]*idx] == 6:
              break
            elif 1<= new_maze[row+dx[(t+k)%4]*idx][col+dy[(t+k)%4]*idx]<=5:
              idx +=1
            else:
              new_maze[row+dx[(t+k)%4]*idx][col+dy[(t+k)%4]*idx] = 7
              idx += 1
          stack.append(new_maze)  
          
        break
    if flag:
      break
  # 이놈은 이제 시시티비가 없네

  if not flag:
    
    count = 0
    for i in range(n):
      for j in range(m):
        
        if this_turn_maze[i][j] == 0:
          count += 1
      
    min_count = min(count, min_count)
  
print(min_count)