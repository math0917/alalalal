
sentence = input()
sentence = sentence.split(' ')
N = int(sentence[0])
M = int(sentence[1])
card = input()
card = card.split(' ')

for i in range(N):
    card[i] = int(card[i])
result=set()
for i in range(N-2):
    for j in range(i+1,N-1):
        for k in range(j+1,N):
            val=card[i]+card[j]+card[k]
            if val<=M:
                result.add(val)

print(max(result))