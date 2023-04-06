# 《深入理解高并发编程：核心原理与案例实战》—— 随书源码

## 关于本项目

📚 本项目是《深入理解高并发编程：核心原理与案例实战》随书源码

## 关于我

>冰河，互联网资深技术专家、[TVP腾讯云最具价值专家](https://cloud.tencent.com/tvp/member/669)、数据库技术专家、分布式与微服务架构专家，[《深入理解高并发编程：核心原理与案例实战》](https://item.jd.com/13190783.html)、[《深入理解分布式事务：原理与实战》](<https://item.jd.com/12972343.html>)、[《海量数据处理与大数据技术实战》](<https://item.jd.com/12710993.html>)、[《MySQL技术大全：开发、优化与运维实战》](<https://item.jd.com/13036154.html>) 图书作者，“冰河技术”微信公众号作者，可视化多数据源数据异构中间件mykit-data作者。多年来，一直致力于分布式系统架构、微服务、分布式数据库、分布式事务与大数据技术的研究，在高并发、高可用、高可扩展性、高可维护性和大数据等领域拥有丰富的架构经验。

- :bus: 作品：[`BingheGuide | 冰河指南`](https://github.com/binghe001/BingheGuide) | [`《深入理解高并发编程：核心原理与案例实战》`](https://github.com/binghe001/mykit-concurrent-principle) | [`《深入理解高并发编程：JDK核心技术》`](https://github.com/binghe001/mykit-concurrent-jdk) | [`数据同步`](https://github.com/binghe001/mykit-data) | [`Spring核心技术`](https://github.com/binghe001/spring-annotation-book) | [`分布式限流`](https://github.com/binghe001/mykit-ratelimiter) | [`分布式锁`](https://github.com/binghe001/mykit-lock) | [`分布式缓存`](https://github.com/binghe001/mykit-cache) | [`异步并行框架`](https://github.com/binghe001/mykit-async) | [`分布式事务`](https://github.com/binghe001/mykit-transaction-message) | [`简易版IM`](https://github.com/binghe001/mykit-chat) | [`微信SDK`](https://github.com/binghe001/mykit-wechat-sdk) | [`延迟队列`](https://github.com/binghe001/mykit-delay) | [`分布式ID`](https://github.com/binghe001/mykit-serial) | [更多搜索...](https://github.com/binghe001?tab=repositories)
- :seedling: 干货：[公众号『 冰河技术 』](https://img-blog.csdnimg.cn/20210426115714643.jpg)
- :pencil: 博客：[binghe.gitcode.host](https://binghe.gitcode.host/) - 硬核文章，应有尽有！
- :tv: 视频：[B站 冰河技术](https://space.bilibili.com/517638832)
- :love_letter: 微信：[hacker_binghe](https://binghe.gitcode.host/images/personal/hacker_binghe.jpg) - 备注来意
- :feet: 我的知识星球：[手写企业级中间件项目、大厂高并发秒杀系统、并发编程、性能调优、框架源码、分布式、微服务、1对1解答、答辩晋升技巧、定期直播](https://binghe.gitcode.host/md/starball/2023-01-01-2023%E6%98%9F%E7%90%83%E6%96%B0%E5%B9%B4%E8%A7%84%E5%88%92.html)

---

👨‍💻作者：冰河
<br/>
🌱微信：hacker_binghe —— 可以添加微信备注【高并发读书加群】

>沉淀、成长、突破，帮助他人，成就自我！

## ⛳ 目录

- [Github](https://github.com/binghe001/mykit-concurrent-principle) | [Gitee](https://gitee.com/binghe001/mykit-concurrent-principle) | [GitCode](https://gitcode.net/binghe001/mykit-concurrent-principle)
- [0. 内容简述](#) - 添加冰河微信【hacker_binghe】备注【高并发读书加群】
- [1. 书籍购买](#1-书籍购买)
- [2. 勘误记录](#2-勘误记录) - 非常感谢，`各位小伙伴提交阅读过程中发现的错字和问题`。

### 1. 内容简述

<div align="center">
    <a href="#" target="_blank">
        <img src="https://img-blog.csdnimg.cn/fe76310aea734752b3b79c4df1438943.jpeg?raw=true" width="250px">
    </a>
</div>

<br/>

随着互联网的不断发展， CPU 硬件的核心数也在不断提升，并发编程越来越普及，但是并发编程并不像其他业务那样简单明了。在编写并发程序时，往往会出现各种各样的 Bug，这些Bug 常常以某种“诡异”的形式出现，然后迅速消失，并且在大部分场景下难以复现。所以，高并发编程着实是一项让程序员头疼的技术。

本书从实际需求出发，全面细致地介绍了高并发编程的基础知识、核心原理、实战案例和系统架构等内容。每个章节都根据实际需要配有相关的原理图和流程图，在实战案例篇，还会提供完整的实战案例源码。书中的每个解决方案都经过高并发、大流量的生产环境的考验，可以用于解决实际生产环境中的高并发问题。

通过阅读和学习本书，读者可以更加全面、深入、透彻地理解高并发编程知识，提高对高并发编程问题的处理能力和项目实战能力，并站在更高的层面解决高并发编程系统架构问题。

### 2. 书籍购买

**链接下单**：[https://item.jd.com/13190783.html](https://u.jd.com/KqH6S16)

本书共 20 章：

- 第 1 ~ 2 章:简单地介绍了操作系统线程调度的相关知识和并发编程的基础知识。操作系统线程调度的知识包括冯·诺依曼体系结构、 CPU 架构、操作系统线程和 Java 线程与操作系统线程的关系。并发编程的基础知识包括并发编程的基本概念、并发编程的风险和并发编程中的锁等。
- 第 3 ~ 14 章:使用大量的图解详细介绍了并发编程中各项技术的核心原理，涵盖并发编程的三大核心问题、并发编程的本质问题、原子性的核心原理、可见性与有序性核心原理、 synchronized核心原理、 AQS 核心原理、 Lock 锁核心原理、 CAS 核心原理、死锁的核心原理、锁优化、线程池核心原理和 ThreadLocal 核心原理。
- 第 15 ~ 18 章:在核心原理篇的基础上，实现了 4 个完整的实战案例，包括手动开发线程池实战、基于 CAS 实现自旋锁实战、基于读/写锁实现缓存实战和基于 AQS 实现可重入锁实战。每个实战案例都是核心原理篇的落地实现，掌握这 4 个实战案例的实现方式，有助于我们更好地在实际项目中开发高并发程序。
- 第 19 ~ 20 章: 以高并发、大流量场景下典型的分布式锁架构和秒杀系统架构为例，深入剖析了分布式锁和秒杀系统的架构细节，使读者能够站在更高的架构层面来理解高并发编程。

### 3. 勘误记录

---

感谢图书编辑：张晶、杨中兴

感谢大佬推荐（排名部分先后）：蒋涛（ CSDN 创始人、总裁）、邹欣（ CSDN 副总裁）、右军（蚂蚁金服资深技术专家）、季敏（阿里中间件分布式事务团队负责人）、于雨（ Dubbo-go 社区负责人）、张开涛（高德资深技术专家）、孙玄（奈学科技创始兼 CEO、 58 集团前技术委员会主席）、沈剑（互联网架构专家、公众号“架构师之路”作者）、程军（饿了么前技术总监、公众号“军哥手记”作者）、李鹏云（杭州任你说智能科技 CTO）、李伟（ Apache RocketMQ 北京社区联合发起人）、骆俊武（京东零售架构师）、 Mr.K（“技术领导力”公众号作者、某电商公司 CTO）、“纯洁的微笑（纯洁的微笑”公众号作者）、翟永超（公众号“程序猿 DD”维护人、《 Spring Cloud 微服务实战》作者）


<div align="center">
    <a href="https://github.com/binghe001/BingheGuide">关注冰河技术，解锁更多技能！</a>
</div>

## 我出版的书籍

<div align="center">
    <img src="https://img2023.cnblogs.com/blog/1729473/202303/1729473-20230317173305292-173690733.jpg?raw=true" width="80%">
      <div style="font-size: 18px;"><a href="https://u.jd.com/izMwOkE">《深入理解高并发编程：JDK核心技术》</a></div>
    <br/>
</div>

<div align="center">
    <img src="https://img-blog.csdnimg.cn/fe76310aea734752b3b79c4df1438943.jpeg?raw=true" width="80%">
      <div style="font-size: 18px;"><a href="https://item.jd.com/13190783.html">《深入理解高并发编程：核心原理与案例实战》</a></div>
    <br/>
</div>

<div align="center">
    <img src="https://img-blog.csdnimg.cn/5ff576f8189d46cf83c59fe4e5efc6dd.png?raw=true" width="80%">
      <div style="font-size: 18px;"><a href="https://item.jd.com/10067507938306.html">《深入高平行開發：深度原理&專案實戰》</a></div>
    <br/>
</div>

<div align="center">
    <img src="https://img-blog.csdnimg.cn/5ee367b68023466a87f66763a64a4133.jpg?raw=true" width="100%">
      <div style="font-size: 18px;"><a href="https://item.jd.com/12972343.html">《深入理解分布式事务：原理与实战》</a></div>
    <br/>
</div>

<div align="center">
    <img src="https://img-blog.csdnimg.cn/20210426115257555.png?raw=true" width="80%">
      <div style="font-size: 18px;"><a href="https://item.jd.com/13036154.html">《MySQL技术大全：开发、优化与运维实战》</a></div>
    <br/>
</div>

<div align="center">
    <img src="https://img-blog.csdnimg.cn/20200828011209412.png?raw=true" width="80%">
      <div style="font-size: 18px;"><a href="https://item.jd.com/12710993.html">《海量数据处理与大数据技术实战》</a></div>
    <br/>
</div>

## 加群交流

本群的宗旨是给大家提供一个良好的技术学习交流平台，所以杜绝一切广告！由于微信群人满 100 之后无法加入，请扫描下方二维码先添加作者 “冰河” 微信(hacker_binghe)，备注：`学习加群`。



<div align="center">
    <img src="https://binghe.gitcode.host/images/personal/hacker_binghe.jpg?raw=true" width="180px">
    <div style="font-size: 18px;">冰河微信</div>
    <br/>
</div>



## 公众号

分享各种编程语言、开发技术、分布式与微服务架构、分布式数据库、分布式事务、云原生、大数据与云计算技术和渗透技术。另外，还会分享各种面试题和面试技巧。内容在 **冰河技术** 微信公众号首发，强烈建议大家关注。

<div align="center">
    <img src="https://binghe.gitcode.host/images/personal/ice_wechat.jpg?raw=true" width="180px">
    <div style="font-size: 18px;">公众号：冰河技术</div>
    <br/>
</div>


## 视频号

定期分享各种编程语言、开发技术、分布式与微服务架构、分布式数据库、分布式事务、云原生、大数据与云计算技术和渗透技术。另外，还会分享各种面试题和面试技巧。

<div align="center">
    <img src="https://binghe.gitcode.host/images/personal/ice_video.png?raw=true" width="180px">
    <div style="font-size: 18px;">视频号：冰河技术</div>
    <br/>
</div>



## 星球

加入星球 **[冰河技术](http://m6z.cn/6aeFbs)**，可以获得本站点所有学习内容的指导与帮助。如果你遇到不能独立解决的问题，也可以添加冰河的微信：**hacker_binghe**， 我们一起沟通交流。另外，在星球中不只能学到实用的硬核技术，还能学习**实战项目**！

关注 [冰河技术](https://img-blog.csdnimg.cn/20210426115714643.jpg?raw=true)公众号，回复 `星球` 可以获取入场优惠券。

<div align="center">
    <img src="https://binghe.gitcode.host/images/personal/xingqiu.png?raw=true" width="180px">
    <div style="font-size: 18px;">知识星球：冰河技术</div>
    <br/>
</div>