package com.uxb2b.anz02.helper;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxb2b.util.IOUtils;

public class ConfigHelper {

    private static Logger log = LoggerFactory.getLogger(ConfigHelper.class);
    private static String rootContextPath;
    private static Properties props;

    private static String lastPackageTime;
    private static String buildRevision;
    private static String buildPath;

    private static String schema;
    private static String serverName;
    private static int serverPort = 0;
    
    public static void setPackageTime(String lastPackageTime) {
        ConfigHelper.lastPackageTime = lastPackageTime;
    }
    
    public static boolean isProdEnv() {
        return "prod".equals(getProperty("env.flag"));
    }

    public static String getPackageTime() {
        return lastPackageTime;
    }

    public static int getPageSize() {
        try {
            return Integer.parseInt(getProperty("page.size"));
        } catch (Exception e) {
            return 10;
        }
    }
    
    private static String getProperty(String key) {
        Properties props = getProperties();
        String value = props.getProperty(key);
        return value;
    }

    public static Properties getProperties() {
        if (props == null) {
            InputStream is = IOUtils.getResource("enviroment.properties");
            Properties p = new Properties();

            try {
                p.load(is);
                props = p;
            } catch (Exception e) {
                log.error(e.toString(), e);
            }
        }
        return props;
    }

    public static String getRootContextPath() {
        return rootContextPath;
    }

    public static void setRootContextPath(String rootContextPath) {
        if (ConfigHelper.rootContextPath == null) {
            ConfigHelper.rootContextPath = rootContextPath;
        }
    }

    public static String getBuildPath() {
        return buildPath;
    }

    public static void setBuildPath(String buildPath) {
        ConfigHelper.buildPath = buildPath;
    }

    public static String getBuildRevision() {
        return buildRevision;
    }

    public static void setBuildRevision(String buildRevision) {
        ConfigHelper.buildRevision = buildRevision;
    }
    
    public static String getTrustKeyStorePath() {
        return getProperty("TrustKeyStorePath");
    }

    public static String getTrustKeyStorePassword() {
        return getProperty("TrustKeyStorePassword");
    }
    
    public static String getSchema() {
        return schema;
    }

    public static void setSchema(String schema) {
        if (ConfigHelper.schema == null) {
            ConfigHelper.schema = schema;
        }
    }
    
    public static String getServerName() {
        return serverName;
    }

    public static void setServerName(String serverName) {
        if (ConfigHelper.serverName == null) {
            ConfigHelper.serverName = serverName;
        }
    }
    
    public static int getServerPort() {
        return serverPort;
    }

    public static void setServerPort(int serverPort) {
        if (ConfigHelper.serverPort == 0) {
            ConfigHelper.serverPort = serverPort;
        }
    }
    
    public static String getCbcLastSendTime() {
        return getProperty("cbc.last.send.time");
    }
    
    // 取得是否開放媒體申報
    public static String getOpenMApply() {
        return getProperty("OPEN.M.APPLY");
    }
    
}
