package ua.step.model;


public class FractionResponse {

    public static String Json(long numerator, long denominator) {
        if(denominator == 0) return "{\"error\":\"Denominator can't be 0\"}";

        long gcd = gcd(numerator, denominator);
        numerator = numerator / gcd;
        denominator = denominator / gcd;

        return "{\"numerator\":" + numerator + ",\"denominator\":" +  denominator + "}";
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
