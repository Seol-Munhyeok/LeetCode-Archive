class Solution {
    static final int MOD = 1_000_000_007;
    int[] fact, invFact;

    public int numberOfSequence(int n, int[] sick) {
        fact = new int[n + 1];
        invFact = new int[n + 1];

        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = (int) ((long) fact[i - 1] * i % MOD);
        }

        invFact[n] = (int) modPow(fact[n], MOD - 2);
        for (int i = n - 1; i >= 0; i--) {
            invFact[i] = (int) ((long) invFact[i + 1] * (i + 1) % MOD);
        }

        int totalLen = 0;
        int res = 1;

        int leftLen = sick[0];
        if (leftLen > 0) {
            totalLen += leftLen;
            res = (int) ((long) res * invFact[leftLen] % MOD);
        }

        for (int i = 1; i < sick.length; i++) {
            int len = sick[i] - sick[i - 1] - 1;

            if (len > 0) {
                totalLen += len;
                res = (int) ((long) res * invFact[len] % MOD);
                res = (int) ((long) res * modPow(2, len - 1) % MOD);
            }
        }

        int rightLen = n - sick[sick.length - 1] - 1;
        if (rightLen > 0) {
            totalLen += rightLen;
            res = (int) ((long) res * invFact[rightLen] % MOD);
        }

        res = (int) ((long) res * fact[totalLen] % MOD);

        return res;
    }

    private long modPow(long x, long y) {
        long res = 1;
        x %= MOD;

        while (y > 0) {
            if ((y & 1) == 1) {
                res = res * x % MOD;
            }

            x = x * x % MOD;
            y >>= 1;
        }

        return res;
    }
}