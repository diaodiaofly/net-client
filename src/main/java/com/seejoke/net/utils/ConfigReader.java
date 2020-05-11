package com.seejoke.net.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author yangzhongying
 * @date 2020/5/11 13:18
 * @see com.seejoke.net.utils.ConfigReader
 */
public class ConfigReader {

    private static Log log = LogFactory.getLog(ConfigReader.class);

    /**
     * 整个ini的引用
     */
    private Map<String, Map<String, String>> map = null;
    /**
     * 当前Section的引用
     */
    private String currentSection = null;

    /**
     * 读取
     * @param path
     */
    public ConfigReader(String path) {
        map = new HashMap<>(16);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            read(reader);
        } catch (IOException e) {
            log.error("IOException", e);
            map = new HashMap<>(16);
        }
    }

    /**
     * 读取文件
     * @param reader
     */
    private void read(BufferedReader reader) throws IOException {
        String line = null;
        while((line = reader.readLine()) != null) {
            parseLine(line);
        }
    }

    /**
     * 转换
     * @param line
     */
    private void parseLine(String line) {
        line = line.trim();
        // 此部分为注释
        if (line.matches("^\\#.*$")) {
            return;
        } else if (line.matches("^\\[\\S+\\]$")) {
            // section
            String section = line.replaceFirst("^\\[(\\S+)\\]$", "$1");
            addSection(map, section);
        } else if (line.matches("^\\S+=.*$")) {
            // key ,value
            int i = line.indexOf("=");
            String key = line.substring(0, i).trim();
            String value = line.substring(i + 1).trim();
            addKeyValue(map, currentSection, key, value);
        }
    }


    /**
     * 增加新的Key和Value
     * @param map
     * @param currentSection
     * @param key
     * @param value
     */
    public void addKeyValue(Map<String, Map<String, String>> map,
            String currentSection, String key, String value) {
        if (!map.containsKey(currentSection)) {
            return;
        }
        Map<String, String> childMap = map.get(currentSection);
        if (!childMap.containsKey(key)) {
            childMap.put(key, value);
        }
    }


    /**
     * 增加Section
     * @param map
     * @param section
     */
    public void addSection(Map<String, Map<String, String>> map,
            String section) {
        if (!map.containsKey(section)) {
            currentSection = section;
            Map<String, String> childMap = new HashMap<>(16);
            map.put(section, childMap);
        }
    }

    /**
     * 获取配置文件指定Section和指定子键的值
     * @param section
     * @param key
     * @return
     */
    public String get(String section, String key) {
        if (map.containsKey(section)) {
            return get(section).getOrDefault(key, null);
        }
        return null;
    }


    /**
     * 获取配置文件指定Section的子键和值
     * @param section 块
     * @return Map<String, Map<String, String>>
     */
    public Map<String, String> get(String section) {
        return map.getOrDefault(section, null);
    }

    /**
     * 获取这个配置文件的节点和值
     * @return Map<String, Map<String, String>>
     */
    public Map<String, Map<String, String>> get() {
        return map;
    }
}