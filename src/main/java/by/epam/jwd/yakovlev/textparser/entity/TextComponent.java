package by.epam.jwd.yakovlev.textparser.entity;

import java.util.List;

public interface TextComponent {

    TypeEnum getType();
    String getComponentTreeView();
    List<TextComponent> getComponentPartsListOfType(TypeEnum typeEnum);
    List<TextComponent> getComponentPartsList();
    TextComponent getComponentPart(int index);
    void appendComponentPart(TextComponent componentPart);
    void addComponentPart(int index, TextComponent componentPart);
}
