package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 10. Regular Expression Matching
 * https://leetcode.com/problems/regular-expression-matching/
 */
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        boolean ans = true;
        s = s + "+";
        char[] Schars = s.toCharArray();
        char[] Pchars = p.toCharArray();
        int j = 0;
        List<String> regular = new ArrayList<>();
        for (int i = 0; i < Pchars.length; i++) {
            switch (Pchars[i]) {
                case '.':

//                    System.out.println("i:" + i);
//                    System.out.println("Pchars:" + Arrays.toString(Pchars));
                    if (i < Pchars.length - 1 && Pchars[i + 1] == '*') {

//                        System.out.println("------------i:" + i);
                        regular.add(String.valueOf(Pchars[i]) + Pchars[i + 1]);
//                        System.out.println(regular);
                        i++;
                    } else {
                        regular.add(String.valueOf(Pchars[i]));
                    }
                    break;

//                case '*':
//                    System.out.println("i:" + i);
//                    System.out.println("Pchars:" + Arrays.toString(Pchars));
//                    strings.add(String.valueOf(Pchars[i]) );
//                    System.out.println(strings);
//                    break;

                default:
//                    System.out.println("i:" + i);
//                    System.out.println("Pchars:" + Arrays.toString(Pchars));
                    if (i < Pchars.length - 1 && Pchars[i + 1] == '*') {

//                        System.out.println("------------i:" + i);
                        regular.add(String.valueOf(Pchars[i]) + Pchars[i + 1]);
//                        System.out.println(regular);
                        i++;
                    } else {
                        regular.add(String.valueOf(Pchars[i]));
                    }
                    break;
            }
        }

        System.out.println("regularregularregularregularregular:" + regular);
        boolean b = find(regular, Schars, 0, 0);
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbb:" + b);
        return b;
    }

    boolean n = true;

    public boolean find(List regular, char[] Schars, int regularI, int ScharsI) {
        switch (regular.get(regularI).toString()) {
            case ".*":
                n = true;
                if (regularI == regular.size() - 1) {
                    return true;
                } else {
                    for (int i = ScharsI; i < Schars.length; i++) {
                        if (find(regular, Schars, regularI + 1, i)) {
                            return true;
                        }
                    }
                }

                break;
            case ".":
                n = false;
                if (regularI == regular.size() - 1) {
                    if (ScharsI == Schars.length - 2) {
//                        System.out.println("***********|.|***********true********************");
                        return true;
                    } else {
                        return false;
                    }

                } else {
                    if (ScharsI < Schars.length - 1) {

                        return find(regular, Schars, regularI + 1, ScharsI + 1);
                    } else {
                        return false;
                    }
                }

            default:
                char[] chars = regular.get(regularI).toString().toCharArray();

                if (chars.length == 2) {
                    if (regularI == regular.size() - 1) {
                        if (ScharsI == Schars.length - 1) {
                            return true;
                        } else {
                            for (int i = ScharsI; i < Schars.length - 1; i++) {
                                if (chars[0] != Schars[i]) {
                                    return false;
                                }
                            }
                            return true;
                        }
                    } else {
                        for (int i = ScharsI; i < Schars.length; i++) {
                            if (chars[0] == Schars[i]) {
                                if (find(regular, Schars, regularI + 1, i)) {

                                    return true;
                                }
                            } else {
                                return find(regular, Schars, regularI + 1, i);
                            }

                        }
                        return false;
                    }

                } else {
                    n = false;
                    if (regularI == regular.size() - 1) {

//                        System.out.println("---------------////////--------------" + regular.get(regularI));
                        if (ScharsI == Schars.length - 2 && chars[0] == Schars[ScharsI]) {
//                            System.out.println("*******|a|***************true********************");
                            return true;
                        } else {
                            return false;
                        }

                    } else if (ScharsI < Schars.length) {
                        if (chars[0] == Schars[ScharsI]) {
                            return find(regular, Schars, regularI + 1, ScharsI + 1);
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }

        }

        return false;
    }
}
