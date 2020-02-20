package by.epam.jwd.yakovlev.textparser.entity;

import java.util.ArrayList;

public class RegularTextComponent implements TextComponent {

    private final TypeEnum type;
    private ArrayList<TextComponent> components;

    public RegularTextComponent(TypeEnum type) {
        this.type = type;
        this.components = new ArrayList<>();
    }

    @Override
    public TextComponent processTextComponent(TextComponent textComponent) {
        return null;
    }

    @Override
    public ArrayList<TextComponent> getComponentList() {
        return components;
    }

    @Override
    public TypeEnum getType() {
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
}
