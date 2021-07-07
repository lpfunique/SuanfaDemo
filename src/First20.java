import java.util.ArrayList;
import java.util.Stack;

public class First20 {

    public static void main(String[] args) {

//        findNumberTest();
//        replaceBlankTest();
//        reverseLinkListTest();
//        rebuildBinaryTreeTest();
//        minNumberInRotateArrayTest();
//        fibonacciTest();
//        jumpFloorTest();
//        stackToQueueTest();
//        jumpFloorIITest();
//        rectCoverTest();
//        printNumTest();
//        powerTest();
//        reOrderArrayTest();
//        findKthToTailTest();
//        reverseListTest();
        mergeListTest();
//        findFirstNodeOfTwoListTest();
    }


    // 1. 在二维数组中查找某个数字
    public static void findNumberTest() {
        int target = 1;
        int[][] num = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        System.out.println(find(target, num));
    }

    public static boolean find(int target, int[][] array) {
        int row = array.length;
        int col = array[0].length;

        int i, j;
        for (i = row - 1, j = 0; i >= 0 && j <= col; ) {
            if (array[i][j] == target) {
                return true;
            }
            if (array[i][j] < target) {
                j++;
                continue;
            }
            if (array[i][j] > target) {
                i--;
                continue;
            }
        }
        return false;
    }


    // 2. 替换空格
    public static void replaceBlankTest() {
        String s = "I am lpf ";
//        System.out.println(replaceBlank(s));
        System.out.println(replaceBlank2(s));
    }

    public static String replaceBlank(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String replaceBlank2(String s) {
        char[] charArray = s.toCharArray();
        int spaceNum = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                spaceNum++;
            }
        }

        int newLength = charArray.length + spaceNum * 2;
        char[] newCharArray = new char[newLength];
        int newIndex = 0;
        for (int index = 0; index < charArray.length; index++) {
            if (charArray[index] != ' ') {
                newCharArray[newIndex++] = charArray[index];
            } else {
                newCharArray[newIndex++] = '%';
                newCharArray[newIndex++] = '2';
                newCharArray[newIndex++] = '0';
            }
        }
        return new String(newCharArray);
    }


    // 3. 从尾到头打印链表(Stack 或者链表的后序遍历)
    public static void reverseLinkListTest() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);

        head.next = second;
        second.next = third;

//        ArrayList<Integer> result = reverseLinkList(head);
//        ArrayList<Integer> result = reverseLinkList2(head);
//        ArrayList<Integer> result = reverseLinkList3(head);
        ArrayList<Integer> result = reverseLinkList4(head);

        for (Integer item : result) {
            System.out.println(item);
        }
    }

    public static ArrayList<Integer> reverseLinkList(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (!stack.empty()) {
            result.add(stack.pop());
        }
        return result;
    }

    public static ArrayList<Integer> reverseLinkList2(ListNode head) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (head != null) {
            result.addAll(reverseLinkList2(head.next));
            result.add(head.val);
        }
        return result;
    }

    public static ArrayList<Integer> reverseLinkList3(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        while (pre != null) {
            result.add(pre.val);
            pre = pre.next;
        }
        return result;
    }

    public static ArrayList<Integer> reverseLinkList4(ListNode head) {
        ListNode newHead = new ListNode(-1);
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = newHead.next;
            newHead.next = cur;
            cur = next;
        }

        newHead = newHead.next;
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (newHead != null) {
            result.add(newHead.val);
            newHead = newHead.next;
        }
        return result;
    }


    // 4. 重建二叉树
    public static void rebuildBinaryTreeTest() {

        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode root = rebuildBinaryTree(pre, in);
        printTreeNode(root);
    }

    public static TreeNode rebuildBinaryTree(int[] pre, int[] in) {
        // 已知前序和中序
        if (pre == null || pre.length == 0 || in == null || in.length == 0) {
            return null;
        }

        TreeNode root = constructBinaryTree(pre, 0, pre.length - 1,
                in, 0, in.length - 1);

        return root;
    }

    public static TreeNode constructBinaryTree(int[] pre, int startPre, int endPre,
                                               int[] in, int startIn, int endIn) {
        // 不符合条件，直接返回null
        if (startPre > endPre || startIn > endIn) {
            return null;
        }

        // 找到根节点
        TreeNode root = new TreeNode(pre[startPre]);
        // 这里的终止条件是 index <= endIn
        for (int index = startIn; index <= endIn; index++) {
            if (in[index] == pre[startPre]) {
                // 在中序列表中找到root节点，左侧为leftTree, 右侧为rightTree

                int leftTreeLength = index - startIn;
                int newEndPre = startPre + leftTreeLength;

                root.left = constructBinaryTree(pre, startPre + 1, newEndPre,
                        in, startIn, index - 1);

                root.right = constructBinaryTree(pre, newEndPre + 1, endPre,
                        in, index + 1, endIn);

                break;
            }
        }
        return root;
    }

    public static void printTreeNode(TreeNode root) {
        if (root == null) {
            return;
        }
        printTreeNode(root.left);
        printTreeNode(root.right);
        System.out.println(root.val);
    }


    // 5. 旋转数组的最小数
    public static void minNumberInRotateArrayTest() {
//        int[] array = {  5, 6, 1, 2, 3};
        int[] array = {1, 3, 5};
//        System.out.println(minNumberInRotateArray(array));
        System.out.println(minNumberInRotateArray2(array));
    }

    public static int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < max) {
                return array[i];
            }
        }
        return 0;
    }

    public static int minNumberInRotateArray2(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }
        int low = 0, high = array.length - 1;
        while (high - low > 1) {
            // 奇数取中间，偶数取上半部分最后一个
            int mid = low + (high - low) / 2;
            System.out.println("mid =" + array[mid]);
            if (array[mid] > array[high]) {
                low = mid;
            } else if (array[mid] < array[high]) {
                high = mid;
            } else {
                // 两个相等
                high--;
            }
        }
        // 未旋转时，array[low]最小，旋转后，array[high]最小
        return Math.min(array[low], array[high]);
    }


    // 6. 斐波那契数列
    public static void fibonacciTest() {
        System.out.println(fibonacci(5));
        System.out.println(fibonacci2(5));
    }

    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static int fibonacci2(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        // 注意结束条件，i <= n
        for (int i = 2; i <= n; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
        }
        return nums[n];
    }


    // 7. 青蛙跳台阶,可跳1，可跳2
    public static void jumpFloorTest() {
        System.out.println(jumpFloor(5));
        System.out.println(jumpFloor2(5));
    }

    public static int jumpFloor(int target) {
        if (target == 0) return 0;
        if (target == 1) return 1;
        if (target == 2) return 2;
        return jumpFloor(target - 1) + jumpFloor(target - 2);
    }

    public static int jumpFloor2(int target) {
        if (target == 0) return 0;
        if (target == 1) return 1;
        if (target == 2) return 2;
        int[] nums = new int[target + 1];
        nums[0] = 0;
        nums[1] = 1;
        nums[2] = 2;
        for (int i = 3; i <= target; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
        }
        return nums[target];
    }


    // 8. 两个栈实现一个队列
    public static void stackToQueueTest() {
        MockQueue queue = new MockQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.pop());
//        queue.push(3);
//        queue.push(4);
        System.out.println(queue.pop());
//        queue.push(5);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

    public static class MockQueue {

        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if (!stack2.empty()) {
                // 栈2不为空，直接pop
                return stack2.pop();
            } else {
                // 栈2为空，把栈1的内容全部push到栈2
                while (!stack1.empty()) {
                    stack2.push(stack1.pop());
                }
            }
            // 从栈2 pop
            if (!stack2.empty()) {
                return stack2.pop();
            } else {
                return Integer.MIN_VALUE;
            }
        }
    }


    // 9. 跳台阶变态版，一次可以跳1阶，或者2阶，或者n阶
    public static void jumpFloorIITest() {
        System.out.println(jumpFloorII(5));
        System.out.println(jumpFloorII2(5));
    }

    public static int jumpFloorII(int target) {
        if (target <= 0) {
            return 0;
        }
        int[] nums = new int[target];
        nums[0] = 1;
        for (int i = 0; i < target; i++) {
            int sum = 1;
            for (int j = 0; j < i; j++) {
                sum += nums[j];
            }
            nums[i] = sum;
        }
        return nums[target - 1];
    }

    public static int jumpFloorII2(int target) {
        // f(n) = f(1)+f(2)+....+f(n-1)+1;
        // f(n-1) = f(1)+f(2)+....+f(n-2)+1;
        // f(n) = 2 * f(n-1);
        return (int) Math.pow(2, target - 1);
    }


    // 10. 矩形覆盖,用2*1的矩形去覆盖 2*n的大矩形
    public static void rectCoverTest() {
        System.out.println(rectCover(5));
        System.out.println(rectCover2(5));
    }

    public static int rectCover(int target) {
        // 递归实现
        if (target <= 0) return 0;
        if (target == 1) return 1;
        if (target == 2) return 2;
        return rectCover(target - 1) + rectCover(target - 2);
    }

    public static int rectCover2(int target) {
        // 非递归实现
        if (target <= 0) return 0;
        if (target == 1) return 1;
        if (target == 2) return 2;
        int first = 1;
        int second = 2;
        int result = 0;
        for (int i = 3; i <= target; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }


    // 11. 二进制中1的个数
    public static void printNumTest() {

        int a = 2;

        int count = 0;
        for (int i = 0; i < 32; i++) {

            // >>> 无符号右移
            int t = (a & 0x80000000 >>> i) >>> (31 - i);
            if (t == 1) {
                count++;
            }

//            System.out.print(t);
        }
        System.out.println("count = " + count);


        System.out.println(NumberOf1(a));
        System.out.println(NumberOf12(a));
    }

    public static int NumberOf1(int n) {
        int num = 0, flag = 1;
        while (flag != 0) {
            // flag左移,避免num右移时的符号位判断
            if ((n & flag) != 0) {
                num++;
            }
            flag <<= 1;
        }
        return num;
    }

    public static int NumberOf12(int n) {
        int num = 0;
        while (n != 0) {
            num++;
            // 不断把最右边的1变成0
            n &= (n - 1);
        }
        return num;
    }


    // 12. 数值的整数次方 double类型的base, int类型的exponent, base和exponent不同时为0
    public static void powerTest() {
        System.out.println(power(2, 3));
        System.out.println(power(2, 0));
        System.out.println(power(2, -3));
    }

    public static double power(double base, int exponent) {
        if (exponent == 0) return 1;
        if (base == 0) return 0;
        boolean isNegative = false;
        if (exponent < 1) {
            isNegative = true;
            exponent = -exponent;
        }
        double result;
        if (exponent % 2 == 1) {
            result = base * power(base, exponent - 1);
        } else {
            double temp = power(base, exponent / 2);
            result = temp * temp;
        }
        return isNegative ? (1.0 / result) : result;

    }


    // 13. 调整数组顺序,奇数排列到偶数前面
    public static void reOrderArrayTest() {
        int[] nums = {1, 2, 3, 4, 5};
//        reOrderArray(nums);
        reOrderArray2(nums);

        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static void reOrderArray(int[] nums) {

        // 奇数个数
        int oddCount = 0;
        for (int x : nums) {
            if (x % 2 == 1) {
                oddCount++;
            }
        }

        int[] copy = nums.clone();
        int i = 0, j = oddCount;
        for (int num : copy) {
            if (num % 2 == 1) {
                nums[i++] = num;
            } else {
                // 插入偶数的位置
                nums[j++] = num;
            }
        }
    }

    public static void reOrderArray2(int[] nums) {
        // 已经摆好的奇数的个数
        int oddCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                int j = i;
                while (j > oddCount) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                    j--;
                }
                oddCount++;
            }
        }
    }


    // 14. 链表中倒数第k个节点
    public static void findKthToTailTest() {

        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);

        head.next = second;
        second.next = third;

        System.out.println(findKthToTail(head, 3).val);

    }

    public static ListNode findKthToTail(ListNode head, int k) {

        if (head == null || k < 0) {
            return null;
        }

        ListNode first = head;
        // 先让first走k步
        while (k != 0) {
            if (first == null) {
                return null;
            }
            first = first.next;
            k--;
        }
        while (first != null) {
            head = head.next;
            first = first.next;
        }
        return head;
    }


    // 15. 翻转链表
    public static void reverseListTest() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);

        head.next = second;
        second.next = third;

//        ListNode newHead = reverseList(head);
        ListNode newHead = reverseList2(head);

        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }

    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static ListNode reverseList2(ListNode head) {
        if (head == null) return null;
        ListNode cur = head;
        ListNode pre = new ListNode(-1);
        while (cur != null) {
            ListNode next = cur.next;
            // 只有这里不同
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }
        return pre.next;
    }


    // 16. 合并两个排序的链表
    public static void mergeListTest() {


        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);

        head.next = second;
        second.next = third;

        ListNode head2 = new ListNode(2);
        ListNode second2 = new ListNode(3);
        ListNode third2 = new ListNode(4);

        head2.next = second2;
        second2.next = third2;

//        ListNode newHead = mergeList(head,head2);
        ListNode newHead = mergeList2(head, head2);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }

    }

    public static ListNode mergeList(ListNode list1, ListNode list2) {
        ListNode newHead = new ListNode(-1);
        ListNode cur = newHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            System.out.println("-----cur.val=" + cur.val);
            cur = cur.next;
        }
        cur.next = list1 != null ? list1 : list2;
        return newHead.next;
    }

    public static ListNode mergeList2(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 != null ? list1 : list2;
        }
        if (list1.val <= list2.val) {
            list1.next = mergeList2(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeList2(list1, list2.next);
            return list2;
        }
    }


    // 17. 树的子结构(判断二叉树B是不是A的子树,空树不是子树)
    public static void hasSubTreeTest(){

    }
    public static boolean hasSubTree(TreeNode root1, TreeNode root2){
        if(root1 == null || root2 == null) return false;

        // 从当前根节点判断
        if(sameTree(root1, root2)){
            return true;
        } else{
            // 否则分别使用左子树和右子树与root2匹配
            return hasSubTree(root1.left, root2) || hasSubTree(root1.right, root2);
        }
    }

    public static boolean sameTree(TreeNode root1, TreeNode root2) {
        // 子树遍历结束时,返回true
        if (root2 == null) {
            return true;
        }
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                return sameTree(root1.left, root2.left) && sameTree(root1.right, root2.right);
            } else {
                return false;
            }
        }
        return false;
    }


    // 18. 二叉树的镜像
    public static void mirrorTest() {

    }
    public static void mirror(TreeNode root){
        if(root == null){
            return;
        } else {
            root = reverse(root);
        }
    }
    public static TreeNode reverse(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode left = reverse(root.right);
        TreeNode right = reverse(root.left);
        root.left = left;
        root.right = right;
        return root;
    }

    public static int lowestCommonAncestor(TreeNode root, int o1, int o2){
        return CommonAncestor(root, o1, o2).val;
    }
    public static TreeNode CommonAncestor(TreeNode root, int o1, int o2){
        if(root == null || root.val == o1 || root.val == o2){
            return root;
        }
        TreeNode left = CommonAncestor(root.left, o1, o2);
        TreeNode right = CommonAncestor(root.right, o1, o2);
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }
        return root;
    }

    // 19. 找两个链表的相交点
    public static void findFirstNodeOfTwoListTest(){

        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);

        ListNode head2 = new ListNode(4);
        ListNode second2 = new ListNode(5);

        head.next = second;
        second.next = third;


        head2.next = second2;
//        second2.next = third;

        ListNode firstNode = findFirstNodeOfTwoList(head, head2);
        if(firstNode!=null){
            System.out.println(firstNode.val);
        } else {
            System.out.println("null");
        }


    }

    public static ListNode findFirstNodeOfTwoList(ListNode headA, ListNode headB) {

        if(headA == null || headB == null){
            return null;
        }

        ListNode n1 = headA;
        ListNode n2 = headB;

        while(n1 != n2){
            n1 = n1 == null ? headB : n1.next;
            n2 = n2 == null ? headA : n2.next;
        }

        return n1;
    }


}
