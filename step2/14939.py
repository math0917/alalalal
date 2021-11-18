import sys

def next_number(num):
    return_number = 0
    for j in range(10):
        if num & (1<<j):
            if 0< j <= 8:
                return_number^=(1<<j-1)
                return_number^=(1<<j)
                return_number^=(1<<j+1)
            elif j == 0:
                return_number^=(1<<j)
                return_number^=(1<<j+1)
            else:
                return_number^=(1<<j-1)
                return_number^=(1<<j)
    return return_number
arr = [list(sys.stdin.readline().strip()) for _ in range(10)]

board_row = []
result = float('inf')
for i in range(10):
    number = 0
    for j in range(10):
        if arr[i][j] == 'O':
            number |= (1<<j)
    board_row.append(number)
board_row.append(0)
for i in range(2**10):
    lower_light = 0 #아래는 지금 기준으로 바꿔야되고
    this_turn_count = 0
   
    for j in range(10):
        if i & (1<<j):
            lower_light |= (1<<j)
            this_turn_count += 1
    
    upper_light = next_number(lower_light) #위는 지금기준으로 바꿔야됨
    lower_number = board_row[0]
    for j in range(10):
        if result < this_turn_count :
            break
        upper_number = lower_number
        lower_number = board_row[j+1]
        upper_number ^= upper_light
        lower_number ^= lower_light
       
        lower_light = 0
        for k in range(10):
            if upper_number & (1<<k):
                lower_light |= 1<<k
                this_turn_count+=1
       
        upper_light = next_number(lower_light)
  
    if not upper_number:
        result = min(result, this_turn_count)
        
if result == float('inf'):
    print(-1)
else:
    print(result)


    


   
    