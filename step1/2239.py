import sys

def next_row_col(row,col):
    if col == 8:
        return (row+1,0)
    else:
        return (row,col+1)

def squre_right(row,col):
    this_set = set(list(range(1,10)))
    start_row = (row//3)*3
    start_col = (col//3)*3
    for i in range(start_row,start_row+3):
        for j in range(start_col, start_col + 3):
            this_set.discard(sdoku[i][j])
    return this_set
def col_right(row,col):
    this_set = set(list(range(1,10)))
    for i in range(9):
        this_set.discard(sdoku[i][col])
    return this_set

def row_right(row,col):
    this_set = set(list(range(1,10)))
    for i in range(9):
        this_set.discard(sdoku[row][i])
    return this_set

def dfs(row,col):
    if row == 9 and col == 0:
        for i in range(9):
            for j in range(9):
                print(sdoku[i][j],sep='',end='')
            print('')
        exit()

    next_row, next_col = next_row_col(row,col)
    if sdoku[row][col]:
        dfs(next_row,next_col)
    else:
        possible = squre_right(row,col) & col_right(row,col) & row_right(row,col)
        if possible:
            possible = list(possible)
            possible.sort()
            for i in possible:
                sdoku[row][col] = i
                dfs(next_row,next_col)
                sdoku[row][col] = 0
    
sdoku = [list(map(int,sys.stdin.readline().strip())) for _ in range(9)]

dfs(0,0)
