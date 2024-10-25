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

    public String longestPalindrome(String s) {


        int length = s.length();

        if (length == 1) {
            return s;
        }

        int left = 0, right = 0;

        int max = 0;

        String result = "";


        for (int i = 0; i < length; i++) {
            left = i;
            right = i;
            while (left >= 0 && right < length) {
                if (s.charAt(left) == s.charAt(right)) {
                    if (max < s.substring(left, right + 1).length()) {
                        result = s.substring(left, right + 1);
                        max = s.substring(left, right + 1).length();
                    }
                    left--;
                    right++;

                } else {
                    break;
                }
            }

            left = i;
            right = i+1;
            while (left >= 0 && right < length) {
                if (s.charAt(left) == s.charAt(right)) {
                    if (max < s.substring(left, right + 1).length()) {
                        result = s.substring(left, right + 1);
                        max = s.substring(left, right + 1).length();
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

    public String longestPalindrome_2(String s) {
        if(s.length() == 1){
            return s;
        }



        int maxlen = 0;

        String result = "";

        for (int i = 0; i < s.length(); i++) {
            int left = i, right = i;

            while(left >= 0 && right < s.length()){
                if(s.charAt(left) == s.charAt(right)){
                    String substring = s.substring(left, right + 1);
                    if(maxlen < substring.length()){
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
            int left = i, right = i+1;

            while(left >= 0 && right < s.length()){
                if(s.charAt(left) == s.charAt(right)){
                    String substring = s.substring(left, right + 1);
                    if(maxlen < substring.length()){
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


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome_2("babded"));
    }

}
