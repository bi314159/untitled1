import org.junit.Test;

import javax.swing.plaf.nimbus.State;
import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * ClassName: Leetcode
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 毕研政
 * @Create 2023/4/27 11:41
 * @Version 1.0
 */
public class Leetcode {

    public static void main(String[] args) {
        Leetcode leetcode = new Leetcode();
        int sum = leetcode.largestSumAfterKNegations(new int[]{2, -3, -1, 5, -4}, 2);
    }

    /**
     * 借助单调队列
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        MyQueue myQueue = new MyQueue();
        //创建结果数组
        int[] result = new int[nums.length-k+1];
        //创建变量，用于记录result元素下标
        int num = 0;
        //先将前k个元素加入单调队列
        for (int i = 0; i < k; i++) {
            myQueue.myqueue.push(nums[i]);
        }
        //先选出一个最大元素
        result[num++] = myQueue.getMax();
        for (int i = k; i < nums.length; i++) {
            //弹出元素
            myQueue.pop(nums[i-k]);
            //加入元素
            myQueue.push(nums[i]);
            //挑选最大值
            result[num++] = myQueue.getMax();
        }
        //返回结果集
        return result;
    }
    /**
     * 自定义一个单调队列，维护最大值
     */
    class MyQueue{
        public Deque<Integer> myqueue = new LinkedList<>();

        /**
         * 弹出元素时，判断该元素是不是最大元素，如果是将最大元素弹出，否则不进行操作
         */
        public void pop(int element){
            if (!myqueue.isEmpty() && element == myqueue.peek()){
                myqueue.pop();
            }
        }
        /**
         * 添加元素时，如果该元素大于入口元素，则将入口元素移出
         */
        public void push(int element){
            while (!myqueue.isEmpty() && element > myqueue.getLast()){
                myqueue.removeLast();
            }
            myqueue.push(element);
        }
        /**
         * 获取最大元素
         */
        public int getMax(){
            return myqueue.peek();
        }
    }
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
         this.left = left;
          this.right = right;
      }
  }
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        //对nums排序，以便树层去重
        Arrays.sort(nums);
        backTracking(nums);
        return result;
    }

    public void backTracking(int[] nums){
        //终止条件
        if (path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        //单层逻辑
        for (int i = 0; i < nums.length; i++) {
            //处理
            //树层去重
            if (i > 0 && nums[i-1] == nums[i] && used[i-1] == false){
                continue;
            }
            if (used[i] == true){
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            //递归
            backTracking(nums);
            //回溯
            path.remove(path.size()-1);
            used[i] = false;
        }
    }

    /**
     * 贪心思路
     * 如果两天之间的利润>0，则累加
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        //定义sum，作为最大利润
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i-1] > 0){
                sum += prices[i] - prices[i-1];
            }
        }
        return sum;
    }

    /**
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        //如果nums长度为1，因为起初就是在第一个位置，所以一定可以覆盖
        if (nums.length == 1){
            return 0;
        }
        //记录步数
        int count = 0;
        //覆盖最大范围
        int maxCover = 0;
        //当前覆盖的最大范围
        int curCover = 0;
        for (int i = 0; i < nums.length; i++) {
            //不断更新最大覆盖范围
            maxCover = Math.max(maxCover,i+nums[i]);
            //如果最大覆盖范围能够覆盖最后一个位置，则再跳一步即可
            if (maxCover >= nums.length-1){
                count++;
                break;
            }
            //如果到了当前覆盖范围的最后一个位置，则更新当前覆盖范围
            if (i == curCover){
                curCover = maxCover;
                count++;
                break;
            }
        }
        return count;
    }

    /**
     * 判断指定区间的字符串是否是回文串,区间定义为左闭右闭
     * @param s
     * @param startIndex
     * @param endIndex
     * @return
     */
    public boolean isPa(String s, int startIndex, int endIndex){
        for (int i = startIndex,j = endIndex;startIndex > endIndex ; i++, j--) {
            if (s.charAt(i) != s.charAt(j)){
                return false;
            }
        }
        return false;
    }





        /**
         * 双指针法，采用右中左的遍历顺序
         * 定义两个指针，pre指向前一个结点的值，root指向当前结点
         * 递归处理右子树
         * root.val += pre
         * pre = root.val
         * 递归处理左子树
         * @param root
         * @return
         */
    int pre = 0;
    public TreeNode convertBST(TreeNode root) {
        //终止条件
        if (root == null){
            return null;
    }
        //递归处理左子树
        convertBST(root.left);
        //处理中间节点
        root.val += pre;
        //更新pre
        pre = root.val;
        //递归处理右子树
        convertBST(root.right);
        return root;
}





    /**
     * 只要是构造二叉树，都采用先序遍历，根左右
     * 当结点为空时，返回空节点
     * 找到数组的中间位置，根据中间位置的元素先构造中间节点
     * 再递归构造中间节点的左子树、右子树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return construct1(nums,0,nums.length-1);
    }

    /**
     * 区间定义为左闭右闭
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public TreeNode construct1(int[] nums,int left,int right){
        //不满足区间定义时
        if (left > right){
            return null;
        }
        //找到数组的中间位置，根据中间位置的元素先构造中间节点
        int mid = (left+right)/2;
        TreeNode root = new TreeNode(nums[mid]);
        //递归构造中间节点的左子树、右子树
        root.left = construct1(nums,left,mid-1);
        root.right = construct1(nums,mid+1,right);
        return root;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int startIndex = 0;
        for (int i = 0; i < gas.length; i++) {
            curSum += (gas[i] - cost[i]);
            totalSum += (gas[i] - cost[i]);
            if (curSum < 0){
                curSum = 0;
                startIndex = (i+1)%gas.length;
            }
        }
        if (totalSum < 0){
            return -1;
        }
        return startIndex;
    }
    @Test
    public void test(){
        Leetcode leetcode = new Leetcode();
        leetcode.merge(new int[][]{{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}});
    }

    /**
     * 三种情况
     * 情况1 账单是5 直接收下
     * 情况2 账单是10 如果有5 则 5-- 10++ 否则return false
     * 情况3 账单是20 如果有10和5 优先使用 10-- 5--  如果没有10，有多余3张5 则5 -= 3 否则return false
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        for (int i = 0; i < bills.length; i++) {
            //情况1 账单是5 直接收下
            if (bills[i] == 5){
                five++;
            }else if (bills[i] == 10){
                //情况2 账单是10 如果有5 则 5-- 10++ 否则return false
                if (five > 0){
                    five--;
                    ten++;
                }else{
                    return false;
                }
            }else{
                if (ten > 0 && five > 0){
                    ten--;
                    five--;
                }else if(five >= 3){
                    five -= 3;
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 按左边界升序排序，找出不重叠区间个数，区间总数-不重叠区间个数，即所求
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        //对数组按左边界升序排序
        Arrays.sort(intervals,(a,b) -> {
            return a[0] - b[0];
        });
        //count记录不重叠区间数
        int count = 1;
        for (int i = 1 ; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1]){
                intervals[i][1] = Math.min(intervals[i][1],intervals[i-1][1]);
                continue;
            }else{
                count++;
            }
        }
        return intervals.length - count;
    }

    /**
     * 从后向前遍历，如果后面的数字小于前面的数字，则前面的数字-1，同时标记一个数字小于后面数字的位置
     * 从该位置向后都置为1
     * @param n
     * @return
     */
    public int monotoneIncreasingDigits(int n) {
        //将n变为字符串
        String[] strings = (n+"").split("");
        //flag标识一个数字小于后面数字的位置
        int flag = strings.length-1;
        for (int i = strings.length-1; i > 0; i++) {
            if (Integer.parseInt(strings[i]) < Integer.parseInt(strings[i-1])){
                flag = i;
                int i1 = Integer.parseInt(strings[i - 1]);
                i1--;
                strings[i-1] = String.valueOf(i1);
            }
        }
        for (int i = flag; i < strings.length-1; i++) {
            strings[i] = "9";
        }
        StringBuffer s = new StringBuffer();
        for (String string : strings) {
            s.append(string);
        }
        return Integer.parseInt(s.toString());
    }//l = (t + s)/2

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (target < 0){
            target = - target;
        }
        if ((target+sum)%2 != 0){
            return 0;
        }
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] += dp[j-nums[i]] + nums[i];
            }
        }
        return dp[target];
    }
    public int numTrees(int n) {
        //定义dp数组
        int[] dp = new int[n+1];
        //初始化
        dp[0] = 1;
        dp[1] = 1;
        //遍历顺序
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //如果第一个或者最后一个网格有障碍，则没有可行的路径
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1){
            return 0;
        }
        //定义dp数组
        int[][] dp = new int[m][n];
        //初始化
        for (int i = 0; i < m && obstacleGrid[i][0] != 1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n && obstacleGrid[0][i] != 1; i++) {
            dp[0][i] = 1;
        }
        //遍历
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] != 1){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }else{
                    dp[i][j] = 0;
                }
            }
        }
        return dp[m-1][n-1];
    }

    public int climbStairs(int n) {
        if (n == 1){
            return 1;
        }
        //确定递推公式
        int[] dp = new int[n+1];
        //初始化
        dp[1] = 1;
        dp[2] = 2;
        //遍历顺序
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    /**
     * 1.按左边界从小到大排序数组
     * 2.现将第一个元素加入结果集，再遍历后序元素，如果当前元素的左边界小于等于结果集最后一个元素的右边界
     * 则更新结果集最后一个元素的右边界为Max(结果集最后一个元素的右边界,当前元素的右边界)
     * 否则，将当前元素直接加入结果集中
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        //将数组元素按左边界从小到大排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        //创建结果集
        ArrayList<int[]> result = new ArrayList<>();
        //先将第一个元素加入结果集
        result.add(intervals[0]);
        //再遍历后序元素
        for (int i = 1; i < intervals.length; i++) {
            //如果当前元素的左边界小于等于结果集最后一个元素的右边界
            if (intervals[i][0] <= result.get(result.size()-1)[1]){
                int[] pop = result.get(result.size()-1);
                pop[1] = Math.max(pop[1], intervals[i][1]);
                result.remove(result.size()-1);
                result.add(pop);
            }else{
                //否则，将当前元素直接加入结果集中
                result.add(intervals[i]);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
    /**
     * 两次贪心
     * 先把数组按照元素的绝对值，按从大到小排序
     * 第一次贪心:从数组的开始到结束，如果元素<0，则取反,k--
     * 第二次贪心:如果遍历完数组，k还大于0，则此时所有元素均大于0，此时对最后一个元素(此时最后一个元素是最小的一个正数)反复取反，消耗完k
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        //先把数组按照元素的绝对值，按从大到小排序
        Integer[] nums1 = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums1[i] = nums[i];
        }
        Arrays.sort(nums1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o2) - Math.abs(o1);
            }
        });
        //第一次贪心:从数组的开始到结束，如果元素<0，则取反,k--
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] < 0 && k > 0){
                nums1[i] = -nums1[i];
                k--;
            }
        }
        //第二次贪心:如果遍历完数组，k还大于0，则此时所有元素均大于0，此时对最后一个元素(此时最后一个元素是最小的一个正数)反复取反，消耗完k
        if (k > 0){
            if (k % 2 !=0){
                nums1[nums1.length-1] = -nums1[nums1.length-1];
            }
        }
        int sum = 0;
        for (int i = 0; i < nums1.length; i++) {
            sum += nums1[i];
        }
        return sum;
    }

    /**
     * 1.如果没找到，则返回null
     * 2.如果当前结点是要删除的结点，又分为几种情况:
     *      左子树为空，则右子树代替当前结点
     *      右子树为空，则左子树代替当前结点
     *      左右子树均不为空，则采取将右子树代替当前节点，当前节点的左子树放在右子树最左下结点的左子树
     *3.如果当前结点的值小于key,则去当前结点的右子树进行递归删除，如果当前结点的值大于key,则去当前结点的左子树进行递归删除
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        //如果没找到，则返回null
        if (root == null){
            return null;
        }
        //如果当前结点是要删除的结点
        if (root.val == key){
            //左子树为空，则右子树代替当前结点
            if (root.left == null){
                return root.right;
            }
            //右子树为空，则左子树代替当前结点
            if (root.right == null){
                return root.left;
            }
            //左右子树均不为空，则采取将右子树代替当前节点，当前节点的左子树放在右子树最左下结点的左子树
            TreeNode cur = root.right;
            while (cur.left != null){
                cur = cur.left;
            }
            root.right = cur;
            cur.left = root.left;
        }
        //如果当前结点的值小于key,则去当前结点的右子树进行递归删除，如果当前结点的值大于key,则去当前结点的左子树进行递归删除
        if (root.val < key){
            root.right = deleteNode(root.right,key);
        }
        if (root.val > key){
            root.left = deleteNode(root.left,key);
        }
        return root;
    }


    /**
     * 插入节点一定是在叶子节点上插入
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //终止条件  在叶节点插入
        if (root == null)
        {
            TreeNode newNode = new TreeNode(val);
            return newNode;
        }
        //当前结点的值小于val
        if (root.val < val){
            root.left = insertIntoBST(root.left,val);
        }
        //当前结点的值大于val
        if (root.val > val){
            root.right = insertIntoBST(root.right,val);
        }
        return root;
    }







    /**
     * 利用二叉搜索树的特性
     * 如果当前节点的值大于p的值，并且大于q的值，说明最近公共祖先在右子树
     * 如果当前节点的值小于p的值，并且小于q的值，说明最近公共祖先在左子树
     * 否则，即当前节点的值在两个结点之间，说明当前节点就是最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //如果当前节点的值大于p的值，并且大于q的值，说明最近公共祖先在右子树
        if (root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right,p,q);
        }
        //如果当前节点的值小于p的值，并且小于q的值，说明最近公共祖先在左子树
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left,p,q);
        }
        //否则，即当前节点的值在两个结点之间，说明当前节点就是最近公共祖先
        return root;
}







    ArrayList<Integer> resultList = new ArrayList<>();
    int maxCount = 0;
    int count = 0;
    TreeNode pre6 = null;
    /**
     * 主函数
     * @param root
     * @return
     */






    /**
     * 前序构造二叉树
     * 在root1的基础上进行改造
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        //递归出口
        //左子树为空，返回右子树
        if (root1 == null){
            return root2;
        }
        //右子树为空，返回左子树
        if (root2 == null){
            return  root1;
        }
        //中的处理逻辑
        root1.val += root2.val;
        //左的处理逻辑
        root1.left = mergeTrees(root1.left,root2.left);
        //右的处理逻辑
        root1.right = mergeTrees(root1.right,root2.right);
        return root1;
    }


    /**
     * 只要是构造二叉树都采用前序遍历，即先建立根节点，再建立
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums,0,nums.length);
    }

    /**
     * 前序递归构造二叉树,区间定义为左闭右开
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public TreeNode construct(int[] nums, int left, int right){
        //终止条件
        //终止条件一.数组为空,则返回空节点
        if (nums.length == 0){
            return null;
        }
        //终止条件二，数组只有一个结点，即构造叶子结点
        if (nums.length == 1){
            return new TreeNode(nums[0]);
        }
        //中的处理逻辑
        //找到数组最大元素，以及它对应的下标
        int maxValue = nums[left];
        int maxIndex = left;
        for (int i = left; i < right; i++) {
            if (nums[i] > maxValue){
                maxValue = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(maxValue);
        //左的处理逻辑
        root.left = construct(nums,left,maxIndex);
        //右的处理逻辑
        root.right = construct(nums,maxIndex+1,right);
        return root;
    }





    /**
     * 如果数组大小为0，则返回空节点
     * 如果不为空，取后序数组的最后一个元素作为结点
     * 找到该结点在中序数组的位置
     * 以该位置对中序数组切割为左中序数组和右中序数组
     * 再对后序数组切割为左后序数组和右后序数组
     * 递归构造二叉树
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0){
            return null;
        }
        return build(inorder,0,inorder.length,postorder,0,postorder.length);
    }

    /**
     * 根据分割的中序和分割的后序数组递归构造二叉树
     * @param inorder 分割的中序数组
     * @param inStart 中序数组的起始下标(可以取到)
     * @param inEnd 中序数组终止下标(取不到)
     * @param postorder 分割的后序数组
     * @param postStart 后序数组的起始下标(可以取到)
     * @param postEnd 后序数组终止下标(取不到)
     * @return
     */
    public TreeNode build(int[] inorder,int inStart,int inEnd,int[] postorder,int postStart,int postEnd){
        //数组是空数组，则返回空节点
        if (inStart == inEnd){
            return null;
        }
        //根据后序数组的最后一个元素的值构造结点
        TreeNode root = new TreeNode(postorder[postEnd-1]);
        //找到该值在中序数组出现的位置
        int midIndex = 0;
        for (int i = inStart; i < inEnd; i++) {
            if (inorder[i] == root.val){
                midIndex = i;
                break;
            }
        }
        //以该位置对中序数组切割，切割为左中序数组和右中序数组 -----------------
        //左中序数组的起始下标(可以取到)
        int leftInStart = inStart;
        //左中序数组的终止下标(取不到)
        int leftInEnd = midIndex;
        //右中序的起始下标(可以取到)
        int rightInStart = midIndex + 1;
        //右中序数组的终止下标(取不到)
        int rightInEnd = inEnd;
        //再对后序数组进行分割，切割为左后序数组和右后序数组 --------------
        // 左后序数组的起始下标(可以取到)
        int leftPostStart = postStart;
        //左后序数组的终止下标(取不到)
        int leftPostEnd = postStart + (midIndex-inStart);
        //右后序数组的起始下标(可以取到)
        int rightPostStart = leftPostEnd;
        //右后序数组的终止下标(取不到)
        int rightPostEnd = postEnd - 1;
        //递归构造左子树
        root.left = build(inorder,leftInStart,leftInEnd,postorder,leftPostStart,leftPostEnd);
        //递归构造右子树
        root.right = build(inorder,rightInStart,rightInEnd,postorder,rightPostStart,rightPostEnd);
        //返回构造的二叉树的根节点
        return root;
    }





    /**
     * 递归+回溯
     * 前中后序都可以，因为不需要中的处理逻辑
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null){
            return false;
        }
        return hasPath(root,targetSum);
    }

    /**
     * 用于寻找存在题意的路径
     * @param root 根节点
     * @param count 主函数调用时，传入targetSum,然后再向下遍历的时候，减去当前结点的val
     * @return
     */
    public boolean hasPath(TreeNode root,int count){
        //递归出口
        if (root.left ==null && root.right == null && count == 0){
            //遍历到叶子结点，而且符合题意，则返回true
            return true;
        }
        if (root.left ==null && root.right == null && count != 0){
            //遍历到叶子结点，但不符合题意，则返回false
            return false;
        }
        //左
        if (root.left != null){
            //递归
            count -= root.left.val;
            boolean left = hasPath(root.left,count);
            if (left == true){
                return true;
            }
            //回溯
            count += root.left.val;
        }
        //右
        if (root.right != null){
            //递归
            count -= root.right.val;
            boolean right = hasPath(root.right,count);
            if (right == true){
                return true;
            }
            //回溯
            count += root.right.val;
        }
        return false;
    }

    /**
     * 递归+回溯
     * 前序遍历
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        //定义结果集
        List<String> result = new ArrayList<>();
        traverse(root,new ArrayList<Integer>(),result);
        return result;
    }

    /**
     * 前序遍历，当结点为叶子结点时，收获结果，加入结果集
     * @param root
     * @param path
     * @param result
     */
    public void traverse(TreeNode root,List<Integer> path,List<String> result){
        //中
        //将当前节点的值加入path
        path.add(root.val);
        //如果结点是叶子结点，则收获结果，加入结果集
        if (root.left == null && root.right == null){
            //拼接结果
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < path.size()-1; i++) {
                stringBuilder.append(path.get(i)).append("->");
            }
            stringBuilder.append(path.get(path.size()-1));
            //将结果加入结果集
            result.add(stringBuilder.toString());
        }

        if (root.left != null){//左
            traverse(root.left,path,result);//递归
            path.remove(path.size()-1);//回溯
        }
        if (root.right != null){//右
            traverse(root.right,path,result);//递归
            path.remove(path.size()-1);//回溯
        }
    }









    /**
     * 用求高度的方法求深度
     * 如果结点的左子树为空，右子树不为空，则该结点的高度为1+右子树的高度
     * 如果结点的左子树不为空，右子树为空，则该结点的高度为1+左子树的高度
     * 如果结点的左右子树都不为空，则该结点的高度为1+左右子树高度的最小值，
     * 最后该高度就是最小深度，返回即可
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        //求左子树高度
        int left = minDepth(root.left);
        //求右子树高度
        int right = minDepth(root.right);
        //如果结点的左子树为空，右子树不为空，则该结点的高度为1+右子树的高度
        if (root.left==null && root.right!=null){
            return 1+right;
        }else if (root.left!=null && root.right==null){
        //如果结点的左子树不为空，右子树为空，则该结点的高度为1+左子树的高度
            return 1+left;
        }
        //如果结点的左右子树都不为空，则该结点的高度为1+左右子树高度的最小值
        return 1+Math.min(left,right);
    }



    /**
     * 递归法
     * 前序和后序都可以，中序麻烦
     * 以前序为例，前序遍历顺序为根、左、右
     * 先处理根节点，即先交换根节点的左右孩子，再对根节点的左右孩子，分别递归调用翻转函数
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        //如果结点为空，则返回
        if (root == null){
            return root;
        }
        //处理根节点
        swap(root);
        //递归处理根节点的左右孩子
        invertTree(root.left);
        invertTree(root.right);
        //返回翻转后的二叉树的根节点
        return root;
    }

    /**
     * 定义一个函数，用于交换两个结点
     */
    public void swap(TreeNode root){
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }











    /**
     * 求出两链表的差值
     * 定义两个指针,指针longList指向长链表的头结点，指针shortList指向短链表的头结点
     * 让longlist先走差值步，然后两个指针同步后移，只到指向同一个结点
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //定义长短指针
        ListNode longList = new ListNode();
        ListNode shortList = new ListNode();
        //定义变量，用于存放两链表长度的差值
        int dist = 0;
        //分别求出两链表的长度
        int lengthA = 0;
        int lengthB = 0;
        ListNode workA = headA;
        ListNode workB = headB;
        while (workA != null){
            lengthA++;
            workA = workA.next;
        }
        while (workB != null){
            lengthB++;
            workB = workB.next;
        }
        //让longList指向长链表的头结点，shortList指向短链表的头结点
        if (lengthA < lengthB){
            shortList = headB;
            longList = headA;
            dist = lengthB - lengthA;
        }else{
            shortList = headA;
            longList = headB;
            dist = lengthA - lengthB;
        }
        //让长链表先走差值步
        while (dist!=0 && longList!=null){
            longList = longList.next;
            dist--;
        }
        //两个指针再同步后移
        while (shortList!=null && longList!=null && shortList!=longList){
            shortList = shortList.next;
            longList = longList.next;
        }
        return shortList;
    }














































    /**
     * 大体思路用map
     * 遍历一个元素时，寻找(target-该元素)是否在map的key中存在过，如果存在，则返回要求的结果数组
     * 否则，将该元素的值作为key，下标作为value存入map即可
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            //如果target-nums[i]是否在map存在过，则返回结果数组
            if (map.containsKey(target-nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
            }else{
                //不存在，则将nums[i]作为key  下标i作为value存入map
                map.put(nums[i],i);
            }
        }
        return result;
    }
}



/**
 * 链表结点定义
 */
class ListNode{
    /**
     * 数据域
     */
    int val;
    /**
     * 指针域
     */
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


