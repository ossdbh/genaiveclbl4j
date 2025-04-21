package io.github.ossdbh.genaiveclbl4j.enums;

import io.github.ossdbh.genaiveclbl4j.constants.Constants;

import java.util.HashMap;
import java.util.Map;

public enum JavaStandardLibraryClassEnum {
    JAVA_LANG_OBJECT(Constants.JAVA_LANG_OBJECT_SIMPLECLASSNAME, Constants.JAVA_LANG_OBJECT_CLASSNAME),
    JAVA_UTIL_LIST(Constants.JAVA_UTIL_LIST_SIMPLECLASSNAME, Constants.JAVA_UTIL_LIST_CLASSNAME),
    JAVA_UTIL_ARRAYLIST(Constants.JAVA_UTIL_ARRAYLIST_SIMPLECLASSNAME, Constants.JAVA_UTIL_ARRAYLIST_CLASSNAME),
    JAVA_UTIL_MAP(Constants.JAVA_UTIL_MAP_SIMPLECLASSNAME, Constants.JAVA_UTIL_MAP_CLASSNAME),
    JAVA_UTIL_HASHMAP(Constants.JAVA_UTIL_HASHMAP_SIMPLECLASSNAME, Constants.JAVA_UTIL_HASHMAP_CLASSNAME);

    public static Map<String, JavaStandardLibraryClassEnum> collectionsClassesMap = new HashMap<>();

    static {
        collectionsClassesMap.put(Constants.JAVA_UTIL_LIST_CLASSNAME, JavaStandardLibraryClassEnum.JAVA_UTIL_LIST);
        collectionsClassesMap.put(Constants.JAVA_UTIL_ARRAYLIST_CLASSNAME, JavaStandardLibraryClassEnum.JAVA_UTIL_ARRAYLIST);
        collectionsClassesMap.put(Constants.JAVA_UTIL_MAP_CLASSNAME, JavaStandardLibraryClassEnum.JAVA_UTIL_MAP);
        collectionsClassesMap.put(Constants.JAVA_UTIL_HASHMAP_CLASSNAME, JavaStandardLibraryClassEnum.JAVA_UTIL_HASHMAP);
    }

    private String shortName;
    private String longName;

    JavaStandardLibraryClassEnum(String shortName, String longName) {
        this.shortName = shortName;
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

}
