import sys
input = sys.stdin.readline

n,m= map(int,input().split())

if n<m:
    print('<')
elif n == m:
    print('==')
else:
    print('>')