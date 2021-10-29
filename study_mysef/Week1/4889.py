import sys
import collections
arr = []

while True:
    string = sys.stdin.readline().strip()
    if string[0] =='-':
        break
    else:
        arr.append(string)

stable = collections.deque([])
result = []
for i in arr:
    count = 0
    for j in i:
        if j == '{':
            stable.append(j)
        else:
            try:
                compare = stable.pop()
            except:
                count += 1
                stable.append('{')
    real_count = 0
    while stable:
        stable.pop()
        real_count+=1
    count+=(real_count+1)//2
    result.append(count)
for i, num in enumerate(result):
    print('%d. %d' %(i+1, num))