package cn.hsmxg1204.test.learn;


import cn.hsmxg1204.core.exception.MyException;
import cn.hsmxg1204.core.utils.URLBuilder;
import cn.hsmxg1204.test.entity.RefundRecordReq;
import cn.hsmxg1204.test.entity.UserInfo;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-05-26 13:49
 */
public class LearnRoad {
    static class Test
    {
        private int data;
        int result = 0;
        public void m()
        {
            result += 2; //第一次 result 2
            data += 2;  // 第一次 data 2
            System.out.print(result + "  " + data); // 2 2
        }
    }
    static class ThreadExample extends Thread
    {
        private Test mv;
        public ThreadExample(Test mv)
        {
            this.mv = mv;
        }
        public void run()
        {
            synchronized(mv)
            {
                mv.m();
            }
        }
    }
//    public static void main(String[] args) {
//        Test mv = new Test();
//        Thread t1 = new ThreadExample(mv);
//        Thread t2 = new ThreadExample(mv);
//        Thread t3 = new ThreadExample(mv);
//        t1.start();
//        t2.start();
//        t3.start();
//    }

//    static class Chinese{
//        private static Chinese objref =new Chinese();
//        private Chinese(){}
//        public static Chinese getInstance() { return objref; }
//    }
//    public static void main(String [] args) {
//        Chinese obj1 = Chinese.getInstance();
//        Chinese obj2 = Chinese.getInstance();
//        System.out.println(obj1 == obj2);
//    }


}

//public class MyClass{
//    long var;
//    public void MyClass(long param) { var = param; }//(1)
//    public static void main(String[] args) {
//        MyClass a, b;
//        a =new MyClass();//(2)
//        b =new MyClass(5);//(3)
//    }
//}

class A{
    public float getNum(){
        return 3.0f;
    }
}
class B extends A{

    public void getNum(double d){

    }

    public static void main(String[] args) {
        // . 表示匹配 除换行符 \n 之外的任何单字符 。
        // 匹配 . 需要 \\.
        String classFile = "com.jd.". replaceAll(".", "/") + "MyClass.class";
        System.out.println(classFile);
    }
}


class Test {
    public static void main(String[] args) {
        System.out.println(new B().getValue());
    }
    static class A {
        protected int value;
        public A (int v) {
            setValue(v);
        }
        public void setValue(int value) {
            this.value= value;
        }
        public int getValue() {
            try {
                value ++;
                return value;
            } finally {
                this.setValue(value);
                System.out.println(value);
            }
        }
    }
    static class B extends A {

        public B () {
            super(5);
            setValue(getValue()- 3);
        }
        public final void  setValue(int value) {
            super.setValue(2 * value);
        }
    }
}

final class Foo {
    public static void main(String[] args) throws Exception {

        PrintWriter out = new PrintWriter(
                new java.io.OutputStreamWriter(System.out), true);
        out.println("Hello");
    }

    final String x= " 111";
}

class Inc {
    public static void main(String[] args) {
        Inc inc = new Inc();
        int i = 0;
        inc.fermin(i);
        i= ++i;
        System.out.println(i);

        byte b1=1,b2=2,b3=0,b6;
        final byte b4=4,b5=6;
        b6=b4+b5;
//        b3=(b1+b2);  // 将byte 转换为int
        System.out.println(b3+b6); //没有初始值
    }
    void fermin(int i){
        i++;
    }
}

class Demo {
    public static void main(String[] args) {
        Collection<?>[] collections =
                {new HashSet<String>(), new ArrayList<String>(), new HashMap<String, String>().values()};
        Super subToSuper = new Sub();
        for(Collection<?> collection: collections) {
            System.out.println(subToSuper.getType(collection));
        }
    }
    abstract static class Super {
        public static String getType(Collection<?> collection) {
            return "Super:collection";
        }
        public static String getType(List<?> list) {
            return "Super:list";
        }
        public String getType(ArrayList<?> list) {
            return "Super:arrayList";
        }
        public static String getType(Set<?> set) {
            return "Super:set";
        }
        public String getType(HashSet<?> set) {
            return "Super:hashSet";
        }
    }
    static class Sub extends Super {
        public static String getType(Collection<?> collection) {
            return "Sub";
        }
    }
}

class ThreadTest{
    public static void main(String[] args) {
        int a = 0;
        ThreadTest test = new ThreadTest();
        MyThread t1 = test.new MyThread(a);
        MyThread t2 = test.new MyThread(a);
        t1.start();
        t2.start();
    }

    class MyThread extends Thread{
        private int a = 0;
        MyThread(){ }
        MyThread(int a){
            this.a= a;
        }
        @Override
        public void run() {
            synchronized (this){
                boolean isOdd =false;

                for (int i = 1; i <= 2; ++i) {
                    if(i % 2 ==1) isOdd = true;
                    else isOdd = false;
                    a += i*(isOdd?1:-1);
                }
                System.out.println("a result is "+a);
            }
        }
    }
}

class TestTry{
    public static void main(String[] args) {
        try{
            throw new Exception("测试");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("finally ");
        }
    }
}

//class Demo2{
//    float func1() {
//        int i=1;
//        return; // 必须返回值
//    }
//    float func2() {
//        short i=2;
//        return i;
//    }
//    float func3() {
//        long i=3;
//        return i;
//    }
//    float func4() {
//        double i=4;
//        return i; //返回的是 long 类型
//    }
//}
class Demo2{
    public static void main(String[] args) {
        int a =100,b=50,c=a---b,d=a---b;
        // c = 100 - 50 -> a-- 99
        // c = 99 - 50 -> a-- 98
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);

        byte a1 =2,a2=4,a3;
        short s = 16;
        a2 = (byte) s;
//        a3 = s;  // 要强转
//        a3 = a1 * a2; // 要转换为int

        int arr[] = null;
        arr[0] = 1; // 将抛出 NullPointerException

        int i = Integer.parseInt("123a"); // NumberFormatException
    }
}

class Arraytest {
    static int a[] = new int[6];
    public static void main ( String arg[] ) {
        // int 的 默认值 为 0 ； Integer 的默认值为 null；
        System.out.println ( a[0] );
    }
}

class TestStatic{
    static{
        int x=5;
    }
    static int x,y;
    public static void main(String args[]){
        x--;  // 默认值 为 0 ; -> -1
        myMethod( );
        System.out.println(x+y+ ++x); // x = 1 -> x + y + (x+1) -> 1 + 0 + 2 -> 3
        char[] chars = new char[]{'A','b','c'};
    }
    public static void myMethod( ){
        // x = -1
        System.out.println(x);
        y=x++ + ++x; //y =  (x++) + (++x) -> -1 + 0  = -1 -> x+1 -> x = 1
        // x+1 = 0
        char s ='\u0639';
        Object o = 'f';
        String s2 = "Hello,world \0";
        int[] a = new int[]{1,2,3,4,5};
        int[] b = new int[]{6,7,8,9,0};
        boolean b1 = Arrays.equals(a, b);
    }
}

class TestCount{
    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            int temp = count;
            count = count + 1;
            count = temp;
//            count = count++;
        }
        System.out.println(count);

        byte[] src,dst;
        // 数组需要初始化；
//        dst = new String(src,"GBK").getBytes(StandardCharsets.UTF_8);
    }
}

class SuperTest extends Date{
    private static final long serialVersionUID = 1L;
    private void test(){
        // SuperTest Date 的getClass没有被重写
        // 所以调用Object.getClass
        // Object的 getClass作用是返回运行时类的名字。
        // 运行时的类指的是当前类。
        System.out.println(super.getClass().getName());
    }

    public static void main(String[]args){
        new SuperTest().test();
    }
}

class TestHello {
    public static void hello() {
        System.out.println("hello");
    }
}

class MyApplication extends ClassLoader {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TestHello test=null;
        test.hello();
    }
}

class Enclosingone {
    public class InsideOne {}
}
class inertest {
    public static void main(String[]args) {
        Enclosingone eo = new Enclosingone();
        //insert code here
        Enclosingone.InsideOne insideOne = eo.new InsideOne();

    }
}

class JAVAMethod {
    /**
     *  堆区 : 只存放类对象，线程共享；
     *  方法去 : 又叫静态存储区，存放class文件和静态数据，线程共享；
     *   栈区 : 存放方法局部变量，基本类型变量区、执行环境上下文、操作指令区，线程不共享
     */
    private String a = "aa"; // 类中成员 存放在堆区
    public boolean methodB() {
        // b,c 是方法中的局部变量，存放在栈区。
        String b = "bb";
        final String c = "cc";
        int i , sum = 0;
        for (i = 0; i < 10; ++i,sum+=i) {

        }
        System.out.println(i);
        return false;
    }

    /**
     *  内部类
     *
     */
    private float f = 1.0f;

//    class InnerClass{
//        public static float func(){
//            return f;
//        }
//    }
//    abstract class InnerClass2{
////        public abstract float func(){}
//        public abstract float func();
//    }
//    static class InnerClass3{
//        protected static float func(){
//            return f;
//        }
//    }
//    public class InnerClass4{
//        static float func(){
//            return f;
//        }
//    }
    public static void main(String[] args) {
        JAVAMethod method = new JAVAMethod();
        method.methodB();

        byte a =127;
        byte b = 127;
        byte d = 2,c = 3;
        a+=b;
        System.out.println(b);
        System.out.println(5 + ~10);
    }
}

/**
 * 二分搜索法
 */
class Solution{
    public int search(int[] nums,int target){
        int low = 0, high;
        high = nums.length - 1;
        int index = -1;
        while (low <= high){
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                index = mid;
                high = mid  - 1;
            }else if (nums[mid] < target) {
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,4,5,6};
        Solution solution = new Solution();
        int search = solution.search(nums, 4);
        System.out.println("result is : "+search);
    }
}

/**
 * 最长回文字串
 */
class LongestPalindrome{
    public int getLongestPalindrome(String str, int n) {
        //边界
        if(n < 2)
            return str.length();
        int maxLen = 0;
        for (int i = 0; i < n;i++) {
//            if(n - i <= maxLen / 2) break;
//            int left = i;
//            int right = i;
//            while ( right < n - i && str.charAt(right + 1) == str.charAt(right)){
//                ++right;
//                --left;
//            }
//            if(right - left + 1 > maxLen){
//                maxLen = right - left + 1;
//            }
            for (int j = 0; j < n; j++) {
                if( isPalindrome(str,i,j)){
                    if(maxLen < j - i + 1){
                        maxLen = j - i + 1;
                    }
                }
            }
        }
        return maxLen;
    }

    private boolean isPalindrome(String s,int start,int end){
        while (start < end){
            if (s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "abc1234321ab";
        LongestPalindrome palindrome = new LongestPalindrome();
        System.out.println(palindrome.getLongestPalindrome(str, 12));
    }
}

/**
 * 数组中出现次数超过一半的数字
 * <p>
 *     思路
 *     暴力算法
 *     遍历数组每个值记录该值出现的次数
 * </p>
 */
class MoreThanHalfNum_Solution{
    public int MoreThanHalfNum(int[] array){
        //用于记录出现次数
        int count = 0;
        for (int i = 0; i < array.length; i++) {

        }
        // 使用 1.8 流
//        Arrays.sort(array);
//        int i = array[array.length / 2 ];
//        return IntStream.of(array).filter(l -> l == i).count() > array.length / 2 ? i : 0;
        return 0;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};
        MoreThanHalfNum_Solution s = new MoreThanHalfNum_Solution();
        System.out.println(s.MoreThanHalfNum(array));
    }
}
/**
 * 反转链表
 *
 */
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
class ReverseList{
    public ListNode ReverseList(ListNode head){
        ListNode pre = null;// 当前节点的前一节点
        ListNode next = null;// 当前节点的后一节点
        while(head != null){
            next = head.next; // 当前节点的下一个节点位置，防止原链表丢失
            head.next = pre;  // 当前节点指向前一个节点位置，完成反转
            pre = head; // pre 右移
            head = next; // head 右移
        }
        return pre;
    }
}
}

/**
 * 将字符串中的中文编码改为UTF-8
 *  2.0v
 *  空格 被编码成+ 或者 %20 原因
 *  <p>
 *      W3C标准规定，当Content-Type为application/x-www-form-urlencoded时，
 *      URL中查询参数名和参数值中空格要用加号+替代，所以几乎所有使用该规范的浏览器
 *      在表单提交后，URL查询参数中空格都会被编成加号+。而在另一份规范
 *      (RFC 2396，定义URI)里, URI里的保留字符都需转义成%HH格式(Section 3.4 Query Component)，
 *      因此空格会被编码成%20，加号+本身也作为保留字而被编成%2B，对于某些遵循RFC 2396标准的应用来说，
 *      它可能不接受查询字符串中出现加号+，认为它是非法字符。所以一个安全的举措是URL中统一使用%20来编码空格字符。
 *  </p>
 *  解决办法
 *  <p>
 *      用replaceAll(“\\+”, “%20″)，将所有加号+替换为%20
 *  </p>
 */
class CheckChineseInString{
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "http://192.168.90.61:8081//Saved/2021/06/03/孙婷 25床-20210521101827799019-2021060320450.pdf";
        char[] chars = str.toCharArray();
//        System.out.println(URLEncoder.encode(str, "UTF-8"));
//        HashMap<String,char[]>
        String buffer = "";
        for (int i = 0; i < chars.length;i++){
            if ((chars[i] >= 0x4e00) && (chars[i] <= 0x9fbb) || chars[i] == ' ') {
                buffer = String.valueOf(chars[i]);
                System.out.println(buffer);
            }
            str = str.replaceAll(buffer, URLEncoder.encode(buffer, "UTF-8"));
        }
        if(str.contains("+")){
            str = str.replaceAll("\\+","%20");
        }
        System.out.println(str);
        System.out.println(new String(str.getBytes(StandardCharsets.UTF_8)));
    }
}

/**
 * 生成随机id
 */
class RandomNum{
    public static synchronized String getRandomNum(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            String randomNum  = System.currentTimeMillis() + getRandomNum(6);
            System.out.println(randomNum);
            Thread.sleep(2000);
        }

    }
}

/**
 *
 */
class URLForm{
    public static void main(String[] args) {
        String str = "25456132545_rmyy";
        if(str.contains("_")){
            String[] s = str.split("_");
            Arrays.stream(s).forEach(System.out::println);
        }
    }
}

class testJSONObject{
    public static void main(String[] args) {
        String str = "{\"status\":1000,\"msg\":\"SUCCESS\",\"data\":{\"tradeNo\":\"21061509331077029993\",\"outerOrderNo\":\"12021061509095322677779677101229\",\"totalAmount\":2.0000,\"innerOrderNo\":\"1404612924981145602\",\"paymentChannel\":\"SCRCU\",\"refundId\":null,\"tradeStatus\":\"REFUND_SUCCESS\",\"payType\":\"WECHAT\"}}";
        JSONObject object = JSONObject.parseObject(str);
        System.out.println("1000".equals(object.getString("status")));
    }
}

/**
 * String to xml 解析
 */
class XMLTOJSON{
    public static void main(String[] args) throws DocumentException {
        String str = "<Response><Row><reportNo>202110749072</reportNo><SUCCESS>1</SUCCESS><patientName>胡继明</patientName><reportDate>2021-06-17 09:51:45</reportDate><createTime>2021-06-17 13:00:24</createTime><contentpicsrc>E:\\webfile\\LISBGD\\2021-06-17-15-27-47\\LIS_2106046053_202110749072.1.jpg</contentpicsrc><inspectName>检验科</inspectName><HISReqNo>202104079583</HISReqNo><PatientNo>2106046053</PatientNo><checkName>PSA</checkName><doctorName>侯龙敏</doctorName><chekckdoctorName>朱剑峰</chekckdoctorName><isPrint>0</isPrint><reportType>1</reportType></Row></Response>";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",str);
        Document document = DocumentHelper.parseText(jsonObject.getString("data"));

        Element root = document.getRootElement();


        String row = root.elementText("Row");
        Element content = root.element("Row");
        System.out.println(row);
        String code = content.elementText("SUCCESS");
        System.out.println(code);
    }
}

class URLBuilderTest{
    public static void main(String[] args) throws DocumentException {
//        String s = URLBuilder.builder().setDomain("blog.hsmxg1204.cn")
//                .setScheme("http")
//                .setPath("/")
//                .setPort(8081)
//                .setCredential("test", "123456")
//                .build();
//        System.out.println(s);
        String str ="<Response><SUCCESS>2</SUCCESS><ERRMSG>未查询到相关病人信息</ERRMSG></Response>";
        Document resultDoc = DocumentHelper.parseText(str);
        Element root = resultDoc.getRootElement();
        String code = root.elementText("SUCCESS");
        System.out.println(code);

        if (!"1".equals(code)) {
            String errmsg = root.elementText("ERRMSG");
            throw new MyException(errmsg);
        }
    }

}

class testReject{
    public static void main(String[] args) throws IllegalAccessException {
        UserInfo userInfo = new UserInfo();
        String userName = "userName";
        Class<? extends UserInfo> aClass = userInfo.getClass();
        for(Field field : aClass.getDeclaredFields()){
            field.setAccessible(true);
            if(userName.equals(field.getName())){
                field.set(userInfo,"gxm");
            }
            System.out.println(field.getName());
        }
        System.out.println(userInfo);
    }
}
