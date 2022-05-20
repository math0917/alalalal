import sys
import math
input = sys.stdin.readline

def find(num):
    ans = num & 1
    try:
        idx = int(math.log2(num))+1
    except:
        return ans
    for i in reversed(range(1,idx)):
        if (num & (1<<i)):
            ans += func[i-1] + (num - (1<<i) + 1)
            num -= 1<<i
    return ans
fr, to = map(int,input().split())

func = [1]

idx=int(math.log2(to)) + 1

for i in range(1,idx):
    func.append(2*func[-1] + (1<<i))

print(find(to) - find(fr-1))