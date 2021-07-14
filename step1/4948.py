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
arr = []
while(1):
    N = int(sys.stdin.readline())
    if N:
        arr.append(N)
    else:
        break

max_val = max(arr)

pr = primes(max_val+max_val+1)

result = []
for i in arr:
    count =0
    for j in range(i+1,i+i+1):
        if pr[j]==True:
            count+=1
    result.append(count)

for i in result:
    print(i)