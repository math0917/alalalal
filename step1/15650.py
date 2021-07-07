from copy import deepcopy
import sys

def permutations(real,result,M):
    if M == 0:
        for i in result:
            print(i,'',end='')
        print('')
        return
    if len(real)<M:
        return
    for i in range(len(real)):
        result2 = deepcopy(result)
        result.append(real[i])
        permutations(real[i+1:len(real)+1],result,M-1)
        result = result2

N,M = map(int,sys.stdin.readline().split())

real =list(range(1,N+1))

permutations(real,[],M)