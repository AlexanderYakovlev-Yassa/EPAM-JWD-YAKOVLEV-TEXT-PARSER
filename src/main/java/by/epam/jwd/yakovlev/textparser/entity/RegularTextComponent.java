package by.epam.jwd.yakovlev.textparser.entity;

import java.util.ArrayList;
import java.util.List;

public class RegularTextComponent implements TextComponent {

    private final TypeEnum type;
    private List<TextComponent> components;

    public RegularTextComponent(TypeEnum type) {
        this.type = type;
        this.components = new ArrayList<>();
    }

    @Override
    public TypeEnum getType() {
        return type;
    }

    @Override
    public String getComponentTreeView() {

        StringBuilder sb = new StringBuilder();
        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < type.ordinal(); i++) {
            prefix.append("\t");
        }

        String typeName;
        String componentTreeView;
        for (TextComponent tc : components){
            if (tc != null){
                typeName = tc.getType().name();
                componentTreeView = tc.getComponentTreeView();
            } else {
                typeName = "NULL";
                componentTreeView = "";
            }
            sb.append(prefix + typeName + "\n" + componentTreeView);
        }

        return sb.toString();
    }

    @Override
    public List<TextComponent> getComponentPartsListOfType(TypeEnum type) {

        List<TextComponent> componentList = new ArrayList<>();

        for (TextComponent tc : components){
            if (tc.getType() == type) {
                componentList.add(tc);
            }
            componentList.addAll(tc.getComponentPartsListOfType(type));
        }

        return componentList;
    }

    @Override
    public List<TextComponent> getComponentPartsList(){

        return this.components;
    }

    @Override
    public TextComponent getComponentPart(int index) {
        return this.components.get(index);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (TextComponent tc : components){
            sb.append(tc.toString());
        }

        return sb.toString();
    }

    @Override
    public void appendComponentPart(TextComponent componentPart) {
        components.add(componentPart);
    }

    @Override
    public void addComponentPart(int index, TextComponent componentPart) {
        components.add(index, componentPart);
    }
}
