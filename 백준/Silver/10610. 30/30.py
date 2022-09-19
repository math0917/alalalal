import sys
input = sys.stdin.readline

arr = list(input().strip())

if '0' not in arr:
  print(-1)
  sys.exit()
value = 0
for i in arr:
  value += int(i)
if value % 3 == 0:
  arr.sort(reverse = True)
  for i in arr:
    print(i,end='')
else:
  print(-1)
  sys.exit()



  