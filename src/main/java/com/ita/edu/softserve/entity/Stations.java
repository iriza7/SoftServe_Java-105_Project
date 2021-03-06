package com.ita.edu.softserve.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author admin
 * 
 *         The persistent class for the STATIONS database table.
 */

@Entity
@Table(name = "stations")
@NamedQueries({
		@NamedQuery(name = Stations.STATIONS_FIND_ALL, query = Stations.STATIONS_FIND_ALL_QUERY),
		@NamedQuery(name = Stations.STATIONS_FIND_COUNT, query = Stations.STATIONS_FIND_COUNT_QUERY),
		@NamedQuery(name = Stations.FIND_BY_NAME, query = Stations.FIND_BY_NAME_QUERY),
		@NamedQuery(name = Stations.FIND_BY_LINE_NAME, query = Stations.FIND_BY_LINE_NAME_QUERY),
		@NamedQuery(name = Stations.GET_COUNT_STATIONS_WITH_CRITERIA, query = Stations.GET_COUNT_STATIONS_WITH_CRITERIA_QUERY) })
public class Stations extends BaseEntity {

	public static final String SEARCH_STRING = "searchstring";

	public static final String STATIONS_FIND_ALL = "Stations.findAll";
	public static final String STATIONS_FIND_ALL_QUERY = "SELECT s FROM Stations s";

	public static final String STATIONS_FIND_COUNT = "Stations.findCount";
	public static final String STATIONS_FIND_COUNT_QUERY = "SELECT COUNT(s.stationId) FROM Stations s";

	public static final String FIND_BY_NAME = "Stations.findByName";
	public static final String FIND_BY_NAME_QUERY = "SELECT u FROM Stations u WHERE u.stationName = ?1";

	public static final String FIND_BY_LINE_NAME = "Stations.findByLineName";
	public static final String FIND_BY_LINE_NAME_QUERY = "select s from StationsOnLine stln inner join stln.stationId as s inner join stln.lineId as l where l.lineName = ?1";

	public static final String FIND_STATIONS_LIST_BY_CRITERIA = "SELECT s FROM Stations s WHERE s.stationCode LIKE :"
			+ SEARCH_STRING
			+ " OR s.stationName LIKE :"
			+ SEARCH_STRING
			+ " ORDER BY ";

	public static final String GET_COUNT_STATIONS_WITH_CRITERIA = "Stations.getCountAllStationsWithCriteria";

	public static final String GET_COUNT_STATIONS_WITH_CRITERIA_QUERY = "SELECT COUNT(s.stationId) FROM Stations s WHERE (s.stationCode LIKE :"
			+ SEARCH_STRING 
			+ " OR s.stationName LIKE :" 
			+ SEARCH_STRING + ")";

	@Id
	@Column(name = "STATIONID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer stationId;

	@Column(name = "STATIONCODE", length = 25, nullable = false)
	private String stationCode;

	@Column(name = "STATIONNAME", length = 25, nullable = false)
	private String stationName;

	/* Bi-directional one-to-many association to StationsOnLine */
	@OneToMany(mappedBy = "stationId", fetch = FetchType.LAZY)
	private Set<StationsOnLine> stationsOnLines;

	/**
	 * Default Constructor
	 */
	public Stations() {
	}

	/**
	 * @param stationCode
	 * @param stationName
	 *            Constructor with parametrs stationCode, stationName.
	 */
	public Stations(String stationCode, String stationName) {
		this();
		this.setStationCode(stationCode);
		this.setStationName(stationName);
	}

	/**
	 * @return the stationId
	 */
	public Integer getStationId() {
		return stationId;
	}

	/**
	 * @param stationId
	 *            the stationId to set
	 */
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	/**
	 * @return the stationCode
	 */
	public String getStationCode() {
		return stationCode;
	}

	/**
	 * @param stationCode
	 *            the stationCode to set
	 */
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	/**
	 * @return the stationName
	 */
	public String getStationName() {
		return stationName;
	}

	/**
	 * @param stationName
	 *            the stationName to set
	 */
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	/**
	 * @return the stationsOnLines
	 */
	public Set<StationsOnLine> getStationsOnLines() {
		return stationsOnLines;
	}

	/**
	 * @param stationsOnLines
	 *            the stationsOnLines to set
	 */
	public void setStationsOnLines(Set<StationsOnLine> stationsOnLines) {
		this.stationsOnLines = stationsOnLines;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(stationId).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stations other = (Stations) obj;
		return new EqualsBuilder().append(stationId, other.stationId)
				.isEquals();
	}

}
