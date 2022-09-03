import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**5)
def post_order(num, left, right):
 
  if left == right:
    print(num)
    return
  for i in range(left, right+1):
    if num < pre_order[i]:
      break
  # 오른쪽이 없는 경우인듯...
  else:
    post_order(pre_order[left+1],left+1,right)
    print(num)
    return
  # 왼쪽이 있네요..
  if num > pre_order[left+1]:
    post_order(pre_order[left+1], left+1, i-1)
    post_order(pre_order[i], i, right)
    print(num)
    return
  # 왼쪽이 없네요
  else:
    post_order(pre_order[i],i,right)
    print(num)

pre_order = []
while True:
  try:
    pre_order.append(int(input()))
  except:
    break

post_order(pre_order[0],0, len(pre_order)-1)