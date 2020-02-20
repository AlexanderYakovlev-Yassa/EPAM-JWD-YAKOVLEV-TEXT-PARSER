package by.epam.jwd.yakovlev.textparser.entity;

import by.epam.jwd.yakovlev.textparser.entity.exception.NotCompatibleType;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public interface TextComponent {

    TextComponent processTextComponent(TextComponent textComponent) throws OperationNotSupportedException;

    ArrayList<TextComponent> getComponentList();

    TypeEnum getType();

    String getStringRepresentation();

}
