import copy
import sys
def solution(beginning, target):
    answer = 0
    rowLen = len(beginning)
    colLen = len(beginning[0])
    cnt = float('inf')
    for rowBit in range(2 ** rowLen):
        newCoin = copy.deepcopy(beginning)
        changeCount = 0
        for i in range(rowLen):
            if (1<<i) & rowBit:
                changeCount += 1
                for j in range(colLen):
                    newCoin[i][j] = (newCoin[i][j] + 1) % 2
        flag = True
        for i in range(rowLen):
            if flag:
                for j in range(colLen):
                    if flag:
                        if newCoin[i][j] != target[i][j]:
                            if i > 0:
                                flag = False
                                continue
                            else:
                                changeCount += 1
                                for t in range(rowLen):
                                    newCoin[t][j] = (newCoin[t][j] + 1) % 2
                    else:
                        break
            else: break
        if flag:
            cnt = min(cnt, changeCount)
                        
    return cnt if cnt != float('inf') else -1
