import sys
sys.setrecursionlimit(100000)
def find(x):
    if parent[x] == x:
        return x
    else:
        parent[x] = find(parent[x])
        return parent[x]
def union(mini, maxi):
    minimum = find(mini)
    maximum = find(maxi)
    if minimum == maximum:
        return False
    parent[maximum] = minimum
    return True

n, m = map(int,sys.stdin.readline().split())

arr = [list(map(int,sys.stdin.readline().split())) for _ in range(m)]

arr.sort(key = lambda x : -x[2])

parent = [i for i in range(n+1)]

path = 0

for _ in range(n-2):
    while True:
        this_turn = arr.pop()
        x, y = min(this_turn[0],this_turn[1]), max(this_turn[0],this_turn[1])
        if union(x,y):
            path += this_turn[2]
            break
print(path)