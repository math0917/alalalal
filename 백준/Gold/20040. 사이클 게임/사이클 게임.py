import sys
input = sys.stdin.readline

def find(n):
    if parent[n] == n:
        return n
    else:
        parent[n] = find(parent[n])
        return parent[n]
def union(f,t):
    parent[find(t)] = find(f)

n, m = map(int,input().split())

is_cycle = [list(map(int,input().split())) for _ in range(m)]

parent = [i for i in range(n)]

for idx, (x, y) in enumerate(is_cycle):
    if find(x)==find(y):
        print(idx+1)
        break
    else:
        union(min(x,y),max(x,y))
else:
    print(0)