package com.DecupleProject;

// Made By DeveloperDecuple
// Copyright (c) 2020~2021 Team Decuple. All Rights Reserved.

public class Hangeul {

    private static boolean existsIn(String a, String[] b) {

        for (Object n : b) {
            if (a.equals(n)) return true;
        }

        return false;

    }

    private static int getIndex(String a, String[] b) {

        int i = 0;

        for (Object n : b) {
            if (a.equals(n)) return i;
            i++;
        }

        return -1;

    }

    public static String qwertyToHangeul(String input) {
        // 'dkssudgktpdy' 와 같은 것들을 '안녕하세요' 로 변환시켜 줍니다.

        StringBuilder sb = new StringBuilder();

        char[] inputChar = input.toCharArray();

        // dks sud gk tp dy
        // 0123456789012345
        //

        for (int i = 0; i < inputChar.length; i++) {

            char nowChar = inputChar[i];

            try {

                String[] qw_a = {"r", "R", "s", "e", "E", "f", "a", "q", "Q",
                        "t", "T", "d", "w", "W", "c", "z", "x", "b", "g"}; // 초성
                String[] qw_b = {"k", "o", "i", "O", "j", "p", "u", "P", "h",
                        "hk", "ho", "hl", "y", "n", "nj", "np", "nl",
                        "b", "m", "ml", "l"}; // 중성
                String[] qw_c = {"", "r", "R", "rt", "s", "sw", "sg", "e", "f",
                        "fr", "fa", "fq", "ft", "fx", "fv", "fg", "a", "q", "qt",
                        "t", "T", "d", "w", "c", "z", "x", "v", "g"}; // 종성

                String nowCharStr = String.valueOf(inputChar[i]);

                if (!existsIn(nowCharStr, qw_a) && !existsIn(nowCharStr, qw_b) && !existsIn(nowCharStr, qw_c)) {
                    sb.append(nowChar);
                } else {

                    int cho = getIndex(String.valueOf(nowChar), qw_a); // 초성 인덱스
                    int jun = getIndex(String.valueOf(inputChar[i + 1]), qw_b); // 중성 인덱스
                    int jon = 0; // 종성 인덱스

                    try {
                        jon = i + 3 >= inputChar.length ? 0 :
                                existsIn(String.valueOf(inputChar[i + 3]), qw_b) ? 0 :
                                        existsIn(String.valueOf(inputChar[i + 4]), qw_c) ?
                                                getIndex(String.valueOf(inputChar[i + 2]) + String.valueOf(inputChar[i + 3]), qw_c) :
                                                getIndex(String.valueOf(inputChar[i + 2]), qw_c); // 인덱스 구하는 코드

                        if (jon < 0) jon = 0; // 오류 방지
                        if (
                                jon == 0
                                        && !existsIn(String.valueOf(inputChar[i + 3]), qw_a)
                                        && !existsIn(String.valueOf(inputChar[i + 3]), qw_b)
                                        && !existsIn(String.valueOf(inputChar[i + 3]), qw_c)
                        ) {
                            jon = getIndex(String.valueOf(inputChar[i + 2]), qw_c);

                            if (jon < 0) jon = 0;
                        }
                        if (
                                !existsIn(String.valueOf(inputChar[i + 2]), qw_a)
                                        && !existsIn(String.valueOf(inputChar[i + 2]), qw_b)
                                        && !existsIn(String.valueOf(inputChar[i + 2]), qw_c)
                        ) {
                            jon = 0;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // ignore
                    }

                    char result = (char) (((cho * 21) + jun) * 28 + jon + 44032); // 조합
                    int resultLen = qw_a[cho].length() + qw_b[jun].length() + qw_c[jon].length();
                    i = i + resultLen - 1; // -1을 해 주는 이유 : for 는 반복할 때마다 i를 더해주기 때문.

                    sb.append(result);

                }

            } catch (ArrayIndexOutOfBoundsException e) {
                sb.append(nowChar);
            }

        }

        return sb.toString();

    }

}
