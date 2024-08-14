package com.agoracharis.productservice.utils;


import java.security.SecureRandom;
import java.util.Random;

public class SkuGenerator {

    private static final String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SKU_LENGTH = 6;
    private static final Random RANDOM = new SecureRandom();

    public static String generateSku() {
        StringBuilder sku = new StringBuilder("SKU-");
        for (int i = 0; i < SKU_LENGTH; i++) {
            sku.append(ALPHANUMERIC_CHARACTERS.charAt(RANDOM.nextInt(ALPHANUMERIC_CHARACTERS.length())));
        }
        return sku.toString();
    }
}
