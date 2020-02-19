package by.epam.jwd.yakovlev.textparser.entity;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public class RegularTextComponent implements TextComponent {

    public static final String WHITE_SPACE = " ";
    public static final String EMPTY_STRING = "";

    private final TextComponentTypesEnum type;
    private ArrayList<TextComponent> components;

    public RegularTextComponent(TextComponentTypesEnum type) {
        this.type = type;
        this.components = new ArrayList<>();
    }

    @Override
    public TextComponent processTextComponent(TextComponent textComponent) {
        return null;
    }



    @Override
    public void append(TextComponent textComponent) {

        if (textComponent == null){
            return;
        }

        components.add(textComponent);
    }

    @Override
    public void add(int index, TextComponent textComponent) {

        if (textComponent == null){
            return;
        }

        components.add(index, textComponent);
    }

    @Override
    public boolean remove(Object o) {

        return components.remove(o);
    }

    @Override
    public TextComponent remove(int index) {
        return components.remove(index);
    }

    /*@Override
    public String getText() {

        String res;

        switch (type){
            case TEXT: {
                res = restoreText(System.lineSeparator());
                break;
            }
            case MATH_EQUATION: {
            }
            case PARAGRAPH: {
                res = restoreText(WHITE_SPACE);
                break;
            }
            default: {
                res = restoreText(EMPTY_STRING);
            }
        }

        return null;
    }*/

    @Override
    public ArrayList<TextComponent> getComponents() {
        return components;
    }

    @Override
    public TextComponent getComponents(int index) throws OperationNotSupportedException {
        return components.get(index);
    }

    @Override
    public TextComponentTypesEnum getType() {
        return type;
    }

    @Override
    public String getStringRepresentation() {

        StringBuilder sb = new StringBuilder();

        for (TextComponent tc : components){
            sb.append(tc.getStringRepresentation());
        }

        return sb.toString();
    }

    /*@Override
    public TextComponent getSymbol(char character) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Can't return symbol!!!");
    }*/

    /*private String restoreText(String separator){

        StringBuilder sb = new StringBuilder();

        for (TextComponent tc : components){
            sb.append(tc.getText());
            sb.append(separator);
        }

        return sb.toString();
    }*/
}
