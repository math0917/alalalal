import sys
input = sys.stdin.readline
import heapq
import collections
def find(arr, time, start, finish):
    
    if start > finish:
        return finish
    mid = (start+finish)//2
    if arr[mid] == time:
        return mid
    elif arr[mid] > time:
        return find(arr, time, start,mid-1)
    else:
        return find(arr, time ,mid+1, finish)
def next_time(time, delay):
    hour, minute = map(int,time.split(":"))
    hour += (minute + delay)//60
    minute = (minute+delay)%60
    
    return str(hour).rjust(2,'0')+":"+str(minute).rjust(2,'0')
N = int(input())
for test_case in range(N):
    T = int(input())
   
    NA, NB = map(int,input().split())

    Aschedule = [list(input().strip().split()) for _ in range(NA)]
    Bschedule = [list(input().strip().split()) for _ in range(NB)]
    heap = []
    for fr,to in Aschedule:
        heapq.heappush(heap,(to,fr,"A"))
    for fr,to in Bschedule:
        heapq.heappush(heap,(to,fr,"B"))
    A = []
    B = []
    
    A_count = 0
    B_count = 0
    while heap:
        
        arrive, depart, station  = heapq.heappop(heap)
        if station == "A":
            idx = find(A,depart,0, len(A)-1)
            if idx == -1:
                A_count += 1
            else:
                del(A[idx])
            B.append(next_time(arrive,T))
        else:
            idx = find(B,depart,0, len(B) - 1)
           
            if idx == -1:
                B_count += 1
            else:
                del(B[idx])
            A.append(next_time(arrive,T))
        
    print("Case #"+str(test_case+1)+": " + str(A_count)+" "+ str(B_count))            
            