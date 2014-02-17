package com.ita.edu.softserve.manager;

import java.util.List;

import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.impl.TransportTravel;

/**
 * @author Roman
 */
public interface TransportsManager extends BaseManager {

	/**
	 * Finds transports by ID.
	 */
	Transports findTransportsById(int id);

	/**
	 * Saves transports.
	 */
	void saveTransports(Transports... entities);

	/**
	 * Removes transports.
	 */
	void removeTransports(Transports... entities);

	/**
	 * Removes transports by ID.
	 */
	public void removeTransportById(Integer transportId);

	/**
	 * Updates transports.
	 */
	List<Transports> updateTransports(Transports... entities);

	/**
	 * Gets all transports.
	 */
	List<Transports> getAllTransports();

	/**
	 * Saves new transport into Transports table if not exist otherwise updates it.
	 */
	void saveOrUpdateTransport(Integer transportId, String transportCode, String startTime,
			String routes, String seatclass1, String seatclass2, String seatclass3,
			String genprice);

	/**
	 * Gets transport by two stations in certain order
	 */
	List<TransportTravel> getTransportByTwoStations(String stationName1, String stationName2);
	
	public long getTransportByTwoStListCount(String stationName1,
			String stationName2);

	public List<TransportTravel> getTransportByTwoStForPage(String stationName1,
			String stationName2, int pageNumber, int count);

	public List<TransportTravel> getTransportByTwoStForLimit(String stationName1,
			String stationName2, int firstElement, int count);
	

	List<Transports> getTransportsForLimit(int firstElement, int count);

	public List<Transports> getTransportsForPage(int pageNumber, int count);

	long getTransportsListCount();

}
