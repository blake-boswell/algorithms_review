# Greedy Algorithms
## Activity selector
- Assumes input activities are sorted by increasing finish time
- A: activity set output
- Add to the set if the start time is not earlier than the previously finished task

GREEDY-ACTIVITY-SELECTOR(s,f):
    n = s.len
    A = {a_1}
    k = 1
    for m = 2 to n:
        if s[m] >= f[k]:
            A = A U {a_m}
            k = m
    return A

## Huffman Codes