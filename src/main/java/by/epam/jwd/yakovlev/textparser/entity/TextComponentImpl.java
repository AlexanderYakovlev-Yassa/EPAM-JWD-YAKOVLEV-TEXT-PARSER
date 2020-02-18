package by.epam.jwd.yakovlev.textparser.entity;

import by.epam.jwd.yakovlev.textparser.entity.exception.NotCompatibleType;

import java.util.ArrayList;

public class TextComponentImpl implements TextComponent {

    public static final String WHITE_SPACE = " ";
    public static final String EMPTY_STRING = "";

    private final TextComponentType type;
    private ArrayList<TextComponent> components;

    public TextComponentImpl(TextComponentType type) {
        this.type = type;
        this.components = new ArrayList<>();
    }

    @Override
    public TextComponent processTextComponent(TextComponent textComponent) {
        return null;
    }

    @Override
    public void append(TextComponent textComponent) throws NotCompatibleType {

        if (textComponent == null){
            throw new NullPointerException("Null is not acceptable here");
        }

        if (type.getInnerType() != textComponent.getType()){
            throw new NotCompatibleType(
                    "Type " + textComponent.getType().name() + "is not compatible for " + type.name());
        }

        components.add(textComponent);
    }

    @Override
    public void add(int index, TextComponent textComponent) throws NotCompatibleType {

        if (textComponent == null){
            throw new NullPointerException("Null is not acceptable here");
        }

        if (type.getInnerType() != textComponent.getType()){
            throw new NotCompatibleType(
                    "Type " + textComponent.getType().name() + "is not compatible for " + type.name());
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

    @Override
    public String getText() {

        String res;

        switch (type){
            case TEXT: {
                res = restoreText(System.lineSeparator());
                break;
            }
            case DIGIT_EQUATION: {
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
    }

    @Override
    public ArrayList<TextComponent> getComponents() {
        return components;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    private String restoreText(String separator){

        StringBuilder sb = new StringBuilder();

        for (TextComponent tc : components){
            sb.append(tc.getText());
            sb.append(separator);
        }

        return sb.toString();
    }
}
