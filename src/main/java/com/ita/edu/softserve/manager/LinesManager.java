/**
 * 
 */
package com.ita.edu.softserve.manager;

import java.util.List;

import com.ita.edu.softserve.entity.Lines;

/**
 * 
 * @author yuraloga
 * @author MPS
 * @author pankivanastasiia
 * 
 */
public interface LinesManager extends BaseManager { 
	
	public List<Lines> getFullLines();
	
	public List<Lines> getLinesByStation(String stationName);
	
	public List<Lines> getLinesTwoStationsCertainOrder(String stationName1,
			String stationName2);
	public void createLine(String lineName);
	
	public void editLine(String lineName, String newLineName);
	
	public void deleteLine(String lineName);
}
