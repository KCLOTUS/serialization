repository name
Java 序列化:

1. 序列化时，只对对象的状态进行保存，而不管对象的方法。
2. 当一个父类实现序列化，子类自动实现序列化，不需要显式实现Serializable接口。
3. 当一个对象的实例变量引用其它对象，序列化该对象时也把引用对象进行序列化。
4. 当某个字段被声明为transient后，默认序列化机制就会忽略该字段。
5. 对于上述已被声明为transient的字段，可以在类中添加私有方法writeObject()与readObject()两个方法来进行序列化。
