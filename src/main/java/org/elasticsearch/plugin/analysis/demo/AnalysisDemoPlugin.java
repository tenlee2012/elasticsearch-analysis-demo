package org.elasticsearch.plugin.analysis.demo;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.index.analysis.demo.DemoAnalyzerProvider;
import org.elasticsearch.index.analysis.demo.DemoTokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tenlee
 * @date 2020/5/28
 */
public class AnalysisDemoPlugin extends Plugin implements AnalysisPlugin {

    // todo 改为自己的插件名字所在目录
    public static String PLUGIN_NAME = "analysis-demo";

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
        Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> extra = new HashMap<>();

        // todo 改为自己的分词器名字
        extra.put("demo_tokenizer", DemoTokenizerFactory::getTokenizerFactory);

        return extra;
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> extra = new HashMap<>();

        // todo 改为自己的分词器名字
        extra.put("demo_analyzer", DemoAnalyzerProvider::getAnalyzerProvider);

        return extra;
    }
}
