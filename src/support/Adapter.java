package support;


import beans.interfaces.FI_Adapter_onPosition;

import java.util.ArrayDeque;

import static support.constants.Constants.*;

public class Adapter {
    public static final FI_Adapter_onPosition NUMBER = Character::isDigit;
    public static final FI_Adapter_onPosition LETTER = Character::isAlphabetic;
    public static final FI_Adapter_onPosition BRACKET = (c) -> c == '(';
    public static String adapt(String s) {
        return adaptPow(s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY));
    }

    /*private static String adaptPow(String input) {
        StringBuilder result = new StringBuilder();
        if (input.contains(POW)) {
            char[] a = input.toCharArray();
            StringBuilder current = new StringBuilder();
            StringBuilder tmp = new StringBuilder();

            boolean lb = false, rb = false, lb_r = false, rb_r = true;
            int lb_i = 0, lb_c = 0, b_r_c = 0;

            for (int i = 0; i < a.length; i++) {
                current.append(ELEMENT.readElement(a, i, ELEMENT.getType(a[i])));
                if (current.toString().equals(POW)) {
                    if (a[i] != a.length - 1) {
                        if (NUMBER.in(a[i + 1]) || LETTER.in(a[i + 1])) {
                            tmp.append(current).append(LEFT_BRACKET);
                            lb_i = tmp.lastIndexOf(LEFT_BRACKET);
                            current.setLength(0);
                        }
                        if (!BRACKET.in(a[i + 1])) {
                            if (!lb) lb = true;
                            lb_c++;
                        } else {
                            tmp.append(current);
                            current.setLength(0);
                        }
                    }
                } else if (lb) {
                    if (OPERATOR.found(current)) {
                        if (!OPERATOR.isBracket(current)) {
                            if (!lb_r) {
                                if (!current.toString().equals(POW)) {
                                    if (i != a.length - 1) {
                                        if (!BRACKET.in(a[i + 1])) {
                                            tmp.append(RIGHT_BRACKET);
                                            lb_c--;
                                            rb = true;

                                        }
                                        if (!rb && BRACKET.in(a[i + 1])) {
                                            tmp.deleteCharAt(lb_i);
                                            lb_c--;
                                        }
                                    }
                                    while (lb_c > 0) {
                                        tmp.append(RIGHT_BRACKET);
                                        lb_c--;
                                    }
                                    result.append(tmp).append(current);
                                    tmp.setLength(0);
                                    lb = false;
                                }
                            }
                        } else {
                            if (current.toString().equals(LEFT_BRACKET)) {
                                lb_r = true; rb_r = false;
                            }
                            if (current.toString().equals(RIGHT_BRACKET)) {
                                if (lb && lb_i == 1)  {
                                    tmp.deleteCharAt(lb_i);
                                    lb_c--;
                                }
                                rb_r = true; lb_r = false;
                            }
                        }
                        if (!rb && lb) {
                            tmp.append(current);
                        }
                        if (rb) rb = false;
                        current.setLength(0);
                    } else {
                        if (i == a.length - 1) {
                            result.append(tmp.deleteCharAt(lb_i))
                                    .append(current).append(RIGHT_BRACKET);
                            return result.toString();
                        } else  {
                            tmp.append(current);
                            current.setLength(0);
                        }
                    }
                } else {
                    result.append(current);
                    current.setLength(0);
                }
            }
        }
        return result.toString();
    }*/

    private static String adaptPow(String expression) {
        char[] a = expression.toCharArray();
        final int END = a.length - 1;
        StringBuilder tmp = new StringBuilder();
        String current;
        StringBuilder result = new StringBuilder();
        ArrayDeque<String> pows = new ArrayDeque<>();
        boolean lb = false;

        for (int i = 0; i < a.length; i++) {
            current = ELEMENT.readElement(a, i, ELEMENT.getType(a[i]));
            if (OPERATOR.found(current)) {
                if (i != END) {
                    if (current.equals(POW)) {
                        if (NUMBER.in(a[i + 1]) || LETTER.in(a[i + 1])) {
                            //tmp.append(current).append(LEFT_BRACKET);
                            lb = true;
                        }

                        pows.push(current);
                    }
                }
                result.append(lb ? current + LEFT_BRACKET : current);
                //if (tmp.length() > 0) tmp.setLength(0);
                if (pows.size() > 1) {
                    while (!pows.isEmpty()) {
                        result.append(RIGHT_BRACKET);
                        pows.pop();
                    }
                    lb = false;
                }
            } else {
                if (i != END && POW.equals(String.valueOf(a[i + 1]))) {
                    /*if ()*/
                }
                result.append(current);
                if (pows.size() > 1) {
                    while (!pows.isEmpty()) {
                        tmp.append(RIGHT_BRACKET);
                        pows.pop();
                    }
                    lb = false;
                }
            }
        }
        return result.toString();
    }
}
