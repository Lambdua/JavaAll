package com.test;

import java.util.Objects;

/**
 * @author 梁先生
 * @Date 2020/9/21
 **/
public class MyPizza extends Pizza{
    public enum Size{SMALL,MEDIUM,LARGE}
    private final Size size;
    public static class Builder extends Pizza.Builder<Builder>{
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        Pizza build() {
            return new MyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    MyPizza(Builder builder) {
        super(builder);
        size=builder.size;
    }
}
