import sys
input = sys.stdin.readline


def dfs(this_turn, money):
    if this_turn == N:
        if (room_info[this_turn][0] == "E" or room_info[this_turn][0] == "L"):
            return True
        else:
            if int(room_info[this_turn][1]) <= money:
                return True
            else:
                return False
    if room_info[this_turn][0] == "E":
        for i in range(2,len(room_info[this_turn])-1):
            if not visited[int(room_info[this_turn][i])]:
                visited[int(room_info[this_turn][i])] = True
                if dfs(int(room_info[this_turn][i]), money):
                    return True
                visited[int(room_info[this_turn][i])] = False
        return False
    elif room_info[this_turn][0] == "L":
        for i in range(2,len(room_info[this_turn])-1):
            if not visited[int(room_info[this_turn][i])]:
                visited[int(room_info[this_turn][i])] = True
                if dfs(int(room_info[this_turn][i]), max(money,int(room_info[this_turn][1]))):
                    return True
                visited[int(room_info[this_turn][i])] = False
        return False
    else:
        if money >= int(room_info[this_turn][1]):
            for i in range(2,len(room_info[this_turn])-1):
                if not visited[int(room_info[this_turn][i])]:
                    visited[int(room_info[this_turn][i])] = True
                    if dfs(int(room_info[this_turn][i]), money - int(room_info[this_turn][1])):
                        return True
                    visited[int(room_info[this_turn][i])] = False
        return False
    return False
while True:
    N = int(input())
    if not N:
        break
    room_info = [0]+[list(map(str,input().strip().split())) for _ in range(N)]
    visited = [False]*(N+1)
    visited[1] = True
    if dfs(1,0):
        print("Yes")
    else:
        print("No")

