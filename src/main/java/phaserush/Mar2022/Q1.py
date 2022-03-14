import math
from collections import Counter, OrderedDict
from functools import reduce


def calc_entropy(input_str):
    freq = {char: count / len(input_str) for (char, count) in Counter(input_str).items()}
    return reduce(lambda acc, curr: acc - curr * math.log2(curr), freq.values(), 0.0)


def compress(input_str, dict):
    output = ""
    sEnd = 1
    s = input_str[0: sEnd]
    while sEnd < len(input_str):
        c = input_str[sEnd: sEnd + 1]
        if s + c in dict:
            s += c
        else:
            output += str(dict[s])
            dict[s + c] = len(dict)
            s = c
        sEnd += 1
    output += str(dict[s])
    return output


if __name__ == '__main__':
    input_str = input("Enter the string to be compressed:\t")
    print(f"Entropy:\t{calc_entropy(input_str)}")
    dict = OrderedDict({"A": 0, "B": 1, "C": 2, "AB": 3, "BA": 4})
    compressed = compress(input_str, dict)
    print(f"Compressed:\t{compressed}")
    print(f"Ratio:\t{len(input_str) / len(compressed)}")
    print(f"Dictionary")
    for k, v in dict.items():
        print(k, v)
