package com.siims.szb.service.service.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;

import org.apache.velocity.texen.util.PropertiesUtil;

public class ResouceUtil {

    // 配置文件的名称
    private static String PROPERTIES_FILE_NAME = "com/siims/vmaque/util/domain.properties";
    private static HashMap map = new HashMap();
    private static ClassLoader classLoader = PropertiesUtil.class.getClassLoader();


    /**
     * 从系统配置文件中获取系统属性。
     * 
     * @return
     */
    public static Properties getProperties() {
        Properties properties = (Properties) getProperties(PROPERTIES_FILE_NAME);
        if (properties == null) {
            System.err.println("系统无法找到必要的配置文件" + PROPERTIES_FILE_NAME);
            return null;
        }
        return properties;
    }

    /**
     * 指定文件名获取Properties对象。将在classpath中查找.properties配置文件。
     * 
     * @param fileName
     * @return
     */
    public static Properties getProperties(String fileName) {
        Properties props = (Properties) map.get(fileName);
        if (props == null) {
            props = loadPropertiesFromClassPath(fileName);
            map.put(fileName, props);
        }
        return props;
    }

    /**
     * 根据properties的名称，从配置文件中读取该properties对应的值。
     * 
     * @param propertiesName properties的名称
     * @return java.lang.String
     */
    public static String getProperty(String propertyName) {
        Properties properties = getProperties();
        if (properties == null) {
            return null;
        }
        return properties.getProperty(propertyName);
    }

    /**
     * 根据properties的名称，从配置文件中读取该properties对应的值。
     * 
     * @param propertiesName properties的名称
     * @return java.lang.String
     */
    public static String getProperty(String fileName, String propertyName) {
        Properties props = getProperties(fileName);
        if (props == null) {
            return null;
        }
        return props.getProperty(propertyName);
    }

    /**
     * 在classpath中查找properties文件，并初始化Properties对象。
     * 
     * @param filePath classpath中文件的路径。
     * @return Properties
     */
    private static Properties loadPropertiesFromClassPath(String filePath) {
        InputStream inputStream = null;
        try {
            inputStream = classLoader.getResourceAsStream(filePath);
            Properties props = new Properties();
            props.load(inputStream);
            // convertEncoding(props);
            return props;
        } catch (Exception e) {
            // e.printStackTrace(System.err);
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {

            }
        }

    }

    /**
     * 从文件系统中获取配置文件
     * 
     * @param filePath
     * @return
     */
    private static Properties loadPropertiesFromSystem(String filePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
            Properties props = new Properties();
            props.load(inputStream);
            // convertEncoding(props);
            return props;
        } catch (Exception e) {
            // e.printStackTrace(System.err);
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {

            }
        }

    }


    public static void main(String[] args) {
        Properties props = ResouceUtil.getProperties();
        Iterator iterator = new TreeSet(props.keySet()).iterator();
        System.out.println("********" + PROPERTIES_FILE_NAME + "********");
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = ResouceUtil.getProperty(key);
            System.out.println(key + "=" + value);
           
        }
        System.out.println("******************************************");
    }

    

}
