package com.gsmh.kz.home.array;

/**
 * @author Vlad Mihalcea
 */
public class EnumArrayTypeDescriptor
        extends AbstractArrayTypeDescriptor<Enum[]> {

    public static final EnumArrayTypeDescriptor INSTANCE = new EnumArrayTypeDescriptor();

    public EnumArrayTypeDescriptor() {
        super(Enum[].class);
    }

    @Override
    public String getSqlArrayType() {
        return "integer";
    }
}
