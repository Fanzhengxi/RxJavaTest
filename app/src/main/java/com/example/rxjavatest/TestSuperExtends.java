package com.example.rxjavatest;

import com.example.rxjavatest.bean.Plate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan.zhengxi on 2020/9/21
 * Describe:
 */
class TestSuperExtends {
    private void genericTest() {
        Food food = new Food();
        Apple apple = new Apple();
        Fruit fruit = new Fruit();
        Meat meat = new Meat();
        Beef beef = new Beef();
        RedApple redApple = new RedApple();
        //<? supple Fruit>测试：用于存Fruit和Fruit的子类，不能放bean1的父类
        List<? super Apple> appleList = new ArrayList<>();//只能存不能取
        appleList.add(apple);
//        appleList.add(fruit);//error
//        Plate<Fruit> fruitPlate=new Plate<Apple>();//error 容器里的东西有继承关系，但容器之间没有
//        Plate<Apple> applePlate=new Plate<Fruit>();//error
        Plate<? super Fruit> supperFruitPlate = new Plate<>();//能存放包括Fruit和以Fruit为父类的所有元素
        supperFruitPlate.setItem(apple);
        supperFruitPlate.setItem(fruit);
        supperFruitPlate.setItem(redApple);
//        supperFruitPlate.setItem(meat);//error
//        supperFruitPlate.setItem(food);//error：不能放Fruit的父类
        //
        Fruit getFruit = (Fruit) supperFruitPlate.getItem();
        Apple getApple = (Apple) supperFruitPlate.getItem();
        Object object = supperFruitPlate.getItem();
        Food food1 = (Food) supperFruitPlate.getItem();
        //<? extends Fruit>测试：不能存只能取，取出来的东西是Fruit或Fruit的子类对象，可以向上转型，放在Fruit和Fruit的父类中，或者做强制转换
        Plate<? extends Fruit> extendsFruitPlate = new Plate<>();
//        extendsFruitPlate.setItem(fruit);//error
//        extendsFruitPlate.setItem(apple);//error
//        extendsFruitPlate.setItem(food);//error
        Fruit getFruit2 = extendsFruitPlate.getItem();
        Apple getApple2 = (Apple) extendsFruitPlate.getItem();
        Food food2 = extendsFruitPlate.getItem();
    }

    //Lev 1
    class Food {
    }

    //Lev 2
    class Fruit extends Food {
    }

    class Meat extends Food {
    }

    //Lev 3
    class Apple extends Fruit {
    }

    class Banana extends Fruit {
    }

    class Pork extends Meat {
    }

    class Beef extends Meat {
    }

    //Lev 4
    class RedApple extends Apple {
    }

    class GreenApple extends Apple {
    }

}
