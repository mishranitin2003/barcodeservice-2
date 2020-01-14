package org.restful.barcodeservice2;

import com.google.zxing.BarcodeFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationConfig {

    @Bean(name="supportedBarcodeFormats")
    public Map<String, Object> supportedBarcodeFormats() {
        Map<String, Object> supportedBarcodeFormats = new HashMap<>();
        supportedBarcodeFormats.put("code128", BarcodeFormat.CODE_128);
        supportedBarcodeFormats.put("qrcode", BarcodeFormat.QR_CODE);
        supportedBarcodeFormats.put("code39", BarcodeFormat.CODE_39);
        supportedBarcodeFormats.put("datamatrix", BarcodeFormat.DATA_MATRIX);
        supportedBarcodeFormats.put("ean13", BarcodeFormat.EAN_13);
        supportedBarcodeFormats.put("pdf417", BarcodeFormat.PDF_417);
        supportedBarcodeFormats.put("codeabar", BarcodeFormat.CODABAR);
        return supportedBarcodeFormats;
    }
}
