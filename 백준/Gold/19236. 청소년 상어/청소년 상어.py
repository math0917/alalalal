import sys
input = sys.stdin.readline
import collections
import copy
dx = [-1,-1,0,1,1,1,0,-1]
dy = [0,-1,-1,-1,0,1,1,1]

def next_direction(dir):
  return (dir+1)%8


water = [list(map(int,input().split())) for _ in range(4)]

# 기억하고 싶은 것 : 물고기 번호가 있는 위치, 방향
# 그러면 두개의 기억장치를 만들까? 512MB있으니까 해보자
# fishLocDirectionInfoByNum : num을 기준으로 위치, 방향을 찾기
# fishNumInfoByLoc : 위치를 기반으로 num 찾기
fish_loc_direction_info_by_num = collections.defaultdict(tuple)
fish_num_info_by_loc = collections.defaultdict(int)
for i in range(4):
  for j in range(0,8,2):
    fish_loc_direction_info_by_num[water[i][j]] = (i,j//2,water[i][j+1]-1)
    fish_num_info_by_loc[(i,j//2)] = water[i][j]
# stack은 상어 위치, 상어의 크기, 이번 상어의 방향

stack = collections.deque([(0,0,water[0][0], water[0][1]-1,fish_loc_direction_info_by_num,fish_num_info_by_loc)])
# 번호로 조회 할때 이미 shark뱃속에 있는경우 (-1,-1,-1)
fish_loc_direction_info_by_num[water[0][0]] = (-1,-1,-1)
# 위치로 조회 할때 shark가 있으면 -1, 빈공간이면 0
fish_num_info_by_loc[(0,0)] = -1


result = 0
while stack:
  shark_row, shark_col, ever_eaten, shark_dir,fish_loc_direction_info_by_num,fish_num_info_by_loc = stack.pop()
  result = max(result, ever_eaten)
  # 이제 물고기의 움직임이 있는 타이밍
  for i in range(1,17):
    # 상어 뱃속에 있지 않는 경우 이동해야함
    if fish_loc_direction_info_by_num[i][0] != -1:
      fish_row, fish_col, fish_direction = fish_loc_direction_info_by_num[i]
      
      # 이동할 수 있는 방향을 조사해야함
      while True:
       
        row = fish_row + dx[fish_direction]
        col = fish_col + dy[fish_direction] 
        
        if 0 <= row < 4 and 0 <= col < 4 and fish_num_info_by_loc[(row,col)] != -1:
          # 빈 공간이면 
          if fish_num_info_by_loc[(row,col)] == 0:
            fish_loc_direction_info_by_num[i] = (row,col,fish_direction)
            fish_num_info_by_loc[(fish_row,fish_col)]= 0
            fish_num_info_by_loc[(row,col)] = i
            break
          # 다른 물고기가 있으면
          else:
            have_to_change_fish_num = fish_num_info_by_loc[(row,col)]
            change_row, change_col, change_direction = fish_loc_direction_info_by_num[have_to_change_fish_num]
            fish_loc_direction_info_by_num[i] = (change_row,change_col, fish_direction)
            fish_num_info_by_loc[(change_row, change_col)] = i
            fish_loc_direction_info_by_num[have_to_change_fish_num] = (fish_row, fish_col, change_direction)
            fish_num_info_by_loc[(fish_row,fish_col)] = have_to_change_fish_num
            break
        fish_direction = next_direction(fish_direction)
  # 이제는 상어가 먹을 수 있는 물고기나 빈칸을 찾는 과정
  length = 1
  while True:
    row = shark_row + dx[shark_dir]*length
    col = shark_col + dy[shark_dir]*length
    if 0<= row < 4 and 0 <= col < 4:
      new_fish_num_info_by_loc = copy.deepcopy(fish_num_info_by_loc)
      new_fish_loc_direction_info_by_num = copy.deepcopy(fish_loc_direction_info_by_num)
      # 그 안에 fish가 있다면~
      if fish_num_info_by_loc[(row,col)]:
        fish_num = fish_num_info_by_loc[(row,col)]
        fish_row = row
        fish_col = col
        
        # 여기선 기존 상어의 위치를 공백으로 바꾸고 새로운
        # 상어위치를 설정해줘야함
        new_fish_num_info_by_loc[(row,col)] = -1
        new_fish_num_info_by_loc[(shark_row,shark_col)] = 0
        
        # fish를 없애야함
        new_shark_direction = fish_loc_direction_info_by_num[fish_num][2]
        new_fish_loc_direction_info_by_num[fish_num] = (-1,-1,-1)
      
     
        stack.append((row,col,ever_eaten + fish_num, new_shark_direction,new_fish_loc_direction_info_by_num,new_fish_num_info_by_loc))
      length += 1
    else:
      break
print(result)