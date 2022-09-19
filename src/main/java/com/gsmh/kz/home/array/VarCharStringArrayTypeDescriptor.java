package com.gsmh.kz.home.array;

/**
 * @author Vlad Mihalcea
 */
public class VarCharStringArrayTypeDescriptor
        extends StringArrayTypeDescriptor {

    public static final VarCharStringArrayTypeDescriptor INSTANCE =
            new VarCharStringArrayTypeDescriptor();

    @Override
    public String getSqlArrayType() {
        return "varchar";
    }
}
