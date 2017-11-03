# AOP
## 添加依赖

```
repositories {
    jcenter()
}
compile ('com.devin:tool-aop:1.0.0'){
    exclude group: 'com.android.support', module: 'appcompat-v7'
}
```
## 配置
#### 1、Project - build.gradle 配置

```
dependencies {
    ...
    classpath 'org.aspectj:aspectjtools:1.8.10'
    classpath 'org.aspectj:aspectjweaver:1.8.10'
}
```
#### 2、App - build.gradle配置
```
放在最后就可以

final def variants = project.android.applicationVariants

variants.all { variant ->
    JavaCompile javaCompile = variant.javaCompile
    javaCompile.doLast {
        String[] args = ["-showWeaveInfo",
                         "-1.8",
                         "-inpath", javaCompile.destinationDir.toString(),
                         "-aspectpath", javaCompile.classpath.asPath,
                         "-d", javaCompile.destinationDir.toString(),
                         "-classpath", javaCompile.classpath.asPath,
                         "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]
        MessageHandler handler = new MessageHandler(true)
        new Main().run(args, handler)
    }
}
```

#### 3、Module - build.gradle配置

```
放在最后就可以

final def variants = project.android.applicationVariants

variants.all { variant ->
    JavaCompile javaCompile = variant.javaCompile
    javaCompile.doLast {
        String[] args = ["-showWeaveInfo",
                         "-1.8",
                         "-inpath", javaCompile.destinationDir.toString(),
                         "-aspectpath", javaCompile.classpath.asPath,
                         "-d", javaCompile.destinationDir.toString(),
                         "-classpath", javaCompile.classpath.asPath,
                         "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]
        MessageHandler handler = new MessageHandler(true)
        new Main().run(args, handler)
    }
}
```
## 如何使用
#### 1、权限申请@Permission

```
@Permission(value = Manifest.permission.CALL_PHONE, must = false)
public void method() {}

参数介绍

1、value 是 权限的名称
2、must false 表示如果用户没有授权 申请权限弹窗 点击可消失 true 表示如果用户没有授权 申请权限弹窗 一直显示 默认值是 false
```
#### 2、权限申请@SingleClick

```
@SingleClick(value = 1000)
public void onClick(View v) {}

参数介绍

value 默认值 1000 毫秒 也可以自己设置（单位是毫秒）

注意事项：@SingleClick要在View的点击事件方法上面加
```
#### 3、打印方法耗时@SpendTimeLog

```
打印方法耗时
@SpendTimeLog
private void method() {}

查看Logcat看方法耗时
```
## 混淆配置

```
-dontwarn com.devin.tool_aop.**
-keep class com.devin.tool_aop.** {*;}
```


