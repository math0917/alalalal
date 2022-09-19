import sys
input = sys.stdin.readline
n = int(input())
mid = int((n*2)**(1/2))

if mid*(mid+1) <= 2*n:
  print(mid)
else:
  print(mid-1)