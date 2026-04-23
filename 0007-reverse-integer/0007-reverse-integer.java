class Solution {
    public int reverse(int x) {
        int rev = 0;
        int digit = 0;

        while (x != 0) {
            digit = x % 10;

            // Check Overflow
            if (rev > Integer.MAX_VALUE / 10 ||
                rev == Integer.MAX_VALUE / 10 && digit > 7) return 0;
            
            if (rev < Integer.MIN_VALUE / 10 ||
                rev == Integer.MAX_VALUE / 10 && digit > 8) return 0;
                
            rev *= 10;
            rev += digit;
            x /= 10;
        }

        return rev;
    }
}