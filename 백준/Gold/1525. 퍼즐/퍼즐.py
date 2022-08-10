import sys
input = sys.stdin.readline
import collections

dx = [-1,1,3,-3]

puzzle = ""
for _ in range(3):
  this_string = input().strip().split()
  for j in range(3):
    puzzle += this_string[j]

puzzle_set = set()

aim_puzzle = "123456780"

queue = collections.deque([(puzzle,0)])
puzzle_set.add(puzzle)
while queue:
  this_string, this_count = queue.popleft()
  
  if this_string == aim_puzzle:
    print(this_count)
    sys.exit()
  for j in range(9):
    if this_string[j] == '0':
      break

  for i in dx:
    if 0 <= j + i < 9:
      if (j // 3 == (j+i)//3 and i % 3 != 0) or  i%3 == 0:
        swap_left, swap_right = min(j, i+j) ,max(j, i+j)
        new_string = this_string[:swap_left] + this_string[swap_right] + this_string[swap_left+1:swap_right] + this_string[swap_left] + this_string[swap_right+1:]
        if new_string not in puzzle_set:
          puzzle_set.add(new_string)
          queue.append((new_string, this_count + 1))
        
print(-1)