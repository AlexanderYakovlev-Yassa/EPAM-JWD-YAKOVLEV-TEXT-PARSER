package by.epam.jwd.yakovlev.textparser.entity.reversepolishnotation;

import by.epam.jwd.yakovlev.textparser.entity.reversepolishnotation.exception.RPNException;

import java.util.ArrayList;
import java.util.Stack;

public class ReversePolishNotation {

   public RPNCompatible doAction(RPNCompatible sign, RPNCompatible firstArgument, RPNCompatible secondArgument) throws RPNException {

       if (sign == null || firstArgument == null || secondArgument == null){
           throw new RPNException("Wrong argument!!!");
       }

       if (sign.getClass() != ActionSign.class ||
               firstArgument.getClass() != RPNNumber.class ||
               secondArgument.getClass() != RPNNumber.class){

           throw new RPNException("Wrong argument!!!");
       }

       ActionSign actionSign = (ActionSign)sign;
       RPNNumber firstNumber = (RPNNumber)firstArgument;
       RPNNumber secondNumber = (RPNNumber)secondArgument;

       RPNNumber res = null;

       switch (actionSign){
           case ADDITION: {
               res.setNumber(firstNumber.getNumber() + (secondNumber.getNumber()));
           }
       }

       return res;
   }

   public Stack<RPNCompatible> readEquation(ArrayList<RPNCompatible> equationList){

       Stack<RPNCompatible> stack = new Stack<>();

       return stack;
   }
}
