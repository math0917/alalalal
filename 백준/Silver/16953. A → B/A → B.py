import sys
input = sys.stdin.readline

A, B = map(int,input().split())

count = 0

while B > A:
  if B % 2:
    if B%10 == 1:
      B//=10
    else:
      print(-1)
      sys.exit()
  else:
    B //= 2
  if B == A:
    print(count+2)
    sys.exit()
  count += 1
print(-1)