import sys
n, m, r = map(int,sys.stdin.readline().split())

matrix = [list(map(int,sys.stdin.readline().split())) for _ in range (n)]

procedure = list(map(int,sys.stdin.readline().split()))

row1 = [1,2,3,4,5]

row2 = [2,3,4,5,6]

row3 = [4,55,6,5,2]

row4= [2,3,4,5,6]
row = [row1, row2, row3, row4]
for i in range(len(row)//2):
    row[i], row[len(row)-i-1] = row[len(row)-i-1], row[i]

print(row)