# wxt-spring-ioc-aop-test

Spring IOC and AOP practice demo project
## IOC（控制反转）
### 解决问题
~~~java
public class AServiceImpl implements AService{
    
    private BService bService = new BServiceImpl();
    // 后期需要将 BServiceImpl 改成 NewBServiceImpl
    private BService bService = new NewBServiceImpl();
    //...
}
~~~

对象间的依赖关系，AService的实现类中使用到了BService接口的方法，必须将BService的实现类绑定到AService的实现类中，假如BService的实现后期进行了修改，那么AService要进行同步修改，非常不灵活。

### 思路
#### 1.怎么解决对象之类的依赖关系
我们要解决这种依赖问题，那么就是不想将new BServiceImpl()出现在AService的实现类中，那么Java中除了new关键词可以实例化对象，还可以通过什么操作实例化对象呢？就是反射，通过反射机制，我们可以在动态的实例化对象。
#### 2.如何通过反射实例化对象
假设我们通过反射进行对象的实例化，那么项目中类似AService和BService之间的依赖关系有很多，需要通过反射创建的对象也会很多，那么我们可以利用工厂模式来进行对象的实例化操作，然后工厂对外提供获取相应对应的方法，那么我们无需关注对象的创建，想要获取那个对象找工厂要就行了。
#### 3.怎么知道需要创建那些对象
反射需要创建实例对象对类的全限定名，我们可以将需要创建的对象配置信息（比如全限定名）配置到xml中，与项目代码分离，这样以后修改对象或者创建新的对象只需要修改xml的配置就可以，不需要对代码进行变动。
## AOP（面向切面编程）
### 解决问题
开发过程中，像事务控制、方法起止调用日志打印等，这些逻辑都和业务代码无关，但是由于需要，又要和业务代码强耦合，这并不是一个好的编程思想和实现，为了将这些和业务无关的代码与真正的业务代码进行解耦。
### 思路
#### 1.如何能实现将业务代码和非业务代码进行解耦
我们可以参考类似过滤器的实现，将一次对某个方法的调用前后加上对应的非业务逻辑，也就是说我们可以将AService调用BService，改成AService调用TempBService，TempBService中的方法实现非业务逻辑，然后在TempBService中在调用BService，也就是说给BService做一层代理，在代理类中实现非业务代码的执行
这样可以实现解耦
#### 2.Java中如何实现代理模式
Java中通过静态代理和动态代理可以实现代理模式，使用静态代理的话每个需要代理的类都要生成一个对应的代理类，不好维护，所以我们考虑使用动态代理
动态代理的话有两种方式：JDK动态代理和Cglib动态代理，两者都可。