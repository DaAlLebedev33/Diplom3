package steps;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataForCreateUser {
    public static String randomEmail() {
        return RandomStringUtils.randomAlphanumeric(10).toLowerCase() + "@test.com";
    }
    public static String randomName() {
        return RandomStringUtils.randomAlphanumeric(6);
    }
    public static String randomPassword(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }
}
