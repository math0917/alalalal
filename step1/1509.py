import sys
import collections
def ispal(start, end):
    if alpha[start] != alpha[end]:
        return False
    else:
        if start+1 == end:
            return True
        return pal[start+1][end-1]
    


alpha = list(sys.stdin.readline().strip())

pal = [[False]*len(alpha) for _ in range(len(alpha))]

for col in range(len(alpha)):
    pal[col][col] = True
    for row in range(col):
        pal[row][col] = ispal(row,col)

min_val = float('inf')

result = [float('inf')]*(len(alpha)+1)
result[0] = 0

for i in range(1,len(alpha)+1):
    result[i] = min(result[i],result[i-1]+1)
    for j in range(i-1,len(alpha)+1):
        if pal[i-1][j-1]:
            result[j] = min(result[j], result[i-1]+1)
    
print(result[-1])