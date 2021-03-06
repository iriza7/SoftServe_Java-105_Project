package com.ita.edu.softserve.web;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.components.Encoder;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.TripsManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.ValidatorUtil;
import com.ita.edu.softserve.validation.AddTripsValidator;
import com.ita.edu.softserve.validation.EditTripsValidator;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TransportForAddTripsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.TripsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.impl.AddTripsErrorContainer;
import com.ita.edu.softserve.validationcontainers.impl.AddTripsInfoValidationContainer;
import com.ita.edu.softserve.validationcontainers.impl.EditTripsErrorContainer;
import com.ita.edu.softserve.validationcontainers.impl.EditTripsInfoValidationContainer;
import com.ita.edu.softserve.validationcontainers.impl.PageInfoContainerImpl;
import com.ita.edu.softserve.validationcontainers.impl.TransportForAddTripsCriteriaContainerImpl;
import com.ita.edu.softserve.validationcontainers.impl.TripsCriteriaContainerImpl;

@Controller
public class TripsController {

	/**
	 * Name for jstl variable that contains errors information
	 */
	private static final String ERROR_NAME = "errors";
	/**
	 * Name for jstl variable that contains information about failed params
	 * during adding trips
	 */

	private static final String INFO_NAME = "info";

	/**
	 * Names for extra params (in case of add trips failure)
	 */
	private static final String[] ADD_TRIPS_EXTRA_PARAMS = { "from=", "to=" };

	/**
	 * Separator for manualy constructed get requests
	 */
	private static final String GET_SEPARATOR = "&";

	/**
	 * String for ukrainian language representation in locale format (used in
	 * formatting date)
	 */

	public static final String UKRAINIAN = "ua";

	/**
	 * String for spanish language representation in locale format (used in
	 * formatting date)
	 */

	public static final String SPANISH = "es";

	/**
	 * String for ukrainian or spanish date format
	 */

	public static final String UKRAINIAN_OR_SPANISH_DATE_FORMAT = "dd.MM.yyyy";
	/**
	 * String for default date format
	 */
	public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
	/**
	 * Part of URL that defines trips web page
	 */
	private static final String TRIPS_WEB_NAME = "/trips";
	/**
	 * Part of URL that defines web page for trips AJAX paging
	 */
	private static final String TRIPSPAGE_WEB_NAME = "/tripspage";
	/**
	 * Part of any redirect spring jsp definition
	 */

	private static final String REDIRECT_SUBSTRING = "redirect:/";

	/**
	 * Part of any redirect spring jsp definition that is on same level with
	 * caller controller maping
	 */

	private static final String REDIRECT_SAME_LEVEL_SUBSTRING = "redirect:";
	/**
	 * Part of URL that defines trips manager web page
	 */
	private static final String MANAGETRIPS_WEB_NAME = "/tripsmanager";
	/**
	 * Part of URL that defines web page for trips manager AJAX paging
	 */
	private static final String MANAGETRIPSPAGE_WEB_NAME = "/tripsmanagerpage";
	/**
	 * Part of URL that defines adding trips web page
	 */
	private static final String ADDTRIP_WEB_NAME = "/addTrip";
	/**
	 * Part of URL that defines web page for adding trips AJAX paging
	 */
	private static final String ADDTRIPPAGE_WEB_NAME = "/addTripPage";
	/**
	 * Part of URL that defines potential web page responsible for action of
	 * adding trips
	 */
	private static final String ADDNEWTRIPS_WEB_NAME = "/addNewTrips";
	/**
	 * Spring response that defines trips jsp page
	 */
	private static final String TRIPS_SPRING_NAME = "trips";
	/**
	 * Spring response that defines jsp page for trips AJAX paging
	 */
	private static final String TRIPSPAGE_SPRING_NAME = "tripspage";
	/**
	 * Spring response that defines trips manager jsp page
	 */
	private static final String MANAGETRIPS_SPRING_NAME = "tripsmanager";
	/**
	 * Spring response that defines jsp page for trips manager AJAX paging
	 */
	private static final String MANAGETRIPSPAGE_SPRING_NAME = "tripsmanagerpage";
	/**
	 * Spring response that defines jsp page for trips manager AJAX paging
	 */
	private static final String ERRORINPUT_SPRING_NAME = "addtripserrorinput";
	/**
	 * Spring response that defines adding trips jsp page
	 */
	private static final String ADDTRIP_SPRING_NAME = "addTrip";
	/**
	 * Spring response that defines jsp page for adding trips AJAX paging
	 */
	private static final String ADDTRIPPAGE_SPRING_NAME = "addTripPage";
	/**
	 * Name for in-jsp jstl variable, representing list of trips
	 */
	private static final String TRIPSLIST_NAME = "tripsList";
	/**
	 * Name for in-jsp jstl variable, representing list of transports
	 */
	private static final String TRANSPORTSLIST_NAME = "transportsList";
	/**
	 * Name for in-jsp jstl variable, representing date format
	 */
	private static final String DATEFORMAT_NAME = "dateFormat";
	/**
	 * Name for in-jsp jstl variable, representing language (used for jQuery UI
	 * datepicker)
	 */
	private static final String LANGUAGE_NAME = "language";

	/**
	 * Name for showing transport code attribute
	 */
	private static final String IS_TRANSPORT_CODE_ATTRIBUTE_NAME = "isTransportCode";

	/**
	 * Name for showing route name attribute
	 */
	private static final String IS_ROUTE_NAME_ATTRIBUTE_NAME = "isRouteName";

	/**
	 * Name for showing route code attribute
	 */
	private static final String IS_ROUTE_CODE_ATTRIBUTE_NAME = "isRoutesCode";

	/**
	 * Name for showing Class1 places count attribute
	 */
	private static final String IS_CLASS1_ATTRIBUTE_NAME = "isClass1";
	/**
	 * Name for showing Class1 places count attribute
	 */
	private static final String IS_CLASS2_ATTRIBUTE_NAME = "isClass2";
	/**
	 * Name for showing Class1 places count attribute
	 */
	private static final String IS_CLASS3_ATTRIBUTE_NAME = "isClass3";

	/**
	 * Constant for mapping variable for REST request
	 */
	private static final String TRIPID_PATH_VARIABLE = "tripId";

	/**
	 * Name for showing minimal date count attribute
	 */
	private static final String IS_MIN_DATE_ATTRIBUTE_NAME = "isMinDate";

	/**
	 * Name for showing minimal date count attribute
	 */
	private static final String IS_MAX_DATE_ATTRIBUTE_NAME = "isMaxDate";

	/**
	 * Name for showing price attribute
	 */
	private static final String IS_PRICE_ATTRIBUTE_NAME = "isPrice";

	/**
	 * Name for criteria container attribute
	 */
	private static final String CRITERIA_CONTAINER_ATTRIBUTE_NAME = "container";

	/**
	 * Name for current trip attribute
	 */
	private static final String CURRENT_TRIP_NAME = "currentTrip";

	/**
	 * Name for encoder attribute name
	 */
	private static final String ENCODER_ATTRIBUTE_NAME = "encoder";

	/**
	 * Constant for mapping trips delete
	 */

	private static final String TRIP_DELETE_PATH = "/tripDelete/{tripId}";

	/**
	 * Constant for mapping trips edit
	 */

	private static final String TRIP_EDIT_PATH = "/editTrip/{tripId}";

	/**
	 * Constant for mapping trips edit redirection
	 */

	private static final String TRIP_EDIT_PART_PATH = "/editTrip/";

	/**
	 * Constant for mapping trips edit action
	 */

	private static final String TRIP_EDIT_ACTION_PATH = "/editTripAction/{tripId}";

	/**
	 * Constant for mapping trips edit page
	 */

	private static final String TRIP_EDIT_PAGE_PATH = "/editTripPage/{tripId}";

	/**
	 * Part of URL that defines editing trips web page
	 */
	private static final String EDITTRIP_SPRING_NAME = "editTrip";

	/**
	 * Part of URL that defines web page for editing trips AJAX paging
	 */
	private static final String EDITTRIPPAGE_SPRING_NAME = "editTripPage";

	/**
	 * Field for using trips-related controller-level methods
	 */

	@Autowired
	private TripsManager tripsManager;

	/**
	 * Field for using transports-related controller-level methods
	 */

	@Autowired
	private TransportsManager transportsManager;

	/**
	 * Field for validator using while adding trips
	 */
	@Autowired
	@Qualifier("addTripsValidator")
	private Validator addTripsValidator;

	/**
	 * Field for validator using while editing trips
	 */
	@Autowired
	@Qualifier("editTripsValidator")
	private Validator editTripsValidator;

	/**
	 * Container of trips search and sorting information
	 */

	@Autowired
	Encoder encoder;

	/**
	 * Field for using paging-related controller-level methods (class realized
	 * using singleton)
	 */

	private PaginationManager paginationManager = PaginationManager
			.getInstance();

	/**
	 * Puts boolean variables in the page to define if non-default values should
	 * be shown in form elements
	 * 
	 * @param tripsCriteriaContainer
	 *            container with filtering info
	 * @param modelMap
	 *            Model map to fill
	 */
	private void putFillElementsOptions(TripsCriteriaContainer container,
			Map<String, Object> modelMap) {
		modelMap.put(IS_TRANSPORT_CODE_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getTransportCode()));
		modelMap.put(IS_ROUTE_NAME_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getRouteName()));
		modelMap.put(
				IS_CLASS1_ATTRIBUTE_NAME,
				(container.getRemSeatClass1() == null)
						|| (container.getRemSeatClass1() < 0));
		modelMap.put(
				IS_CLASS2_ATTRIBUTE_NAME,
				(container.getRemSeatClass2() == null)
						|| (container.getRemSeatClass2() < 0));
		modelMap.put(
				IS_CLASS3_ATTRIBUTE_NAME,
				(container.getRemSeatClass3() == null)
						|| (container.getRemSeatClass3() < 0));
		modelMap.put(IS_MIN_DATE_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getMinDate()));
		modelMap.put(IS_MAX_DATE_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getMaxDate()));

	}

	/**
	 * Puts boolean variables in the page to define if non-default values should
	 * be shown in form elements
	 * 
	 * @param container
	 *            container with filtering info
	 * @param modelMap
	 *            Model map to fill
	 */
	private void putFillAddTripsElementsOptions(
			TransportForAddTripsCriteriaContainer container,
			Map<String, Object> modelMap) {

		modelMap.put(IS_TRANSPORT_CODE_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getTransportCode()));
		modelMap.put(IS_ROUTE_NAME_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getRouteName()));
		modelMap.put(IS_ROUTE_CODE_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getRoutesCode()));
		modelMap.put(
				IS_CLASS1_ATTRIBUTE_NAME,
				(container.getSeatClass1() == null)
						|| (container.getSeatClass1() < 0));
		modelMap.put(
				IS_CLASS2_ATTRIBUTE_NAME,
				(container.getSeatClass2() == null)
						|| (container.getSeatClass2() < 0));
		modelMap.put(
				IS_CLASS3_ATTRIBUTE_NAME,
				(container.getSeatClass3() == null)
						|| (container.getSeatClass3() < 0));

	}

	/**
	 * Method for filling model map used in transports-list related controllers
	 * 
	 * 
	 * @param container
	 *            container with paging info
	 * @param transportForAddTripsCriteriaContainer
	 *            container with filtering info
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 */
	private void completeMapForAddTrip(
			PageInfoContainer container,
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		putFillAddTripsElementsOptions(transportForAddTripsCriteriaContainer,
				modelMap);
		transportsManager
				.validateTransportForAddTripsCriteria(transportForAddTripsCriteriaContainer);
		modelMap.put(
				IS_PRICE_ATTRIBUTE_NAME,
				transportForAddTripsCriteriaContainer.getPrice().equals(
						Double.MAX_VALUE));
		long count = transportsManager
				.getTransportsListForAddTripsCountWithContainers(transportForAddTripsCriteriaContainer);
		container.setCount(count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put(CRITERIA_CONTAINER_ATTRIBUTE_NAME,
				transportForAddTripsCriteriaContainer);
		modelMap.put(ENCODER_ATTRIBUTE_NAME, encoder);

		List<Transports> transports = transportsManager
				.getTransportsListForAddTripsWithContainers(container,
						transportForAddTripsCriteriaContainer);
		modelMap.put(TRANSPORTSLIST_NAME, transports);
		modelMap.put(LANGUAGE_NAME, locale.getLanguage());
		modelMap.put(
				DATEFORMAT_NAME,
				new SimpleDateFormat(
						locale.getLanguage().equalsIgnoreCase(UKRAINIAN)
								|| locale.getLanguage().equalsIgnoreCase(
										SPANISH) ? UKRAINIAN_OR_SPANISH_DATE_FORMAT
								: DEFAULT_DATE_FORMAT));
	}

	/**
	 * 
	 * Method for filling model map used in trips editing controllers
	 * 
	 * @param tripId
	 *            id of trip to edit
	 * @param container
	 *            container with paging info
	 * @param transportForAddTripsCriteriaContainer
	 *            container with filtering info
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 */
	private void completeMapForEditTrip(
			Integer tripId,
			PageInfoContainer container,
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		Trips currentTrip = tripsManager.getTripById(tripId);
		Integer pageOnIncome = container.getPageNumber();
		int newPageNumber;
		modelMap.put(CURRENT_TRIP_NAME, currentTrip);
		putFillAddTripsElementsOptions(transportForAddTripsCriteriaContainer,
				modelMap);
		transportsManager
				.validateTransportForAddTripsCriteria(transportForAddTripsCriteriaContainer);
		modelMap.put(
				IS_PRICE_ATTRIBUTE_NAME,
				transportForAddTripsCriteriaContainer.getPrice().equals(
						Double.MAX_VALUE));
		long count = transportsManager
				.getTransportsListForAddTripsCountWithContainers(transportForAddTripsCriteriaContainer);
		container.setCount(count);
		paginationManager.validatePaging(container);
		if (!container.getPageNumber().equals(pageOnIncome)) {
			newPageNumber = (int) transportsManager
					.getTransportIndexForAddTrips(
							transportForAddTripsCriteriaContainer,
							currentTrip.getTransport(),
							container.getResultsPerPage());
			container.setPageNumber(newPageNumber);
			paginationManager.validatePaging(container);
		}
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put(CRITERIA_CONTAINER_ATTRIBUTE_NAME,
				transportForAddTripsCriteriaContainer);
		modelMap.put(ENCODER_ATTRIBUTE_NAME, encoder);
		List<Transports> transports = transportsManager
				.getTransportsListForAddTripsWithContainers(container,
						transportForAddTripsCriteriaContainer);
		modelMap.put(TRANSPORTSLIST_NAME, transports);
		modelMap.put(LANGUAGE_NAME, locale.getLanguage());
		modelMap.put(
				DATEFORMAT_NAME,
				new SimpleDateFormat(
						locale.getLanguage().equalsIgnoreCase(UKRAINIAN)
								|| locale.getLanguage().equalsIgnoreCase(
										SPANISH) ? UKRAINIAN_OR_SPANISH_DATE_FORMAT
								: DEFAULT_DATE_FORMAT));
	}

	/**
	 * 
	 * Method for filling model map used in trips-list related controllers
	 * 
	 * @param container
	 *            container with paging info
	 * @param tripsCriteriaContainer
	 *            container with filtering info
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 */

	private void completeMapForTrips(PageInfoContainer container,
			TripsCriteriaContainer tripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		List<Trips> deployedTrips = null;
		String lang = locale.getLanguage();
		putFillElementsOptions(tripsCriteriaContainer, modelMap);
		tripsManager.validateTripsCriteria(tripsCriteriaContainer, locale);
		long count = tripsManager
				.getTripsListCriteriaCountUsingContainers(tripsCriteriaContainer);
		container.setCount(count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put(CRITERIA_CONTAINER_ATTRIBUTE_NAME, tripsCriteriaContainer);
		modelMap.put(ENCODER_ATTRIBUTE_NAME, encoder);
		deployedTrips = tripsManager.getTripsForCriteriaUsingContainers(
				tripsCriteriaContainer, container);
		modelMap.put(TRIPSLIST_NAME, deployedTrips);
		modelMap.put(
				DATEFORMAT_NAME,
				new SimpleDateFormat(
						lang.equalsIgnoreCase(UKRAINIAN)
								|| lang.equalsIgnoreCase(SPANISH) ? UKRAINIAN_OR_SPANISH_DATE_FORMAT
								: DEFAULT_DATE_FORMAT));
		modelMap.put(LANGUAGE_NAME, lang);
	}

	/**
	 * 
	 * Controller method for displaying trips list page
	 * 
	 * @param container
	 *            container with paging info
	 * @param tripsCriteriaContainer
	 *            container with filtering info
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */

	@RequestMapping(value = TRIPSPAGE_WEB_NAME, method = RequestMethod.GET)
	public String printTripsPage(PageInfoContainerImpl container,
			TripsCriteriaContainerImpl tripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(container, tripsCriteriaContainer, modelMap, locale);
		return TRIPSPAGE_SPRING_NAME;
	}

	/**
	 * Controller method for displaying AJAX-source trips list page
	 * 
	 * @param container
	 *            container with paging info
	 * @param tripsCriteriaContainer
	 *            container with filtering info
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */

	@RequestMapping(value = TRIPS_WEB_NAME, method = RequestMethod.GET)
	public String printTrips(PageInfoContainerImpl container,
			TripsCriteriaContainerImpl tripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(container, tripsCriteriaContainer, modelMap, locale);
		return TRIPS_SPRING_NAME;
	}

	/**
	 * Controller method for displaying trips manager page
	 * 
	 * @param container
	 *            container with paging info
	 * @param tripsCriteriaContainer
	 *            container with filtering info
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */
	@RequestMapping(value = MANAGETRIPSPAGE_WEB_NAME, method = RequestMethod.GET)
	public String printManageTripsPage(PageInfoContainerImpl container,
			TripsCriteriaContainerImpl tripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(container, tripsCriteriaContainer, modelMap, locale);
		return MANAGETRIPSPAGE_SPRING_NAME;
	}

	/**
	 * Controller method for displaying AJAX-source trips manager page
	 * 
	 * @param container
	 *            container with paging info
	 * @param tripsCriteriaContainer
	 *            container with filtering info
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */

	@RequestMapping(value = MANAGETRIPS_WEB_NAME, method = RequestMethod.GET)
	public String printManageTrips(PageInfoContainerImpl container,
			TripsCriteriaContainerImpl tripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(container, tripsCriteriaContainer, modelMap, locale);
		return MANAGETRIPS_SPRING_NAME;
	}

	/**
	 * 
	 * Controller method for displaying adding trips page
	 * 
	 * @param container
	 *            container with paging info
	 * @param transportForAddTripsCriteriaContainer
	 *            container with filtering info
	 * @param errors
	 *            container for errors from unsuccessful attempt to add trips
	 * @param info
	 *            container for parameters from unsuccessful attempt to add
	 *            trips
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */
	@RequestMapping(value = ADDTRIP_WEB_NAME, method = RequestMethod.GET)
	public String printAddTrips(
			PageInfoContainerImpl container,
			TransportForAddTripsCriteriaContainerImpl transportForAddTripsCriteriaContainer,
			AddTripsErrorContainer errors,
			AddTripsInfoValidationContainer info, Map<String, Object> modelMap,
			Locale locale) {
		completeMapForAddTrip(container, transportForAddTripsCriteriaContainer,
				modelMap, locale);
		modelMap.put(ERROR_NAME, errors);
		modelMap.put(INFO_NAME, info);
		return ADDTRIP_SPRING_NAME;
	}

	/**
	 * 
	 * Controller method for displaying AJAX-source adding trips page
	 * 
	 * @param container
	 *            container with paging info
	 * @param transportForAddTripsCriteriaContainer
	 *            container with filtering info
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */

	@RequestMapping(value = ADDTRIPPAGE_WEB_NAME, method = RequestMethod.GET)
	public String printAddTripsPage(
			PageInfoContainerImpl container,
			TransportForAddTripsCriteriaContainerImpl transportForAddTripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForAddTrip(container, transportForAddTripsCriteriaContainer,
				modelMap, locale);
		return ADDTRIPPAGE_SPRING_NAME;
	}

	/**
	 * 
	 * Controller method for displaying editing trips page
	 * 
	 * @param container
	 *            container with paging info
	 * @param transportForAddTripsCriteriaContainer
	 *            container with filtering info
	 * @param errors
	 *            container for errors from unsuccessful attempt to edit trip
	 * @param info
	 *            container for parameters from unsuccessful attempt to edit
	 *            trip
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */

	@RequestMapping(value = TRIP_EDIT_PATH, method = RequestMethod.GET)
	public String printEditTrips(
			@PathVariable(TRIPID_PATH_VARIABLE) Integer tripId,
			PageInfoContainerImpl container,
			EditTripsErrorContainer errors,
			TransportForAddTripsCriteriaContainerImpl transportForAddTripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForEditTrip(tripId, container,
				transportForAddTripsCriteriaContainer, modelMap, locale);
		modelMap.put(ERROR_NAME, errors);
		return EDITTRIP_SPRING_NAME;
	}

	/**
	 * 
	 * Controller method for displaying AJAX-source editing trip page
	 * 
	 * @param container
	 *            container with paging info
	 * @param transportForAddTripsCriteriaContainer
	 *            container with filtering info
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */

	@RequestMapping(value = TRIP_EDIT_PAGE_PATH, method = RequestMethod.GET)
	public String printEditTripsPage(
			@PathVariable(TRIPID_PATH_VARIABLE) Integer tripId,
			PageInfoContainerImpl container,
			TransportForAddTripsCriteriaContainerImpl transportForAddTripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForEditTrip(tripId, container,
				transportForAddTripsCriteriaContainer, modelMap, locale);
		return EDITTRIPPAGE_SPRING_NAME;
	}

	/**
	 * Controller method for performing addition of trips
	 * 
	 * @param container
	 *            container with definition of trips range to add
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */

	@RequestMapping(value = ADDNEWTRIPS_WEB_NAME)
	public String printAddTripsPage(Locale locale,
			AddTripsInfoValidationContainer container, BindingResult result) {
		container.setLocaleParam(locale);
		addTripsValidator.validate(container, result);
		if (result.hasErrors()) {
			StringBuilder path = new StringBuilder().append("?");
			path.append(ADD_TRIPS_EXTRA_PARAMS[0])
					.append(encoder.encode(container.getFrom()))
					.append(GET_SEPARATOR).append(ADD_TRIPS_EXTRA_PARAMS[1])
					.append(encoder.encode(container.getTo()))
					.append(GET_SEPARATOR);
			if (result.hasFieldErrors(AddTripsValidator.WRONG_DATE_MAX)
					|| result.hasFieldErrors(AddTripsValidator.WRONG_DATE_MIN)) {
				path.append(AddTripsValidator.WRONG_DATE_MESSAGE);
			}
			if (result.hasFieldErrors(ValidatorUtil.WRONG_TRANSPORT_ID)) {
				path.append(ValidatorUtil.WRONG_TRANSPORT_ID_MESSAGE);
			}
			path.deleteCharAt(path.length() - 1);
			return REDIRECT_SAME_LEVEL_SUBSTRING + ADDTRIP_SPRING_NAME
					+ path.toString();
		} else {
			if (tripsManager.addTripsWithContainer(container)) {
				return REDIRECT_SAME_LEVEL_SUBSTRING + MANAGETRIPS_SPRING_NAME;
			} else {
				return REDIRECT_SAME_LEVEL_SUBSTRING + ERRORINPUT_SPRING_NAME;
			}
		}

	}

	/**
	 * 
	 * Controller method for performing editing of trips
	 * 
	 * @param tripId
	 *            id of trips to edit
	 * @param container
	 *            container with definition of trip changes
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */

	@RequestMapping(value = TRIP_EDIT_ACTION_PATH, method = RequestMethod.POST)
	public String editTripAction(
			@PathVariable(TRIPID_PATH_VARIABLE) Integer tripId,
			EditTripsInfoValidationContainer container, Locale locale,
			BindingResult result) {
		container.setLocaleParam(locale);
		editTripsValidator.validate(container, result);
		if (result.hasErrors()) {
			StringBuilder path = new StringBuilder().append("?");
			if (result.hasFieldErrors(EditTripsValidator.WRONG_DATE)) {
				path.append(EditTripsValidator.WRONG_DATE_MESSAGE);
			}
			if (result.hasFieldErrors(EditTripsValidator.WRONG_REM_SEAT_CLASS1)
					|| result
							.hasFieldErrors(EditTripsValidator.WRONG_REM_SEAT_CLASS2)
					|| result
							.hasFieldErrors(EditTripsValidator.WRONG_REM_SEAT_CLASS3)) {
				path.append(EditTripsValidator.WRONG_REM_SEAT_MESSAGE);
			}
			if (result.hasFieldErrors(ValidatorUtil.WRONG_TRANSPORT_ID)) {
				path.append(ValidatorUtil.WRONG_TRANSPORT_ID_MESSAGE);
			}
			path.deleteCharAt(path.length() - 1);
			return REDIRECT_SUBSTRING + TRIP_EDIT_PART_PATH + tripId
					+ path.toString();
		} else {
			if (tripsManager.editTrip(tripId, container)) {
				return REDIRECT_SUBSTRING + MANAGETRIPS_SPRING_NAME;
			} else {
				return REDIRECT_SUBSTRING + ERRORINPUT_SPRING_NAME;
			}
		}

	}
	
	/**
	 * 
	 * Controller method for performing deleting of trips
	 * 
	 * @param tripId
	 *            id of trips to delete
	 * @return definition of jsp to use
	 */	

	@RequestMapping(value = TRIP_DELETE_PATH)
	public String deleteTrip(@PathVariable(TRIPID_PATH_VARIABLE) Integer tripId) {
		tripsManager.removeTrip(tripId);
		return REDIRECT_SUBSTRING + MANAGETRIPS_SPRING_NAME;
	}

}
