package org.example.types;

public interface Type{

    interface RootType extends Type{
        @Override
        default String delimiter() {
            return "\n";
        }
    }

    interface FieldType extends Type{}

    default String delimiter() {
        return "";
    }
}
