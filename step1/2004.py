import sys

def find_five(num):
    count = 0
    compare = 5
    while compare<=num:
        count += num//compare
        compare*=5
    return count
def find_two(num):
    count = 0
    compare = 2
    while compare<=num:
        count += num//compare
        compare *= 2
    return count



n ,m  = map(int,sys.stdin.readline().split())
if m == 0:
    print(0)
    sys.exit()

top_five = find_five(n)
top_two = find_two(n)

bottom_left_five = find_five(n-m)
bottom_left_two = find_two(n-m)

bottom_right_five = find_five(m)
bottom_right_two = find_two(m)

print(min(top_five- bottom_left_five- bottom_right_five, top_two - bottom_left_two - bottom_right_two))