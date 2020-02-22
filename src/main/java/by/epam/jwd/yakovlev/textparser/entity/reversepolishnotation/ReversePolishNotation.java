package by.epam.jwd.yakovlev.textparser.entity.reversepolishnotation;

import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.reversepolishnotation.exception.RPNException;
import by.epam.jwd.yakovlev.textparser.parser.Parser;
import by.epam.jwd.yakovlev.textparser.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReversePolishNotation {

    private static final Parser PARSER = ServiceFactory.getINSTANCE().getParser();

    public List<TextComponent> resolveEquation(String rawText){

        List<TextComponent> textComponentList = new ArrayList<>();
        List<MathSign> parseToMathSign = PARSER.parseToMathSign(rawText);



        return textComponentList;
    }

    public List<MathSign> convertToReversePolishNotation(List<MathSign> source){

        List<MathSign> result = new ArrayList<>();
        Stack<MathSign> stack = new Stack<>();
        MathSign element;

        for (int i = 0; i < source.size(); i++) {
            element = source.get(i)
            switch (element.getPriority()){
                case 0: {
                    stack.push(element);
                    break;
                }
                case 1: {
                    while (stack.peek().getPriority() != 0){
                        result.add(stack.pop());
                    }
                    stack.pop();
                    break;
                }
                case 2: {

                    break;
                }
                case 3: {

                    break;
                }
                case 4: {
                    break;
                }
                case 5: {
                    result.add(element);
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {

        ReversePolishNotation RPN = new ReversePolishNotation();



        RPN.resolveEquation("(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78");
    }
}
