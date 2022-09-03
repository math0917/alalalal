import sys
input = sys.stdin.readline
import itertools
import copy

def rotate(fr_row, to_row, fr_col, to_col):
  this_turn_arr = []
  for i in range(fr_col, to_col):
    this_turn_arr.append(this_turn_matrix[fr_row][i])
  for i in range(fr_row, to_row):
    this_turn_arr.append(this_turn_matrix[i][to_col])
  for i in reversed(range(fr_col+1, to_col+1)):
    this_turn_arr.append(this_turn_matrix[to_row][i])
  for i in reversed(range(fr_row+1, to_row+1)):
    this_turn_arr.append(this_turn_matrix[i][fr_col])
  idx = -1
  for i in range(fr_col, to_col):
    this_turn_matrix[fr_row][i] = this_turn_arr[idx]
    idx+=1
  for i in range(fr_row, to_row):
    this_turn_matrix[i][to_col] = this_turn_arr[idx]
    idx+=1
  for i in reversed(range(fr_col+1, to_col+1)):
    this_turn_matrix[to_row][i] = this_turn_arr[idx]
    idx += 1
  for i in reversed(range(fr_row+1, to_row+1)):
    this_turn_matrix[i][fr_col] = this_turn_arr[idx]
    idx += 1
  

n, m, k = map(int,input().split())

matrix = [list(map(int,input().split())) for _ in range(n)]

proc = [list(map(int,input().split())) for _ in range(k)]

result = float('inf')

for arr in itertools.permutations(range(k)):
  this_turn_matrix = copy.deepcopy(matrix)
  for idx in arr:
    this_row, this_col, rotate_len = proc[idx][0]-1, proc[idx][1]-1, proc[idx][2]
    for j in range(1, rotate_len+1):
      rotate(this_row-j, this_row + j, this_col - j, this_col + j)
  min_row = float('inf')
  for i in range(n):
    min_row = min(min_row, sum(this_turn_matrix[i]))
  result = min(result,min_row)
print(result)
