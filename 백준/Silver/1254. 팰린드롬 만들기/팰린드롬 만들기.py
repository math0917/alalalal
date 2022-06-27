import sys
input = sys.stdin.readline

sen = input().strip()

is_p = [[0]*len(sen) for _ in range(len(sen))]
sen = sen[::-1]

for i in range(len(sen)):
    for j in range(len(sen)-i):
        
        if i == 0:
            is_p[j][j] = 1
        elif i == 1:
            is_p[j][j+1] = 1 if sen[j] == sen[j+1] else 0
        else:
            if is_p[j+1][j+i-1] == 1:
                if sen[j] == sen[j+i]:
                    is_p[j][j+i] = 1
                else:
                    is_p[j][j+i] = 0
            else:
                is_p[j][j+i] = 0

        
for i in reversed(range(len(sen))):
    if is_p[0][i] == 1:
        print(len(sen)+len(sen) - i - 1)
        break