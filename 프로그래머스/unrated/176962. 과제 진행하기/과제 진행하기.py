import collections
from collections import deque
            
def solution(plans):
    plans.append(('몰라',"99:15999","2000"))
   
    plans.sort(key = lambda x : (x[1]))
    answer = []
    leftOver = collections.deque([])
    finishTime = timeAdd(plans[0][1], plans[0][2])
    for i in range(1,len(plans)):
        if finishTime <= plans[i][1]:
            answer.append(plans[i-1][0])
            timeDiff = diff(plans[i][1], finishTime)
            while leftOver:
                times = leftOver.pop()
                if times[1] <= timeDiff:
                    timeDiff -= times[1]
                    answer.append(times[0])
                else:
                    leftOver.append((times[0], times[1] - timeDiff))
                    break
                
        else:
            leftOver.append((plans[i-1][0],diff(finishTime, plans[i][1])))
        finishTime = timeAdd(plans[i][1], plans[i][2])
    
    while leftOver:
        answer.append(leftOver.pop()[0])
    return answer
def diff(time1, time2):
    time1 = list(map(int,time1.split(":")))
    time2 = list(map(int,time2.split(":")))
    
    return (time1[0] - 1 - time2[0]) * 60 + 60 + time1[1] - time2[1]
def timeAdd(timeString, addTimeString):
    time = list(map(int,timeString.split(":")))
    addTime = int(addTimeString)
    newTimeMinute = (time[1] + addTime) %60 
    newTimeHour = time[0] + (time[1] + addTime) // 60
    return str(newTimeHour).zfill(2)+":"+str(newTimeMinute).zfill(2)
