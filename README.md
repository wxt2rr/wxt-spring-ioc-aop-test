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
### 代码结构

## AOP（面向切面编程）

### 解决问题

### 思路

### 代码结构