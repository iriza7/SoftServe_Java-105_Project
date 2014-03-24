package com.ita.edu.softserve.manager.impl;

import java.sql.Time;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.exception.RoutesManagerException;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.RoutesManager;

/**
 * RoutesManagerImpl
 * 
 * @author Lyubomyr
 * 
 */
@Service("routesManager")
public class RoutesManagerImpl implements RoutesManager {

	private static final Logger LOGGER = Logger
			.getLogger(RoutesManagerImpl.class);

	private String entityName = Routes.class.getSimpleName();
	private final String saveRouteMessage = "Could not save Route";
	private final String updateRouteMessage = "Could not update Route";
	private final String removeMessage = " was remove from DB by ";
	private final String removeRouteByIdMessage = "Could not remove Route by id ";
	private final String routesForOnePageMessage = "Could not get Routes for one page";
	private final String routesListCountMessage = "Could not get Routes List count";
	private final String stationNameListCriteriaMessage = "Could not get Station Name by criteria";
	private final String stationNameByLineMessage = "Could not get Station On Certain Line";
	private final String lineNameMessage = "Could not get Line Name";
	private final String routersArrivingForPageMessage = "Could not get Routers By Name arriving for one page";
	private final String routesListCountByArrivingMessage = "Could not get Routers List count by arriving";
	private final String routersDepartingForPageMessage = "Could not get Routers By Name departing for one page";
	private final String routesListCountByDepartingMessage = "Could not get Routers List count by departing";
	private final String routersListByStationArriving = "Could not get Routers By Station arriving";
	private final String routersListByStationDeparting = "Could not get Routers By Station departing";
	private final String allRoutesList = "Could not get all Routers";
	private final String routesById = "Could not find Routes by Id";
	private final String routesByCode = "Could not find Routes by Code";

	/**
	 * Gets access to Routes DAO.
	 */
	@Autowired
	private RoutesDAO routeDao;

	/**
	 * Gets access to Lines DAO.
	 */
	@Autowired
	private LinesDAO lineDao;

	/**
	 * Gets access to Station DAO.
	 */
	@Autowired
	private StationsDAO stationDao;

	/**
	 * The constructor without arguments.
	 */
	public RoutesManagerImpl() {
		super();
	}

	/**
	 * Returns list with routes for current page
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Routes> getRoutesForPage(int currentPage, int count,
			String orderByParam, String orderByDirection) {
		try {
			return routeDao.getRoutesForLimits((currentPage - 1) * count,
					count, orderByParam, orderByDirection);
		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(
					routesForOnePageMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Returns count for all Routes
	 */
	@Transactional(readOnly = true)
	@Override
	public long getRoutesListCount() {
		try {
			return routeDao.getRoutesListCount();
		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(
					routesListCountMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Returns list with station name, which name start as input stationName
	 */
	@Transactional(readOnly = true)
	@Override
	public List<String> getStationNameListCriteria(String stationName) {
		try {
			return routeDao.getStationNameListCriteria(stationName + "%");
		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(
					stationNameListCriteriaMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Returns list with station name, which are in current line
	 */
	@Transactional(readOnly = true)
	@Override
	public List<String> getStationNameByLineListCriteria(String stationName,
			String lineName) {
		try {
			return routeDao.getStationNameByLineListCriteria(stationName + "%",
					lineDao.findByName(lineName).getLineId());
		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(
					stationNameByLineMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}

	}

	/**
	 * Returns list with line name, which name start as input lineName
	 */
	@Transactional(readOnly = true)
	@Override
	public List<String> getLineNameListCriteria(String lineName) {
		try {
			return routeDao.getLineNameListCriteria(lineName + "%");
		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(lineNameMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Returns list with routes, which arriving from current station
	 */
	@Transactional(readOnly = true)
	@Override
	public List<RouteTrip> getRoutersListByStNameArrivingForPage(
			String stationNameArrival, Time timeArrivalMin,
			Time timeArrivalMax, int currentPaget, int count) {
		try {
			return routeDao.getRoutersListByStNameArrivingForLimits(
					stationNameArrival, timeArrivalMin, timeArrivalMax,
					(currentPaget - 1) * count, count);
		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(
					routersArrivingForPageMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Returns count for list with routes, which arriving from current station
	 */
	@Transactional(readOnly = true)
	@Override
	public long getRoutersListByStationNameArrivingCount(
			String stationNameArrival, Time timeArrivalMin, Time timeArrivalMax) {
		try {
			return routeDao.getRoutersListByStationNameArrivingCount(
					stationNameArrival, timeArrivalMin, timeArrivalMax);
		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(
					routesListCountByArrivingMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Returns list with routes, which departing from current station
	 */
	@Transactional(readOnly = true)
	@Override
	public List<RouteTrip> getRoutersListByStNameDepartingForPage(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax, int currentPaget, int count) {
		try {
			return routeDao.getRoutersListByStNameDepartingForLimits(
					stationNameDeparture, timeDepartureMin, timeDepartureMax,
					(currentPaget - 1) * count, count);
		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(
					routersDepartingForPageMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Returns count for list with routes, which departing from current station
	 */
	@Transactional(readOnly = true)
	@Override
	public long getRoutersListByStationNameDepartingCount(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax) {
		try {
			return routeDao.getRoutersListByStationNameDepartingCount(
					stationNameDeparture, timeDepartureMin, timeDepartureMax);

		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(
					routesListCountByDepartingMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Saves the Route object to database
	 */
	@Transactional(readOnly = false)
	@Override
	public void createRoute(Routes route) {
		try {
			routeDao.save(route);
		} catch (IllegalArgumentException e) {
			LOGGER.error(saveRouteMessage, e);
			throw e;
		}
	}

	/**
	 * Updates the Route object in database
	 */
	@Transactional(readOnly = false)
	@Override
	public void updateRoute(Routes route) {
		try {
			routeDao.update(route);
		} catch (IllegalArgumentException e) {
			LOGGER.error(updateRouteMessage, e);
			throw e;
		}
	}

	/**
	 * Removes Route by Id from database.
	 */
	@Transactional
	@Override
	public void removeRouteById(Integer routeId) {
		try {
			routeDao.remove(routeDao.findById(routeId));
			LOGGER.info(entityName + routeId + "was fond");
			LOGGER.info(entityName + removeMessage);

		} catch (IllegalArgumentException e) {
			LOGGER.error(removeRouteByIdMessage,e);
			throw e;
		}
	}

	/**
	 * Saves the Route object to database
	 */
	@Transactional
	@Override
	public void createRoute(String lines, String routeCode,
			String stationStart, String stationEnd) {
		try {

			Routes route = new Routes();
			route.setRouteCode(routeCode);
			route.setLineId(lineDao.findByName(lines));
			route.setRouteName(stationStart + "-" + stationEnd);
			route.setStationStartId(stationDao.findByName(stationStart));
			route.setStationEndId(stationDao.findByName(stationEnd));
			routeDao.save(route);
		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(saveRouteMessage,
					e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Updates the Route object in database
	 */
	@Override
	@Transactional
	public void updateRoute(Integer routeId, String lines, String routeCode,
			String stationStart, String stationEnd) {
		try {

			Routes route = routeDao.findById(routeId);
			route.setRouteCode(routeCode);
			route.setLineId(lineDao.findByName(lines));
			route.setRouteName(stationStart + "-" + stationEnd);
			route.setStationStartId(stationDao.findByName(stationStart));
			route.setStationEndId(stationDao.findByName(stationEnd));
			routeDao.update(route);
		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(
					updateRouteMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Return Routes of transports that are arriving to certain station during
	 * certain times
	 * 
	 * @param stationNameArrival
	 *            - name arriving station
	 * @param timeArrivalMin
	 *            - minimum time arrival
	 * @param timeArrivalMax
	 *            - maximum time arrival
	 * 
	 */
	@Override
	public List<RouteTrip> findRoutersListByStationNameArriving(
			String stationNameArrival, Time timeArrivalMin, Time timeArrivalMax) {
		try {
			List<RouteTrip> routesArrivingList = routeDao
					.findRoutersListByStationNameArriving(stationNameArrival,
							timeArrivalMin, timeArrivalMax);
			return routesArrivingList;

		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(
					routersListByStationArriving, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Return Routes of transports that are departing from certain station
	 * during certain times
	 * 
	 * @param stationNameDeparture
	 *            - name departing station
	 * @param timeDepartureMin
	 *            - minimum time departure
	 * @param timeDepartureMax
	 *            - maximum time departure
	 * 
	 */
	@Override
	public List<RouteTrip> findRoutersListByStationNameDeparting(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax) {
		try {
			List<RouteTrip> routesDepartingList = routeDao
					.findRoutersListByStationNameDeparting(
							stationNameDeparture, timeDepartureMin,
							timeDepartureMax);
			return routesDepartingList;

		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(
					routersListByStationDeparting, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	@Transactional
	@Override
	public List<Routes> getAllRoutes() {
		try {
			return routeDao.getAllEntities();
		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(allRoutesList, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Routes findRoutesById(int id) {
		try {
			return routeDao.findById(id);
		} catch (RuntimeException e) {
			LOGGER.error(routesById, e);
			throw e;
		}

	}

	@Transactional(readOnly = true)
	@Override
	public Routes findByCode(String routeCode) {
		try {
			return routeDao.findByCode(routeCode);
		} catch (IllegalArgumentException e) {
			LOGGER.error(routesByCode, e);
			throw e;
		}
	}

	public static RoutesManager getInstance() {
		return ManagerFactory.getManager(RoutesManager.class);
	}
}