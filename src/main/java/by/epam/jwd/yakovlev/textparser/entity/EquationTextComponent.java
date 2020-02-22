package by.epam.jwd.yakovlev.textparser.entity;

import by.epam.jwd.yakovlev.textparser.entity.RegularTextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TextComponent;
import by.epam.jwd.yakovlev.textparser.entity.TypeEnum;

public class EquationTextComponent extends RegularTextComponent implements TextComponent {

    public EquationTextComponent(TypeEnum type) {
        super(type);
    }

    @Override
    public String getStringRepresentation() {
        return super.getStringRepresentation();
    }
}
