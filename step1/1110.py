import sys

n = int(sys.stdin.readline())
aim = n
count = 0
while True:
    print(n)
    count += 1
    if n < 10:
        n*=10
    ten = n//10
    one = n%10

    new_value = ten + one

    n = one*10 + new_value%10
    if n == aim:
        print(count)
        break
    