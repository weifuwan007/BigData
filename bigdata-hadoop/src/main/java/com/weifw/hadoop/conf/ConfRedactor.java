package com.weifw.hadoop.conf;

import org.apache.hadoop.conf.ConfigRedactor;
import org.apache.hadoop.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * 对敏感信息进行过滤，敏感信息的pattern可以通过key：hadoop.security.sensitive-config-keys进行配置
 */
public class ConfRedactor {

    public static final Logger LOG = LoggerFactory.getLogger(ConfRedactor.class);

    private final Configuration conf;

    private static final String REDACTED_TEXT = "<redacted>";

    private static final String ORIGINAL_VALUE = "Hello, World!";

    public ConfRedactor(boolean loadDefault) {

        this.conf = new Configuration(loadDefault);

    }

    public void redactor() {
        ConfigRedactor redactor = new ConfigRedactor(conf);

        String processedText;

        List<String> sensitiveKeys = Arrays.asList(
                "fs.s3a.secret.key",
                "fs.s3a.bucket.BUCKET.secret.key",
                "fs.s3a.server-side-encryption.key",
                "fs.s3a.bucket.engineering.server-side-encryption.key",
                "fs.azure.account.key.abcdefg.blob.core.windows.net",
                "fs.adl.oauth2.refresh.token",
                "fs.adl.oauth2.credential",
                "dfs.adls.oauth2.refresh.token",
                "dfs.adls.oauth2.credential",
                "dfs.webhdfs.oauth2.access.token",
                "dfs.webhdfs.oauth2.refresh.token",
                "ssl.server.keystore.keypassword",
                "ssl.server.keystore.password",
                "httpfs.ssl.keystore.pass",
                "hadoop.security.sensitive-config-keys"
        );
        for (String key : sensitiveKeys) {
            // find 是否存在与该模式匹配的下一个子序列 matches：整个字符串是否匹配上模式，匹配上则返回true
            processedText = redactor.redact(key, ORIGINAL_VALUE);
            LOG.info("processedText -> " + processedText);
        }

        List<String> normalKeys = Arrays.asList(
                "fs.defaultFS",
                "dfs.replication",
                "ssl.server.keystore.location",
                "httpfs.config.dir",
                "hadoop.security.credstore.java-keystore-provider.password-file",
                "fs.s3a.bucket.engineering.server-side-encryption-algorithm"
        );
        for (String key : normalKeys) {
            processedText = redactor.redact(key, ORIGINAL_VALUE);
            LOG.info("processedText -> " + processedText);
        }

    }


}
