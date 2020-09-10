---
typora-copy-images-to: ./
---

# NIO简介
在 NIO 中，不再是面向流的 IO 了，而是面向**缓冲区**，它会建立一个**通道（Channel）**，该通道我们可以理解为铁路，该铁路上可以运输各种货物，而通道上会有一个缓冲区（Buffer）用于存储真正的数据，缓冲区我们可以理解为一辆火车。

**通道**（铁路）只是作为运输数据的一个连接资源，而真正存储数据的是**缓冲区（火车）**。即**通道负责传输，缓冲区负责存储。**

![img](.\7b6c515ae9484cf2904b2e4d7118e48f~tplv-k3u1fbpfcp-zoom-1.image)

|     **BIO**      |       **NIO**        |
| :--------------: | :------------------: |
| 面向流（Stream） | 面向缓冲区（Buffer） |
|     单向通道     |       双向通道       |
|     阻塞 IO      |      非阻塞 IO       |
|                  | 选择器（Selectors）  |

## 缓冲区（Buffer）

缓冲区是**存储数据**的区域，在 Java 中，缓冲区就是数组，为了可以操作不同数据类型的数据，Java 提供了许多不同类型的缓冲区，**除了布尔类型以外**，其它基本数据类型都有对应的缓冲区数组对象。

![img](.\6ada59b10bfc47b59054d5938bc4d420~tplv-k3u1fbpfcp-zoom-1.image)

|    缓冲区    |               解释               |
| :----------: | :------------------------------: |
|  ByteBuffer  |     存储**字节数据**的缓冲区     |
|  CharBuffer  |     存储**字符数据**的缓冲区     |
| ShortBuffer  |    存储**短整型数据**的缓冲区    |
|  IntBuffer   |     存储**整型数据**的缓冲区     |
|  LongBuffer  |    存储**长整型数据**的缓冲区    |
| FloatBuffer  | 存储**单精度浮点型数据**的缓冲区 |
| DoubleBuffer | 存储**双精度浮点型数据**的缓冲区 |

分配一个缓冲区的方式都高度一致：使用`allocate(int capacity)`方法。

例如需要分配一个 1024 大小的字节数组，代码就是下面这样子。

```java
ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
```

缓冲区**读写数据**的两个核心方法：

- put()：将数据写入到缓冲区中
- get()：从缓冲区中读取数据

缓冲区的重要属性：

- **capacity**：缓冲区中**最大存储数据的容量**，一旦声明则无法改变
- **limit**：表示缓冲区中**可以操作数据的大小**，limit 之后的数据无法进行读写。必须满足 limit <= capacity
- **position**：当前缓冲区中**正在操作数据的下标位置**，必须满足 position <= limit
- **mark**：标记位置，调用 reset() 将 position 位置调整到 mark 属性指向的下标位置，**实现多次读取数据**

缓冲区为高效读写数据而提供的其它**辅助方法**：

- flip()：可以实现**读写模式的切换**，我们可以看看里面的源码
```java
public final Buffer flip() {
    limit = position;
    position = 0;
    mark = -1;
    return this;
}
```

调用 flip() 会将可操作的大小 limit 设置为当前写的位置，操作数据的起始位置 position 设置为 0，即**从头开始读取数据**。

- rewind()：可以将 position 位置设置为 0，再次读取缓冲区中的数据
- clear()：清空整个缓冲区，它会将 position 设置为 0，limit 设置为 capacity，可以**写整个缓冲区**

## 通道（Channel）

上面我们介绍过，通道是作为一种连接资源，作用是传输数据，而真正存储数据的是缓冲区，所以介绍完缓冲区后，我们来学习通道这一块。

通道是可以**双向读写**的，传统的 BIO 需要使用输入/输出流表示数据的流向，在 NIO 中可以减少通道资源的消耗。

![img](3510ef48920c44f0ade64bb1aac06954~tplv-k3u1fbpfcp-zoom-1.image)

可以通过 `getChannel()` 方法获取一个通道，支持获取通道的类如下：

- 文件 IO：FileInputStream、FileOutputStream、RandomAccessFile
- TCP 网络 IO：Socket、ServerSocket
- UDP 网络 IO：DatagramSocket

### 示例：文件拷贝案例

见NIOTest

## 选择器（Selectors）

选择器是提升 IO 性能的灵魂之一，它底层利用了**多路复用 IO**机制，让选择器可以监听多个 IO 连接，根据 IO 的状态响应到服务器端进行处理。通俗地说：**选择器可以监听多个 IO 连接，而传统的 BIO 每个 IO 连接都需要有一个线程去监听和处理。**

![img](284ec041740e4b399f6ee40a19ea386c~tplv-k3u1fbpfcp-zoom-1.image)

图中很明显的显示了在 BIO 中，每个 Socket 都需要有一个专门的线程去处理每个请求，而在 NIO 中，只需要一个 Selector 即可监听各个 Socket 请求，而且 Selector 并不是阻塞的，所以**不会因为多个线程之间切换导致上下文切换带来的开销**。

![image-20200904185402331](76c488230d8142d69dd83052ae42eac5~tplv-k3u1fbpfcp-zoom-1.image)

在 Java NIO 中，选择器是使用 `Selector` 类表示，Selector 可以接收各种 IO 连接，在 IO 状态准备就绪时，会通知该通道注册的 Selector，Selector 在**下一次轮询**时会发现该 IO 连接就绪，进而处理该连接。

Selector 选择器主要用于**网络 IO**当中，在这里我会将传统的 BIO Socket 编程和使用 NIO 后的 Socket 编程作对比，分析 NIO 为何更受欢迎。首先先来了解 Selector 的基本结构。

| **重要方法**             | **方法解析**                                                 |
| ------------------------ | ------------------------------------------------------------ |
| open()                   | 打开一个 Selector 选择器                                     |
| int select()             | 阻塞地等待就绪的通道                                         |
| int select(long timeout) | 最多阻塞 timeout 毫秒，如果是 0 则一直阻塞等待，如果是 1 则代表最多阻塞 1 毫秒 |
| int selectNow()          | 非阻塞地轮询就绪的通道                                       |

在这里，你会看到 select() 和它的重载方法是会阻塞的，如果用户进程轮询时发现没有就绪的通道，操作系统有两种做法：

- 一直等待直到一个就绪的通道，再返回给用户进程
- 立即返回一个错误状态码给用户进程，让用户进程继续运行，不会阻塞

这两种方法对应了**同步阻塞 IO** 和 **同步非阻塞 IO** 。

了解了选择器之后，它的作用就是：**监听多个 IO 通道，当有通道就绪时选择器会轮询发现该通道，并做相应的处理**。那么 IO 状态分为很多种，我们如何去识别就绪的通道是处于哪种状态呢？在 Java 中提供了**选择键（SelectionKey）**。

### 选择键（SelectionKey）

在 Java 中提供了 4 种选择键：

- SelectionKey.OP_READ：套接字通道准备好进行**读操作**
- SelectionKey.OP_WRITE：套接字通道准备好进行**写操作**
- SelectionKey.OP_ACCEPT：服务器套接字通道**接受其它通道**
- SelectionKey.OP_CONNECT：套接字通道准备**完成连接**

在 SelectionKey 中包含了许多属性

- channel：该选择键**绑定的通道**
- selector：轮询到该选择键的**选择器**
- readyOps：当前**就绪选择键的值**
- interesOps：该选择器对该通道**感兴趣的所有选择键**

选择键的作用是：**在选择器轮询到有就绪通道时，会返回这些通道的就绪选择键（SelectionKey），通过选择键可以获取到通道进行操作。**

简单了解了选择器后，我们可以结合缓冲区、通道和选择器来完成一个简易的聊天室应用。

### 示例：简易的客户端服务器通信

见 NIOCommunication

实际上我们在日常开发中很少直接用 NIO 进行编程，通常都会用 Netty，Mina 这种服务器框架，它们都是很好地 NIO 技术，对 Java 原生的 NIO 进行了上层的封装、优化，简化开发难度