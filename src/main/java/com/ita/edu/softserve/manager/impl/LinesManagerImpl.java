/**
 * 
 */
package com.ita.edu.softserve.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsOnLineDAOImpl;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.ManagerFactory;

/**
 * 
 * @author yuraloga
 * @author MPS
 * 
 */
@Service("linesService")
public class LinesManagerImpl implements LinesManager {

	private static final Logger LOGGER = Logger.getLogger(Lines.class);

	@Autowired
	private LinesDAO lineDao;

	@Autowired
	private StationsOnLineDAO stlDao;

	@Autowired
	private StationsDAO stationDao;

	public LinesManagerImpl() {
	}

	public LinesManagerImpl(StationsOnLineDAOImpl stlDao, LinesDAOImpl lineDao) {
		this.lineDao = lineDao;
		this.stlDao = stlDao;
	}

	public LinesManagerImpl(StationsOnLineDAOImpl stlDao) {
		this.stlDao = stlDao;
	}

	public LinesManagerImpl(LinesDAOImpl lineDao) {
		this.lineDao = lineDao;
	}

	public LinesManagerImpl(StationsDAOImpl stationDao) {
		this.stationDao = stationDao;
	}

	/**
	 * Return all Lines
	 * 
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	@Override
	public List<Lines> getFullLines() {
		return lineDao.getAllEntities();

	}

	/**
	 * 
	 * @param stationName
	 *            name of station
	 * @return <code>List&lt;Lines&gt;</code> which includes certain station
	 */
	@Override
	public List<Lines> getLinesByStation(String stationName) {
		Stations station =stationDao.findByStations(stationName).get(0);
		List<StationsOnLine> stlList = stlDao.findByStationId(station.getStationId());
		List<Lines> linesList = new ArrayList<Lines>();
		for (StationsOnLine stl : stlList) {
			//linesList.add(lineDao.findById(stl.getLineId().getLineId()));
			linesList.add(stl.getLineId());
		}
		return linesList;
	}
 
	/**
	 * Return Lines that includes two stations in certain order
	 * 
	 * @param station1
	 *            - first station, departure
	 * @param station2
	 *            - second station, arrival
	 * 
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	@Override
	public List<Lines> getLinesTwoStationsCertainOrder(String stationName1,
			String stationName2) {
		Stations station1;
		Stations station2;
		
		/* Results are stored here */
		List<Lines> lines = new ArrayList<Lines>();
		
		List<StationsOnLine> StationsOnLine1 = new ArrayList<StationsOnLine>();
		List<StationsOnLine> StationsOnLine2 = new ArrayList<StationsOnLine>();
		
		List<StationsOnLine> stOnLine;
		
		try {
			station1 = (Stations) stationDao.findByStations(stationName1).get(0);
			station2 = (Stations) stationDao.findByStations(stationName2).get(0);
		} catch(Exception e) {
			System.out.println("" + e.getMessage());
			return null;
		}
		
		stOnLine = stlDao.getAllEntities();
		
		for (StationsOnLine stationOnLine : stOnLine) {
			if (stationOnLine.getStationId().getStationId() == station1.
					getStationId()) {
				StationsOnLine1.add(stationOnLine);
			} else if (stationOnLine.getStationId().getStationId() == station2.
					getStationId()) {
				StationsOnLine2.add(stationOnLine);
			} 
		}
		
		for (int i = 0; i < StationsOnLine2.size(); i++) {
			for (int j = 0; j < StationsOnLine1.size(); j++) {
				if (StationsOnLine2.get(i).getLineId().getLineId() == StationsOnLine1
						.get(j).getLineId().getLineId()) {
					if (StationsOnLine2.get(i).getStationOrderNum() < StationsOnLine1
							.get(j).getStationOrderNum()) {
						lines.add(StationsOnLine2.get(i).getLineId());
					}
				}
			}
		}

		return lines;
	}
	
	public static LinesManager getInstance() {
		return ManagerFactory.getManager(LinesManager.class); 
	}

}
