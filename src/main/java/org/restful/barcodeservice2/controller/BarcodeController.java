/*
 * The GNU General Public License (GPL-3.0)
 * Copyright 2013-2014 the original author or authors.
 *
 */
package org.restful.barcodeservice2.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * @author nmishra
 * Entry point to Barcode RESTful API
 */
@RestController
public class BarcodeController {

	private static final Logger logger = LoggerFactory.getLogger(BarcodeController.class);

	@Value("#{supportedBarcodeFormats}")
	private Map<String, BarcodeFormat> supportedBarcodeFormats;
	
	/**
	 * Original uri to get barcodeservice2 and text
	 */
	@GetMapping("/{format}/{data}")
	public ModelAndView generateBarcode(@PathVariable("format") String format,
			@PathVariable("data") String data, HttpServletRequest request) {
		BarcodeFormat barcodeFormat = supportedBarcodeFormats.get(format);
		if(barcodeFormat == null) {
			throw new RuntimeException("Barcode format " + format + " is incorrect");
		}

		ModelAndView mv = new ModelAndView("barcode");
		ModelMap model = new ModelMap();
		
		model.put("barcodeUrl", "/barcodeonly/" + format + "/" + data);
		model.put("barcodeText", data);
		mv.addAllObjects(model);
		
		return mv;
	}
	

	/**
	 * URI to fetch barcode only (without text)
	 */
	@GetMapping(value = "/barcodeonly/{format}/{data}")
	@ResponseBody
	public void barcode(@PathVariable("format") String format,
			@PathVariable("data") String data, HttpServletResponse response) {
		BarcodeFormat barcodeFormat = supportedBarcodeFormats.get(format);

		try {
			BitMatrix matrix = new MultiFormatWriter().encode(data, barcodeFormat, 300, 300);

			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write( bufferedImage  , "png", byteArrayOutputStream);

			response.setContentType("image/png");
			response.getOutputStream().write(byteArrayOutputStream.toByteArray());
		} catch (Exception e) {
			logger.error("Error occurred while translating to barcodeservice2", e);
			throw new RuntimeException(e);
		}

	}
	
	/*
	 * Exception Handler. Any Exception will forward to error jsp page
	 */
	@ExceptionHandler(Exception.class)
	public String handleIOException(Exception ex, HttpServletRequest request) {
		return "error";
	}

}
