import sys
arr = []
while(1):
    ptr = list(map(int,sys.stdin.readline().split()))
    if ptr == [-1,-1,-1]:
        break
    arr.append(ptr)
table = []
for i in range(21):
    table.append([])
    for j in range(21):
        table[i].append([])
        for k in range(21):
            table[i][j].append(1)

for i in range(1,21):
    for j in range(1,21):
        for k in range(1,21):
            if i<j and j<k:
                table[i][j][k] = table[i][j][k-1] + table[i][j-1][k-1] - table[i][j-1][k] 
            else:
                table[i][j][k]=table[i-1][j][k]+table[i-1][j-1][k]+table[i-1][j][k-1]-table[i-1][j-1][k-1]
for i in arr:
    x = i[0]
    y = i[1]
    z = i[2]
    print('w(%d, %d, %d) = '%(x,y,z),end='')
    if x<=0 or y<=0 or z<=0:
        print('1')
    elif x>20 or y>20 or z>20:
        print(table[20][20][20])
    else:
        print(table[x][y][z])