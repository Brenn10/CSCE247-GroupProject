package utilities;

import java.util.UUID;

public class UUIDGen {
    public static int getIntUUID() {
        return Math.abs((int)UUID.randomUUID().getLeastSignificantBits());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(getIntUUID());
        }
    }
}