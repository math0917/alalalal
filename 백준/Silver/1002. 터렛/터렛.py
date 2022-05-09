import sys
input = sys.stdin.readline

n = int(input())

case = [list(map(int,input().split())) for _ in range(n)]

for this_turn in case:
    x = (this_turn[0] - this_turn[3])**2
    y = (this_turn[1] - this_turn[4])**2
    distance = (x+y)**(1/2)
    if distance == 0 and this_turn[2] == this_turn[5]:
        print(-1)
# 두원사이 거리와 반지름의 비교
    elif distance > this_turn[2] + this_turn[5]:
        print(0)
# 외접하면
    elif distance == this_turn[2] + this_turn[5]:
        print(1)
    else:
        
        if distance + min(this_turn[5],this_turn[2]) < max(this_turn[5],this_turn[2]):
            print(0)
        elif distance + min(this_turn[5], this_turn[2]) == max(this_turn[5],this_turn[2]):
            print(1)
        else:
            print(2)
