import sys

def primes(num):
    prime = [True for _ in range(num)]
    prime[0]=False
    prime[1]=False
    many = int(num**0.5)
    for i in range(2,many+1):
        if prime[i] == True:
            for j in range(i+i, num, i):
                prime[j]= False
    prime_s = [i for i in range(num) if prime[i] == True]
    return prime,prime_s

def find_add(arr,prime,prime_s): #arr은 입력받은 숫자 lst prime은 모든 소수집합
    chejong = []
    le = len(prime)
    for i in arr:
        yebi = []
        start = 0
        while(start<le and prime_s[start]<i//2+1):
           
            if prime[i-prime_s[start]] == True :
                if len(yebi)!=2:
                        yebi.append(prime_s[start])
                        yebi.append(i-prime_s[start])
                else:
                    if yebi[1]-yebi[0]>i-prime_s[start]-prime_s[start]:
                        yebi[0]=prime_s[start]
                        yebi[1]=i-prime_s[start]
            start+=1
        chejong.append(yebi)
        
    return chejong
    
N = int(sys.stdin.readline())
arr = [int(sys.stdin.readline().strip()) for i in range(N)]

max_val = max(arr)

prime, prime_s = primes(max_val)

result1 = find_add(arr,prime,prime_s)

for i in result1:
    print(i[0],i[1])


