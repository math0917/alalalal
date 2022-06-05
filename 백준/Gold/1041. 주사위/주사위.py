import sys

input = sys.stdin.readline

n = int(input())

dice = list(map(int,input().split()))
if n == 1:
    print(sum(dice)- max(dice))
    sys.exit()

one_min = min(dice)
two_min = float('inf')
three_min = float('inf')
sum_li = []

sum_li.append(min(dice[0],dice[5]))
sum_li.append(min(dice[1],dice[4]))
sum_li.append(min(dice[2],dice[3]))

sum_li.sort()

two_min = sum_li[1]+sum_li[0]
three_min = sum(sum_li)

print(4*three_min + 4*(n-2)*two_min + one_min * ((n-2)**2) + (n-1)*(4*two_min + one_min*4*(n-2)))