# 一个示例 ES 分词器
## 文档地址
[掘金](https://juejin.im/post/5efda55d5188252e685d217e)
[简书](https://www.jianshu.com/p/f36f633eea1b)

## 使用
1. 根据需求修改分词器名称 [AnalysisDemoPlugin](./src/main/java/org/elasticsearch/plugin/analysis/demo/AnalysisDemoPlugin.java)
2. 实现你的分词算法 [MyCustomTokenizerService](./src/main/java/com/itenlee/search/analysis/core/MyCustomTokenizerService.java)
3. 测试[DemoTokenizerTest](./src/test/java/com/itenlee/search/analysis/DemoTokenizerTest.java)
4. 打包 `mvn clean package`
5. 复制target目录下压缩包解压到es plugins目录