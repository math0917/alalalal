import sys

def permu(n,m,result):
    if n == m:
        return result 
    result = (result*n)
    return permu(n-1,m,result)
    
def real(result,m):
    if m ==0:
        print(result%10007)
        sys.exit()
    if m==1:
        print(result%10007)
        sys.exit()
    real(result//m,m-1)
n, m = map(int,sys.stdin.readline().split())

m = max(m, n-m)
result_ = permu(n,max(m,n-m),1)

real(result_,min(m,n-m))