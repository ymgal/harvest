# harvest
Obtain galgame archive data.

### v2.1.0 / Spring Boot 4 接入准备

- 编译目标 Java 21；本次依赖检查使用指定的 JDK 21。Harvest 是普通 Java 库，没有 Spring 自动配置代码。
- 按 Boot 4.1.1 的版本对齐 Jackson 2.21.5、Jakarta Validation 3.1.1 和 Lombok 1.18.46。校验器由宿主的 `spring-boot-starter-validation` 提供，Hibernate Validator 9.1.3.Final 仅用于本库测试。
- 公开接口暴露 Jackson 2 的 `TypeReference` / `ObjectMapper`，因此保留库内 Jackson 2；Jackson 3.1.5 只作为测试依赖，用于检查两代映射器解释同一模型的行为。

后续构建阶段在 JDK 21 环境执行 `mvn clean install -Dmaven.test.skip=true`，宿主切换 Boot 4 时再引用：

```xml
<dependency>
    <groupId>com.ymgal</groupId>
    <artifactId>harvest</artifactId>
    <version>v2.1.0</version>
</dependency>
```

离线回归命令：`mvn test -DskipTests=false "-Dtest=JakartaValidationCompatibilityTest,JacksonCompatibilityTest"`。覆盖嵌套校验、错误消息、空值/空集合、Jackson 注解、泛型响应及库内 JSON 行为，不发起网络抓取。现有其他测试包含网络抓取，不纳入这条命令。

已完成有效 POM、依赖树解析和源码差异检查；没有编译、安装或执行测试，以上命令是后续操作说明。其他开发机和 CI 需要先构建安装本版本，或从团队制品库获取。

### 历史版本

- v2.0.0：Java 17 / Jakarta Validation 3，删除未使用的旧 Persistence API，测试使用 Hibernate Validator 8。当前 Boot 3.5.16 宿主已使用该版本完成验收。
- v1.0.0：旧 javax Validation 版本，与 Jakarta 消费方不兼容。

<br>

### 注意
* 此项目使用openjdk运行可能出现证书问题（the trustAnchors parameter must be non-empty.）这是由于程序内访问SSL接口导致的。
