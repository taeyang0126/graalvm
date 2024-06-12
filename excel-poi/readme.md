```shell
sdk env
```

### fat jar 打包
```shell
 mvn clean package
```

### fat jar 运行
```shell
java --enable-preview -jar target/excel-poi-fat.jar
```

### 使用 `native-image-agent` 查找所有反射、jni等配置，并重新构建native image
```shell
sdk env

mvn clean package 

# config-output-dir 表示输出目录
# experimental-class-define-support 表示支持在本地镜像中定义和加载新的类
# --enable-preview 表示启用预览特性
java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image,experimental-class-define-support --enable-preview -jar target/excel-poi-fat.jar

# debug 方式构建 native-image
mvn clean package -Pnative-debug
# release 方式构建 native-image
#mvn clean package -Pnative-release
```