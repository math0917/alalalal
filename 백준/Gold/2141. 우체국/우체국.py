import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)
def find_min(index, left_length, right_length, left_count, right_count):
  global min_value, min_index
  

  if left_length + right_length < min_value:
    min_value = left_length + right_length
    min_index = road[index][0]
  if right_length:
    find_min(index+1, left_length + (left_count + road[index][1]) * (road[index+1][0] - road[index][0]) , right_length - right_count * (road[index+1][0] - road[index][0]),left_count + road[index][1] , right_count - road[index+1][1])
    

n = int(input())

road = [list(map(int,input().split())) for _ in range(n)]

road.sort()

min_value = float('inf')
min_index = -1


right_len = 0
right_count = 0
for i in range(1, n):
  right_len += (road[i][0] - road[0][0])*road[i][1]
  right_count += (road[i][1])

find_min(0,0,right_len,0,right_count)
print(min_index)