from copy import deepcopy
import sys

def permutations(real, result, M):
    if M == 0:
        for i in result:
            print(i,'', end='')
        print('')
        return
    if len(real)<M:
        return 
    for i in range(len(real)):
        real2 = copy.deepcopy(real)
        result2 = copy.deepcopy(result)
        how = real.pop(-1-i)
        result.append(how)
        permutations(real, result,M-1)
        real = real2
        result = result2
N,M = map(int,sys.stdin.readline().split())

real =list(range(N,0,-1))


permutations(real, [],M)
