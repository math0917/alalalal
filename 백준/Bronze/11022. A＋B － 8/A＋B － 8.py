import sys
input = sys.stdin.readline

T = int(input())

for test_case in range(T):
    a, b = map(int,input().split())
    
    print("Case #"+ str(test_case+1)+": "+ str(a)+" + "+str(b)+" = "+str(a+b))