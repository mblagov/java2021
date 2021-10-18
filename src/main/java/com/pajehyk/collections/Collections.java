package com.pajehyk.collections;

public class Collections {
    public static void main(String[] args) {
        LinearList <Integer> list = new LinearList<>();
        list.add(1);
        list.add(2);
        System.out.println(list);
        list.add(1, 123);
        list.add(2, 8764);
        list.remove(list.size() - 1);
        System.out.println(list);

        MyStack<Integer> stack = new MyStack<>();
        stack.push(17);
        stack.push(21);
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}