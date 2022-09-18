package com.gsmh.kz.home.array;

/**
 * @author Vlad Mihalcea
 */
public class VarCharStringArrayType
        extends StringArrayType {

    public static final VarCharStringArrayType INSTANCE = new VarCharStringArrayType();

    public VarCharStringArrayType() {
        super(VarCharStringArrayTypeDescriptor.INSTANCE);
    }
}