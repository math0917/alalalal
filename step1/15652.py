import sys

def permutations(real, result, M):

    if M == 0:
        for i in result:
            print(i,'',end='')
        print('')
        return
    for i in range(len(real)):
        real2 = real[i:len(real)+1]
        result.append(real[i])
        permutations(real2,result,M-1)
        result.pop(-1)


N ,M = map(int,sys.stdin.readline().split())

real = list(range(1,N+1))

permutations(real,[],M)