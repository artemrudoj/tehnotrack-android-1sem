package com.artem.hometask;

import android.content.Context;

/**
 * Created by artem on 22.03.16.
 */
public class TextGenerator {

    public static String convert(Context context, int num) {
        if (num == 0) {
            return context.getString(R.string.zeroS);
        }
        String thousands = generateThousands(context, num);
        String lte_thousands = generateLessThenThousands(context, num);
        if (thousands.length() == 0 || lte_thousands.length() == 0)
            return thousands + lte_thousands;
        else
            return thousands + " " + lte_thousands;
    }

    private static String generateLessThenThousands(Context context, int num) {
        num = getParticularExponent(num, 2) * 100 +
                getParticularExponent(num, 1) * 10 +
                getParticularExponent(num, 0);
        String ret_val = "";
        if (num == 0) {
            return ret_val;
        }
        ret_val = generate3exponent(context, num);
        if (getParticularExponent(num, 1) == 1) {
            return ret_val + " " + generateFrom10To19(context, num);
        } else {
            ret_val = ret_val + " " + generate2exponent(context, num) + " " + generate1exponent(context, num);
            return ret_val;
        }
    }

    private static String generateThousands(Context context, int num) {
        int thousands_count = getParticularExponent(num, 5) * 100 +
                getParticularExponent(num, 4) * 10 +
                getParticularExponent(num, 3);
        String ret_val = "";
        if (thousands_count == 0) {
            return ret_val;
        }
        ret_val = generate3exponent(context, thousands_count);
        if (getParticularExponent(num, 4) == 1) {
            return ret_val + " " + generateFrom10To19(context , thousands_count) + " " +
                    context.getString(R.string.thousandP);
        } else {
            ret_val = ret_val + " " + generate2exponent(context, thousands_count) + " " +
                    generate1exponentFS(context, thousands_count);
            int exponent3 = getParticularExponent(num, 3);
            if (exponent3 == 1)
                ret_val = ret_val + " " + context.getString(R.string.thousundS);
            if (exponent3 == 2 || exponent3 == 3 || exponent3 == 4)
                ret_val = ret_val + " " + context.getString(R.string.thousandPRP);

            if (exponent3 >= 5)
                ret_val = ret_val + context.getString(R.string.thousandP);
            return ret_val;
        }
    }

    private static String generate1exponent(Context context, int num) {
        int exponent = getParticularExponent(num, 0);
        switch (exponent) {
            case 0:
                return "";
            case 1:
                return context.getString(R.string.one);
            case 2:
                return context.getString(R.string.two);
            case 3:
                return context.getString(R.string.three);
            case 4:
                return context.getString(R.string.four);
            case 5:
                return context.getString(R.string.five);
            case 6:
                return context.getString(R.string.six);
            case 7:
                return context.getString(R.string.seven);
            case 8:
                return context.getString(R.string.eight);
            case 9:
                return context.getString(R.string.nine);
        }
        return "";
    }

    private static String generate1exponentFS(Context context, int num) {
        int exponent = getParticularExponent(num, 0);
        switch (exponent) {
            case 1:
                return context.getString(R.string.oneFS);
            case 2:
                return context.getString(R.string.twoFS);
            default:
                return generate1exponent(context, num);
        }
    }



    private static String generateFrom10To19(Context context, int num) {
        num = getParticularExponent(num, 1) * 10 +
                getParticularExponent(num, 0) * 1;
        switch (num) {
            case 10:
                return context.getString(R.string.ten);
            case 11:
                return context.getString(R.string.eleven);
            case 12:
                return context.getString(R.string.twelve);
            case 13:
                return context.getString(R.string.therteen);
            case 14:
                return context.getString(R.string.fourteen);
            case 15:
                return context.getString(R.string.fivteen);
            case 16:
                return context.getString(R.string.sixteen);
            case 17:
                return context.getString(R.string.seventeen);
            case 18:
                return context.getString(R.string.eighteen);
            case 19:
                return context.getString(R.string.nineteen);
        }
        return "";
    }

    private static String generate2exponent(Context context, int num) {
        num = getParticularExponent(num, 1);
        switch (num) {
            case 2:
                return context.getString(R.string.twenty);
            case 3:
                return context.getString(R.string.therty);
            case 4:
                return context.getString(R.string.fourty);
            case 5:
                return context.getString(R.string.fivty);
            case 6:
                return context.getString(R.string.sixty);
            case 7:
                return context.getString(R.string.seventy);
            case 8:
                return context.getString(R.string.eighty);
            case 9:
                return context.getString(R.string.ninety);
        }
        return "";
    }

    private static String generate3exponent(Context context, int num) {
        int exponent = getParticularExponent(num, 2);
        switch (exponent) {
            case 1:
                return context.getString(R.string.onehundred);
            case 2:
                return context.getString(R.string.twohundred);
            case 3:
                return context.getString(R.string.threehundred);
            case 4:
                return context.getString(R.string.fourhundred);
            case 5:
                return context.getString(R.string.fivehundred);
            case 6:
                return context.getString(R.string.sixhundred);
            case 7:
                return context.getString(R.string.sevenhundred);
            case 8:
                return context.getString(R.string.eighthundred);
            case 9:
                return context.getString(R.string.ninehundred);
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