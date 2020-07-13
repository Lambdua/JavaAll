package com.lt.behavioral.templatePattern;

import com.lt.behavioral.templatePattern.instance1.Basketball;
import com.lt.behavioral.templatePattern.instance1.FootBall;
import com.lt.behavioral.templatePattern.instance1.Game;

/**
 * @author liangtao
 * @Date 2019/9/26
 **/
public class TemplatePatternDemo {
    public static void main(String[] args) {
        Game game=new Basketball(19);
        Game game2=new FootBall(15);
        game.template();
        game2.template();

    }
}
