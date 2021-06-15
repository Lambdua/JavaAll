package com.lt.slides;

import com.aspose.slides.License;

import java.io.InputStream;

/**
 * @author liangtao
 * @description
 * @date 2021年06月15 09:25
 **/
public class SlidesTest {
    public static void main(String[] args) {
        InputStream licenseStream = SlidesTest.class.getClassLoader().getResourceAsStream("license.xml");
        License license = new License();
        license.setLicense(licenseStream);
        System.out.println(license.isLicensed());
    }
}
