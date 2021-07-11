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

N = int(sys.stdin.readline())

arr = list(map(int,sys.stdin.readline().split()))

max_val = max(arr)+1

ptr = primes(max_val)


result = []
for i in arr:
    if ptr[i]==True:
        result.append(i)

print(len(result))