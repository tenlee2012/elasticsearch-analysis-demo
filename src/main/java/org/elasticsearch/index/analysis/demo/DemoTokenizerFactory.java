package org.elasticsearch.index.analysis.demo;

import com.itenlee.search.analysis.core.Configuration;
import com.itenlee.search.analysis.core.DemoTokenizer;
import com.itenlee.search.analysis.help.ESPluginLoggerFactory;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenizerFactory;
import org.elasticsearch.index.analysis.TokenizerFactory;

/**
 * @author tenlee
 * @date 2020/5/28
 */
public class DemoTokenizerFactory extends AbstractTokenizerFactory {

    private static final Logger logger = ESPluginLoggerFactory.getLogger(DemoTokenizerFactory.class.getName());

    private Configuration configuration;

    /**
     * 构造函数
     * @param indexSettings 索引配置
     * @param name 分析器或者分词器名称。如果是自定义分析器，则为自定义分析器名称
     * @param env es环境配置
     * @param settings 自定义分析器配置参数
     */
    public DemoTokenizerFactory(IndexSettings indexSettings, String name, Environment env, Settings settings) {
        super(indexSettings,  settings, name);
        configuration = new Configuration(indexSettings, name, env, settings);
    }

    public static TokenizerFactory getTokenizerFactory(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        return new DemoTokenizerFactory(indexSettings, name, environment, settings);
    }

    @Override
    public Tokenizer create() {
        return new DemoTokenizer(configuration);
    }
}
