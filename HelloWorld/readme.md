### graalvm helloWorld

[native-image](https://www.graalvm.org/latest/reference-manual/native-image/)

### java必须是graalvm22
```shell
sdk env
```

### fat jar 打包
```shell
 mvn clean package
```

### native image 打包
```shell
 mvn clean package -Pnative
```

### 执行可执行文件
```shell
./target/HelloWorld
```

### 使用 `native-image-agent` 查找所有反射、jni等配置，并重新构建native image
```shell
sdk env

mvn clean package 

# config-output-dir 表示输出目录，一般用在第一次输出
java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image -jar target/HelloWorld-1.0-SNAPSHOT.jar StringReverser reverse "hello"

# 由于代码可能有多个分支，因为参数走不同的分支，json文件等需要进行合并
# 使用 config-merge-dir
java -agentlib:native-image-agent=config-merge-dir=src/main/resources/META-INF/native-image -jar target/HelloWorld-1.0-SNAPSHOT.jar StringCapitalizer capitalize "hello"

# 重新构建native image
mvn clean package -Pnative
```

