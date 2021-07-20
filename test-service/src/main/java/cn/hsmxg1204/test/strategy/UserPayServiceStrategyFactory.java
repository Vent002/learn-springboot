package cn.hsmxg1204.test.strategy;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-17 17:02
 */
public class UserPayServiceStrategyFactory {
//    private static Map<String, Buyer> serviceMap = new ConcurrentHashMap<String,Buyer>();
//
//    public  static Buyer getByUserType(String type){
//        return serviceMap.get(type);
//    }
//
//    public static void register(String userType,Buyer userPayService){
//        Assert.notNull(userType,"userType can't be null");
//        serviceMap.put(userType,userPayService);
//    }

    public static void main(String[] args) {
        String[] array = new String[] {"apply","orange","banana","lemon"};
        Arrays.sort(array,(s1,s2) ->{
            return s1.compareTo(s2);
        });
        System.out.println(String.join(",",array));

        List<String> names = Arrays.asList("Alisa","Bob","Tom","Nancy");
        // JDK1.9 支持
        // List<String> listOf = Arrays.of("Alisa","Bob","Tom","Nancy");
        List<Person> persons = names.stream()
                .map(Person::new) // Stream<Person>
                .collect(Collectors.toList());
        System.out.println(persons);

        Stream<String> stream = Stream.of("A","B","C","E","D","G","H");
        stream.forEach(System.out::println);

        Stream<BigInteger> stream1 = Stream.generate(new FibonacciSupplier());
        stream1.limit(20).forEach(System.out::println);
        // Stream.map() 是把一个Stream 转换成另一个 Stream;
        Stream<Integer> s = Stream.of(1,2,3,4,5)
                .map(n -> n * n);

        // map(),将String 转化成localDate
        // map()方法用于将一个Stream的每个元素映射成另一个元素并转换成一个新的Stream；
        // 可以将一种元素类型转换成另一种元素类型
        String[] stringArrayDate = new String[]{
                "2021-05-24",
                "2021-06-15",
                "2018-12-04",
                "2020-06-14"
        };
        Arrays.stream(stringArrayDate).map(strDate -> strDate.replaceAll("\\s*",""))
                .map(LocalDate::parse)
                .forEach(System.out::println);

        // use filter
        // filter() 对一个Stream 的所有元素根据条件进行过滤，不满足条件的的被“过滤”掉。
        IntStream.of(1,2,3,4,5,6,8,9,10)
                .filter(n -> n % 2 != 0)
                .forEach(System.out::println);
        Stream.generate(new LocalDateSupplier())
                .limit(31)
                .filter(ldt -> ldt.getDayOfWeek() == DayOfWeek.SATURDAY || ldt.getDayOfWeek() == DayOfWeek.SUNDAY)
                .forEach(System.out::println);
        // 例子： 使用 filter() 过滤出成绩及格的同学，并打印名字；
        List<Student> students = Arrays.asList(new Student("小泉",59.9),
                new Student("小京",89.00),
                new Student("小王",82.00),
                new Student("小高",54.00),
                new Student("小何",99.00));
        students.stream()
                .filter(stu -> stu.score >= 60.00)
//                .forEach(name -> System.out.println(new String(name.name.getBytes(StandardCharsets.UTF_8))));
                .map(stuName -> stuName.name)
                .map(str -> new String(str.getBytes(StandardCharsets.UTF_8)))
                .map(Optional::ofNullable)
                .map(Optional::get)
                .forEach(System.out::println);

        // use reduce()
        // map()和filter()都是Stream的转换方法，
        // 而Stream.reduce()则是Stream的一个聚合方法，
        // 它可以把一个Stream的所有元素按照聚合函数聚合成一个结果。
        //  计算求积时，必须设置初始值 1 ；
        int sum = Stream.of(1,2,3,4,5,6,7,8,9)
                .reduce(0, Integer::sum);
        System.out.println("reduce() reslut: "+sum);

        // 使用map() reduce() 将配置文件聚合成 Map<String,String>
        List<String> props = Arrays.asList("profile=native", "debug=true", "logging=warn", "interval=500");
        Map<String,String> map = props.stream()
                .map(kv -> {

                    String[] strPropsArray = kv.split("\\=", 2);
                    System.out.println("split result is : "+strPropsArray[0] +" = " +strPropsArray[1]);
                    return Collections.singletonMap(strPropsArray[0], strPropsArray[1]);
                })
                .reduce(new HashMap<String, String>(), (m, kv) -> {
                    m.putAll(kv);
                    return m;
                });
        map.forEach((k,v) ->{
            System.out.println(k + " = " + v);
        });

        // 输出集合
        /**
         * 转换操作 map()、filter() 转换操作不会触发任何计算
         * 聚合操作 reduce(): 使Stream 会立刻输出每个元素，开始一次计算，并获得最终结果。做聚合计算后，结果不再是一个Stream，而转变为其他JAVA 对象。
         *
         */
        // 1. 输出为List
        Stream<String> toList = Stream.of("Apple"," ",null,"Bear","Orange");
        List<String> outToList = toList.filter(s1 -> s1 != null && !s1.isEmpty())
                .collect(Collectors.toList());
        System.out.println("输出集合为List: "+outToList);
        // 2. 输出为数组
        List<String> toArray = Arrays.asList("Apple", "litchi", "", "mango");
        String[] outToArray = toArray.stream()
                .toArray(String[]::new);
        System.out.println("输出集合为数组："+ outToArray);
        // 3. 输出为Map
        Stream<String> toMap = Stream.of("Appl:Apple","MS:MicroSoft");
        Map<String, String> outToMap = toMap.collect(Collectors.toMap(
                k -> k.substring(0, k.indexOf(':')),
                k -> k.substring(k.indexOf(':') + 1))
        );
        System.out.println("输出集合为Map: "+outToMap);
        // 4. 分组输出
        /**
         * 分组使用 Collectors.groupingBy(), 需要分组key,如 l -> l.substring(0,1)
         */
        List<String> toGroupList = Arrays.asList("Apple", "Banana", "Blackberry", "Avocado", "Cherry", "Apricots", "Coconut");
        Map<String, List<String>> outGroupResult = toGroupList.stream()
                .collect(Collectors.groupingBy(l -> l.substring(0, 1), Collectors.toList()));
        System.out.println("输出分组结果：" + outGroupResult);

        // 其他操作
        // 1. 排序
        /**
         * 使用 sorted()，会 返回一个新的Stream
         */
        Arrays.stream(array)
                .sorted()
                .collect(Collectors.toList())
                .forEach(arr -> System.out.println(new String(arr.getBytes(StandardCharsets.UTF_8))));
        // 2. 去重
        /**
         * 使用distinct()
         */
        Arrays.asList("A","B","A","C","D","B","E")
                .stream()
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
        // 3. 截取
        /**
         * 将一个无限的 Stream 转换成 有限的Steam;
         * skip()用于跳过当前Stream的前N个元素，limit() 用于截取当前Stream最多N个元素;
         * 将返回一个新的Stream
         */
        Arrays.asList("A","B","C","D","E","F")
                .stream()
                .skip(1L) // 跳过 A
                .limit(3L) // 截取 B,C,D
                .collect(Collectors.toList())
                .forEach(System.out::println);
        // 4. 合并
        /**
         * 将两个Stream 合并为一个Stream，使用concat()
         */
        Stream<String> stream2 = Stream.of("A", "B", "C");
        Stream<String> stream3 = Stream.of("D", "E", "F");
        Stream<String> concat = Stream.concat(stream2, stream3);
        System.out.println(concat.collect(Collectors.toList()));
        // 5. flatMap
        /**
         * 所谓flatMap()，是指把Stream的每个元素（这里是List）映射为Stream，然后合并成一个新的Stream
         */
        Stream<List<Integer>> listStream = Stream.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );
        listStream.flatMap(Collection::stream)
                .forEach(System.out::println);
        // 6. 并行
        Arrays.stream(Arrays.stream(array).parallel()
                .toArray(String[]::new))
                .sorted()
                .forEach(System.out::println);

        Integer i01 = 59;
        int i02 = 59;
        Integer i03 =Integer.valueOf(59);
        Integer i04 = new Integer(59);
        System.out.println(i01 == i02);
        System.out.println(i01 == i03);
        System.out.println(i03 == i04);
        System.out.println(i02 == i04);
    }

    static int cmp(String s1,String s2){
        return s1.compareTo(s2);
    }
    static class Person{
        String name;
        public Person(String name){
            this.name = name;
        }
        public String toString(){
            return "Person: " + this.name;
        }
    }

    // Fibonacci
    static class FibonacciSupplier implements Supplier<BigInteger>{
        static Queue<BigInteger> queue = new LinkedList<>();
        static {
            queue.add(new BigInteger("0"));
            queue.add(new BigInteger("1"));
        }
        @Override
        public BigInteger get() {
            BigInteger prev = queue.poll();
            assert prev != null;
            queue.add(prev.add(queue.peek()));
            return queue.peek();
        }
    }

    // filter weekday
    static class LocalDateSupplier implements Supplier<LocalDate>{
        LocalDate start = LocalDate.of(2021,1,1);
        int n = -1;
        @Override
        public LocalDate get() {
            n++;
            return start.plusDays(n);
        }
    }

    // 过滤成绩合格同学
    static class Student{
        private String name;
        private Double score;

        public Student(String name,Double score){
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
