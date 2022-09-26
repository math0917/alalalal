import sys
input = sys.stdin.readline
import collections

def add_one():
  global dictionary
  for i in range(10):
    dictionary[str(i)] += 1

n = input().strip()

dictionary = collections.defaultdict(int)
count = 1

add_one()

for i in n:
    
  if i == '6' or i == '9':
    if dictionary['6'] >= 1:
      dictionary['6'] -= 1
    elif dictionary['9'] >= 1:
      dictionary['9']-=1
    else:
      add_one()
      dictionary['6']-=1
      count += 1
  else:
    if dictionary[i] >= 1:
      dictionary[i] -= 1
    else:
      add_one()
      dictionary[i] -= 1
      count += 1
print(count)