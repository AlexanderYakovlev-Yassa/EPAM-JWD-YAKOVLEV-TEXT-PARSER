package by.epam.jwd.yakovlev.textparser.entity;

import java.util.ArrayList;

public interface TextComponent {

    TextComponent processTextComponent(TextComponent textComponent);

    void add(TextComponent textComponent);

    void exclude(TextComponent textComponent);

    String getText();

    ArrayList<TextComponent> getComponents();
}
