arr = [True] * (10001)

arr[0] = False
idx = 1
while idx < 10001:
    if arr[idx]:
        print(idx)
        number = idx
        idx += 1
        while number < 10001:
            this_turn = str(number)
            for i in this_turn:
                number += int(i)
            if number <= 10000:
                arr[number] = False
        
    else:
        idx += 1