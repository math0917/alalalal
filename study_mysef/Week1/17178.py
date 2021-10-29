import sys
import collections
n = int(sys.stdin.readline())

arr = [list(map(lambda x : x.split('-'),sys.stdin.readline().split())) for _ in range(n)]
flag = 1
alpha = dict()
waiting = collections.deque([])
for i in range(n):
    for j in range(5):
        this_turn_alpha = arr[i][j][0]
        this_turn_num = int(arr[i][j][1])
        
        while waiting:
            compare = waiting.pop()
            if compare[0] > this_turn_alpha: #기존거보다 작은거면 기존거는 다시넣고 작은거도 넣고
                waiting.append(compare)
                
                break
            elif compare[0] == this_turn_alpha:
                if compare[1] < this_turn_num: #들어올 숫자가 더 크면 기존의 숫자는 보내야됨
                    try:
                        if alpha[compare[0]] < compare[1]: #더 작은수가 들어갔으면 이제 바꾸면됨
                            alpha[compare[0]] = compare[1]
                        else: # 만약 이미 더 큰수가 들어가있으면 안되는거임
                            flag= 0
                            sys.exit()
                    except: #아직뺀적없으면 그값을 넣어두면됨
                        alpha[compare[0]] = compare[1]
                else: #들어올 숫자가 더 작으면 걍 넣으면됨
                    waiting.append(compare)
                   
                    break
            else: #기존거보다 크면
                try:
                    if alpha[compare[0]] < compare[1]:
                        alpha[compare[0]] = compare[1]
                    else:
                        flag = 0
                        sys.exit()
                except:
                    alpha[compare[0]] = compare[1]
        waiting.append((this_turn_alpha,this_turn_num))
        

while waiting:
    compare = waiting.pop()
    try:
        if alpha[compare[0]] < compare[1]:
            alpha[compare[0]] = compare[1]
        else:
            flag= 0
            sys.exit()
    except:
        alpha[compare[0]] = compare[1]
if flag:
    print('GOOD')
else:
    print('BAD')

