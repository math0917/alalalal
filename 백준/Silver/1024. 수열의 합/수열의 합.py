import sys
input = sys.stdin.readline


n, l = map(int,input().split())

while l <= 100:
    if l % 2== 0:
        if (n+(l//2))%l == 0:
            d = l//2
            if (n+(l//2))//l - d < 0:
                print(-1)
                sys.exit()
            for i in reversed(range(1,d+1)):
                print((n+(l//2))//l - i,'',end='')
            for i in range(d):
                print((n+(l//2))//l + i,'',end='')
            sys.exit()
        else:
            l += 1
    else:
        if n % l == 0:
            d = l//2
            if n//l - d < 0:
                print(-1)
                sys.exit()
            for i in reversed(range(1,d+1)):
                print(n//l-i,'',end='')
            print(n//l,'',end='')
            for i in range(1,d+1):
                print(n//l+i,'',end='')
            sys.exit()
        else:
            l+=1
print(-1)