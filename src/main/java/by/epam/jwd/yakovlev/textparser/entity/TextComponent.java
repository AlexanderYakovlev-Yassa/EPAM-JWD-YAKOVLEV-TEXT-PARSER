package by.epam.jwd.yakovlev.textparser.entity;

import java.util.List;

public interface TextComponent {


    public List<TextComponent> getComponentList();
    public TypeEnum getType();
    public String getStringRepresentation();
    public String getComponentTreeView();
    public List<TextComponent> getComponentsOfType(TypeEnum typeEnum);
}
