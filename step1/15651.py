
import sys

def permutations(real, result, M):
    if M == 0:
        for i in result:
            print(i,'',end='')
        print('')
        return
    for i in real:
        
        result.append(i)
        permutations(real, result, M-1)
        result.pop(-1)
        


N,M = map(int, sys.stdin.readline().split())

real = list(range(1,N+1))

permutations(real,[],M)