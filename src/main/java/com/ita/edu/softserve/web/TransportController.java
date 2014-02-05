package com.ita.edu.softserve.web;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.manager.TransportsManager;

@Controller
public class TransportController {

	private static final Logger LOGGER = Logger
			.getLogger(TransportController.class);

	@Autowired
	private TransportsManager transportsManager;
	
	@Autowired
	private RoutesManager routesManager;
	
	@RequestMapping(value = "/transport", method = RequestMethod.GET)
	public String printTransports(Map<String, Object> modelMap) {

		modelMap.put("transportsList", transportsManager.getAllTransports());

		return "transport";
	}
	
	/*--------------------------------------Spring FORM-----------------------------------*/
	
//	@RequestMapping("/formTransport.htm")
//	public String transportForm(Map<String, Object> map) {
//		map.put("transport", new Transports());
//		return "addTransport";
//	}
//
//	@RequestMapping(value = "/addTransport.htm", method = RequestMethod.POST)
//	public String addTransportToBD(
//			@ModelAttribute("transport") Transports transport,
//			BindingResult result) {
//		try {
//			transportsManager.saveOrUpdateTransport(transport);
//		} catch (Exception e) {
//			LOGGER.error(e.toString());
//		}
//		return "redirect:/transport";
//	}

	/*--------------------------------------HTML FORM-----------------------------------*/

	@RequestMapping(value = "/formTransport.htm", method = RequestMethod.GET)
	public String transportForm() {
		return "addTransport";
	}

	@RequestMapping(value = "/addTransport.htm", method = RequestMethod.POST)
	public String addTransportToBD(
			@ModelAttribute("transportCode") String transportCode,
			@ModelAttribute("startTime") String startTime,
			@ModelAttribute("routes") String routes,
			Map<String, Object> modelMap) {

		Transports transport = transportsManager.createTransport(transportCode,
				startTime, routes);
		try {
			transportsManager.saveOrUpdateTransport(transport);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return "redirect:/transport";
	}

	/*------------------------------------------------------------------------------*/

	@RequestMapping(value = "/transportTravel", method = RequestMethod.GET)
	public String getLinesByTwoStations(Map<String, Object> model) {
		return "transportTravel";
	}

	@RequestMapping(value = "/transportTravelFind", method = RequestMethod.GET)
	public String getLinesByTwoStations(
			@RequestParam("stationName1") String stationName1,
			@RequestParam("stationName2") String stationName2,
			Map<String, Object> model) {

		if (stationName1.equals("") || stationName2.equals("")) {
			return "transportTravel";
		}

		model.put("TransportTravelList", transportsManager.getTransportByTwoStations(
				stationName1, stationName2));

		return "transportTravel";
	}

}