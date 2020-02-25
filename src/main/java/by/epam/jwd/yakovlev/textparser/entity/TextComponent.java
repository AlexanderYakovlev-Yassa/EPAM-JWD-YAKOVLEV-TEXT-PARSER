package by.epam.jwd.yakovlev.textparser.entity;

import java.util.List;

public interface TextComponent {


    //public List<TextComponent> getComponentList();
    public TypeEnum getType();
    public String getComponentTreeView();
    public List<TextComponent> getComponentPartsListOfType(TypeEnum typeEnum);
    public List<TextComponent> getComponentPartsList();
    public TextComponent getComponentPart(int index);
}
