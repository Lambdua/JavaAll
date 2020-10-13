package com.test;

/**
 * @author 梁先生
 * @Date 2020/9/21
 **/
public class Calzone extends Pizza {
    private final boolean sauceInside;

    public static class CalzoneBuilder extends Pizza.Builder<CalzoneBuilder> {
        private boolean sauceInside = false;

        public CalzoneBuilder(boolean sauceInside) {
            this.sauceInside = sauceInside;
        }

        @Override
        Pizza build() {
            return new Calzone(this);
        }

        @Override
        protected CalzoneBuilder self() {
            return this;
        }
    }

    Calzone(CalzoneBuilder builder) {
        super(builder);
        this.sauceInside = builder.sauceInside;
    }

    public static void main(String[] args) {
        Pizza build = new MyPizza.Builder(MyPizza.Size.LARGE).addTopping(Topping.SAUSAGE).addTopping(Topping.MUSHROOM).build();
        Pizza build2 = new Calzone.CalzoneBuilder(true).addTopping(Topping.ONION).build();
    }
}
