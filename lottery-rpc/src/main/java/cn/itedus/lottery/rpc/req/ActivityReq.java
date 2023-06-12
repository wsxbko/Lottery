package cn.itedus.lottery.rpc.req;

import java.io.Serializable;

/**
 * question:为什么要实现Serializable接口
 *  一般情况下，我们在定义实体类时会实现Serializable接口
 *
 * question：什么是Serializable接口
 *  这是一个对象序列化接口，一个类只有实现Serializable接口，它的对象才能被序列化
 *  实现了Serializable接口的类可以被ObjectOutputStream转换为字节流，同时也可以通过ObjectInputStream再将其解析为对象
 *
 * question：什么是序列化
 *  序列化是将对象状态转换为可保持或传输的格式的过程
 *
 * question：为什么要序列化对象
 *  把对象转换为字节序列的过程称为对象的序列化，把字节序列恢复为对象的过程称为对象的反序列化
 *  底层IO操作都是以字节流的方式进行的，所以写操作都涉及将编程语言数据类型转换为字节流，而读操作则又涉及将字节流转化为编程语言类型的特定数据类型
 *
 * question: 什么情况需要序列化
 *  当我们需要把对象的状态信息通过网络进行传输，或者需要将对象的状态信息持久化，以便将来使用时都需要把对象进行序列化。
 *
 */
public class ActivityReq implements Serializable {

        private Long activityId;

        public Long getActivityId() {
        return activityId;
        }

        public void setActivityId(Long activityId) {
        this.activityId = activityId;
        }
}
