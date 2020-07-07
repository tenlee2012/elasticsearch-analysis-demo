package com.itenlee.search.analysis.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.itenlee.search.analysis.help.JSONUtil;

import java.io.IOException;
import java.security.PrivilegedActionException;
import java.util.List;

/**
 * @author tenlee
 * @date 2020/7/7
 */
public class MyCustomTokenizerService {


    public List<Term> getTextTokenizer(String line, Configuration configuration) throws IOException {
        // todo 自己的逻辑处理，对line分词。
        String json = "";
        try {
            return JSONUtil.parseJSON(json, new TypeReference<List<Term>>() {
            });
        } catch (PrivilegedActionException e) {
            throw new IOException(e);
        }
    }
}
