import sys
input = sys.stdin.readline
import collections

def check():
  global ans
  for row,col in dot:
    d = 0
    for _ in range(3):
      if (row + dx[d], col + dy[d]) in dot:
        row += dx[d]
        col += dy[d]
        d+=1
        continue
      else:
        break
    else:
      ans += 1


def dfs(row, col, direction,count,length):
  if count > length:
    return
  
  if count == 0:
    row += dx[direction[0]]
    col += dy[direction[0]]
    dot.add((row,col))
  elif count == 1:
    row += dx[direction[1]]
    col += dy[direction[1]]
    dot.add((row,col))
  else:
    
    for i in range(2**(count-1), 2**count):
      row += dx[direction[i]]
      col += dy[direction[i]]
      dot.add((row,col))
  for i in reversed(direction):
    direction.append((i+1)%4)


  dfs(row,col,direction,count+1,length)
    
 

n = int(input())

dx = [0,-1,0,1]
dy = [1,0,-1,0]

dot = set()

for _ in range(n):
  x, y, d, g = map(int,input().split())
  dot.add((y,x))
  dfs(y,x,[d],0,g)

ans = 0
check()
print(ans)