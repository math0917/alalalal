import sys
def primes(num):
    prime = [True for _ in range(num)]
    prime[0] = False
    prime[1] = False
    
    for i in range(2,num):
        if prime[i] == True:
            for j in range(i+i,num,i):
                prime[j]=False
    
    return prime

N= int(sys.stdin.readline())
M= int(sys.stdin.readline())

arr = list(range(N,M+1))

pr = primes(M+1)

result =[]

for i in arr:
    if pr[i]==True:
        result.append(i)

if len(result) == 0:
    print(-1)
else:
    print(sum(result))
    print(result[0])
