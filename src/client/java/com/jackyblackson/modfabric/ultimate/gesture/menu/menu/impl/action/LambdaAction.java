package com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.action;

import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.IGestureMenuItem;
import lombok.Setter;

import java.util.function.Consumer;
import java.util.function.Function;

@Setter
public class LambdaAction extends  BaseMenuAction{
    Function<IGestureMenuItem, String> lambdaAction = (menuItem -> {return null;});

    @Override
    public String act(IGestureMenuItem item) {
        return lambdaAction.apply(item);
    }
}
