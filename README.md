# Neutron
`neutron` is a event engine, which delivers and handles events along with paths in event graph, making handle variant events easy like cooking.

`neutron`是一个事件引擎，能够依照事件图处理各种消息事件，易学习，易使用，轻量高效。其设计目的在于使复杂事件的处理变得像做饭一样简单。

# How to use

## Java （base maven）

### Import maven

```pom
  <dependency>
    <groupId>io.github.chonmb</groupId>
    <artifactId>neutron</artifactId>
    <version>${neutron.version}</version>
  </dependency>
```
### Start Engine

```java
  EventEngineApplication application=new EventEngineApplication();
  application.run();
```

### Create & Handle Event

```java
  // create a event named `topic` and deliver it to handle
  application.deliverEvent(application.createEvent("topic"));
```
