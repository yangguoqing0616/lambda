package com.java8character;

import com.java8character.entity.Employee;

import java.io.PrintStream;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Character {

    /*
     * 一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->"
     * 该操作符称为箭头操作符或  Lambda 操作符 箭头操作符将 Lambda 表达式拆分成两部分：
     *
     * 左侧：Lambda 表达式的参数列表
     * 右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体
     *
     * 语法格式一：无参数，无返回值
     * 		() -> System.out.println("Hello Lambda!");
     *
     * 语法格式二：有一个参数，并且无返回值
     * 		(x) -> System.out.println(x)
     *
     * 语法格式三：若只有一个参数，小括号可以省略不写
     * 		x -> System.out.println(x)
     *
     * 语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
     *		Comparator<Integer> com = (x, y) -> {
     *			System.out.println("函数式接口");
     *			return Integer.compare(x, y);
     *		};
     *
     * 语法格式五：若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
     * 		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
     *
     * 语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
     * 		(Integer x, Integer y) -> Integer.compare(x, y);
     *
     * 上联：左右遇一括号省
     * 下联：左侧推断类型省
     * 横批：能省则省
     *
     * 二、Lambda 表达式需要“函数式接口”的支持
     * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 可以使用注解 @FunctionalInterface 修饰
     * 			 可以检查是否是函数式接口
     */


    public static void test1() {
        System.out.println("语法1 无参数无返回值");
        int num = 0;//jdk 1.7 前，必须是 final

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num);
            }
        };

        r.run();

        System.out.println("-------------------------------");

        Runnable r1 = () -> System.out.println("Hello Lambda!");
        r1.run();

        System.out.println("语法2 有一个参数无返回值");

        Consumer c = (x) -> System.out.println(x);
        c.accept("lamdba表达式 ");

        System.out.println("----------------------");
        System.out.println("语法3 若只有一个参数小括号可以不写");

        Consumer c2 = x -> System.out.println(x);
        c2.accept("省略括号");
        System.out.println("----------------------");
        System.out.println("语法4 有两个以上的参数，有返回值，并且 Lambda 体中有多条语句");
        Comparator<Integer> co = (x, y) -> {
            System.out.println("进入4");
            return Integer.compare(x, y);
        };
        co.compare(2, 5);

        System.out.println("----------------------");

        System.out.println("语法5 若lamdba 体中只有一条数据 return 和 {} 都不用写");

        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        System.out.println(comparator.compare(2, 4));

        System.out.println("----------------------");

        System.out.println("Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”");

        Comparator<Integer> comparator1 = (Integer x, Integer y) -> Integer.compare(x, y);
        System.out.println(comparator.compare(2, 4));

        System.out.println("----------------------");

        /*
        二、Lambda 表达式需要“函数式接口”的支持
            函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。
            可以使用注解 @FunctionalInterface 修饰 可以检查是否是函数式接口
     	*/
    }

    /*
     * Java8 内置的四大核心函数式接口
     *
     * Consumer<T> : 消费型接口
     * 		void accept(T t);
     *
     * Supplier<T> : 供给型接口
     * 		T get();
     *
     * Function<T, R> : 函数型接口
     * 		R apply(T t);
     *
     * Predicate<T> : 断言型接口
     * 		boolean test(T t);
     *
     */

    //Consumer<T> 消费型接口 :
    //在方法里面将接口当做参数传入,在调用的时候对接口进行实现
    public static void happy(Double db, Consumer<Double> consumer) {
        consumer.accept(db);
    }


    //Supplier<T> 供给型接口 :
    //需求：产生指定个数的整数，并放入集合中
    public static List<Integer> getList(int num, Supplier<Integer> supplier) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }

    //Function<T, R> 函数型接口：

    public static String str(String string, Function<String, String> fun) {
        return fun.apply(string);
    }

    //Predicate<T> 断言型接口：
    //需求：将满足条件的字符串，放入集合中

    public static List<String> addStr(String[] arr, Predicate<String> predicate) {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (predicate.test(arr[i])) {
                strings.add(arr[i]);
            }
        }
        return strings;
    }


    public static void test2() {
        //在调用方法的时候对接口进行实现
        happy(12.2, (x) -> System.out.println("消费的金额是" + x + "元"));
        System.out.println("----------------------");

        //Supplier<T> 供给型接口 :
        List<Integer> list = getList(100, () -> (int) (Math.random() * 100));
        for (Integer a : list) {
            System.out.println(a);
        }
        System.out.println("----------------------");

        //Function<T, R> 函数型接口：

        String wew = str("wew", (x) -> x.toUpperCase());
        System.out.println(wew);
        System.out.println("----------------------");

        String[] stringss = {"q", "qq", "www", "ssss"};
        List<String> strings1 = addStr(stringss, (x) -> x.length() > 2);
        for (String s : strings1) {
            System.out.println(s);
        }

    }

    /*
     * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
     * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
     *
     * 1. 对象的引用 :: 实例方法名
     *
     * 2. 类名 :: 静态方法名
     *
     * 3. 类名 :: 实例方法名
     *
     * 注意：
     * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
     * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
     *
     * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
     *
     * 1. 类名 :: new
     *
     * 三、数组引用
     *
     * 	类型[] :: new;
     *
     *
     */

    public static void test3() {
        System.out.println("---------------------");
        //对象的引用 :: 实例方法名
        PrintStream out = System.out;
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("测试");

        Consumer<String> consumer1 = out::println;
        consumer1.accept("测试2");
        System.out.println("--------------------------");

        Employee emp = new Employee(101, "张三", 18, 9999.99);
        Supplier<String> supplier = () -> emp.getName();
        System.out.println(supplier.get());
        System.out.println("---------------------------");
        Supplier<String> supplier1 = emp::getName;
        System.out.println(supplier1.get());

        System.out.println("----------------------------");
        ////类名 :: 静态方法名
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        System.out.println(comparator.compare(2, 3));

        Comparator<Integer> comparator1 = Integer::compare;
        System.out.println(comparator1.compare(1, 2));

        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> Math.max(x, y);
        System.out.println(biFunction.apply(1, 2));

        BiFunction<Integer, Integer, Integer> biFunction1 = Math::max;
        System.out.println(biFunction.apply(3, 4));

        System.out.println("----------------------------");

        //类名 :: 实例方法名

        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        System.out.println(bp.test("abcde", "abcde"));

        BiPredicate<String, String> bp2 = String::equals;
        System.out.println(bp2.test("abc", "abc"));


        Function<Employee, String> fun = (e) -> e.show();
        System.out.println(fun.apply(new Employee()));

        Function<Employee, String> fun2 = Employee::show;
        System.out.println(fun2.apply(new Employee()));

        System.out.println("-----------------------------------------");

        //构造器引用  1. 类名 :: new

        Supplier<Employee> supplier2 = () -> new Employee();
        System.out.println(supplier2);

        Supplier<Employee> supplier3 = Employee::new;
        System.out.println(supplier3);

        Function<String, Employee> employeeFunction = Employee::new;
        System.out.println(employeeFunction.apply("小明"));

        BiFunction<String, Integer, Employee> employeeBiFunction = Employee::new;
        System.out.println(employeeBiFunction.apply("小明", 11));

        Employee ne = new Employee("小明", 11);

        System.out.println("-----------------------------------------");
        //数组引用 类型[] :: new;

        Function<Integer, String[]> function = String[]::new;
        String[] apply = function.apply(10);
        System.out.println(apply.length);

        Function<Integer, Employee[]> function1 = Employee[]::new;
        Employee[] apply1 = function1.apply(20);
        System.out.println(apply1.length);
    }

    /*
     * 一、Stream API 的操作步骤：
     *
     * 1. 创建 Stream
     *
     * 2. 中间操作
     *
     * 3. 终止操作(终端操作)
     */

    public static void test4() {
        // 一 创建stream
        //list 有两种创建 stream的方法 1,list.stream 和 list.parallelStream()
        ArrayList<String> strings = new ArrayList<>();
        //创建一个循序流
        Stream<String> stream = strings.stream();
        //创建一个并行流
        Stream<String> stringStream = strings.parallelStream();
        System.out.println("----------------------");

        // 二 数组创建stream
        Integer[] integers = new Integer[5];
        Stream<Integer> stream1 = Arrays.stream(integers);
        System.out.println("----------------------");

        // 三 通过stream的静态方法创建stream
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
        System.out.println("----------------------");

        //创建无限流
        //迭代
        Stream<Integer> limit = Stream.iterate(0, (x) -> x + 2).limit(10);
        limit.forEach(System.out::println);
        System.out.println("----------------------");
        //生成
        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        stream4.forEach(System.out::println);

    }

    //2. 中间操作
    static List<Employee> emps = Arrays.asList(
            new Employee(102, "李四2", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    //中间操作
    public static void test5() {

        /*
         2. 中间操作
		映射
		map——接收 Lambda ， 将元素转换成其他形式或提取信息。
		接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
		flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流

		sorted()——自然排序
		sorted(Comparator com)——定制排序

	    筛选与切片
		filter——接收 Lambda ， 从流中排除某些元素。
		limit——截断流，使其元素不超过给定数量。
		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素

	 */
        List<String> strings = Arrays.asList("aa", "bb", "cc", "dd");
        strings.stream().map(String::toUpperCase).forEach(System.out::println);
        System.out.println("-----------wwwwwwwwwww-----------");

        //接受stream ,将多个stream合并到一起,组成一个stream
        Stream<Character> stream3 = strings.stream()
                .flatMap(Java8Character::filterCharacter);
        stream3.forEach(System.out::println);

        System.out.println("----------------aaaaaaaaaaaaa------------------");

        emps.stream().map((x) -> {
            System.out.println("进入sorted");
            return x.getAge();
        }).sorted().forEach(System.out::println);

        System.out.println("--------------1111111111111111111--------");

        emps.stream().sorted((x, y) -> {
            if (x.getAge().equals(y.getAge())) {
                return x.getName().compareTo(y.getName());
            } else {
                //降序排列
                return -x.getAge().compareTo(y.getAge());
            }
        }).forEach(System.out::println);
        System.out.println("----------------------");

        Stream<Employee> stream = emps.stream().filter((e) -> {
            System.out.println("进入循环");
            return e.getName().length() > 2;//需要返回值
        });
        stream.forEach(System.out::println);
        System.out.println("----------------------");

        emps.stream().filter((x) -> {
            System.out.println("进入中间操作语句");
            return x.getAge() > 8;
        }).limit(2).forEach(System.out::println);
        System.out.println("----------------------");

        emps.stream().filter((x) -> {
            System.out.println("skip 进行测试");
            return x.getAge() > 8;
        }).skip(2).forEach(System.out::println);
        System.out.println("----------------------");

        emps.stream().filter((x) -> {
            System.out.println("进入  distinct");
            return x.getAge() == 8;
        }).distinct().forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
/*
        dataList.stream().sorted(Comparator.comparing(TbDictionary::getSortCode).reversed()).collect(Collectors.toList()); //按照年龄有小到大排序
                Collections.sort(dataList, new Comparator<TbDictionary>(){
                    public int compare(TbDictionary o1, TbDictionary o2) {
                        if(o1.getSortCode() > o2.getSortCode()){
                            return -1;
                        }
                        if(o1.getSortCode() == o2.getSortCode()){
                            return 0;
                        }
                        return 1;
                    }
                });*/



        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

    //3. 终止操作
	/*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */

    static List<Employee> emps1 = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    public static void test6() {
        boolean b = emps1.stream().allMatch((x) -> {
            return x.getAge() == 8;
        });
        System.out.println(b);
        System.out.println("----------------------");

        boolean b1 = emps1.stream().anyMatch((x) -> {
            System.out.println("测试  anyMatch");
            return x.getAge() == 8;
        });
        System.out.println(b1);
        System.out.println("----------------------");

        boolean b2 = emps1.stream().noneMatch((x) -> {
            System.out.println("进入noneMatch");
            return x.getAge() == 8;
        });
        System.out.println(b2);
        System.out.println("----------------------");

        Optional<Employee> first = emps1.stream().findFirst();
    }


    static List<Employee> emps2 = Arrays.asList(
            new Employee(102, "李四", 79, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );

    //3. 终止操作
	/*
		归约
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
		collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
	 */

    public static void test7() {
        //reduce
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        System.out.println("----------------------");

        Optional<Integer> reduce1 = emps2.stream().map((x) -> x.getAge()).reduce(Integer::sum);
        System.out.println(reduce1.get());

        System.out.println("----------------------");
        //需求：搜索名字中 “六” 出现的次数
        Integer count = emps2.stream().map((x) -> {
            String name = x.getName();
            if (name.contains("六")) {
                return 1;
            } else {
                return 0;
            }
        }).reduce(0, (x, y) -> x + y);
        System.out.println(count);
        System.out.println("----------------------");
        //collect
        emps2.stream().map(Employee::getName).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("----------------------");
        emps2.stream().map(Employee::getName).collect(Collectors.toSet()).forEach(System.out::println);
        System.out.println("----------------------");
        emps2.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new)).forEach(System.out::println);
        System.out.println("----------------------");
        Optional<Double> collect = emps2.stream().map(Employee::getSalary).collect(Collectors.maxBy(Double::compareTo));
        System.out.println(collect.get());
        Optional<Employee> op = emps.stream()
                .collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(op.get());
        System.out.println("----------------------");
        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));

        System.out.println(sum);



    }

    //多级分组
    public void test8(){
        Map<Employee.Status, Map<String, List<Employee>>> collect = emps2.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() >= 60)
                        return "老年";
                    else if (e.getAge() >= 35)
                        return "中年";
                    else
                        return "成年";
                })));

        System.out.println(collect);
    }

    //分区
    public void test9(){
        Map<Boolean, List<Employee>> map = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() >= 5000));

        System.out.println(map);
    }

    //
    public void test10(){
        String str = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining("," , "----", "----"));

        System.out.println(str);
    }

    public void test11(){
        Optional<Double> sum = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.reducing(Double::sum));

        System.out.println(sum.get());
    }

    /*
     * 一、Optional 容器类：用于尽量避免空指针异常
     * 	Optional.of(T t) : 创建一个 Optional 实例
     * 	Optional.empty() : 创建一个空的 Optional 实例
     * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
     * 	isPresent() : 判断是否包含值
     * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
     * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
     * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
     * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
     */

    public static void test12(){
        Optional<Employee> employee = Optional.of(new Employee());
        System.out.println(employee.get());
       /* Optional<Employee> employee1 = Optional.of(null);
        System.out.println(employee1.get());*/
        System.out.println("----------------------");
       /* Optional<Object> empty = Optional.empty();
        System.out.println(empty.get());*/
        System.out.println("----------------------");
        Optional<Employee> employee2 = Optional.ofNullable(new Employee());
        System.out.println(employee2.get());
        System.out.println("----------------------");
        /*Optional<Employee> o = Optional.ofNullable(null);
        System.out.println(o.get());*/
        System.out.println("----------------------");

        Optional<Employee> employee1 = Optional.ofNullable(new Employee("有"));
        if(employee1.isPresent()){
            System.out.println("有");
        }

        Employee san = employee1.orElse(new Employee("张三"));
        System.out.println(san);

        Employee employee3 = employee1.orElseGet(Employee::new);
        System.out.println(employee3);
        System.out.println("----------------------");
        Optional<Employee> employee4 = Optional.ofNullable(new Employee("有"));
        Optional<String> s = employee4.map((x) -> {
            return x.getName();
        });
        if(s.isPresent()){
            System.out.println("yuou");
        }
        System.out.println("----------------------");
        Optional<String> s1 = employee4.flatMap((x) -> Optional.of(x.getName()));
        System.out.println(s1.get());

    }
    public  static void main(String[] args) {

        test5();

    }

}
