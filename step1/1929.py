import sys
def primes(start,num):
    prime = [True for _ in range(num)]
    prime[0] = False
    prime[1] = False
    
    for i in range(2,num):
        if prime[i] == True:
            for j in range(i+i,num,i):
                prime[j]=False
    
    return [i for i in range(num) if prime[i]==True and i>= N]

N ,M = map(int,sys.stdin.readline().split())

ptr = primes(N,M+1)

for i in ptr:
    print(i)

