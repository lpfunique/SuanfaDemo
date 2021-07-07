import apple.laf.JRSUIUtils;

import javax.swing.plaf.TextUI;
import java.util.*;

/**
 * Created by lpf on 2021/6/17.
 */
public class Second20 {

    public static void main(String[] args) {

//        convertTreeTest();
//        moreThanOneHalfTest();
//        findLatestNumbersTest();
//        findMaxSumTest();
//        printMinNumberTest();
//        uglyNumberTest();
        firstAppearTest();
//        findNumsAppearOnceTest();
//        findContinuousSequenceTest();
//        findNumbersWithSumTest();
//        leftRotateStringTest();
//        reverseSentenceTest();
//        isContinuousTest();
//        lastRemainingTest();
//        sumTest();
//        addTest();
//        strToIntTest();
//        findFistAppearOnce();
//        entryNodeOfLoopTest();
//        deleteDuplicationTest();
//        judeTreeTest();
//        printTreeTest();
//        printTreeLinesTest();
//        serializeTreeTest();
//        findKthNodeTest();
//        cutRopeTest();

    }

    // 26. 二叉搜索树转成有序的双向链表(中序遍历树)
    public static void convertTreeTest() {

        TreeNode root = new TreeNode(4);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        root.left = two;
        root.right = five;
        two.left = one;
        two.right = three;

        TreeNode newHead = convert(root);

        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.right;
        }

    }

    static TreeNode head = null;
    static TreeNode end = null;

    public static TreeNode convert(TreeNode root) {
        // 使用递归,设置一个head, 一个end.

        invert(root);

        return head;
    }

    public static void invert(TreeNode node) {
        if (node == null) {
            return;
        }
        invert(node.left);
        if (end == null) {
            // 第一个结点
            head = node;
            end = node;
        } else {
            // 拼接其他结点
            end.right = node;
            node.left = end;
            end = node;
        }
        invert(node.right);
    }


    // 27. 字符串的排序

    // 28. 数组中出现次数超过一半的数字
    public static void moreThanOneHalfTest() {

        int[] array = {1, 2, 3, 4, 4, 4, 5};

        System.out.println(moreThanOneHalf(array));
    }

    public static int moreThanOneHalf(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int num = array[0];
        int count = 1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                count++;
            } else {
                count--;
            }

            // 使用新的数字标记
            if (count == 0) {
                num = array[i];
                count++;
            }
        }

        count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                count++;
            }
        }

        if (count > array.length / 2) {
            return num;
        } else {
            return 0;
        }
    }


    // 29. 最小的k个数
    public static void findLatestNumbersTest() {

        int[] inputs = {1, 3, 5, 2, 4, 6, 7, -1};
        ArrayList<Integer> list = findLatestNumbers(inputs, 4);
        for (Integer item : list) {
            System.out.println(item);
        }
    }

    public static ArrayList<Integer> findLatestNumbers(int[] inputs, int k) {
        if (inputs == null || k > inputs.length || k < 0) {
            return new ArrayList<>();
        }

        // 这里的有限队列
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(
                (o1, o2) -> o2 - o1
        );
        for (int num : inputs) {
            priorityQueue.add(num);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        return new ArrayList<>(priorityQueue);
    }


    // 30. 连续子数组的最大和
    public static void findMaxSumTest() {

        int[] array = {1, 2, -1, -2, 3, 4, 1};
        System.out.println(findMaxSum(array));
        System.out.println(findMaxSum2(array));

    }

    public static int findMaxSum(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int tempSum = 0;
            // 冒泡算法
            for (int j = i; j < array.length; j++) {
                tempSum = tempSum + array[j];
                if (tempSum > result) {
                    result = tempSum;
                }
            }
        }
        return result;
    }

    public static int findMaxSum2(int[] array) {
        int res = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            // 字母i下表对应的最大值
            max = Math.max(max + array[i], array[i]);
            // 和已保存的最大值做对比
            res = Math.max(max, res);
        }
        return res;
    }


    // 31. 整数中1出现的次数

    // 32. 把数组排成最小的数
    public static void printMinNumberTest() {

        int[] array = {3, 32, 321};
        System.out.println(printMinNumber(array));
    }

    public static String printMinNumber(int[] numbers) {
        String[] strs = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strs[i] = String.valueOf(numbers[i]);
        }
        // 对字符串排序
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s);
        }
        return res.toString();
    }


    // 33. 丑数(只包含质因子 2,3,5的数),1是第一个丑数
    public static void uglyNumberTest() {

        System.out.println(findNthUglyNumber(7));
    }

    public static int findNthUglyNumber(int index) {
        if (index == 0) {
            return 0;
        }
        int[] ugly = new int[index];
        ugly[0] = 1;
        int num_2 = 0, num_3 = 0, num_5 = 0;
        for (int i = 0; i < index; i++) {
            ugly[i] = getMin(ugly[num_2] * 2, ugly[num_3] * 3, ugly[num_5] * 5);
            if (ugly[i] / ugly[num_2] == 2) {
                num_2++;
            }
            if (ugly[i] / ugly[num_3] == 3) {
                num_3++;
            }
            if (ugly[i] / ugly[num_5] == 5) {
                num_5++;
            }
        }
        return ugly[index - 1];
    }

    public static int getMin(int a, int b, int c) {
        return a < (b < c ? b : c) ? a : (b < c ? b : c);
    }


    // 34. 第一次只出现一次的字符
    public static void firstAppearTest() {

        String str = "google";
        System.out.println(firstAppear(str));
    }

    public static int firstAppear(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }

        int[] nums = new int[58];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = -1;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int index = c - 'A';
            if (nums[index] >= 0) {
                nums[index] = 999999;
            } else {
                nums[index] = i;
            }
        }

        int index = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != -1 && nums[i] != 999999) {
                index = Math.min(index, nums[i]);
            }
        }
        return index == Integer.MAX_VALUE ? -1 : index;

    }


    // 35. 数组中的逆序对

    // 36. 两个链表的第一个公共节点
    public static void findFirstCommonNodeTest() {

    }

    public static ListNode findFirstCommonNode(ListNode head1, ListNode head2) {
        Set<ListNode> set = new HashSet<>();
        while (head1 != null) {
            set.add(head1);
            head1 = head1.next;
        }
        while (head2 != null) {
            if (set.contains(head2)) {
                return head2;
            }
            head2 = head2.next;
        }
        return null;
    }

    public static ListNode findFirstCommonNode2(ListNode root1, ListNode root2) {
        if (root1 == null || root2 == null) {
            return null;
        }
        ListNode head1 = root1;
        ListNode head2 = root2;
        // 拼接法
        while (head1 != head2) {
            head1 = (head1 == null) ? root2 : head1.next;
            head2 = (head2 == null) ? root1 : head2.next;
        }
        return head1;
    }


    // 37. 数字在升序数组中出现的次数

    // 40. 数组中只出现一次的数字,一共有两个数字
    public static void findNumsAppearOnceTest() {
        int[] array = {1, 2, 3, 4, 3, 2};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        findNumsAppearOnce(array, num1, num2);
        findNumsAppearOnce2(array, num1, num2);
    }

    public static void findNumsAppearOnce(int[] array, int num1[], int num2[]) {
        Map<Integer, Integer> maps = new HashMap<Integer, Integer>();
        if (array != null) {
            for (int n : array) {
                Integer num = maps.get(n);
                if (num == null) {
                    maps.put(n, 1);
                } else {
                    maps.put(n, num + 1);
                }
            }
        }

        int index = 1;
        for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
            if (entry.getValue() == 1) {
                if (index == 1) {
                    num1[0] = entry.getKey();
                    index++;
                } else {
                    num2[0] = entry.getKey();
                    break;
                }
            }
        }

        System.out.println("--- num1=" + num1[0] + " num2=" + num2[0]);

    }

    public static void findNumsAppearOnce2(int[] array, int num1[], int num2[]) {

        // A和B异或的结果
        int res = 0;
        for (int val : array) {
            res ^= val;
        }

        // temp保留了两个数最后一个不同的位
        // 负数的反码+1 = 补码
        int temp = res & (-res);

        // 保存和最后一个不同的位异或的结果
        int res1 = 0;
        for (int val : array) {
            if ((temp & val) != 0) {
                // 找到其中一个数
                res1 ^= val;
            }
        }

        // 满足条件的数字都出现两次,结果肯定就是只出现一次
        num1[0] = res1;

        // 求出另一个数
        num2[0] = res ^ res1;


        System.out.println("num1=" + num1[0] + " num2=" + num2[0]);
    }


    //41. 和为S的连续正数序列
    public static void findContinuousSequenceTest() {

        ArrayList<ArrayList<Integer>> arrays = findContinuousSequence(9);

        for (ArrayList<Integer> list : arrays) {
            System.out.println(list.toString());
        }
    }

    public static ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        for (int start = 1; start <= sum / 2; start++) {
            int tempSum = 0;
            System.out.println("开始新一轮");
            ArrayList<Integer> integers = new ArrayList<>();
            for (int j = start; j < sum; j++) {
                integers.add(j);
                tempSum = tempSum + j;
                if (tempSum == sum) {
                    results.add(integers);
                    break;
                } else if (tempSum > sum) {
                    System.out.println("结束这一轮");
                    break;
                }
            }
        }
        return results;
    }

    public static ArrayList<ArrayList<Integer>> findContinuousSequence2(int sum) {
        // 使用滑动窗口
        return null;
    }


    // 42. 和为S的两个数字,最小乘积
    public static void findNumbersWithSumTest() {

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};

        ArrayList<Integer> result = findNumbersWithSum(array, 8);
        ArrayList<Integer> result2 = findNumbersWithSum2(array, 8);
        ArrayList<Integer> result3 = findNumbersWithSum3(array, 8);

        for (Integer item : result) {
            System.out.println(item);
        }

        System.out.println("print result2");

        for (Integer item : result2) {
            System.out.println(item);
        }

        System.out.println("print result3");

        for (Integer item : result3) {
            System.out.println(item);
        }
    }

    public static ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
        // 暴力解法
        ArrayList<Integer> results = new ArrayList<>();
        long mutip = Long.MAX_VALUE;
        if (array == null || array.length < 2) {
            return results;
        }

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i; j < array.length; j++) {
                int tempI = array[i];
                int tempJ = array[j];

                if (tempI + tempJ == sum && tempI * tempJ < mutip) {
                    results.clear();
                    results.add(tempI);
                    results.add(tempJ);
                    mutip = tempI * tempJ;
                } else if (tempI + tempJ > sum) {
                    break;
                }
            }
        }
        return results;
    }

    public static ArrayList<Integer> findNumbersWithSum2(int[] array, int sum) {
        // hash方法
        ArrayList<Integer> results = new ArrayList<Integer>();
        long mutip = Long.MAX_VALUE;
        HashSet<Integer> set = new HashSet<>();

        if (array == null || array.length < 2) {
            return results;
        }

        for (int i = 0; i < array.length; i++) {
            int tempI = array[i];
            if (set.contains(sum - tempI) && tempI * (sum - tempI) < mutip) {
                results.clear();
                results.add(tempI);
                results.add(sum - tempI);
                mutip = tempI * (sum - tempI);
            } else {
                set.add(tempI);
            }
        }
        return results;
    }

    public static ArrayList<Integer> findNumbersWithSum3(int[] array, int sum) {
        // 双链表法(从两端向中间靠拢)

        ArrayList<Integer> results = new ArrayList<Integer>();
        long mutip = Long.MAX_VALUE;

        if (array == null || array.length < 2) {
            return results;
        }

        int left = 0, right = array.length - 1;
        while (left < right) {
            int tempLeft = array[left];
            int tempRight = array[right];
            if (tempLeft + tempRight == sum) {

                long tempMutip = tempLeft * tempRight;
                if (tempMutip < mutip) {
                    mutip = tempMutip;
                    results.clear();
                    results.add(tempLeft);
                    results.add(tempRight);
                }

                left++;
                right--;
            } else if (tempLeft + tempRight < sum) {
                left++;
            } else {
                right--;
            }
        }
        return results;
    }

    // 43. 左旋转字符串
    public static void leftRotateStringTest() {

        String str = "1234lpfComeon";
        System.out.println(leftRotateString(str, 4));
        System.out.println(leftRotateString2(str, 4));
    }

    public static String leftRotateString(String str, int n) {

        if (str == null || str.length() == 0) {
            return str;
        }

        int realN = n % str.length();

        StringBuilder sb = new StringBuilder();
        for (int i = realN; i < str.length(); i++) {
            sb.append(str.charAt(i));
        }
        for (int i = 0; i < realN; i++) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public static String leftRotateString2(String str, int n) {

        if (str == null || str.length() == 0) {
            return str;
        }

        int realN = n % str.length();

        char[] chars = str.toCharArray();
        char[] newChars = new char[chars.length];
        System.arraycopy(chars, realN, newChars, 0, chars.length - realN);
        System.arraycopy(chars, 0, newChars, chars.length - realN, realN);
        return String.valueOf(newChars);
    }


    // 44. 翻转单词序列
    public static void reverseSentenceTest() {

        String str = "You are a cool boy.";
//        System.out.println(reverseSentence(str));
//        System.out.println(reverseSentence2(str));
        System.out.println(reverseSentence3(str));

    }

    public static String reverseSentence(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        String[] array = str.split(" ");

        int left = 0, right = array.length - 1;
        while (left < right) {
            String tempLeft = array[left];
            String tempRight = array[right];
            String temp = tempLeft;
            array[left] = tempRight;
            array[right] = temp;
            left++;
            right--;
        }

        StringBuilder sb = new StringBuilder();
        for (String itemStr : array) {
            sb.append(itemStr);
            sb.append(" ");
        }
        String result = sb.toString().trim();
        return result;
    }

    public static String reverseSentence2(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        String[] array = str.split(" ");

        StringBuilder sb = new StringBuilder();
        for (int i = array.length - 1; i >= 0; i--) {
            sb.append(array[i]);
            sb.append(i == 0 ? "" : " ");
        }
        return sb.toString();
    }

    public static String reverseSentence3(String str) {
        // 借助堆栈
        if (str == null || str.length() == 0) {
            return str;
        }

        String[] array = str.split(" ");

        StringBuilder sb = new StringBuilder();

        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < array.length; i++) {
            stack.push(array[i]);
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            sb.append(" ");
        }

        return sb.toString().trim();
    }


    // 45. 扑克牌顺子,0表示任意值
    public static void isContinuousTest() {

        int[] numbers = {1, 3, 5, 0, 0};
        int[] numbers2 = {1, 3, 6, 0, 0};
        System.out.println(isContinuous(numbers));
        System.out.println(isContinuous(numbers2));
    }

    public static boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length < 5) {
            return false;
        }

        int min = 14;
        int max = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < numbers.length; i++) {
            int tempNumber = numbers[i];
            if (tempNumber != 0) {
                if (set.contains(tempNumber)) {
                    // 出现重复,返回false
                    return false;
                }
                set.add(tempNumber);
                max = Math.max(max, tempNumber);
                min = Math.min(min, tempNumber);
            }
        }
        return max - min < 5;
    }


    //46. 圆圈中最后剩下的士兵(m个士兵,报数n) ??????
    public static void lastRemainingTest() {

        System.out.println(lastRemaining(5, 3));
        System.out.println(LastRemaining_Solution(5, 3));
        System.out.println(LastRemaining_Solution2(5, 3));
    }

    public static int lastRemaining(int m, int n) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        List<Integer> list = new ArrayList<>();
        // m个士兵入圈
        for (int i = 0; i < m; i++) {
            list.add(i);
        }

        int count = -1;
        int index = -1;
        int result = 0;
        while (!list.isEmpty()) {
            count++;
            index++;
            if (index >= list.size()) {
                index = 0;
            }
            if (count == n - 1) {
                result = list.remove(index);
                count = -1;
                index--;
            }
        }
        return result;
    }

    public static int LastRemaining_Solution(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int count = -1;
        int index = -1;
        int result = 0;
        while (!list.isEmpty()) {
            count++;
            index++;
            if (index >= list.size()) {
                index = 0;
            }
            if (count == m - 1) {
                result = list.remove(index);
                count = -1;
                index--;
            }
        }
        return result;
    }

    public static int LastRemaining_Solution2(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = (result + m) % n;
        }
        return result;
    }


    //47. 求n-1的和
    public static void sumTest() {
        System.out.println(sum(10));
    }

    public static int sum(int n) {
        if (n >= 0) {
            return n + sum(n - 1);
        }
        return 0;
    }


    //48. 不使用加减乘除实现加法
    public static void addTest() {

        System.out.println(add(3, 4));
    }

    public static int add(int num1, int num2) {
        while (num2 != 0) {
            // 进位 等价于 与运算且左移一位
            int c = (num1 & num2) << 1;
            // 无进位 等价于 异或运算
            num1 = num1 ^ num2;
            num2 = c;
        }
        return num1;
    }


    // 49. 字符串转换成整数
    public static void strToIntTest() {

        System.out.println(strToInt("+12345"));
        System.out.println(strToInt("-0012345"));
        System.out.println(strToInt("+1ddd2345"));
    }

    public static int strToInt(String str) {
        int result = 0;
        boolean biggerThanZero = true;
        if (str == null || str.length() < 0) {
            return Integer.MIN_VALUE;
        }

        for (int i = 0; i < str.length(); i++) {
            char itemChar = str.charAt(i);
            if (itemChar == '+' && i == 0) {
                continue;
            } else if (itemChar == '-' && i == 0) {
                biggerThanZero = false;
                continue;
            } else {

                if (itemChar - '0' >= 0 && itemChar - '9' <= 0) {
                    result = result * 10 + (itemChar - '0');
                } else {
                    return 0;
                }
            }
        }
        return biggerThanZero ? result : -result;
    }


    // 50. 数组中重复的数字(找第一个),长度为n ,所有数字都在0~n-1
    public static void repeatNumberTest() {

    }

    public static int repeatNumber(int[] numbers) {
        if (numbers != null) {
            Set<Integer> set = new HashSet<Integer>();
            for (int i = 0; i < numbers.length; i++) {
                int tempNumber = numbers[i];
                if (set.contains(tempNumber)) {
                    return tempNumber;
                } else {
                    set.add(tempNumber);
                }
            }
        }
        return -1;
    }

    public static int repeatNumber2(int[] numbers) {


        if (numbers != null) {
            int[] nums = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                if (nums[numbers[i]] == 1) {
                    return numbers[i];
                } else {
                    nums[numbers[i]] = 1;
                }
            }
        }
        return -1;
    }

    public static int repeatNumber3(int[] numbers) {

        // 只适用于求解一个重复数字
        int i = 0;
        while (i < numbers.length) {
            int tempNumber = numbers[i];
            if (tempNumber == i) {
                i++;
                continue;
            }
            if (numbers[tempNumber] == tempNumber) {
                return tempNumber;
            }

            int tmp = tempNumber;
            tempNumber = numbers[tempNumber];
            numbers[tempNumber] = tmp;

        }
        return -1;
    }


    // 51. 构建乘积数组
    public static void multiplyTest() {

    }

    // 52. 正则表达式匹配

    // 53. 表达数值的字符串

    // 54. 字符流中的第一个不重复的字符
    public static void findFistAppearOnce() {

        FirstAppear appear = new FirstAppear();

        appear.insert('1');
        appear.insert('2');
        System.out.println(appear.firstAppearOnce());
        appear.insert('1');
        System.out.println(appear.firstAppearOnce());

    }

    public static class FirstAppear {
        int[] charCount = new int[128];
        Queue<Character> queue = new LinkedList<>();

        public void insert(char ch) {
            if (charCount[ch] == 0) {
                queue.add(ch);
            }
            charCount[ch]++;
        }

        public char firstAppearOnce() {
            Character character = null;
            char c = 0;
            while ((character = queue.peek()) != null) {
                c = character;
                if (charCount[c] == 1) {
                    return c;
                } else {
                    queue.remove();
                }
            }
            return '#';
        }
    }


    // 55. 链表中环的入口节点
    public static void entryNodeOfLoopTest() {

        ListNode root = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        root.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;

        System.out.println(entryNodeOfLoop(root).val);

    }

    public static ListNode entryNodeOfLoop(ListNode root) {
        if (root == null) {
            return root;
        }

        ListNode fast = root;
        ListNode slow = root;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                slow = root;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }


    // 56. 删除链表中重复的节点
    public static void deleteDuplicationTest() {
        ListNode root = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(4);

        root.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode result = deleteDuplication(root);
        ListNode result2 = deleteDuplication2(root);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

        while (result2 != null) {
            System.out.println(result2.val);
            result2 = result2.next;
        }

    }

    public static ListNode deleteDuplication(ListNode root) {
        if (root == null) {
            return root;
        }
        // todo 这里应该使用LinkedHashMap
        HashMap<Integer, Integer> maps = new HashMap<Integer, Integer>();
        while (root != null) {
            // 遍历一遍,把不重复的都标记为1
            if (!maps.containsKey(root.val)) {
                maps.put(root.val, 1);
            } else {
                maps.put(root.val, -1);
            }
            root = root.next;
        }

        ListNode newHead = new ListNode(-1);
        ListNode cur = newHead;
        for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
            if (entry.getValue() == 1) {
                cur.next = new ListNode(entry.getKey());
                cur = cur.next;
            }
        }
        return newHead.next;
    }

    public static ListNode deleteDuplication2(ListNode head) {
        ListNode newHead = new ListNode(-1);
        newHead.next = head;

        ListNode pre = newHead, cur = newHead;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                cur = cur.next;
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return newHead.next;
    }


    // 57. 二叉树的下一个节点
    public static void getNextNodeTest() {

    }

    public static List<TreeLinkNode> treeLinkNodes = new ArrayList<>();

    public static TreeLinkNode getNextNode(TreeLinkNode node) {
        if (node == null) {
            return node;
        }

        // 找到根节点
        TreeLinkNode root = node;
        while (root != null & root.parent != null) {
            root = root.parent;
        }
        // 根据根节点中序遍历
        inOrder(root);
        for (int i = 0; i < treeLinkNodes.size(); i++) {
            if (treeLinkNodes.get(i) == node) {
                // 判断是否越界
                return i + 1 < treeLinkNodes.size() ? treeLinkNodes.get(i + 1) : null;
            }
        }
        return null;
    }

    public static void inOrder(TreeLinkNode pNode) {
        if (pNode != null) {
            inOrder(pNode.left);
            treeLinkNodes.add(pNode);
            inOrder(pNode.right);
        }
    }

    public static TreeLinkNode getNextNode2(TreeLinkNode pNode) {
        // 右节点不为空,找右节点的最左子孙节点
        if (pNode.right != null) {
            TreeLinkNode pRight = pNode.right;
            while (pRight.left != null) {
                pRight = pRight.left;
            }
            return pRight;
        }

        // 右节点为空,当前节点是父节点的左节点,下一个就是其父节点
        if (pNode.parent != null && pNode.parent.left == pNode) {
            return pNode.parent;
        }

        // 右节点为空,当前节点是父节点的右节点,只能往上走
        if (pNode.parent != null) {
            TreeLinkNode parentNode = pNode.parent;
            // 判断父节点是不是同样也是右节点,如果是继续往上走,如果不是,则可以返回
            while (parentNode.parent != null
                    && parentNode.parent.right == parentNode) {
                parentNode = parentNode.parent;
            }
            return parentNode.parent;
        }
        return null;
    }


    // 58. 判读是否为对称的二叉树(找镜像树)
    public static void judeTreeTest() {

        TreeNode root = new TreeNode(4);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(1);
        TreeNode five = new TreeNode(2);

        root.left = one;
        root.left.left = two;

        root.right = three;
        root.right.right = five;

        System.out.println(isSymmetrical(root));
        System.out.println(isSymmerical2(root));


    }

    public static boolean isSymmetrical(TreeNode pRoot) {
        return pRoot == null || judeTree(pRoot.left, pRoot.right);
    }

    public static boolean judeTree(TreeNode left, TreeNode right) {

        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }

        return judeTree(left.left, right.right) && judeTree(left.right, right.left);
    }

    public static boolean isSymmerical2(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }

        LinkedList<TreeNode> leftNodes = new LinkedList<>();
        LinkedList<TreeNode> rightNodes = new LinkedList<>();
        leftNodes.add(pRoot.left);
        rightNodes.add(pRoot.right);

        while (!leftNodes.isEmpty() && !rightNodes.isEmpty()) {
            TreeNode leftNode = leftNodes.poll();
            TreeNode rightNode = rightNodes.poll();
            if (leftNode == null && rightNode == null) {
                continue;
            }
            if (leftNode == null || rightNode == null) {
                return false;
            }
            if (leftNode.val != rightNode.val) {
                return false;
            }
            //从左往右添加
            leftNodes.add(leftNode.left);
            leftNodes.add(leftNode.right);
            // 从右往左添加
            rightNodes.add(rightNode.right);
            rightNodes.add(rightNode.left);
        }
        return leftNodes.isEmpty() && rightNodes.isEmpty();
    }


    // 59. 之字型打印二叉树
    public static void printTreeTest() {

        TreeNode root = new TreeNode(4);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(5);
        TreeNode five = new TreeNode(6);

        root.left = one;
        root.left.left = two;

        root.right = three;
        root.right.right = five;

        ArrayList<ArrayList<Integer>> result = printTree(root);
        for (ArrayList item : result) {
            System.out.println(item.toString());
        }
    }

    public static ArrayList<ArrayList<Integer>> printTree(TreeNode pNode) {

        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();

        if (pNode == null) {
            return results;
        }

        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.offer(pNode);
        boolean reverse = true;
        while (!nodes.isEmpty()) {

            ArrayList<Integer> integers = new ArrayList<Integer>();
            // 遍历所有节点
            int size = nodes.size();
            for (int i = 0; i < size; i++) {

                TreeNode node = nodes.poll();
                if (reverse) {
                    integers.add(node.val);
                } else {
                    integers.add(0, node.val);
                }

                if (node.left != null) {
                    nodes.offer(node.left);
                }
                if (node.right != null) {
                    nodes.offer(node.right);
                }
            }

            if (integers.size() > 0) {
                results.add(integers);
            }
            reverse = !reverse;

        }

        return results;
    }


    // 60. 将二叉树打印成多行

    public static void printTreeLinesTest() {
        TreeNode root = new TreeNode(4);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(5);
        TreeNode five = new TreeNode(6);

        root.left = one;
        root.left.left = two;

        root.right = three;
        root.right.right = five;

        ArrayList<ArrayList<Integer>> result = printTreeLines(root);
        for (ArrayList item : result) {
            System.out.println(item.toString());
        }
    }

    public static ArrayList<ArrayList<Integer>> printTreeLines(TreeNode pNode) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        if (pNode == null) {
            return results;
        }

        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(pNode);

        while(!nodes.isEmpty()){

            ArrayList<Integer> integers = new ArrayList<>();

            int size = nodes.size();
            for(int i =0; i<size; i++){
                TreeNode node = nodes.poll();
                integers.add(node.val);

                if(node.left!=null){
                    nodes.add(node.left);
                }
                if(node.right!=null){
                    nodes.add(node.right);
                }
            }


            if(!integers.isEmpty()){
                results.add(integers);
            }
        }
        return results;
    }


    // 61. 序列化和反序列化二叉树
    public static void serializeTreeTest(){
        TreeNode root = new TreeNode(4);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(5);
        TreeNode five = new TreeNode(6);

        root.left = one;
        root.left.left = two;

        root.right = three;
        root.right.right = five;

        String str = serializeTree(root);
        System.out.println(str);
        TreeNode newNode = deserializeTree(str);

        ArrayList<ArrayList<Integer>> result = printTree(newNode);
        for (ArrayList item : result) {
            System.out.println(item.toString());
        }

    }
    public static int index = -1;
    public static String serializeTree(TreeNode pRoot){
        StringBuilder sb = new StringBuilder();
        if(pRoot == null){
            sb.append("#,");
            return sb.toString();
        }
        sb.append(pRoot.val + ",");
        sb.append(serializeTree(pRoot.left));
        sb.append(serializeTree(pRoot.right));
        return sb.toString();
    }
    public static TreeNode deserializeTree(String str){
        index ++;
        String[] strs = str.split(",");
        TreeNode node = null;
        if(!strs[index].equals("#")){
            node = new TreeNode(Integer.valueOf(strs[index]));
            node.left = deserializeTree(str);
            node.right = deserializeTree(str);
        }
        return node;
    }


    // 62. 二叉搜索树的第k个节点
    public static void findKthNodeTest(){
        TreeNode root = new TreeNode(4);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(5);
        TreeNode five = new TreeNode(6);

        root.left = one;
        root.left.left = two;

        root.right = three;
        root.right.right = five;

        System.out.println(kthNode(root, 3).val);
    }

    public static TreeNode kthNode(TreeNode pRoot, int k) {
        if (pRoot == null) {
            return pRoot;
        }

        // k -减去左子树的个数后 剩余的个数
        int leftNum = getNum(pRoot.left, k);
        if (leftNum == 1) {
            return pRoot;
        } else if (leftNum < 1) {
            // 在左子树中查找
            return kthNode(pRoot.left, k);
        } else {
            return kthNode(pRoot.right, leftNum - 1);
        }
    }

    public static int getNum(TreeNode root, int k) {
        if (root == null) {
            return k;
        }
        int leftNum = getNum(root.left, k);
        leftNum--;
        return getNum(root.right, leftNum);
    }


    // 63. 数据流中的中位数


    // 64. 滑动窗口的最大值


    // 67. 剪绳子
    public static void cutRopeTest(){
        System.out.println(cutRope(8));
    }
    public static int cutRope( int target){
        if(target <= 1){
            return target;
        }

        int[] nums = new int[target+1];
        nums[0] = 1;
        nums[1] = 1;

        for(int i =2; i<= target; i++){
            int max = i;
            for (int j = 0; j <= i / 2; j++) {
                int temp = nums[j] * nums[i-j];
                if(temp > max){
                    max = temp;
                }
            }
            nums[i] = max;
        }
        return nums[target];
    }


}
















