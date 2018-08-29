package com.sz.ssr;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.shenzhen.baselib.utils.JsonUtil;
import com.shenzhen.baselib.utils.LogUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.e("MainActivity",""+  numJewelsInStones("ahA","aAAbbbbbbacdAaef"));
        mTextMessage = (TextView) findViewById(R.id.message);
        LogUtil.e(toLowerCase("HElLo ZJ"));
        String[] words = new String[]{"gin", "zen", "gig", "msg"};
        LogUtil.e("result:"+uniqueMorseRepresentations(words));
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
        //StringBuidle，StringBuffter区别
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

}
