import sys
import collections

n, m = map(int,sys.stdin.readline().split())
ladder = collections.defaultdict(int)
for _ in range(n):
    from_lad, to_lad = map(int,sys.stdin.readline().split())
    ladder[from_lad] = to_lad
snake = collections.defaultdict(int)
for _ in range(m):
    from_sna, to_sna = map(int,sys.stdin.readline().split())
    snake[from_sna] = to_sna


visited = [False]*101
stack = collections.deque([[0,1]])
visited[1] = True
while stack:
    this_turn_count, this_turn_loc = stack.popleft()
    if this_turn_loc + 6 >= 100:
        print(this_turn_count + 1)
        break
    for i in reversed(range(1, 7)):
        if this_turn_loc + i in ladder:
            if visited[ladder[this_turn_loc + i]]:
                continue
            visited[ladder[this_turn_loc + i]] = True 
            stack.append([this_turn_count + 1, ladder[this_turn_loc + i]])
        elif this_turn_loc + i in snake:
            if visited[snake[this_turn_loc + i]]:
                continue
            visited[snake[this_turn_loc + i]] = True            
            stack.append([this_turn_count + 1, snake[this_turn_loc + i]])
        else:
            if visited[this_turn_loc + i]:
                continue
            visited[this_turn_loc + i] = True        
            stack.append([this_turn_count + 1, this_turn_loc + i])
            

