import java.util.LinkedList;
import java.util.Queue;

public class Important {

    public static void main(String[] args) {

//        isExistCycleTest();
//        reverseListTest();
        testLowestCommonAncestor();
    }

    // 1. 合并两个链表
    public static ListNode combineTwoList(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val) {
            l1.next = combineTwoList(l1.next, l2);
            return l1;
        } else {
            l2.next = combineTwoList(l1, l2.next);
            return l2;
        }
    }

    // 2. 找到两个链表的相交点
    public static ListNode findFirstNode(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return null;
        }
        ListNode l1 = list1;
        ListNode l2 = list2;
        while (l1 != l2) {
            l1 = l1 == null ? list2 : l1.next;
            l2 = l2 == null ? list1 : l2.next;
        }
        return l1;
    }


    // 3. 是否存在环&环的起点
    public static void isExistCycleTest() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);

        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);

        head.next = second;
        second.next = third;
        third.next = four;
        four.next = five;
        five.next = third;

        System.out.println(isExistCycle(head));

        System.out.println(findFirstCycleNode(head).val);
    }

    // 判断是否存在环
    public static boolean isExistCycle(ListNode list1) {

        ListNode fast = list1;
        ListNode slow = list1;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                System.out.println(fast.val);
                return true;
            }
        }

        return false;
    }

    // 找到环的入口点
    public static ListNode findFirstCycleNode(ListNode list1) {
        ListNode fast = list1;
        ListNode slow = list1;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                slow = list1;
                break;
            }
        }
        while (fast != null && fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }


    // 4. 翻转链表
    public static void reverseListTest() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);

        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);

        head.next = second;
        second.next = third;
        third.next = four;
        four.next = five;
//        ListNode newHead = reverseList(head);
//        ListNode newHead = reverseList2(head);
        ListNode newHead = reverseKGroup(head, 2);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    public static ListNode reverseList(ListNode list1) {
        ListNode cur = list1;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 递归翻转
    public static ListNode reverseList2(ListNode list1) {
        if (list1 == null || list1.next == null) {
            return list1;
        }
        ListNode last = reverseList2(list1.next);
        list1.next.next = list1;
        list1.next = null;
        return last;
    }


    // 5. K个一组翻转链表
    public static ListNode reverseKGroup(ListNode list1, int k) {
        if (list1 == null) return null;
        // 区间[a,b)包含k个待反转元素
        ListNode a, b;
        a = b = list1;
        for (int i = 0; i < k; i++) {
            // 不足k个,不需要反转
            if (b == null) return list1;
            b = b.next;
        }
        // 反转前k个元素
        ListNode newHead = reverse(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    // 反转区间[a,b)
    public static ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, next;
        pre = null;
        cur = a;
        while (cur != b) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 6. 最近公共节点
    public static void testLowestCommonAncestor() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;

        TreeNode result = lowestCommonAncestor(node1, node4, node6);
        System.out.println("结果=" + result.val);
    }

    // 普通二叉树
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // base case
        if (root == null) return null;
        if (p == root || q == root) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 后序遍历
        if (left != null && right != null) {
            System.out.println("11");
            return root;
        }

        System.out.println("33" + "left = " + left + " right = " + right);
        return left == null ? right : left;
    }

    // 二叉搜索树
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        // 如果当前根节点 < 两个值，那说明p q都在右子树
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor2(root.right, p, q);
        }

        // 如果当前根节点 > 两个值，那说明p q都在左子树
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor2(root.left, p, q);
        }

        // 否则 p q 可能是在两边
        return root;
    }

    // 7. 二叉树的深度

    // 递归
    public static int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // 非递归
    public static int maxDepth2(TreeNode root){
        if(root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if(temp.left!=null ){
                    queue.add(temp.left);
                }
                if(temp.right!=null){
                    queue.add(temp.right);
                }
            }
            depth++;
        }
        return depth;
    }

}
