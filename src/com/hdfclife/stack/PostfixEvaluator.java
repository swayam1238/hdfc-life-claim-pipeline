package com.hdfclife.stack;

public final class PostfixEvaluator {
    private PostfixEvaluator() {}

    public static int evaluate(String expression) {
        String[] tokens = expression.trim().split("\\s+");
        LinkedClaimStack stack = new LinkedClaimStack();

        for (String token : tokens) {
            if (token.matches("-?\\d+")) {
                stack.push(Integer.parseInt(token));
                continue;
            }

            int right = stack.pop();
            int left = stack.pop();

            switch (token) {
                case "+" -> stack.push(left + right);
                case "-" -> stack.push(left - right);
                case "*" -> stack.push(left * right);
                case "/" -> stack.push(left / right);
                default -> throw new IllegalArgumentException("Unknown operator: " + token);
            }
        }
        return stack.pop();
    }
}
