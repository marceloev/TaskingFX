package com.taskingfx.util.mask;

import com.jfoenix.controls.JFXTextField;

public class MaskFormatter extends JFXTextField {

    private static final char MASK_ANY = '*'; //Any
    private static final char MASK_DIGIT = '#'; //0-9
    private static final char MASK_LETTER = 'C'; //[A-Z] || [a-z]
    private static final char MASK_LETTER_UPPER = 'U'; //[A-Z]
    private static final char MASK_LETTER_DOWN = 'D'; //[a-z]

    private String[] mask;

    public String[] getMask() {
        return mask;
    }

    public void setMask(String[] mask) {
        this.mask = mask;
    }
}


