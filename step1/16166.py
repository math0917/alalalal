import sys
import collections

n = int(sys.stdin.readline())

station = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

station_visited = set([int(sys.stdin.readline())])

info = collections.defaultdict(set)

for i in range(n):
    for j in range(1, station[i][0]+1):
        info[i].add(station[i][j])

line_visited = 0

for count in range(n):
    
    this_turn_visited = set()
    for i in range(n):
        
        #아직 안간 라인이면
        if not line_visited & 1<<i:
            if station_visited & info[i]:
                this_turn_visited |= info[i]
                line_visited |= 1<<i
    station_visited |= this_turn_visited
    if 0 in station_visited:
        print(count)
        exit()
print(-1)
