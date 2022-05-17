import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**5)
def dfs(num):
    global have_team
    visited[num] = True
    find.append(num)
    if visited[stu[num]]:
        if stu[num] in find:
            have_team += find[find.index(stu[num]):]
    else:
        dfs(stu[num])

t = int(input())
for _ in range(t):
    n = int(input())
    stu =[1]+ list(map(int,input().split()))
    visited = [False]*(n+1)
    have_team = []
    for i in range(1,n+1):
        if not visited[i]:
            find = []
            dfs(i)
    print(n-len(have_team))