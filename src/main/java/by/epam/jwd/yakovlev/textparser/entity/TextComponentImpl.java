package by.epam.jwd.yakovlev.textparser.entity;

import java.util.ArrayList;

public class TextComponentImpl implements TextComponent {

    private final TextComponentTypes type;
    private ArrayList<TextComponent> components;

    public TextComponentImpl(TextComponentTypes type) {
        this.type = type;
        this.components = new ArrayList<>();
    }

    @Override
    public TextComponent processTextComponent(TextComponent textComponent) {
        return null;
    }

    @Override
    public void add(TextComponent textComponent) {

    }

    @Override
    public void exclude(TextComponent textComponent) {

    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public ArrayList<TextComponent> getComponents() {
        return null;
    }
}
