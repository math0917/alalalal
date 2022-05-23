import sys
input =sys.stdin.readline
import re

T = int(input())

for _ in range(T):
    test = input().strip()
    Test = re.compile('(100+1+|01)+')
    t_f = Test.fullmatch(test)
    if t_f:print("YES")
    else:print("NO")    
