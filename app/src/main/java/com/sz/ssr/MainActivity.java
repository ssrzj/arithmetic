package com.sz.ssr;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shenzhen.baselib.adapter.BaseAdapterHelper;
import com.shenzhen.baselib.adapter.BaseQuickAdapter;
import com.shenzhen.baselib.utils.JsonUtil;
import com.shenzhen.baselib.utils.LogUtil;
import com.shenzhen.baselib.utils.ToastUtil;
import com.shenzhen.baselib.utils.ViewBinderUtil;
import com.shenzhen.baselib.utils.ViewUtil;
import com.sz.ssr.activity.base.BaseActivity;
import com.sz.ssr.utils.ScreenUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.act_main_drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.act_main_left_ll_title)
    LinearLayout ll_left_title;
    @BindView(R.id.act_main_left_list)
    ListView     mListView;
    @BindView(R.id.act_main_left_container)
    RelativeLayout rl_left_menu_container;
    BaseQuickAdapter<String,BaseAdapterHelper> mAdapter;
    int[] resIds = new int[]{R.drawable.icon_arithmetic_black,R.drawable.icon_github_black,R.drawable.icon_about,R.drawable.icon_settting};
    List<String> left_menu = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initAdapter();
        initView();
        loadData();
//        [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
        int [][] target =new int[][]{{1,1,0,0},{1,0,0,1},{0,1,1,1},{1,0,1,0}};
        flipAndInvertImage(target);
        // 1维数组反序
        int [] array= new int[]{8,7,6,5,4,3,2,1};
        invertArray(array);
        arrayPairSum(array);
    }

    private void loadData() {
        left_menu.add("算法");
        left_menu.add("Github");
        left_menu.add("关于");
        left_menu.add("设置");
        mAdapter.replaceAll(left_menu);
    }

    private void initAdapter() {
        mAdapter = new BaseQuickAdapter<String, BaseAdapterHelper>(this,R.layout.item_left_menu) {
            @Override
            protected void convert(BaseAdapterHelper helper, final String item) {
                helper.setText(R.id.item_left_menu_tv_info,item);
                helper.setImageResource(R.id.item_left_menu_iv,resIds[left_menu.indexOf(item)]);
                helper.setOnClickListener(R.id.item_left_menu_ll_container,new ItemClickListener(item));
            }
            @Override
            protected BaseAdapterHelper getAdapterHelper(int position, View convertView, ViewGroup parent) {
                return BaseAdapterHelper.get(MainActivity.this,convertView,parent,R.layout.item_left_menu);
            }
        };
    }
    class ItemClickListener implements View.OnClickListener{
        int index;
        public ItemClickListener(String item) {
        index = left_menu.indexOf(item);
        }
        @Override
        public void onClick(View v) {
            switch (index){
                case 0:
                    ToastUtil.showToast("算法");
                    break;
                case 1:
                    ToastUtil.showToast("Github");
                    break;
                case 2:
                    ToastUtil.showToast("关于");
                    break;
                case 3:
                    ToastUtil.showToast("设置");
                    break;
            }
        }
    }

    private void initView() {
        ViewUtil.setViewWidth(rl_left_menu_container, ViewUtil.getScreenWidth()/3*2);
        ViewUtil.setViewHeight(ll_left_title,ViewUtil.getScreenWidth()/3*2);
        ViewUtil.setViewWidth(ll_left_title,ViewUtil.getScreenWidth()/3*2);
        mListView.setAdapter(mAdapter);
    }

    public int numJewelsInStones(String J, String S) {

        // 字符串匹配的时候   注意正则的使用
//         return S.replaceAll("[^" + J + "]", "").length();

        // 嵌套循环变成线性循环
        //  比较a,b， 在相同环境c中，根据标准数组a,设置c的状态，b中每个元素依次与c进行比较就ok,避免a,b 嵌套循环
//        boolean[]  arr = new boolean[128];
//        int count = 0;
//        Log.i("MainActivity",new Gson().toJson(arr));
//        for(int i = 0;i<J.length();i++){
//        Log.i("MainActivity","J.charAt:"+(int)J.charAt(i));
//            arr[J.charAt(i)] = true;
//        }
//        Log.i("MainActivity",new Gson().toJson(arr));
//        for(int j = 0;j<S.length();j++){
//            if(arr[S.charAt(j)])
//                count ++;
//        }
//        return count;


        int res=0;
        for(char c : S.toCharArray()){
            if(J.indexOf(c) != -1){
                res++;



            }
        }
        return res;

//       char[] JArray = J.toCharArray();
//       char[] SArray = S.toCharArray();
//       int count = 0;
//       for(int i=0;i<JArray.length;i++){
//           char target = JArray[i];
//            for(int j=0;j<SArray.length;j++){
//                if(target==SArray[j]){
//                    count++;
//                }
//            }
//       }
//       return count;
    }


    public String toLowerCase(String str) {
        str.toLowerCase();
        //StringBuilder 为了兼容StringBuffer 但是没有同步保证   StringBuffer是线程安全的
        //StringBuilder  当字符序列长度超过内部容量的时候会自动扩张  默认长度16
        //StringBuilder.append(sb)   sb ==null时     拼接的是'null'   toString 方法不是 HashCode值
          // 通常有限使用StringBuilder   StringBuffter加锁，是加载append,insert 之类的方法中，source CharSequence 依旧可以被其他线程访问
        //StringBuidler，StringBuffter区别
        //String.toLowerCase() char.toLowerCase() api
        // char  与int 之间的转换,String 与Int之间的转换
        char[] source_array = str.toCharArray();
        char[] result_array = new char[source_array.length];
        for(int i=0;i<source_array.length;i++){
         if(65<=source_array[i]&&source_array[i]<=90){
            result_array[i] = (char) (source_array[i]+32);
         }else{
            result_array[i] = source_array[i];
         }
        }
        String[] tt = new String[]{""};
        return new String(result_array);
    }

    public int uniqueMorseRepresentations(String[] words) {

        LogUtil.i("尝试合并到其他分支去");
        LogUtil.i("这个是develop分支的代码，为什么会出现再master分支里面");
        // HashSet 与Map 查询的差别

        // 判断数组中有多少少个不相同的数组 1、利用map,2利用HashSet.add(),过滤重复的
        // 将word 元素转换为moCode 组装而成的
     Map map = new HashMap<String,String>();
     String [] moCode = new String []{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.",
                "--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
     String [] letter_arrary = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
     for(int i=0;i<26;i++){
         map.put(letter_arrary[i],moCode[i]);
     }
     String[] change_array = new String[words.length];
     for(int i=0;i<words.length;i++){
         char[] word_array = words[i].toCharArray();
           for(int j=0;j<word_array.length;j++){
                  if(j!=0){
                  change_array[i]=change_array[i]+map.get(String.valueOf(word_array[j]));
                  }else{
                   change_array[i] =(String) map.get(String.valueOf(word_array[j]));
                  }
           }
         words[i] = change_array[i];
     }
     // words  moCode 组成，比较各自不同
        int count = 0;
        for(int i=0;i<words.length;i++){
         if(!map.containsKey(String.valueOf(words[i]))){
         map.put(words[i],i);
         count++;
         }
        }
     return  count;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        return null;
    }

    class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    }
    // a 对应的二进制中1 的个数       逻辑运算符以及位移运算符
    //  偶数对应的二进制编码 最后一位肯定是0 ，所以判断奇偶可以用 a&1来进行判断，若果结果是0，则是偶数，否则是奇数
    //if(a%2 == 1)与if(a&1 == 1)结果是等价的  a >>= 1等价于a /= 2;
    // 获取整数的二进制编码
//    while (x != 0){
//        if (x % 2 == 1){
//            repo.append(1);
//        } else {
//            repo.append(0);
//        }
//        x /= 2;
//    }




    public int hammingDistance(int x, int y) {
//        return Integer.bitCount(x^y);
        int result = x^y;
        String binaryCode = Integer.toBinaryString(result);
        int count = 0;
        for(int i=0;i<binaryCode.toCharArray().length;i++){
            if(binaryCode.toCharArray()[i]==(char)'1'){
                count++;
            }
        }
        LogUtil.e("count:"+count);
        return count;
    }
    public void test(int x){
        String binaryCode = Integer.toBinaryString(x^x-1);
        LogUtil.i("x:"+x+"|"+binaryCode);
        if(x!=0){
            x = x-1^x;
            test(x);
        }
    }

        //  for  while 循环 的差别其中的实现的区别
    public int[][] flipAndInvertImage(int[][] A) {

        //   对 数组进行  反序 翻转的操作，可以看作对每个元素进行该操作  a[i] = invertAndFlip(a[i]) // 如此一来直接翻转a[i]就ok
        LogUtil.i(JsonUtil.object2Json(A));
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[i].length/2;j++){
                int temp = A[i][j];
                A[i][j] = A[i][A[i].length-1-j];
                A[i][A[i].length-1-j] =temp;
            }
        }
        LogUtil.i(JsonUtil.object2Json(A));
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[i].length;j++){
                if(A[i][j]==0){
                    A[i][j]= 1;
                }else{
                    A[i][j]=0;
                }
            }
        }
        return A;
    }
    // 数组反序
    public int[] invertArray(int [] a){
        for(int i=0;i<a.length;i++){
         int temp = a[i];
         a[i]= a[a.length-1-i];
         a[a.length-1-i] = temp;
        }
        return a;
    }

    // 如果 循环里面 采用move.toCharArray() 会timeLimited 减少对象的生成
    public boolean judgeCircle(String moves) {
//        Node node = new Node(0,0);
          char[] targetArray = moves.toCharArray();
//        for(int i=0;i<moves.toCharArray().length;i++){
//            if(moves.toCharArray()[i]==(char)'U'){
//                node.y = node.y+1;
//            }
//            if(moves.toCharArray()[i]==(char)'D'){
//                node.y = node.y-1;
//            }
//            if(moves.toCharArray()[i]==(char)'R'){
//                node.x = node.x+1;
//            }
//            if(moves.toCharArray()[i]==(char)'L'){
//                node.x = node.x-1;
//            }
//        }
//        if(node.x==0&&node.y==0){
//            return true;
//        }else{
//            return false;
//        }
        int left_count=0;
        int right_count =0;
        int up_count = 0;
        int down_count = 0;
        for(int i=0;i<targetArray.length;i++){
            if(targetArray[i]==(char)'U'){
               up_count++;
            }
            if(targetArray[i]==(char)'D'){
              down_count++;
            }
            if(targetArray[i]==(char)'R'){
               right_count++;
            }
            if(targetArray[i]==(char)'L'){
             left_count++;
            }
        }
        if(left_count==right_count&&up_count==down_count){
            return true;
        }else{
            return false;
        }

    }
    class Node{
        int x ;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    // 遍历二叉树  // 肯定是递归的

    public void traverseTrees(TreeNode node){

    }
    public String dgNode(TreeNode node){
        int root = node.val;
        int let=0;
        int right=0;
        if(node.left!=null){
           dgNode(node.left);
        }else{
            let = node.left.val;
        }
        if(node.right!=null){
            dgNode(node.left);
        }else{
            right = node.right.val;
        }
        return ""+root+let+right;
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        return new TreeNode(0);
    }


    public int peakIndexInMountainArray(int[] A) {
        // 黄金分割算法
        // 有序列中寻找最高值  python可以利用api 直接寻找峰值
        // 寻找队列中 第一个 A[i]>A[i+1]
        // 二分法查找
//        int index = -1;
//        for(int i=1;i<A.length;i++) {
//            if(A[i-1]<A[i]&&A[i]>A[i+1]){
//                index = i;
//            }
//        }
//        return index;
         int l=0,r=A.length-1,mid;
         while (l<r){
             mid = (l+r)/2;// 取中间位置，判断峰值在前还是在后
            if(A[mid]<A[mid+1]){
                // 说明 峰值在后半段， 取后半段进行查找
                l=mid;
            }else if(A[mid]>A[mid+1]){
                r = mid;
            }else
                return mid;
         }
         return 0;
    }
    //2n 的数组
    public int arrayPairSum(int[] nums) {
    //  得到的sum 要足够的大
        int sum=0;
//        for(int i=0;i<nums.length/2;i++){
//            nums[i]=Math.min(nums[i],nums[nums.length-1-i]);
//            nums[nums.length-1-i]=0;
//            sum = sum+nums[i];
//        }
        // 尽可能将小的两两组合，大的两两组合
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++){
            list.add(nums[i]);
        }
        LogUtil.i("before:"+JsonUtil.object2Json(list));
        Collections.sort(list);
        LogUtil.i("after:"+JsonUtil.object2Json(list));
        for(int i=0;i<list.size()-1;i=i+2){
           list.set(i,Math.min(list.get(i),list.get(i+1)));
           list.set(i+1,0);
           sum = sum+list.get(i);
        }
        // 排序完毕后  index 为2n 的数 才需要添加进行计算
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i+=2){
            nums[i]=Math.min(nums[i],nums[nums[i+1]]);
            sum = sum+nums[i];
        }
        return sum;

    }
}
