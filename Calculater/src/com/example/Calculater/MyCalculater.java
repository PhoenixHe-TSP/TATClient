package com.example.Calculater;

/**
 * Created by Administrator on 14-1-26.
 */

import java.util.*;

public class MyCalculater {
    private int[] sequence;
    private String operate = "[-+/*]";
    private String digital = "[0-9]";

    public MyCalculater() {
        sequence = new int[256];
        sequence[(int) '#'] = 1;
        sequence[(int) '('] = 1;
        sequence[(int) '*'] = sequence[(int) '/'] = 3;
        sequence[(int) '+'] = sequence[(int) '-'] = 2;
    }

    public boolean compareTo(char a, char b) {
        return sequence[(int) a] >= sequence[(int) b];
    }

    public String[] change(String s) {
        String[] expression = analize(s);
        String[] back = new String[100];
        MyStack stack = new MyStack();
        int num = -1;
        int length = findLength(expression);
        String temp = "";
        for (int i = 0; i < length; i++) {
            temp = expression[i];
            char k = temp.charAt(0);
            if (k >= '0' && k <= '9') {
                num++;
                back[num] = temp;
            } else {
                if (stack.isEmpty()) {
                    stack.push(temp);
                } else {
                    switch (k) {
                        case '(':
                            stack.push(temp);
                            break;
                        case ')': {
                            String get = stack.getPeak();
                            while (!get.equals("(")) {
                                num++;
                                back[num] = get;
                                if (stack.isEmpty())
                                    break;
                                stack.pop();
                                if (stack.isEmpty())
                                    break;
                                get = stack.getPeak();
                            }
                            stack.pop();
                        }
                        break;
                        default: {
                            String get = stack.getPeak();
                            char k2 = get.charAt(0);
                            while (compareTo(k2, k)) {
                                num++;
                                back[num] = get;
                                if (stack.isEmpty()) break;
                                stack.pop();
                                if (stack.isEmpty()) break;
                                get = stack.getPeak();
                                k2 = get.charAt(0);
                            }
                            stack.push(temp);
                        }
                        break;
                    }
                }
            }

        }
        while (!stack.isEmpty()) {
            num++;
            back[num] = stack.pop();
        }
        return back;
    }

    public String calcBack(String[] back) {
        MyStack stack = new MyStack();
        int length = findLength(back);
        for (int i = 0; i < length; i++) {
            String temp = back[i];
            char k = temp.charAt(0);
            if (k >= '0' && k <= '9') {
                stack.push(temp);
            } else {
                String s2 = stack.pop();
                String s1 = stack.pop();
                double a = Double.parseDouble(s1);
                double b = Double.parseDouble(s2);
                double con = 0;
                switch (k) {
                    case '+':
                        con = a + b;
                        break;
                    case '-':
                        con = a - b;
                        break;
                    case '*':
                        con = a * b;
                        break;
                    case '/':
                        con = a / b;
                        break;
                }
                String t = String.format("%2.5f", con);
                stack.push(t);
            }
        }
        return stack.pop();
    }

    public int findLength(String[] temp) {
        int p = 0;
        while (temp[p] != null)
            p++;
        return p;
    }

    public String[] analize(String s) {
        int p = 0;
        String[] result = new String[1000];
        int num = -1;
        while (p < s.length()) {
            char k = s.charAt(p);
            if (k == '(' || k == ')' || k == '+' || k == '-' || k == '*'
                    || k == '/') {
                num++;
                result[num] = "" + k;
                p++;
            } else {
                String temp = "";
                while (!(k == '(' || k == ')' || k == '+' || k == '-'
                        || k == '*' || k == '/')) {
                    temp += k;
                    p++;
                    if (p == s.length())
                        break;
                    k = s.charAt(p);
                }
                num++;
                result[num] = temp;
            }

        }
        return result;
    }

    public String calculate(String s) {
        String[] back = change(s);
        try {
            return calcBack(back);
        } catch (Exception e) {
            return "输入有误！";
        }

    }

}

class MyStack {
    private int size;
    private int num;
    private String[] content;

    public MyStack() {
        size = 1000;
        content = new String[size];
        num = 0;
    }

    public int getSize() {
        return this.size;
    }

    public void push(String s) {
        num++;
        content[num] = s;

    }

    public String pop() {
        num--;

        return content[num + 1];
    }

    public String getPeak() {

        return content[num];
    }

    public boolean isEmpty() {

        if (num == 0)
            return true;
        else
            return false;
    }
}