'''
class MenuItem:
    def __init__(self, name, price):
        self.name = name
        self.price = price

    def __str__(self):
        return '{} 가격: {}'.format(self.name,self.price)
menu1 = MenuItem('햄버거', 4000)
menu2 = MenuItem('콜라', 1500)
menu3 = MenuItem('후렌치 후라이', 1500)
print(menu1)
print(menu2)
print(menu3)
'''

class SimpleCalculator:
    @staticmethod
    def add(param1, param2):
        return param1+param2
    @staticmethod
    def subtract(param1, param2):
        return param1-param2
    @staticmethod
    def multiply(param1, param2):
        return param1 * param2
    @staticmethod
    def divide(param1, param2):
        return param1/param2


