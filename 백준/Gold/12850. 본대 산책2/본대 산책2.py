import sys
input = sys.stdin.readline

def find(n):
    if n == 1:
        return path
    if n == 2:
        temp = []
        for i in range(8):
            temp.append([])
            for j in range(8):
                num = 0
                for t in range(8):
                    num += path[i][t] *path[t][j]
                temp[i].append(num%1000000007)
        return temp
    else:
        # 홀수면
        if n%2:
            square_element = find(n//2)
            temp = []
            for i in range(8):
                temp.append([])
                for j in range(8):
                    num = 0
                    for t in range(8):
                        num += square_element[i][t] *square_element[t][j]
                    temp[i].append(num%1000000007)
            return_matrix = []
            for i in range(8):
                return_matrix.append([])
                for j in range(8):
                    num = 0
                    for t in range(8):
                        num += temp[i][t] *path[t][j]
                    return_matrix[i].append(num%1000000007)
            return return_matrix
        else:
            square_element = find(n//2)
            temp = []
            for i in range(8):
                temp.append([])
                for j in range(8):
                    num = 0
                    for t in range(8):
                        num += square_element[i][t] *square_element[t][j]
                    temp[i].append(num%1000000007)
            return temp


n = int(input())

path = [
    [0,1,1,0,0,0,0,0],
    [1,0,1,1,0,0,0,0],
    [1,1,0,1,1,0,0,0],
    [0,1,1,0,1,1,0,0],
    [0,0,1,1,0,1,1,0],
    [0,0,0,1,1,0,0,1],
    [0,0,0,0,1,0,0,1],
    [0,0,0,0,0,1,1,0]
]

print(find(n)[0][0])