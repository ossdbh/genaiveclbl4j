package io.github.ossdbh.genaiveclbl4j.enums;

import io.github.ossdbh.genaiveclbl4j.constants.Constants;

public enum JavaStandardLibraryClassEnum {
    JAVA_LANG_OBJECT(Constants.JAVA_LANG_OBJECT_SIMPLECLASSNAME, Constants.JAVA_LANG_OBJECT_CLASSNAME),
    JAVA_UTIL_LIST(Constants.JAVA_UTIL_LIST_SIMPLECLASSNAME, Constants.JAVA_UTIL_LIST_CLASSNAME),
    JAVA_UTIL_ARRAYLIST(Constants.JAVA_UTIL_ARRAYLIST_SIMPLECLASSNAME, Constants.JAVA_UTIL_ARRAYLIST_CLASSNAME);

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
