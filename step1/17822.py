import sys



n, m, t = map(int,sys.stdin.readline().split())

arr = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

procedure = [list(map(int,sys.stdin.readline().split())) for _ in range(t)]

clock = [1,-1]

dx = [1,0]
dy = [0,1]



for this_turn in procedure:
  
    round = this_turn[0]
    wise = this_turn[1]
    this_set = set()
    flag = 1
    for i in range(1,(n//round)+1):
        real_row = round * i - 1 
        have_to_go = this_turn[2] % m
        this_row = [0]*m
       
        for j in range(m):
            this_row[(j + have_to_go * clock[this_turn[1]]) % m] = arr[real_row][j]
      
        for j in range(m):
            arr[real_row][j] = this_row[j]
 
    for this_row in range(n):
        for this_col in range(m):
            for k in range(2):
                row = this_row + dx[k]
                col = this_col + dy[k]
                if row < n and col < m:
                    if arr[row][col] == arr[this_row][this_col] and arr[row][col]!=0:
                        flag = 0
                        this_set.add((row,col))
                        this_set.add((this_row,this_col))
                elif row < n and col >= m:
                    if arr[row][0] == arr[this_row][this_col] and arr[row][0]!=0:
                        flag = 0
                        this_set.add((this_row,this_col))
                        this_set.add((row,0))
   
    if flag:
        summation = 0
        cnt = 0
        for i in range(n):
            for j in range(m):
                if arr[i][j]:
                    cnt += 1
                    summation+=arr[i][j]
        if cnt == 0:
            print(0)
            sys.exit()
        avg = summation / cnt
     
        for i in range(n):
            for j in range(m):
                if arr[i][j]:
                    if arr[i][j]>avg:
                        arr[i][j]-=1
                    elif arr[i][j]<avg:
                        arr[i][j]+=1
    else:
        for i in this_set:
            arr[i[0]][i[1]] = 0
    

count = 0

for i in range(n):
    for j in range(m):
        count+=arr[i][j]
print(count)