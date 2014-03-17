package com.ita.edu.softserve.manager.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.exception.TransprtsManagerException;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.utils.StaticValidator;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TransportCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.TransportForAddTripsCriteriaContainer;

/**
 * This is transports manager class.
 * 
 * @author Roman
 */
@Service("transportsManager")
public class TransportsManagerImpl implements TransportsManager {

	private static final Logger LOGGER = Logger
			.getLogger(TransportsManagerImpl.class);

	private String entityName = Transports.class.getSimpleName();

	private String addMessage = " was added to DB";
	private String removeMessage = " was remove from DB by ";

	private final String findTransportsMessage = "Could not find Transport List";
	private final String findTransportsCodeMessage = "Could not find Transport by code";
	private final String saveTransportMessage = "Could not save Transports";
	private final String removeTransportMessage = "Could not remove Transport";
	private final String removeTransportByIdMessage = "Could not remove Transport by id ";
	private final String updateTransportMessage = "Could not update Transport";
	private final String saveOrUpdateTransportMessage = "Could not save or update Transports";
	private final String getAllTransportsMessage = "Could not get list of Transport";
	private final String getTransportByTwoStationsMessage = "Could not get Transport by two stations";

	/**
	 * Gets access to Transports DAO.
	 */
	@Autowired
	private TransportsDao transportsDao;

	/**
	 * Gets access to Routes DAO.
	 */
	@Autowired
	private RoutesDAO routesDao;

	/**
	 * The constructor without arguments.
	 */
	public TransportsManagerImpl() {
		super();
	}

	@Override
	public void validateTransportForAddTripsCriteria(
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer) {
		StaticValidator
				.validateTransportForAddTripsCriteria(transportForAddTripsCriteriaContainer);
	}

	/**
	 * Finds the transport by Id.
	 * 
	 * @return the transport found by Id.
	 * 
	 * @see com.ita.edu.softserve.manager.TransportsManager#findTransportsById(int)
	 */
	@Transactional(readOnly = true)
	@Override
	public Transports findTransportsById(int id) {
		try {
			return transportsDao.findById(id);
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					findTransportsMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Transports findTransportsByCode(String code) {
		try {
			return transportsDao.findByCode(code);
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					findTransportsCodeMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Saves Transports in database.
	 * 
	 * @see com.ita.edu.softserve.manager.TransportsManager#saveTransports(com.ita.edu.softserve.entity.Transports[])
	 */
	@Transactional(readOnly = false)
	@Override
	public void saveTransports(Transports... entities) {
		try {
			transportsDao.save(entities);
			LOGGER.info(entityName + addMessage);
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					saveTransportMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Removes Transports from database.
	 */
	@Transactional(readOnly = false)
	@Override
	public void removeTransports(Transports... entities) {
		try {
			transportsDao.remove(entities);
			LOGGER.info(entityName + removeMessage);
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					removeTransportMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Removes Transport by Id from database.
	 */
	@Transactional
	@Override
	public void removeTransportById(Integer transportId) {
		Transports transport = null;

		try {
			transport = (Transports) transportsDao.findById(transportId);
			LOGGER.info(entityName + transport.getTransportId() + "was fond");

			transportsDao.remove(transport);
			LOGGER.info(entityName + removeMessage);

		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					removeTransportByIdMessage + transport.getTransportId(), e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Updates transport table and get list of all Transports.
	 * 
	 * @return the list of all transports.
	 */
	@Transactional
	@Override
	public List<Transports> updateTransports(Transports... entities) {
		try {
			return transportsDao.update(entities);
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					updateTransportMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Gets the list of all transports.
	 * 
	 * @return the list of all transports.
	 */
	@Transactional
	@Override
	public List<Transports> getAllTransports() {
		try {
			return transportsDao.getAllEntities();
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					getAllTransportsMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Saves the Transport object to database if not exist or updates it. <br/>
	 * <br/>
	 * If <code>transportId</code> is <code>null</code> than it creates new
	 * transport object otherwise it finds existing one in database and updates
	 * it.
	 * @param transport Transports to add or update.
	 */
	@Transactional(readOnly = false)
	@Override
	public void saveOrUpdateTransport(Transports transport) {
		try {
			transportsDao.saveOrUpdate(transport);
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					saveOrUpdateTransportMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}
	
	/*---------------------------for transport paging, sorting, filtering------------------------------------------*/

	@Override
	public void validateTransportCriteria(
			TransportCriteriaContainer transportCriteriaContainer) {
		
		StaticValidator.validateTransportCriteria(transportCriteriaContainer);
	}
	
	@Override
	public List<Transports> getTransportsListWithContainers(
			PageInfoContainer container,
			TransportCriteriaContainer transportCriteriaContainer) {

		return getTransportsListForAddTripsWithPaging(
				container.getPageNumber(), container.getResultsPerPage(),
				transportCriteriaContainer.getTransportCode(),
				transportCriteriaContainer.getRouteName(),
				transportCriteriaContainer.getRoutesCode(),
				transportCriteriaContainer.getSeatClass1(),
				transportCriteriaContainer.getSeatClass2(),
				transportCriteriaContainer.getSeatClass3(),
				transportCriteriaContainer.getPrice(),
				transportCriteriaContainer.getOrderByCriteria(),
				transportCriteriaContainer.getOrderByDirection());
	}

	@Override
	public long getTransportsListCountWithContainers(
			TransportCriteriaContainer transportCriteriaContainer) {
		
		return getTransportsListForAddTripsCount(
				transportCriteriaContainer.getTransportCode(),
				transportCriteriaContainer.getRouteName(),
				transportCriteriaContainer.getRoutesCode(),
				transportCriteriaContainer.getSeatClass1(),
				transportCriteriaContainer.getSeatClass2(),
				transportCriteriaContainer.getSeatClass3(),
				transportCriteriaContainer.getPrice());
	}

	@Transactional(readOnly = true)
	@Override
	public long getTransportsListCount(String transportCode, String routeName,
			String routesCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price) {

		return transportsDao.getTransportsListForAddTripsCount("%"
				+ transportCode + "%", "%" + routeName + "%", "%" + routesCode
				+ "%", seatClass1, seatClass2, seatClass3, price);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Transports> getTransportsList(int firstElement, int count,
			String transportCode, String routeName, String routesCode,
			Integer seatClass1, Integer seatClass2, Integer seatClass3,
			Double price, String orderByCriteria, String orderByDirection) {

		return transportsDao.getTransportsListForAddTrips(firstElement, count,
				"%" + transportCode + "%", "%" + routeName + "%", "%"
						+ routesCode + "%", seatClass1, seatClass2, seatClass3,
				price, orderByCriteria, orderByDirection);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Transports> getTransportsListWithPaging(int pageNumber,
			int count, String transportCode, String routeName,
			String routesCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price, String orderByCriteria,
			String orderByDirection) {

		return transportsDao.getTransportsListForAddTrips((pageNumber - 1)
				* count, count, "%" + transportCode + "%", "%" + routeName
				+ "%", "%" + routesCode + "%", seatClass1, seatClass2,
				seatClass3, price, orderByCriteria, orderByDirection);
	}

	/*--------------------------END-for transport paging, sorting, filtering------------------------------------------*/

	@Override
	public List<Transports> getTransportsListForAddTripsWithContainers(
			PageInfoContainer container,
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer) {

		return getTransportsListForAddTripsWithPaging(
				container.getPageNumber(), container.getResultsPerPage(),
				transportForAddTripsCriteriaContainer.getTransportCode(),
				transportForAddTripsCriteriaContainer.getRouteName(),
				transportForAddTripsCriteriaContainer.getRoutesCode(),
				transportForAddTripsCriteriaContainer.getSeatClass1(),
				transportForAddTripsCriteriaContainer.getSeatClass2(),
				transportForAddTripsCriteriaContainer.getSeatClass3(),
				transportForAddTripsCriteriaContainer.getPrice(),
				transportForAddTripsCriteriaContainer.getOrderByCriteria(),
				transportForAddTripsCriteriaContainer.getOrderByDirection());
	}

	@Override
	public long getTransportsListForAddTripsCountWithContainers(
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer) {
		return getTransportsListForAddTripsCount(
				transportForAddTripsCriteriaContainer.getTransportCode(),
				transportForAddTripsCriteriaContainer.getRouteName(),
				transportForAddTripsCriteriaContainer.getRoutesCode(),
				transportForAddTripsCriteriaContainer.getSeatClass1(),
				transportForAddTripsCriteriaContainer.getSeatClass2(),
				transportForAddTripsCriteriaContainer.getSeatClass3(),
				transportForAddTripsCriteriaContainer.getPrice());
	}

	@Transactional(readOnly = true)
	@Override
	public long getTransportsListForAddTripsCount(String transportCode,
			String routeName, String routesCode, Integer seatClass1,
			Integer seatClass2, Integer seatClass3, Double price) {

		return transportsDao.getTransportsListForAddTripsCount("%"
				+ transportCode + "%", "%" + routeName + "%", "%" + routesCode
				+ "%", seatClass1, seatClass2, seatClass3, price);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Transports> getTransportsListForAddTrips(int firstElement,
			int count, String transportCode, String routeName,
			String routesCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price, String orderByCriteria,
			String orderByDirection) {

		return transportsDao.getTransportsListForAddTrips(firstElement, count,
				"%" + transportCode + "%", "%" + routeName + "%", "%"
						+ routesCode + "%", seatClass1, seatClass2, seatClass3,
				price, orderByCriteria, orderByDirection);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Transports> getTransportsListForAddTripsWithPaging(
			int pageNumber, int count, String transportCode, String routeName,
			String routesCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price, String orderByCriteria,
			String orderByDirection) {

		return transportsDao.getTransportsListForAddTrips((pageNumber - 1)
				* count, count, "%" + transportCode + "%", "%" + routeName
				+ "%", "%" + routesCode + "%", seatClass1, seatClass2,
				seatClass3, price, orderByCriteria, orderByDirection);
	}

	/**
	 * @return the instance of TransportsManager.
	 */
	public static TransportsManager getInstance() {
		return ManagerFactory.getManager(TransportsManager.class);
	}

	/**
	 * Returns number of transport elements that go through
	 * two stations including stops 
	 * 
	 * @param stationName1 - name of the first station
	 * @param stationName2 - name of the second station
	 * 
	 * @return number of transport elements that go through
	 * 		   two stations including stops
	 */
	@Transactional(readOnly = true)
	@Override
	public long getTransportByTwoStListCount(String stationName1,
			String stationName2) {

		return transportsDao.getTransportByTwoStListCount(stationName1,
				stationName2);
	}

	/**
	 * Returns <code>TransportTravel</code> object, that contains all transport
	 * that goes through two stations
	 * 
	 * @param stationName1 - name of the first station
	 * @param stationName2 - name of the second station
	 * 
	 *@return <code>TransportTravel</code>, that contains transport
	 *        code, departure and arrival times, duration
	 */
	@Transactional(readOnly = true)
	@Override
	public List<TransportTravel> getTransportByTwoStations(String stationName1,
			String stationName2) {
		// Results are stored here
		List<TransportTravel> transportTravel = null;

		try {
			transportTravel = transportsDao.findByTwoStations(stationName1,
					stationName2);
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					getTransportByTwoStationsMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}

		return transportTravel;
	}
	
	/**
	 * Returns transport by two stations (limited number of records)
	 * including stops at these stations.
	 * 
	 * @param stationName1 - name of the first station
	 * @param stationName2 - name of the second station
	 * @param pageNumber - number of page to get results for  
	 * @param count - number of elements to return
	 * @param sDate - date of trip
	 * 
	 * @return <code>List</code> of <code>transportTravel</code>
	 * 		   which contains transport and some info about trip
	 * 		   duration, arrival time, departure time
	 */
	@Transactional(readOnly = true)
	@Override
	public List<TransportTravel> getTransportByTwoStForPage(
			String stationName1, String stationName2, int pageNumber,
			int count, String sDate) {

		return getTransportByTwoStForLimit(stationName1, stationName2,
				(pageNumber - 1) * count, count, sDate);
	}

	/**
	 * Returns transport by two stations (limited number of records)
	 * including stops at these stations.
	 * 
	 * @param stationName1 - name of the first station
	 * @param stationName2 - name of the second station
	 * @param firstElement - element from what to start 
	 * @param count - number of elements to return
	 * @param sDate - date of trip
	 * 
	 * @return <code>List</code> of <code>transportTravel</code>
	 * 		   which contains transport and some info about trip
	 * 		   duration, arrival time, departure time
	 */
	@Transactional(readOnly = true)
	@Override
	public List<TransportTravel> getTransportByTwoStForLimit(
			String stationName1, String stationName2, int firstElement,
			int count, String sDate) {

		return transportsDao.getTransportByTwoStForLimits(stationName1,
				stationName2, firstElement, count, sDate);
	}

}
