import sys
input = sys.stdin.readline
while True:
    a1, a2 = map(int,input().split())
    if a1 == 0 and a2 == 0:
        break
    else:
        print(a1+a2)