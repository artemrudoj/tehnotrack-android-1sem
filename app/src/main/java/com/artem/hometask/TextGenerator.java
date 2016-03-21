package com.artem.hometask;

/**
 * Created by artem on 22.03.16.
 */
public class TextGenerator {

    static String convert(int num) {
        if (num == 0) {
            return "ноль";
        }
        String thousands = generateThousands(num);
        String lte_thousands = generateLessThenThousands(num);
        if (thousands.length() == 0 || lte_thousands.length() == 0)
            return thousands + lte_thousands;
        else
            return thousands + " " + lte_thousands;
    }

    static String generateLessThenThousands(int num) {
        num = getParticularExponent(num, 2) * 100 +
                getParticularExponent(num, 1) * 10 +
                getParticularExponent(num, 0);
        String ret_val = "";
        if (num == 0) {
            return ret_val;
        }
        ret_val = generate3exponent(num);
        if (getParticularExponent(num, 1) == 1) {
            return ret_val + " " + generateFrom10To19(num);
        } else {
            ret_val = ret_val + " " + generate2exponent(num) + " " + generate1exponent(num);
            return ret_val;
        }
    }

    static String generateThousands(int num) {
        int thousands_count = getParticularExponent(num, 5) * 100 +
                getParticularExponent(num, 4) * 10 +
                getParticularExponent(num, 3);
        String ret_val = "";
        if (thousands_count == 0) {
            return ret_val;
        }
        ret_val = generate3exponent(thousands_count);
        if (getParticularExponent(num, 4) == 1) {
            return ret_val + " " + generateFrom10To19(thousands_count) + " тысяч";
        } else {
            ret_val = ret_val + " " + generate2exponent(thousands_count) + " " + generate1exponentFS(thousands_count);
            int exponent3 = getParticularExponent(num, 3);
            if (exponent3 == 1)
                ret_val = ret_val + " тысяча";
            if (exponent3 == 2 || exponent3 == 3 || exponent3 == 4)
                ret_val = ret_val + " тысячи";

            if (exponent3 >= 5)
                ret_val = ret_val + " тысяч";
            return ret_val;
        }
    }

    private static String generate1exponent(int num) {
        int exponent = getParticularExponent(num, 0);
        switch (exponent) {
            case 0:
                return "";
            case 1:
                return "один";
            case 2:
                return "два";
            case 3:
                return "три";
            case 4:
                return "четыре";
            case 5:
                return "пять";
            case 6:
                return "шесть";
            case 7:
                return "семь";
            case 8:
                return "восемь";
            case 9:
                return "девять";
        }
        return "";
    }

    private static String generate1exponentFS(int num) {
        int exponent = getParticularExponent(num, 0);
        switch (exponent) {
            case 1:
                return "одна";
            case 2:
                return "две";
            default:
                return generate1exponent(num);
        }
    }



    private static String generateFrom10To19(int num) {
        num = getParticularExponent(num, 1) * 10 +
                getParticularExponent(num, 0) * 1;
        switch (num) {
            case 10:
                return "десять";
            case 11:
                return "одиннадцать";
            case 12:
                return "двенадцать";
            case 13:
                return "тринадцать";
            case 14:
                return "четырнадцать";
            case 15:
                return "пятнадцать";
            case 16:
                return "шестнадцать";
            case 17:
                return "семнадцать";
            case 18:
                return "восемнадцать";
            case 19:
                return "девятнадцать";
        }
        return "";
    }

    private static String generate2exponent(int num) {
        num = getParticularExponent(num, 1);
        switch (num) {
            case 2:
                return "двадцать";
            case 3:
                return "тридцать";
            case 4:
                return "сорок";
            case 5:
                return "пятьдесят";
            case 6:
                return "шестьдесят";
            case 7:
                return "семдесят";
            case 8:
                return "восемьдесят";
            case 9:
                return "девяносто";
        }
        return "";
    }

    private static String generate3exponent(int num) {
        int exponent = getParticularExponent(num, 2);
        switch (exponent) {
            case 1:
                return "сто";
            case 2:
                return "двести";
            case 3:
                return "триста";
            case 4:
                return "четыреста";
            case 5:
                return "пятьсот";
            case 6:
                return "шестьсот";
            case 7:
                return "семьсот";
            case 8:
                return "восемьсот";
            case 9:
                return "девятьсот";
        }
        return "";
    }

    private static int getParticularExponent(int num, int exponent) {
        int diexponented_val = 1;
        for (int i = 1; i <= exponent; i++) {
            diexponented_val *= 10;
        }
        return num / diexponented_val - (num / (diexponented_val * 10)) * 10;
    }
}