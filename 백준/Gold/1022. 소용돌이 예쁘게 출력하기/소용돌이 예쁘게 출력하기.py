import sys
import math
input = sys.stdin.readline
def can_fill(row,col,num):
    global start_row, finish_row, start_col, finish_col
    
    if start_row <= row < finish_row and start_col <=col  < finish_col:
        
        result[row - start_row][col-start_col] = num
def fill(rc,count):
    global num
    up_direction = count*2 - 1
    left_direction = count*2
    row = rc
    col = rc
    can_fill(row,col+1,num)
    col += 1
    num += 1
    for _ in range(up_direction):
        can_fill(row-1,col,num)
        row -= 1
        num += 1
    for _ in range(left_direction):
        can_fill(row,col-1,num)
        col -= 1
        num +=1
    for _ in range(left_direction):
        can_fill(row+1,col,num)
        row += 1
        num += 1
    for _ in range(left_direction):
        can_fill(row,col+1,num)
        col += 1
        num += 1


r1, c1, r2, c2 = map(int,input().split())

row_max = max(abs(r1),abs(r2))
col_max = max(abs(c1),abs(c2))
square_max = max(row_max,col_max)


start_row = square_max + r1
finish_row = square_max + r2 + 1
start_col = square_max + c1
finish_col = square_max + c2 + 1
result = [[0]*(finish_col-start_col) for _ in range(finish_row-start_row)]

num = 1

can_fill(square_max,square_max,num)
num += 1
for i in range(square_max,2*square_max):
    fill(i,(i-square_max+1))




blank =int(math.log10(max(max(result))))

for i in range(len(result)):
    for j in range(len(result[0])):
        this_turn_blank = int(math.log10(result[i][j]))
        print(' '*(blank-this_turn_blank),result[i][j],' ',sep='',end='')
    print('')