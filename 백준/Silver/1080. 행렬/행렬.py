import sys
input = sys.stdin.readline

n, m = map(int,input().split())

before = [list(map(int,input().strip())) for _ in range(n)]

after = [list(map(int,input().strip())) for _ in range(n)]

have_to_change = []

for i in range(n):
    have_to_change.append([])
    for j in range(m):
        if before[i][j] != after[i][j] : 
            have_to_change[i].append(True)
        else:
            have_to_change[i].append(False)
count = 0

for i in range(n-2):
    for j in range(m-2):
        if have_to_change[i][j]:
            count += 1
            for row in range(3):
                for col in range(3):
                    have_to_change[i+row][j+col] = False if have_to_change[i+row][j+col] else True

for i in range(n):
    for j in range(m):
        if have_to_change[i][j]:
            print(-1)
            sys.exit()
print(count)