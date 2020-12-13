package com.teamsayy.drmednotifier.data;

import java.util.Random;

public class GenerateRandomInt {
    private static final Random random = new Random();

    public static int get() {
        return random.nextInt(Integer.MAX_VALUE);
    }
}