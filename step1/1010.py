import sys

def permu(n,m,result):
    if n == m:
        return result 
    result = (result*n)
    return permu(n-1,m,result)
    
def real(result,m):
    if m ==0:
        
        return result
    if m==1:
        
        return result
    return real(result//m,m-1)

n = int(sys.stdin.readline())

arr = [list(map(int,sys.stdin.readline().split()))for _ in range(n)]

for i in arr:
    n,m = max(i[0], i[1]), min(i[0],i[1])
    result_ = permu(n,max(m,n-m),1)
    print(real(result_,min(m,n-m)))