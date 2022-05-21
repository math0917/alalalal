import sys
input = sys.stdin.readline

def ccw(A,B,C):
    return A[0]*B[1] + B[0]*C[1] + C[0]*A[1] - (A[1]*B[0]+B[1]*C[0] + C[1]*A[0])

x11,y11,x12,y12 = map(int,input().split())
x21,y21,x22,y22 = map(int,input().split())

if ccw((x11,y11),(x12,y12),(x21,y21))*ccw((x11,y11),(x12,y12),(x22,y22)) <= 0 and ccw((x21,y21),(x22,y22),(x11,y11)) *ccw((x21,y21),(x22,y22),(x12,y12))<=0:
    if ccw((x11,y11),(x12,y12),(x21,y21))*ccw((x11,y11),(x12,y12),(x22,y22)) == 0 and ccw((x21,y21),(x22,y22),(x11,y11)) *ccw((x21,y21),(x22,y22),(x12,y12))==0:
        mx1, my1, mx2, my2 = min(x11, x12), min(y11, y12), max(x11, x12), max(y11, y12)
        mx3, my3, mx4, my4 = min(x21, x22), min(y21, y22), max(x21, x22), max(y21, y22)
        if mx1 <= mx4 and mx3 <= mx2 and my1 <= my4 and my3 <= my2:
            print(1)
        else:
            print(0)
    else:
        print(1)

else:
    print(0)