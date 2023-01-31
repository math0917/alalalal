import sys
input = sys.stdin.readline
def findGcd(a, b):
  returnValue = 1
  for i in range(2, min(a,b)):
    while a % i == 0 and b % i == 0:
      a //= i
      b //= i
      returnValue *=i
  return returnValue
n, rose1Count, rose1Money, rose2Count, rose2Money = map(int,input().split())

lcm = rose1Count // findGcd(rose1Count, rose2Count) * rose2Count
result = max((n - lcm) // lcm,0) 

minCost = min(lcm // rose1Count * rose1Money, lcm // rose2Count * rose2Money)
remain = n - (result * lcm)
result *=minCost

midResult = float('inf')
for i in range(remain//rose1Count + 2):
  remain2 = remain - rose1Count * i
  if remain2 <= 0:
    midResult = min(rose1Money * i, midResult)
  else:
    if remain2 % rose2Count == 0:
      midResult = min(rose1Money * i + (remain2 // rose2Count) * rose2Money, midResult)
    else:
      midResult = min(rose1Money * i + (remain2 // rose2Count + 1) * rose2Money, midResult)

print(result + midResult)