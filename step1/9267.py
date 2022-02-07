import sys
def sol(a,b):
    print(a,b)
    if not a%b:
        return
    else:
        sol(b,a%b)
        
a, b, s = map(int,sys.stdin.readline().split())

sol(227,143)