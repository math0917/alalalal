import sys

n = int(sys.stdin.readline())
if n == 1:
    print(0)
    sys.exit()
prime = [True]*(n+1)

prime[0] = False
prime[1] = False

idx = 2
while idx < n+1:
    if prime[idx]:
        multiplier = idx * 2
        while multiplier < n+1:
            prime[multiplier] = False
            multiplier += idx
    
    idx +=1

prime_lst = [i for i in range(n+1) if prime[i]]


summation = prime_lst[0]
start = 0
finish = 1
count = 0
while start<=finish:
 
    if finish == len(prime_lst):
        while start<finish:
            if summation == n:
                count +=1
            summation -= prime_lst[start]
            start += 1
        break
    if summation < n:
        summation += prime_lst[finish]
        finish += 1
    elif summation > n:
        summation -= prime_lst[start]
        start += 1
    else:
        summation+=prime_lst[finish]
        count += 1
        finish += 1
print(count)