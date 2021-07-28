/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turkcell.poc.enumerator;

/**
 *
 * @author Adem
 */
public enum YesNoEnum {

    YES("Y"),
    NO("N");

    private final String value;

    YesNoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
