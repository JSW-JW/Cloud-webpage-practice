package com.udacity.jwdnd.course1.cloudstorage.utils;

import java.util.UUID;

public class CommonUtils {
    public static String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
