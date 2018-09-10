package com.spacex.test.mis;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CodeTransferTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        System.out.println(toCountryCode("HK"));
        System.out.println(toCountryCode("TW"));
        System.out.println(toCountryCode("MO"));
        System.out.println(toCountryCode("CN"));
        System.out.println(toCountryCode("US"));
        System.out.println(toCountryCode(""));
        System.out.println(toCountryCode(null));
    }

    private static List<String> toCountryCode(String realCountryCode) {
        String HK = "HK";
        String TW = "TW";
        String MO = "MO";
        List<String> countryCodeList = new ArrayList<>();
        if (StringUtils.isNotBlank(realCountryCode)) {
            if (CountryCodeEnum.CN.getCode().equalsIgnoreCase(realCountryCode)) {
                countryCodeList.add(realCountryCode);
            } else if (HK.equalsIgnoreCase(realCountryCode) ||
                    TW.equalsIgnoreCase(realCountryCode) ||
                    MO.equalsIgnoreCase(realCountryCode)) {
                countryCodeList.add(CountryCodeEnum.CN.getCode());
                countryCodeList.add(CountryCodeEnum.GREAT_CHINA.getCode());
            } else {
                countryCodeList.add(CountryCodeEnum.OVERSEA.getCode());
            }
        }

        countryCodeList.add(CountryCodeEnum.ALL.getCode());
        return countryCodeList;
    }

    private static enum CountryCodeEnum {
        ALL("ALL"), CN("CN"), GREAT_CHINA("G-CN"), OVERSEA("OVERSEA"),;

        private String code;

        CountryCodeEnum(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
