import sys
input = sys.stdin.readline

x1,y1,x2,y2,x3,y3 = map(int,input().split())

if (y2-y1)*(x3-x1) == (y3-y1)*(x2-x1):
    print(-1)
else:
    cp1 = ((x2-x1)**2 + (y2-y1)**2)**(1/2) + ((x1-x3)**2 + (y1-y3)**2)**(1/2)
    cp2 = ((x2-x1)**2 + (y2-y1)**2)**(1/2) + ((x2-x3)**2 + (y2-y3)**2)**(1/2)
    cp3 = ((x2-x3)**2 + (y2-y3)**2)**(1/2) + ((x1-x3)**2 + (y1-y3)**2)**(1/2)
    print(abs(2*max(cp1,cp2,cp3)- 2*min(cp1,cp2,cp3)))