package com.yu.interview;

/* 枚举 */
public enum CountryEnum {

    ONE(1, "齐"),
    TWO(2, "楚"),
    THREE(3, "燕"),
    FOUR(4, "赵"),
    FIVE(5, "魏"),
    SIX(6, "韩");

    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    CountryEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CountryEnum forEach(int index){
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum countryEnum : countryEnums) {
            if (countryEnum.getCode() == index){
                return countryEnum;
            }
        }
        return null;
    }

}
