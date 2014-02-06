package com.ita.edu.softserve.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.entity.StationsOnLine;

/**
 * 
 * @author iryna
 * 
 */
@Repository
public class StationsOnLineDAOImpl extends AbstractDAO<StationsOnLine>
		implements StationsOnLineDAO {

	@Override
	public Class<StationsOnLine> getEntityClass() {
		return StationsOnLine.class;
	}

	@Override
	public List<StationsOnLine> findByStationId(int id) {
		Query query = entityManager.createNamedQuery(
				StationsOnLine.FIND_BY_STATIONS_ID).setParameter(1, id);
		List<StationsOnLine> list = query.getResultList();
		return list;
	}

	@Override
	public List<StationsOnLine> findByLineId(int id) {
		Query query = entityManager.createNamedQuery(
				StationsOnLine.FIND_BY_LINE_ID).setParameter(1, id);
		List<StationsOnLine> list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StationsOnLine> findByTwoStations(String stationName1,
			String stationName2) {
		Query query = entityManager
				.createNamedQuery(StationsOnLine.FIND_BY_TWO_STATIONS)
				.setParameter(1, stationName1).setParameter(2, stationName2);

		return query.getResultList();
	}

	@Override
	public int getLinesByStationCount(int id) {

		return (int) find((Query) entityManager.createNamedQuery(
				StationsOnLine.GET_LINES_BY_STATION_COUNT).setParameter(1, id));
	}

	private List<StationsOnLine> getLinesByStationForPaging(int from,
			int count, int id) {
		Query query = entityManager
				.createNamedQuery(
						StationsOnLine.GET_LINES_BY_STATION_FOR_PAGING)
				.setParameter(1, id).setFirstResult(from).setMaxResults(count);
		return (List<StationsOnLine>) getRange(from, count, query);
	}

	@Override
	public List<StationsOnLine> getLinesByStationForOnePage(int from,
			int count, int id) {
		return this.getLinesByStationForPaging(from, count, id);
	}

	@Override
	public StationsOnLine findByStationIdAndLineId(Integer stationId,
			Integer lineId) {
		Query query = entityManager
				.createNamedQuery(StationsOnLine.FIND_BY_LINE_ID_AND_STATION_ID)
				.setParameter(1, stationId).setParameter(2, lineId);
		return (StationsOnLine) query.getSingleResult();
	}
}
