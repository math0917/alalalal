import sys
input = sys.stdin.readline

def check():
    global p1, p2, p3, p4, x_123,x_124,x_341,x_342
    if x_123 * x_124 == 0:
        if x_341 * x_342 == 0:
            
            if p3<= p2 and p1<= p4:
                return True
            else:
                return False
    if x_123 * x_124 <=0 :
        if x_341* x_342 <=0:
            return True
    return False                

def CCW(x1, y1, x2, y2, x3, y3):
    return (x1*y2 + x2*y3 + x3*y1) - (y1*x2 + y2*x3 + y3*x1)


x1, y1, x2, y2 = map(int,input().split())
x3, y3, x4, y4 = map(int,input().split())

p1 = (x1, y1)
p2 = (x2, y2)
p3 = (x3, y3)
p4 = (x4, y4)
if p1 > p2:
    p1, p2 = p2, p1
if p3 > p4:
    p3, p4 = p4, p3
x_123 = CCW(x1,y1,x2,y2,x3,y3)
x_124 = CCW(x1,y1,x2,y2,x4,y4)
x_341 = CCW(x3,y3,x4,y4,x1,y1)
x_342 = CCW(x3,y3,x4,y4,x2,y2)

# 우선 만나기는 하는애들
if check():
    print(1)
    try:
        result_x = ((x3-x4)*(x1*y2 - x2*y1) + (x2 - x1)*(x3*y4-x4*y3))/((x3 - x4)*(y2 - y1) - (y4 - y3)*(x1 - x2))
        result_y = ((y3 - y4)* (x1*y2 - x2*y1) + (y2- y1)*(x3*y4-x4*y3)) / ((x3 - x4)*(y2 - y1) - (y4 - y3)*(x1 - x2))
        print(result_x, result_y)
    except:
       
        if p2 == p3:
            print(p2[0], p2[1])
        elif p1 == p4:
            print(p1[0], p1[1])
    
else:
    print(0)