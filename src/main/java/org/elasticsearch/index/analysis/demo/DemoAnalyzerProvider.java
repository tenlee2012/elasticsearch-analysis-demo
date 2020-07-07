package org.elasticsearch.index.analysis.demo;

import com.itenlee.search.analysis.core.Configuration;
import com.itenlee.search.analysis.core.DemoAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;
import org.elasticsearch.index.analysis.AnalyzerProvider;

/**
 * @author tenlee
 * @date 2020/5/28
 */
public class DemoAnalyzerProvider extends AbstractIndexAnalyzerProvider<DemoAnalyzer> {
    private final DemoAnalyzer analyzer;

    /**
     * Constructs a new analyzer component, with the index name and its settings and the analyzer name.
     *
     * @param indexSettings the settings and the name of the index
     * @param name          The analyzer name
     * @param settings
     */
    public DemoAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings, boolean indexMode) {
        super(indexSettings, name, settings);
        Configuration configuration = new Configuration(indexSettings, name, env, settings).setIndexMode(indexMode);

        analyzer = new DemoAnalyzer(configuration);
    }

    public static AnalyzerProvider<? extends Analyzer> getAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        return new DemoAnalyzerProvider(indexSettings, env, name, settings, false);
    }


    @Override
    public DemoAnalyzer get() {
        return analyzer;
    }
}
