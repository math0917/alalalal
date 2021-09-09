import sys
import collections
r, c, k = map(int,sys.stdin.readline().split())

arr = [list(map(int,sys.stdin.readline().split())) for _ in range(3)]

count = 0

row_count = 3
col_count = 3

while count<100:
    if arr[r-1][c-1] == k:
        print(count)
        break
    else:
        if row_count >= col_count:
            result = []
            length = 0
            for i in range(row_count):
                row_dict = dict()
                this_row_length = 0
                for j in range(col_count):
                    try:
                        row_dict[arr[i][j]]+=1
                    except:
                        row_dict[arr[i][j]] = 1
                        this_row_length+=1
                length = max(this_row_length, length)
            

        
