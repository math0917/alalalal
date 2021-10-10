import sys

def building_time(idx):
    if build[idx]:
        return build[idx]
    this_turn = arr[idx][1:]
    
    max_time = 0
    for i in this_turn:
        max_time = max(building_time(i-1),max_time)
    build[idx] = max_time + arr[idx][0]
    return build[idx]

n = int(sys.stdin.readline())

arr = [list(map(int,sys.stdin.readline().split()))[:-1] for _ in range(n)]

build = [0]*n

for i in range(len(arr)):
    building_time(i)

for i in build:
    print(i)