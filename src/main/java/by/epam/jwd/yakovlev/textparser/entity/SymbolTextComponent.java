package by.epam.jwd.yakovlev.textparser.entity;

import java.util.ArrayList;
import java.util.List;

public class SymbolTextComponent implements TextComponent {

    private final TypeEnum type = TypeEnum.SYMBOL;
    private final char symbol;

    public SymbolTextComponent(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public ArrayList<TextComponent> getComponentList() {
        ArrayList<TextComponent> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    @Override
    public TypeEnum getType() {
        return type;
    }

    @Override
    public String getStringRepresentation() {
        return String.valueOf(symbol);
    }

    @Override
    public String getComponentTreeView() {

        String s = String.valueOf(symbol);

        if (s.equals("\n")) {
            s = "\"\\n\"";
        }
        if (s.equals("\t")) {
            s = "\"\\t\"";
        }
        if (s.equals(" ")) {
            s = "\" \"";
        }
        return "\t\t\t\t\t\t" + s + "\n";
    }

    @Override
    public List<TextComponent> getComponentsOfType(TypeEnum typeEnum) {

        List<TextComponent> list = new ArrayList<>();

        if (typeEnum == TypeEnum.SYMBOL){
            list.add(this);
        }

        return list;
    }
}
