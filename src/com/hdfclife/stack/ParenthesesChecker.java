package com.hdfclife.stack;

public final class ParenthesesChecker {
    private ParenthesesChecker() {}

    public static boolean isBalanced(String input) {
        ArrayClaimStack stack = new ArrayClaimStack(Math.max(4, input.length()));

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) return false;
                stack.pop();
            } else if (c == '[' || c == ']' || c == '{' || c == '}') {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
