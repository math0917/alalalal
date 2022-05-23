import sys
input = sys.stdin.readline
def is_square(num):
    return int(num**(1/2))**2 == num
n, m = map(int,input().split())

arr = [list(map(int,input().strip())) for _ in range(n)]
result = -1
for row in range(n):
    for col in range(m):
        for row_d in range(-n+1,n):
            for col_d in range(-m+1, m):
                if row_d == 0 and col_d == 0:
                    
                    if is_square(arr[row][col]):
                        result = max(arr[row][col],result)
                    continue
                this_num = 0
                count = 0
                while True:
                    this_turn_row = row + count*row_d
                    this_turn_col = col + count*col_d  
                    if 0<= this_turn_row < n and 0<= this_turn_col < m:
                        this_num += arr[this_turn_row][this_turn_col]
                       
                        if is_square(this_num):
                            result = max(result,this_num)
                        this_num *= 10
                        count += 1
                    else:
                        break
        
print(result)