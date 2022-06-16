import sys
import collections
input = sys.stdin.readline
N = int(input())

lst = list(map(int,input().split()))

sor_lst = sorted(lst)

for idx,value in enumerate(lst):
    # 이번 턴은 i찾기
    val = sor_lst.index(value)
    print(val,'',end = '')
    sor_lst[val] = -1