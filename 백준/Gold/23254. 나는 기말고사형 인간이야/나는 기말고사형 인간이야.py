import sys
import heapq
input = sys.stdin.readline
n, m = map(int,input().split())
heap = []
grade = list(map(int,input().split()))

per_hour = list(map(int,input().split()))

for i in range(m):
    can_study = min(100 - grade[i], per_hour[i]) 
    if can_study == 0:
        continue
    heapq.heappush(heap,(-can_study ,grade[i],i))

# 공부가능한 시간
possible_time = 24*n

while heap and possible_time > 0:
    this_turn = heapq.heappop(heap)
    can_study_per_hour, this_grade, index = (-this_turn[0],this_turn[1], this_turn[2])
    study_time = (100 - this_grade) // can_study_per_hour
    if possible_time - study_time >= 0 :
        grade[index] += study_time * can_study_per_hour
        possible_time -= study_time
        if grade[index] != 100:
            heapq.heappush(heap, (-(100-grade[index]), grade[index], index))
    else:
        grade[index] += possible_time * can_study_per_hour
        break
print(sum(grade))