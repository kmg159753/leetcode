package com.example.algorithm;

import java.util.*;

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

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return List.of();
        }


        Map<Integer, List<Character>> map = new HashMap<>();
        List<String> result = new ArrayList<>();


        map.put(2, List.of('a', 'b', 'c'));
        map.put(3, List.of('d', 'e', 'f'));
        map.put(4, List.of('g', 'h', 'i'));
        map.put(5, List.of('j', 'k', 'l'));
        map.put(6, List.of('m', 'n', 'o'));
        map.put(7, List.of('p', 'q', 'r', 's'));
        map.put(8, List.of('t', 'u', 'v'));
        map.put(9, List.of('w', 'x', 'y', 'z'));

        recursion(digits, new StringBuilder(), result, 0, map);

        return result;
    }

    private void recursion(String digits, StringBuilder comb, List<String> result, int inputIndex, Map<Integer, List<Character>> map) {
        if (inputIndex == digits.length()) {// 최대 depth까지 선별했으면 return
            result.add(comb.toString());
            return;
        }

        int i = Character.getNumericValue(digits.charAt(inputIndex));// 현재 depth의 값 추출
        List<Character> characters = map.get(i);// 현재 depth의 알파벳 리스트

        for (Character character : characters) {
            comb.append(character);
            inputIndex++;
            recursion(digits, comb, result, inputIndex, map);
            comb.deleteCharAt(comb.length() - 1);
            inputIndex--;
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        generateParenthesis_backtracking(n, 0, 0, result, stringBuilder);

        return result;


    }

    private void generateParenthesis_backtracking(int n, int left, int right, List<String> result, StringBuilder stringBuilder) {
        if (stringBuilder.length() == 2 * n) {
            result.add(stringBuilder.toString());
            return;
        }

        if (left < n) {
            stringBuilder.append('(');
            generateParenthesis_backtracking(n, left + 1, right, result, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        if (left > right) {
            stringBuilder.append(')');
            generateParenthesis_backtracking(n, left, right + 1, result, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

    }

    public void solveSudoku(char[][] board) {

        sudokuBacktracking(board, 0, 0);
    }

    private boolean sudokuBacktracking(char[][] board, int row, int col) {
        if (row == board.length) {
            return true;
        }

        if (col == board.length) {
            return sudokuBacktracking(board, row + 1, 0);
        }
        if (board[row][col] != '.') {
            return sudokuBacktracking(board, row, col + 1);
        }

        for (char i = '1'; i <= '9'; i++) {
            if (isValid(board, row, col, i)) {
                board[row][col] = i;
                if (sudokuBacktracking(board, row, col + 1)) {
                    return true;
                }

                board[row][col] = '.';
            }
        }

        return false;
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if (i != col && board[row][i] == num) {
                return false;
            }
            if (i != row && board[i][col] == num) {
                return false;
            }
            int boxrow = (row / 3) * 3;
            int boxcol = (col / 3) * 3;

            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (board[boxrow + j][boxcol + k] == num) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    lists.add(List.of((nums[i]), nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;


                } else if ((nums[i] + nums[left] + nums[right] < 0)) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return lists;
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    left++;
                    if (Math.abs(target - sum) < min) {
                        min = Math.abs(target - sum);
                        result = sum;
                    }
                } else {
                    right--;
                    if (Math.abs(sum - target) < min) {
                        min = Math.abs(target - sum);
                        result = sum;
                    }
                }
            }
        }

        return result;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1, right = nums.length - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];


                    if (sum == target) {
                        result.add(List.of(nums[i], nums[j], nums[left], nums[right]));

                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public int search(int[] nums, int target) {
        int index = nums.length / 2;
        int start = 0;
        int end = nums.length - 1;

        while (index >= start && index <= end && start <= end) {

            if (nums[index] >= nums[start]) {//왼쪽 정렬?
                if (nums[start] <= target && nums[index] >= target) { // 왼쪽에 포함?
                    end = index;
                    index = start + (end - start) / 2;
                    while (index >= start && index <= end && start <= end) {
                        if (nums[index] < target) {
                            start = index + 1;
                            index = start + (end - start) / 2;
                        } else if (nums[index] > target) {
                            end = index - 1;
                            index = start + (end - start) / 2;
                        } else {
                            return index;
                        }
                    }
                    return -1;
                } else { // 포함 안되면 오른쪽
                    start = index + 1;
                    index = start + (end - start) / 2;
                }
            } else {// 오른쪽 정렬
                if (nums[end] >= target && nums[index] <= target) { // 오른쪽에 포함?
                    start = index;
                    index = start + (end - start) / 2;
                    while (index >= start && index <= end && start <= end) {
                        if (nums[index] < target) {
                            start = index + 1;
                            index = start + (end - start) / 2;
                        } else if (nums[index] > target) {
                            end = index - 1;
                            index = start + (index - start) / 2;
                        } else {
                            return index;
                        }
                    }
                    return -1;
                } else { // 포함 안되면 왼쪽
                    end = index - 1;
                    index = start + (end - start) / 2;
                }
            }
        }
        return -1;
    }

    public int[] searchRange(int[] nums, int target) {
        int left = binarySearch(nums, target, true);
        int right = binarySearch(nums, target, false);

        return new int[]{left, right};
    }

    private int binarySearch(int[] nums, int target, boolean isLeft) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                index = mid;
                if (isLeft) right = mid - 1;
                else left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return index;

    }

    public int jump(int[] nums) {
        int max = 0; // 지금 검사하고 있는 것중 최대로 움직일 수 있는 위치
        int posjump = 0;
        int jump = 0; // result

        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);

            if (i == posjump) {
                jump++;
                posjump = max;
            }
        }

        return jump;
    }

    public boolean canJump(int[] nums) {
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            if (max < i) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }

        return true;
    }

    public boolean canJump2(int[] nums) {
        int goal = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            int nxtindex = i + nums[i];
            if (nxtindex >= goal) {
                goal = i;
            }
        }

        return goal == 0;
    }

    public long minEnd(int n, int x) {
        long result = x;
        long remaining = n - 1;
        long position = 1;

        while (remaining != 0) {
            if ((x & position) == 0) {
                result |= (remaining & 1) * position;
                remaining >>= 1;
            }
            position <<= 1;
        }

        return result;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorderHelper(node.left, result); // Visit left subtree
        result.add(node.val);             // Visit current node
        inorderHelper(node.right, result); // Visit right subtree
    }

    public boolean isValidBST(TreeNode root) {
        boolean result = true;
        return isValidBSTDFS(root, null, null);

    }

    private boolean isValidBSTDFS(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }
        if (lower != null && lower >= node.val) {
            return false;
        }

        if (upper != null && upper <= node.val) {
            return false;
        }


        if (!isValidBSTDFS(node.left, lower, node.val)) {
            return false;
        }
        if (!isValidBSTDFS(node.right, node.val, upper)) {
            return false;
        }

        return true;
    }

    TreeNode prev = null, first = null, second = null;

    void inorder(TreeNode root) {
        if (root == null)
            return;
        inorder(root.left);
        if (prev != null && root.val < prev.val) {
            if (first == null)
                first = prev;
            second = root;
        }
        prev = root;
        inorder(root.right);
    }

    public void recoverTree(TreeNode root) {
        if (root == null)
            return;
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    public void printInorder(TreeNode node) {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return isSaneTreeBackTracking(p, q);
    }

    private boolean isSaneTreeBackTracking(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null && q != null) {
            return false;
        }
        if (p != null && q == null) {
            return false;
        }

        if (!isSaneTreeBackTracking(p.left, q.left)) {
            return false;
        }


        if (p.val != q.val) {
            return false;
        }

        if (!isSaneTreeBackTracking(p.right, q.right)) {
            return false;
        }

        return true;
    }

    public TreeNode invertTree(TreeNode root) {
        invertTreeBT(root);

        return root;
    }

    private void invertTreeBT(TreeNode node) {
        if (node == null) {
            return;
        }

        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        invertTreeBT(node.left);
        invertTreeBT(node.right);
    }

    private TreeNode other;

    public boolean isSymmetric(TreeNode root) {
        return isSymmetricBT(root.left, root.right);
    }

    private boolean isSymmetricBT(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }

        if (!isSymmetricBT(root1.left, root2.right)) {
            return false;
        }

        return true;
    }

    private int max = 0;

    public int maxDepth(TreeNode root) {


        countDepth(root, 1);

        return max;
    }

    private void countDepth(TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        if (depth > max) {
            max = depth;
        }

        countDepth(node.left, depth + 1);
        countDepth(node.right, depth + 1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        pathSumBT(list, root, 0, targetSum, result);

        return result;
    }

    private void pathSumBT(List<Integer> list, TreeNode node, int sum, int targetSum, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        list.add(node.val);
        sum += node.val;

        if (node.left == null && node.right == null && sum == targetSum) {
            result.add(new ArrayList<>(list));
        }

        pathSumBT(list, node.left, sum, targetSum, result);


        pathSumBT(list, node.right, sum, targetSum, result);
        list.remove(list.size() - 1);


    }

    private TreeNode prev1 = null;

    public void flatten(TreeNode root) {

        flattenBT(root);
    }

    private void flattenBT(TreeNode node) {
        if (node == null) {
            return;
        }

        flattenBT(node.right);
        flattenBT(node.left);

        node.right = prev1;
        node.left = null;
        prev1 = node;

    }


    public class ListNode2 {
        int val;
        ListNode2 next;

        ListNode2() {
        }

        ListNode2(int val) {
            this.val = val;
        }

        ListNode2(int val, ListNode2 next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode2 removeNthFromEnd(ListNode2 head, int n) {
        ListNode2 dummy = new ListNode2(0);
        dummy.next = head;

        ListNode2 first = dummy;
        ListNode2 second = dummy;

        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;

        return dummy;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            Character now = s.charAt(i);

            if (now == '(' || now == '{' || now == '[') {
                stack.push(now);
            } else {
                if (
                        stack.isEmpty() ||
                        (now == ')' && stack.pop() != '(') ||
                        (now == '}' && stack.pop() != '{') ||
                        (now == ']' && stack.pop() != '[')
                ) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        boolean valid = solution.isValid("[");

        System.out.println(valid);

    }


}
