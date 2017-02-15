## drawG

A light tool of converting Java Graph Object to vertices and edges text, which can be recognized by [Graph Builder](http://live.yworks.com/yfiles-for-html/2.0/databinding/graphbuilder/index.html).

### Usage
```java
// create graph builder
GraphBuilder graphBuilder = new SimpleArrayLazyNodeGraphBuilder(new Graph());

// get vertices string
String verticesString = graphBuilder.getVerticesString();

// get edges string
String edgesString = graphBuilder.getEdgesString();
```

### Description

Abstract class `GraphBuilder` is the common interface of graph builder, rewrite it if needed. 