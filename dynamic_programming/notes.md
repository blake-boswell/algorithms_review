# Dynamic Programming
## Rod Cutting
- Given a rod of length n inches and a table of prices P_i for i = 1,2,...,n
- Determine the max revenue obtainable by cutting and selling the pieces

EXTENDED-BOTTOM-UP-CUT-ROD(p,n):
    let r[0..n] and s[0..n] be new arrays
    r[0] = 0
    for j = 1 to n:
        q = -infinity
        for i = 1 to j:
            if q < p[i] + r[j-1]:
                q = p[i] + r[j-1]
                s[j] = i
        r[j] = q
    return s,r

## Matrix Chain Mult
- Split the chain at k where i <= k < j
- A_1..n = A_1 * A_2 * ... * A_n
- m[i][j]: number of scalar multiplications needed to comput A_i..j
- m[i][j] =
    - if i == j: 0
    - if i < j: min i <= k < j { m[i][k] + m[k+1][j] + p_i-1 * p_k * p_j }
- s[i][j]: the value of k that we split the product A_i..j

MATRIX-CHAIN-ORDER(p):
    n = p.len -1
    let m[1..n][1..n] and s[1..n-1][2..n] be new tables
    for i = 1 to n:
        m[i][i] = 0
    for len = 2 to n:                                               // len: length of matrix chain
        for i = 1 to n - len + 1:
            j = i + len - 1
            m[i][j] = infinity
            for k = i to j -1:
                q = m[i][k] + m[k+1][j] + p_i-1 * p_k * p_j
                if q < m[i][j]:
                    m[i][j] = q
                    s[i][j] = k
    return m,s