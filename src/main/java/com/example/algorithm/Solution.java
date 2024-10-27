package com.example.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int a = 0, b = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    a = i;
                    b = j;
                }
            }
        }
        return new int[]{a, b};
    }

    public int[] twoSum_2(int[] nums, int target) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            // target - nums[i]
            int x = target - cur;
            if (result.containsKey(x)) {
                return new int[]{result.get(x), i};
            }
            result.put(cur, i);
        }
        return null;

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode result = dummy;
        int carry = 0;


        while (l1 != null || l2 != null) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;

            int sum = num1 + num2 + carry;
            carry = sum / 10;

            int x = sum % 10;

            result.next = new ListNode(x);
            result = result.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

        }
        if (carry == 1) result.next = new ListNode(1);

        return dummy.next;
    }

    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int max = 0, first = 0, last = 0;

        while (last < s.length()) {
            if (!set.contains(s.charAt(last))) {
                set.add(s.charAt(last));
                last++;
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(first));
                first++;

            }
        }
        return max;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int i = 0, j = 0;

        int[] array = new int[n + m];

        int cnt = 0;

        while (i < n || j < m) {

            if (i >= n) {
                array[cnt] = nums2[j];
                j++;
                cnt++;
            } else if (j >= m) {
                array[cnt] = nums1[i];
                i++;
                cnt++;
            } else if (nums1[i] > nums2[j]) {
                array[cnt] = nums2[j];
                cnt++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                array[cnt] = nums1[i];
                cnt++;
                i++;
            } else {
                array[cnt] = nums1[i];
                i++;
                cnt++;
                array[cnt] = nums2[j];
                j++;
                cnt++;
            }
        }

        if (array.length % 2 == 0) {
            int index = (n + m) / 2;
            return (double) (array[index - 1] + array[index]) / 2;
        } else {
            int index = (n + m) / 2;
            return array[index];
        }
    }

    public double findMedianSortedArrays_2(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;

        // Ensure nums1 is the smaller array for simplicity
        if (n1 > n2)
            return findMedianSortedArrays_2(nums2, nums1);

        int low = 0, high = n1;
        int left = (n1 + n2 + 1) / 2;

        while (low <= high) {
            int partition1 = (low + high) / 2;
            int partition2 = left - partition1;

            int left1 = partition1 == 0 ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int right1 = partition1 == n1 ? Integer.MAX_VALUE : nums1[partition1];
            int left2 = partition2 == 0 ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int right2 = partition2 == n2 ? Integer.MAX_VALUE : nums2[partition2];

            if (left1 <= right2 && left2 <= right1) {
                if ((n1 + n2) % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                } else {
                    return Math.max(left1, left2);
                }
            } else if (left1 > right2) {
                high = partition1 - 1;
            } else {
                low = partition1 + 1;
            }
        }

        return 0;
    }

    public String longestPalindrome_dp(String s) {
        if (s.length() <= 1) {
            return s;
        }

        int maxLen = 1;
        int start = 0;
        int end = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); ++i) {
            dp[i][i] = true;
            for (int j = 0; j < i; ++j) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    if (i - j + 1 > maxLen) {
                        maxLen = i - j + 1;
                        start = j;
                        end = i;
                    }
                }
            }
        }

        return s.substring(start, end + 1);
    }

    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }


        int maxlen = 0;

        String result = "";

        for (int i = 0; i < s.length(); i++) {
            int left = i, right = i;

            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) == s.charAt(right)) {
                    String substring = s.substring(left, right + 1);
                    if (maxlen < substring.length()) {
                        maxlen = substring.length();
                        result = substring;
                    }
                    left--;
                    right++;
                } else {
                    break;

                }
            }

        }

        for (int i = 0; i < s.length(); i++) {
            int left = i, right = i + 1;

            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) == s.charAt(right)) {
                    String substring = s.substring(left, right + 1);
                    if (maxlen < substring.length()) {
                        maxlen = substring.length();
                        result = substring;
                    }
                    left--;
                    right++;

                } else {
                    break;
                }
            }
        }

        return result;
    }

    public String convert(String s, int numRows) {

        String[] word = new String[numRows];

        if (numRows == 1 || s.length() == 1) {
            return s;
        }

        for (int i = 0; i < numRows; i++) {
            word[i] = "";
        }
        int row = 0;
        boolean dir = true;

        for (int i = 0; i < s.length(); i++) {
            word[row] += s.charAt(i);

            if (dir && row >= numRows - 1) {
                dir = false;
                row--;
            } else if (!dir && row == 0) {
                dir = true;
                row++;
            } else if (dir) {
                row++;
            } else if (!dir) {
                row--;
            }
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < numRows; i++) {
            result.append(word[i]);
        }

        return result.toString();
    }

    public int reverse(int x) {
        int temp = x;

        int cnt = 1;

        while (Math.abs(x) >= 10) {
            x /= 10;
            cnt *= 10;
        }

        int result = 0;

        while (Math.abs(temp) > 0) {

            if (temp >= 0 && result > Integer.MAX_VALUE - (temp % 10) * cnt

            ) {
                return 0;
            }

            if (temp < 0 && result < Integer.MIN_VALUE - (temp % 10) * cnt) {
                return 0;
            }
            result += (temp % 10) * cnt;

            temp /= 10;
            cnt /= 10;
        }


        return result;

    }

    public int reverse_string(int x) {
        String stringX = Integer.toString(Math.abs(x));

        StringBuilder stringBuilder = new StringBuilder(stringX);

        StringBuilder reverse = stringBuilder.reverse();

        boolean isnegative = x < 0 ? true : false;

        try {
            if (isnegative) {
                return -Integer.parseInt(reverse.toString());
            } else {
                return Integer.parseInt(reverse.toString());
            }
        } catch (NumberFormatException e) {
            return 0;
        }
    }

//    public int myAtoi(String s) {
//
//    }

    public boolean isPalindrome(int x) {
        int temp = x;
        String reverseNum = "";

        if (x < 0) {
            return false;
        }

        while (temp > 0) {
            int num = temp % 10;
            temp /= 10;

            reverseNum += num;
        }

        try {
            if (x == 0 || x == Integer.parseInt(reverseNum)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public boolean isMatch(String s, String p) {

        boolean result = false;

        for (int i = 0; i < p.length(); i++) {
            boolean istrue = true;

            int sindex = 0;
            int pindex = i;

            while (sindex < s.length() && pindex < p.length()) {
                if (p.charAt(pindex) == '*') {

                    while (true) {
                        if (!(sindex < s.length() && pindex < p.length())) break;

                        if (p.charAt(pindex - 1) == s.charAt(sindex) || p.charAt(pindex - 1) == '.') {
                            sindex++;
                        } else {
                            break;
                        }
                    }

                    pindex++;
                } else if (p.charAt(pindex) == '.') {
                    sindex++;
                    pindex++;
                } else {
                    if (p.charAt(pindex) == s.charAt(sindex)) {
                        sindex++;
                        pindex++;
                    } else {
                        if ((pindex != p.length() - 1) && p.charAt(pindex + 1) == '*') {
                            sindex++;
                            pindex++;
                        } else {
                            istrue = false;
                            break;
                        }

                    }
                }
            }
            if (!(sindex >= s.length() && pindex >= p.length())) {
                istrue = false;
            }

            if (istrue) {
                return istrue;
            }
        }

        return result;
    }

    public boolean isMatch_2(String s, String p) {

        boolean istrue = true;

        int sindex = 0;
        int pindex = 0;

        while (sindex < s.length() && pindex < p.length()) {
            if (p.charAt(pindex) == '*') {
                if (pindex == 0) {
                    istrue = false;
                    break;
                } else {
                    while (true) {
                        if (!(sindex < s.length() && pindex < p.length())) break;

                        if (p.charAt(pindex - 1) == s.charAt(sindex) || p.charAt(pindex - 1) == '.') {
                            sindex++;
                        } else {
                            break;
                        }
                    }
                }
                pindex++;
            } else if (p.charAt(pindex) == '.') {
                sindex++;
                pindex++;
            } else {
                if (p.charAt(pindex) == s.charAt(sindex)) {
                    sindex++;
                    pindex++;
                } else {
                    istrue = false;
                    break;
                }
            }
        }

        if (!(sindex >= s.length() && pindex >= p.length())) {
            istrue = false;
        }

        return istrue;
    }

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int maxStore = 0;

        while (left < right) {
            int width = right - left;
            int length = Math.min(height[left], height[right]);

            maxStore = Math.max(maxStore, width * length);

            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }

        return maxStore;
    }

    public String intToRoman(int num) {
        String result = "";

        int count = 1;

        int temp = num;

        while (temp >= 10) {
            temp /= 10;
            count *= 10;
        }
        if (count == 1000) {
            int x = num / 1000;

            for (int i = 0; i < x; i++) {
                result += "M";
            }
            num -= x * count;
            count /= 10;
        }
        if (count == 100) {
            int x = num / 100;

            if (x != 0) {
                if (1 <= x && x <= 3) {
                    for (int i = 0; i < x; i++) {
                        result += "C";
                    }
                } else if (6 <= x && x <= 8) {
                    result += "D";
                    for (int i = 0; i < x - 5; i++) {
                        result += "C";
                    }
                } else if (x == 4) {
                    result += "CD";
                } else if (x == 9) {
                    result += "CM";
                } else {
                    result += "D";
                }
            }

            num -= x * count;
            count /= 10;
        }

        if (count == 10) {
            int x = num / 10;

            if (x != 0) {
                if (1 <= x && x <= 3) {
                    for (int i = 0; i < x; i++) {
                        result += "X";
                    }
                } else if (6 <= x && x <= 8) {
                    result += "L";
                    for (int i = 0; i < x - 5; i++) {
                        result += "X";
                    }
                } else if (x == 4) {
                    result += "XL";
                } else if (x == 9) {
                    result += "XC";
                } else {
                    result += "L";
                }
            }


            num -= x * count;
            count /= 10;
        }

        if (count == 1) {
            int x = num;

            if (x != 0) {
                if (1 <= x && x <= 3) {
                    for (int i = 0; i < x; i++) {
                        result += "I";
                    }
                } else if (6 <= x && x <= 8) {
                    result += "V";
                    for (int i = 0; i < x - 5; i++) {
                        result += "I";
                    }
                } else if (x == 4) {
                    result += "IV";
                } else if (x == 9) {
                    result += "IX";
                } else {
                    result += "V";
                }
            }
        }


        return result;
    }

    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();

        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int sum = 0;
        int index = 0;

        while (index < s.length()) {
            if (index < s.length() - 1 && map.containsKey(s.substring(index, index + 2))) {
                sum += map.get(s.substring(index, index + 2));
                index++;
                index++;
            } else {
                sum += map.get(s.substring(index, index + 1));
                index++;
            }
        }
        return sum;
    }

    public String longestCommonPrefix(String[] strs) {

        int count = 0;

        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                try {
                    if (!(strs[0].charAt(i) == strs[j].charAt(i))) {
                        if (count == 0) {
                            return "";
                        } else {
                            return strs[0].substring(0, count);
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    if (count == 0) {
                        return "";
                    } else {
                        return strs[0].substring(0, count);
                    }
                }
            }
            count++;

        }

        return strs[0].substring(0, count);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestCommonPrefix(new String[]{"dog", "do", "d"}));


    }

}
