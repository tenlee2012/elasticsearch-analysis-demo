package com.itenlee.search.analysis.core;

import com.itenlee.search.analysis.help.ESPluginLoggerFactory;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.io.PathUtils;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.plugin.analysis.demo.AnalysisDemoPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

/**
 * @author tenlee
 * @date 2020/6/3
 */
public class Configuration {
    private static final Logger logger = ESPluginLoggerFactory.getLogger(Configuration.class.getName());

    private Environment environment;

    private Settings settings;

    /**
     * 是否启用索引（细颗粒度）模式
     */
    private boolean indexMode;
    /**
     * 索引名称
     */
    private String indexName;

    private static Path confDir;

    private static Properties props;

    /**
     * 配置文件名
     */
    private final static String FILE_NAME = "DemoAnalyzer.cfg.xml";

    /**
     * @param indexSettings
     * @param name          分词器名称
     * @param env
     * @param settings
     */
    @Inject
    public Configuration(IndexSettings indexSettings, String name, Environment env, Settings settings) {
        this.environment = env;
        this.settings = settings;
        initialProperties(env);

        // index settings里面的配置参数
        /**
         * settings": {
         *       "index": {
         *         "analysis": {
         *             "my_demo_token": {
         *                   "type": "demo_tokenizer",
         *                   "enableIndexMode": "true"
         *              }
         *          }
         *      }
         *  }
         */
        this.indexMode = settings.get("enableIndexMode", "false").equals("true");
        if (indexSettings != null) {
            this.indexName = indexSettings.getIndex().getName();
        }
    }

    private static Path getConfigInPluginDir() {
        return PathUtils
                .get(new File(AnalysisDemoPlugin.class.getProtectionDomain().getCodeSource().getLocation().getPath())
                        .getParent(), "config")
                .toAbsolutePath();
    }


    private static void initialProperties(Environment env) {
        if (props == null) {
            synchronized (Configuration.class) {
                if (props == null) {
                    props = new Properties();
                    confDir = env.configFile().resolve(AnalysisDemoPlugin.PLUGIN_NAME);
                    Path configFile = confDir.resolve(FILE_NAME);

                    InputStream input = null;
                    try {
                        logger.info("try load config from {}", configFile);
                        input = new FileInputStream(configFile.toFile());
                    } catch (FileNotFoundException e) {
                        confDir = getConfigInPluginDir();
                        configFile = confDir.resolve(FILE_NAME);
                        try {
                            logger.info("try load config from {}", configFile);
                            input = new FileInputStream(configFile.toFile());
                        } catch (FileNotFoundException ex) {
                            logger.error("demo-analyzer", e);
                        }
                    }
                    if (input != null) {
                        try {
                            props.loadFromXML(input);
                        } catch (IOException e) {
                            logger.error("demo-analyzer", e);
                        }
                    }
                }
            }
        }
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public boolean isIndexMode() {
        return indexMode;
    }

    public Configuration setIndexMode(boolean indexMode) {
        this.indexMode = indexMode;
        return this;
    }


    @Override
    public String toString() {
        return "Configuration{" +
                "environment=" + environment +
                ", settings=" + settings +
                ", indexMode=" + indexMode +
                ", indexName='" + indexName + '\'' +
                '}';
    }
}
