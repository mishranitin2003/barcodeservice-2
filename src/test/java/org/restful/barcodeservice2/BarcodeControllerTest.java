package org.restful.barcodeservice2;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BarcodeControllerTest {

	@Autowired
    private MockMvc mockMvc;

	@Test
	public void testGeneratedBarcodeCode128Decoding() throws Exception {
		String barcodeValue = "AA1234566";
		MvcResult mvcResult = mockMvc.perform(get("/barcodeonly/code128/" + barcodeValue))
		.andExpect(status().isOk()).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		byte[] barcodeBytes = response.getContentAsByteArray();
	    
	    InputStream is = new ByteArrayInputStream(barcodeBytes);
	    Map<DecodeHintType, Boolean> hintMap = new HashMap<DecodeHintType, Boolean>();
	    hintMap.put(DecodeHintType.TRY_HARDER, true);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(is))));
		Result barcodeResult = new MultiFormatReader().decode(binaryBitmap,
				hintMap);
		assertEquals(barcodeValue, barcodeResult.getText());
	}
}
