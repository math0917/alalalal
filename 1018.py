N,M = map(int, input().split())

arr=[]

for i in range(N):
    sent = input()
    arr.append(sent)

Min_count = 10000

for row in range(N-7):
    for col in range(M-7):
        count = 0
        for i in range(row,row+8,1):
            for j in range(col,col+8,1):
                if (i+j) % 2 == 0:
                    if arr[i][j] == 'B':
                        continue
                    count = count + 1
                else:
                    if arr[i][j] == 'W':
                        continue
                    count = count +1
        
        count = min(count, 64 - count)
        
        if Min_count > count :
            Min_count = count


print(Min_count)