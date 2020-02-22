package by.epam.jwd.yakovlev.textparser.entity.reversepolishnotation;

import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.reversepolishnotation.exception.RPNException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReversePolishNotation {

    private static final Pattern EQUATION_ELEMENTS_REGEX =
            Pattern.compile("([/])|([*])|([-])|([+])|([(])|([)])|([&])|([|])|([~])|([<]{2})|([>]{2})|([\\d]+)");

    public List<TextComponent> resolveEquation(String rawText){

        List<TextComponent> textComponentList = new ArrayList<>();
        List<MathSign> equationInnerComponentsList = new ArrayList<>();

        Matcher matcher = EQUATION_ELEMENTS_REGEX.matcher(rawText);

        while (matcher.find()){

            equationInnerComponentsList.add(MathSign.recogniseMathSign(matcher.group()));
        }

        for (MathSign ms : equationInnerComponentsList){
            System.out.println(ms.name());
        }

        return textComponentList;
    }

    public static void main(String[] args) {

        ReversePolishNotation RPN = new ReversePolishNotation();

        RPN.resolveEquation("(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78");
    }
}
