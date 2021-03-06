package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.google.common.collect.Iterables;
import com.ita.edu.softserve.dao.impl.TransportsDaoImpl;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.UserNameService;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TransportForAddTripsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.TransportsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.impl.PageInfoContainerImpl;
import com.ita.edu.softserve.validationcontainers.impl.TransportForAddTripsCriteriaContainerImpl;
import com.ita.edu.softserve.validationcontainers.impl.TransportsCriteriaContainerImpl;

/**
 * Class under test
 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl}
 * 
 * @author Roman
 */
@RunWith(MockitoJUnitRunner.class)
public class TestTransportsManagerImpl {

	@Mock
	private TransportsDaoImpl mockTransportsDaoImpl;

	@Mock
	private UserNameService userName;

	@InjectMocks
	private TransportsManagerImpl transportsManagerImpl = new TransportsManagerImpl();

	private Transports transports;

	int transportsIdMock = 20;
	int illegalId = -1;
	private static final String mockTransportsCode = "T000000001";
	private static final String illegalTransportsCode = "T00000@@@1";

	@Before
	public final void setUp() {
		when(userName.getLoggedUsername()).thenReturn("roman");
		transports = mock(Transports.class);
	}

	/**
	 * Test the methods for Equals. Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsById(int)}
	 * .
	 */
	@Test()
	public void testFindTransportsById() {
		when(mockTransportsDaoImpl.findById(transportsIdMock)).thenReturn(
				transports);

		Transports actualTransport = transportsManagerImpl
				.findTransportsById(transportsIdMock);

		verify(mockTransportsDaoImpl, times(1)).findById(transportsIdMock);
		assertEquals(transports, actualTransport);
	}

	/**
	 * Test the methods for <code>null</code>. the found entity instance or null
	 * if the entity does not exist Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsById(int)}
	 * .
	 */
	@Test
	public final void testFindTransportsByIdForNull() {
		when(mockTransportsDaoImpl.findById(transportsIdMock)).thenReturn(null);

		Transports expectedTransport = transportsManagerImpl
				.findTransportsById(transportsIdMock);

		verify(mockTransportsDaoImpl, times(1)).findById(Mockito.anyInt());
		assertNull(expectedTransport);
	}

	/**
	 * IllegalArgumentException - if the argument is not a valid type for that
	 * Entity's primary key or is null.<br>
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsById(int)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testFindTransportsByIdException() {
		when(mockTransportsDaoImpl.findById(illegalId)).thenThrow(
				new IllegalArgumentException());

		transportsManagerImpl.findTransportsById(illegalId);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsByCode(java.lang.String)}
	 * .
	 */
	@Test()
	public void testFindTransportsByCode() {
		when(mockTransportsDaoImpl.findByCode(mockTransportsCode)).thenReturn(
				transports);
		Transports actual = transportsManagerImpl
				.findTransportsByCode(mockTransportsCode);

		verify(mockTransportsDaoImpl, times(1)).findByCode(mockTransportsCode);
		assertEquals(transports, actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsByCode(java.lang.String)}
	 * .
	 */
	@Test
	public final void testFindTransportsByCodeForNull() {
		when(mockTransportsDaoImpl.findByCode(illegalTransportsCode))
				.thenReturn(null);

		Transports expectedTransport = transportsManagerImpl
				.findTransportsByCode(illegalTransportsCode);

		verify(mockTransportsDaoImpl, times(1)).findByCode(
				illegalTransportsCode);
		assertNull(expectedTransport);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsByCode(java.lang.String)}
	 * .
	 */
	@Test(expected = RuntimeException.class)
	public final void testFindTransportsByCodeException() {
		when(mockTransportsDaoImpl.findByCode(illegalTransportsCode))
				.thenThrow(new RuntimeException());

		transportsManagerImpl.findTransportsByCode(illegalTransportsCode);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveTransports(com.ita.edu.softserve.entity.Transports[])}
	 * .
	 */
	@Test()
	public void testSaveTransports() {
		doNothing().when(mockTransportsDaoImpl).save(transports);

		transportsManagerImpl.saveTransports(transports);

		verify(mockTransportsDaoImpl, times(1)).save(transports);
	}

	/**
	 * IllegalArgumentException - if the instance is not an entity.<br>
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveTransports(com.ita.edu.softserve.entity.Transports[])}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSaveTransportsException() {
		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.save(transports);

		transportsManagerImpl.saveTransports(transports);
	}

	/**
	 * EntityExistsException - if the entity already exists. (If the entity
	 * already exists, the EntityExistsException may be thrown when the persist
	 * operation is invoked, or the EntityExistsException or another
	 * PersistenceException may be thrown at flush or commit time.) Test method
	 * for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveTransports(com.ita.edu.softserve.entity.Transports[])}
	 * .
	 */
	@Test(expected = EntityExistsException.class)
	public void testSaveTransportsException2() {
		transports.setTransportId(Mockito.anyInt());
		doThrow(new EntityExistsException()).when(mockTransportsDaoImpl).save(
				transports);

		transportsManagerImpl.saveTransports(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#removeTransports(com.ita.edu.softserve.entity.Transports[])}
	 * .
	 */
	@Test()
	public void testRemoveTransports() {
		doNothing().when(mockTransportsDaoImpl).remove(transports);

		transportsManagerImpl.removeTransports(transports);

		verify(mockTransportsDaoImpl, times(1)).remove(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#removeTransports(com.ita.edu.softserve.entity.Transports[])}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveTransportsException() {
		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.remove(transports);

		transportsManagerImpl.removeTransports(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#removeTransportById(java.lang.Integer)}
	 * .
	 */
	@Test()
	public void testRemoveTransportById() {
		doNothing().when(mockTransportsDaoImpl).removeById(transportsIdMock);

		transportsManagerImpl.removeTransportById(transportsIdMock);

		verify(mockTransportsDaoImpl, times(1)).removeById(transportsIdMock);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#removeTransportById(java.lang.Integer)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveTransportByIdException() {
		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.removeById(transportsIdMock);

		transportsManagerImpl.removeTransportById(transportsIdMock);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#updateTransports(com.ita.edu.softserve.entity.Transports[])}
	 * . .
	 */
	@Test()
	public void testUpdateTransports() {
		transportsManagerImpl.updateTransports(transports);

		verify(mockTransportsDaoImpl, times(1)).update(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#updateTransports(com.ita.edu.softserve.entity.Transports[])}
	 * .
	 */
	@Test()
	public void testUpdateTransportsEquals() {
		List<Transports> expectedListOfTransports = new ArrayList<Transports>();
		Transports transport1 = mock(Transports.class);
		Transports transport2 = mock(Transports.class);

		expectedListOfTransports.add(transport1);
		expectedListOfTransports.add(transport2);
		expectedListOfTransports.add(transports);

		when(mockTransportsDaoImpl.update(transports)).thenReturn(
				expectedListOfTransports);

		List<Transports> actualListOfTransports = transportsManagerImpl
				.updateTransports(transports);

		verify(mockTransportsDaoImpl, times(1)).update(transports);
		assertEquals(expectedListOfTransports, actualListOfTransports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#updateTransports(Transports)}
	 * .
	 */
	@Test
	public final void testUpdateTransportsForNull() {
		when(mockTransportsDaoImpl.update(transports)).thenReturn(null);

		Transports expectedTransport = transportsManagerImpl
				.findTransportsByCode(illegalTransportsCode);

		verify(mockTransportsDaoImpl, times(1)).findByCode(
				illegalTransportsCode);
		assertNull(expectedTransport);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#updateTransports(com.ita.edu.softserve.entity.Transports[])}
	 * . .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateTransportsException() {
		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.update(transports);

		transportsManagerImpl.updateTransports(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveOrUpdateTransport(com.ita.edu.softserve.entity.Transports)}
	 * .
	 */
	@Test()
	public void testSaveOrUpdateTransport() {
		doNothing().when(mockTransportsDaoImpl).saveOrUpdate(transports);

		transportsManagerImpl.saveOrUpdateTransport(transports);

		verify(mockTransportsDaoImpl, times(1)).saveOrUpdate(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveOrUpdateTransport(com.ita.edu.softserve.entity.Transports)}
	 * .
	 */
	@Test()
	public void testSaveOrUpdateTransportForNullId() {
		// Transports transports = new Transports();
		transports.setTransportId(null);

		doNothing().when(mockTransportsDaoImpl).saveOrUpdate(transports);

		transportsManagerImpl.saveOrUpdateTransport(transports);

		verify(mockTransportsDaoImpl, times(1)).saveOrUpdate(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveOrUpdateTransport(com.ita.edu.softserve.entity.Transports)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSaveOrUpdateTransportException() {
		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.saveOrUpdate(transports);

		transportsManagerImpl.saveOrUpdateTransport(transports);
	}

	/**
	 * Method under test. Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getAllTransports()}
	 * .
	 */
	@Test
	public final void testGetAllTransportsEmptyList() {
		List<Transports> expected = new ArrayList<Transports>();

		when(mockTransportsDaoImpl.getAllEntities()).thenReturn(expected);
		List<Transports> actual = transportsManagerImpl.getAllTransports();

		verify(mockTransportsDaoImpl, times(1)).getAllEntities();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getAllTransports()}
	 * .
	 */
	@Test
	public final void testGetAllTransportsForNull() {
		when(mockTransportsDaoImpl.getAllEntities()).thenReturn(null);

		List<Transports> actual = transportsManagerImpl.getAllTransports();

		verify(mockTransportsDaoImpl, times(1)).getAllEntities();

		assertNull(actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getAllTransports()}
	 * .
	 */
	@Test(expected = RuntimeException.class)
	public final void testGetAllTransportsShouldThrowException() {

		when(mockTransportsDaoImpl.getAllEntities()).thenThrow(
				new RuntimeException());

		transportsManagerImpl.getAllTransports();
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#validateIfTransportExist(Transports, Errors)}
	 * .
	 */
	@Test
	public void testValidateIfTransportExist() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {

		Transports actualTransports = new Transports();
		actualTransports.setTransportId(1);

		when(mockTransportsDaoImpl.findByCode("T000000001")).thenReturn(
				actualTransports);

		Transports expectedTransports = new Transports();
		expectedTransports.setTransportId(1);
		expectedTransports.setTransportCode("T000000001");

		Errors errors = new BeanPropertyBindingResult(actualTransports,
				"transports");
		transportsManagerImpl.validateIfTransportExist(expectedTransports,
				errors);

		verify(mockTransportsDaoImpl, times(1)).findByCode("T000000001");
		assertNull(errors.getFieldError("transportCode"));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#validateIfTransportExist(Transports, Errors)}
	 * .
	 */
	@Test
	public void testBlackValidateIfTransportExist()
			throws IllegalArgumentException, IllegalAccessException,
			NoSuchFieldException, SecurityException {

		Transports testTransports = new Transports();
		testTransports.setTransportId(2);

		when(mockTransportsDaoImpl.findByCode("T000000001")).thenReturn(
				testTransports);

		Transports transports = new Transports();
		transports.setTransportId(1);
		transports.setTransportCode("T000000001");

		Errors errors = new BeanPropertyBindingResult(transports, "transports");
		transportsManagerImpl.validateIfTransportExist(transports, errors);

		verify(mockTransportsDaoImpl, times(1)).findByCode("T000000001");
		assertNotNull(errors.getFieldError("transportCode"));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStations(Stations, Stations)}
	 * .
	 */
	@Test
	public final void getTransportByTwoStationsTest() {
		String stationName1 = "Pisochne";
		String stationName2 = "Sknyliv";

		List<TransportTravel> listOfTTravel = new ArrayList<TransportTravel>();
		TransportTravel ttravel = mock(TransportTravel.class);
		listOfTTravel.add(ttravel);

		List<TransportTravel> expectedTTravel = Collections
				.singletonList(ttravel);

		when(
				mockTransportsDaoImpl.findByTwoStations(stationName1,
						stationName2)).thenReturn(listOfTTravel);
		List<TransportTravel> actualTTravel = transportsManagerImpl
				.getTransportByTwoStations(stationName1, stationName2);

		assertTrue(Iterables.elementsEqual(expectedTTravel, actualTTravel));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStations(Stations, Stations)}
	 * . If empty list
	 */
	@Test
	public final void getTransportByTwoStationsIfEmptyListTest() {
		String stationName1 = "Rahiv";
		String stationName2 = "Lviv";

		List<TransportTravel> listOfTTravel = new ArrayList<TransportTravel>();
		List<TransportTravel> expectedTTravel = new ArrayList<TransportTravel>();

		when(
				mockTransportsDaoImpl.findByTwoStations(stationName1,
						stationName2)).thenReturn(listOfTTravel);
		List<TransportTravel> actualTTravel = transportsManagerImpl
				.getTransportByTwoStations(stationName1, stationName2);

		assertTrue(Iterables.elementsEqual(expectedTTravel, actualTTravel));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStations(Stations, Stations)}
	 * .
	 */
	@Test(expected = RuntimeException.class)
	public final void getTransportByTwoStationsIfExceptionTest() {
		String stationName1 = "Pisochne";
		String stationName2 = "Sknyliv";

		List<TransportTravel> listOfTTravel = new ArrayList<TransportTravel>();
		TransportTravel ttravel = mock(TransportTravel.class);
		listOfTTravel.add(ttravel);

		when(
				mockTransportsDaoImpl.findByTwoStations(stationName1,
						stationName2)).thenThrow(new RuntimeException());

		transportsManagerImpl.getTransportByTwoStations(stationName1,
				stationName2);
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStForLimit(String stationName1, String stationName2, int firstElement, int count, String sDate)}
	 * .
	 */
	@Test
	public final void getTransportByTwoStForLimitTest() {
		String stationName1 = "Pisochne";
		String stationName2 = "Sknyliv";

		int firstElement = 0;
		int count = 10;

		TransportTravel transportTravel = mock(TransportTravel.class);
		List<TransportTravel> expectedTransportTravel = Collections
				.singletonList(transportTravel);

		when(
				mockTransportsDaoImpl.getTransportByTwoStForLimits(
						stationName1, stationName2, firstElement, count, null))
				.thenReturn(expectedTransportTravel);
		List<TransportTravel> actualTransportTravel = transportsManagerImpl
				.getTransportByTwoStForLimit(stationName1, stationName2,
						firstElement, count, null);

		assertTrue(Iterables.elementsEqual(expectedTransportTravel,
				actualTransportTravel));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStForLimit(String stationName1, String stationName2, int firstElement, int count, String sDate)}
	 * .
	 */
	@Test
	public final void getTransportByTwoStForLimitIfEmptyListTest() {
		String stationName1 = "Lviv";
		String stationName2 = "Kyiv";

		int firstElement = 0;
		int count = 10;

		List<TransportTravel> expectedTransportTravel = new ArrayList<TransportTravel>();

		when(
				mockTransportsDaoImpl.getTransportByTwoStForLimits(
						stationName1, stationName2, firstElement, count, null))
				.thenReturn(expectedTransportTravel);

		List<TransportTravel> actualTransportTravel = transportsManagerImpl
				.getTransportByTwoStForLimit(stationName1, stationName2,
						firstElement, count, null);

		assertTrue(Iterables.elementsEqual(expectedTransportTravel,
				actualTransportTravel));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStListCount(String stationName1, String stationName2)}
	 * .
	 */
	@Test
	public final void getLinesByTwoStCountTest() {
		String stationName1 = "Pisochne";
		String stationName2 = "Sknyliv";

		long expectedTransportTravelCount = 12;

		when(
				mockTransportsDaoImpl.getTransportByTwoStListCount(
						stationName1, stationName2)).thenReturn(
				expectedTransportTravelCount);

		long actualTransportTravelCount = transportsManagerImpl
				.getTransportByTwoStListCount(stationName1, stationName2);

		assertEquals(expectedTransportTravelCount, actualTransportTravelCount);
	}

	/*--------------------------*/
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#validateTransportCriteria(TransportsCriteriaContainer)}
	 * .
	 */
	@Test
	public final void testValidateCorrectTripsCriteriaTest() {
		TransportsCriteriaContainer transportsCriteriaContainer = new TransportsCriteriaContainerImpl(
				"T000000001", "Stryy-Pyatnychany", "1000000000003", 150, 150,
				150, "24.0", "t.transportCode", "ASC");
		TransportsCriteriaContainer transportsCriteriaContainerCopy = new TransportsCriteriaContainerImpl(
				"T000000001", "Stryy-Pyatnychany", "1000000000003", 150, 150,
				150, "24.0", "t.transportCode", "ASC");

		transportsManagerImpl
				.validateTransportCriteria(transportsCriteriaContainer);
		assertEquals(transportsCriteriaContainer.getTransportCode(),
				transportsCriteriaContainerCopy.getTransportCode());
		assertEquals(transportsCriteriaContainer.getRouteName(),
				transportsCriteriaContainerCopy.getRouteName());
		assertEquals(transportsCriteriaContainer.getRoutesCode(),
				transportsCriteriaContainerCopy.getRoutesCode());
		assertEquals(transportsCriteriaContainer.getSeatClass1(),
				transportsCriteriaContainerCopy.getSeatClass1());
		assertEquals(transportsCriteriaContainer.getSeatClass2(),
				transportsCriteriaContainerCopy.getSeatClass2());
		assertEquals(transportsCriteriaContainer.getSeatClass3(),
				transportsCriteriaContainerCopy.getSeatClass3());
		assertEquals(transportsCriteriaContainer.getPriceName(),
				transportsCriteriaContainerCopy.getPriceName());
		assertEquals(transportsCriteriaContainer.getOrderByCriteria(),
				transportsCriteriaContainerCopy.getOrderByCriteria());
		assertEquals(transportsCriteriaContainer.getTransportCode(),
				transportsCriteriaContainerCopy.getTransportCode());
		assertEquals(transportsCriteriaContainer.getOrderByDirection(),
				transportsCriteriaContainerCopy.getOrderByDirection());
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getTransportsListWithContainers(com.ita.edu.softserve.validationcontainers.PageInfoContainer, com.ita.edu.softserve.validationcontainers.TransportsCriteriaContainer)}
	 * .
	 */
	@Test
	public void testGetTransportsListWithContainers() {
		PageInfoContainer container = mock(PageInfoContainerImpl.class);
		TransportsCriteriaContainer transportCriteriaContainer = mock(TransportsCriteriaContainerImpl.class);

		List<Transports> expectedListOfTransports = new ArrayList<Transports>();

		when(
				transportsManagerImpl.getTransportsListWithPaging(1, 10,
						"T000000001", "Stryy-Pyatnychany", "1000000000003",
						150, 150, 150, 24.0, "routeCode", "ASC")).thenReturn(
				expectedListOfTransports);

		List<Transports> actualListOfTransports = transportsManagerImpl
				.getTransportsListWithContainers(container,
						transportCriteriaContainer);

		assertEquals(expectedListOfTransports, actualListOfTransports);
	}

	@Test
	public final void testGetTransportsListCountWithContainers() {

		long expected = 0;
		long actual;

		TransportsCriteriaContainer transportsCriteriaContainer = new TransportsCriteriaContainerImpl(
				"T000000001", "Stryy-Pyatnychany", "1000000000003", 150, 150,
				150, "24.0", "t.transportCode", "ASC");

		when(
				mockTransportsDaoImpl.getTransportsListCount(
						transportsCriteriaContainer.getTransportCode(),
						transportsCriteriaContainer.getRouteName(),
						transportsCriteriaContainer.getRoutesCode(),
						transportsCriteriaContainer.getSeatClass1(),
						transportsCriteriaContainer.getSeatClass2(),
						transportsCriteriaContainer.getSeatClass3(),
						transportsCriteriaContainer.getPrice())).thenReturn(
				expected);

		actual = transportsManagerImpl
				.getTransportsListCountWithContainers(transportsCriteriaContainer);

		verify(mockTransportsDaoImpl, times(1)).getTransportsListCount(
				"%" + transportsCriteriaContainer.getTransportCode() + "%",
				"%" + transportsCriteriaContainer.getRouteName() + "%",
				"%" + transportsCriteriaContainer.getRoutesCode() + "%",
				transportsCriteriaContainer.getSeatClass1(),
				transportsCriteriaContainer.getSeatClass2(),
				transportsCriteriaContainer.getSeatClass3(),
				transportsCriteriaContainer.getPrice());
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getTransportsListWithPaging(int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Double, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetTransportsListWithPaging() {

		List<Transports> expectedListOfTransports = new ArrayList<Transports>();

		when(
				mockTransportsDaoImpl.getTransportsList(1, 10, "T000000001",
						"Stryy-Pyatnychany", "1000000000003", 150, 150, 150,
						24.0, "routeCode", "ASC")).thenReturn(
				expectedListOfTransports);

		List<Transports> actualListOfTransports = transportsManagerImpl
				.getTransportsListWithPaging(1, 10, "T000000001",
						"Stryy-Pyatnychany", "1000000000003", 150, 150, 150,
						24.0, "routeCode", "ASC");

		assertEquals(expectedListOfTransports, actualListOfTransports);
	}

	@Test
	public final void testValidateTransportForAddTripsCriteria() {
		TransportForAddTripsCriteriaContainer expectedTransportForAddTripsCriteriaContainer = new TransportForAddTripsCriteriaContainerImpl(
				"T000000001", "Stryy-Pyatnychany", "1000000000003", 150, 150,
				150, "24.0", "t.transportCode", "ASC");
		TransportForAddTripsCriteriaContainer actualTransportForAddTripsCriteriaContainer = new TransportForAddTripsCriteriaContainerImpl(
				"T000000001", "Stryy-Pyatnychany", "1000000000003", 150, 150,
				150, "24.0", "t.transportCode", "ASC");

		transportsManagerImpl
				.validateTransportForAddTripsCriteria(expectedTransportForAddTripsCriteriaContainer);
		assertEquals(
				expectedTransportForAddTripsCriteriaContainer
						.getTransportCode(),
				actualTransportForAddTripsCriteriaContainer.getTransportCode());
		assertEquals(
				expectedTransportForAddTripsCriteriaContainer.getRouteName(),
				actualTransportForAddTripsCriteriaContainer.getRouteName());
		assertEquals(
				expectedTransportForAddTripsCriteriaContainer.getRoutesCode(),
				actualTransportForAddTripsCriteriaContainer.getRoutesCode());
		assertEquals(
				expectedTransportForAddTripsCriteriaContainer.getSeatClass1(),
				actualTransportForAddTripsCriteriaContainer.getSeatClass1());
		assertEquals(
				expectedTransportForAddTripsCriteriaContainer.getSeatClass2(),
				actualTransportForAddTripsCriteriaContainer.getSeatClass2());
		assertEquals(
				expectedTransportForAddTripsCriteriaContainer.getSeatClass3(),
				actualTransportForAddTripsCriteriaContainer.getSeatClass3());
		assertEquals(
				expectedTransportForAddTripsCriteriaContainer.getPriceName(),
				actualTransportForAddTripsCriteriaContainer.getPriceName());
		assertEquals(
				expectedTransportForAddTripsCriteriaContainer
						.getOrderByCriteria(),
				actualTransportForAddTripsCriteriaContainer
						.getOrderByCriteria());
		assertEquals(
				expectedTransportForAddTripsCriteriaContainer
						.getTransportCode(),
				actualTransportForAddTripsCriteriaContainer.getTransportCode());
		assertEquals(
				expectedTransportForAddTripsCriteriaContainer
						.getOrderByDirection(),
				actualTransportForAddTripsCriteriaContainer
						.getOrderByDirection());
	}

	@Test
	public void testGetTransportsListForAddTripsWithContainers() {
		PageInfoContainer container = mock(PageInfoContainerImpl.class);
		TransportForAddTripsCriteriaContainer transportCriteriaContainer = mock(TransportForAddTripsCriteriaContainerImpl.class);

		List<Transports> expectedListOfTransports = new ArrayList<Transports>();

		when(
				transportsManagerImpl.getTransportsListForAddTripsWithPaging(1,
						10, "T000000001", "Stryy-Pyatnychany", "1000000000003",
						150, 150, 150, 24.0, "routeCode", "ASC")).thenReturn(
				expectedListOfTransports);

		List<Transports> actualListOfTransports = transportsManagerImpl
				.getTransportsListForAddTripsWithContainers(container,
						transportCriteriaContainer);

		assertEquals(expectedListOfTransports, actualListOfTransports);
	}

	@Test
	public final void testGetTransportsListForAddTripsCount() {

		long expected = 0;
		long actual;

		TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer = new TransportForAddTripsCriteriaContainerImpl(
				"T000000001", "Stryy-Pyatnychany", "1000000000003", 150, 150,
				150, "24.0", "t.transportCode", "ASC");

		when(
				mockTransportsDaoImpl.getTransportsListForAddTripsCount(
						transportForAddTripsCriteriaContainer
								.getTransportCode(),
						transportForAddTripsCriteriaContainer.getRouteName(),
						transportForAddTripsCriteriaContainer.getRoutesCode(),
						transportForAddTripsCriteriaContainer.getSeatClass1(),
						transportForAddTripsCriteriaContainer.getSeatClass2(),
						transportForAddTripsCriteriaContainer.getSeatClass3(),
						transportForAddTripsCriteriaContainer.getPrice()))
				.thenReturn(expected);

		actual = transportsManagerImpl.getTransportsListForAddTripsCount(
				transportForAddTripsCriteriaContainer.getTransportCode(),
				transportForAddTripsCriteriaContainer.getRouteName(),
				transportForAddTripsCriteriaContainer.getRoutesCode(),
				transportForAddTripsCriteriaContainer.getSeatClass1(),
				transportForAddTripsCriteriaContainer.getSeatClass2(),
				transportForAddTripsCriteriaContainer.getSeatClass3(),
				transportForAddTripsCriteriaContainer.getPrice());

		verify(mockTransportsDaoImpl, times(1))
				.getTransportsListForAddTripsCount(
						"%"	+ transportForAddTripsCriteriaContainer.getTransportCode() + "%",
						"%"	+ transportForAddTripsCriteriaContainer.getRouteName() + "%",
						"%"	+ transportForAddTripsCriteriaContainer.getRoutesCode() + "%",
						transportForAddTripsCriteriaContainer.getSeatClass1(),
						transportForAddTripsCriteriaContainer.getSeatClass2(),
						transportForAddTripsCriteriaContainer.getSeatClass3(),
						transportForAddTripsCriteriaContainer.getPrice());
		assertEquals(expected, actual);
	}

	@Test
	public void testGetTransportsListForAddTripsWithPaging() {

		List<Transports> expectedListOfTransports = new ArrayList<Transports>();

		when(
				mockTransportsDaoImpl.getTransportsListForAddTrips(1, 10,
						"T000000001", "Stryy-Pyatnychany", "1000000000003",
						150, 150, 150, 24.0, "routeCode", "ASC")).thenReturn(
				expectedListOfTransports);

		List<Transports> actualListOfTransports = transportsManagerImpl
				.getTransportsListForAddTripsWithPaging(1, 10, "T000000001",
						"Stryy-Pyatnychany", "1000000000003", 150, 150, 150,
						24.0, "routeCode", "ASC");

		assertEquals(expectedListOfTransports, actualListOfTransports);
	}

	@Test
	public void testGetTransportsListForAddTrips() {
		List<Transports> expectedListOfTransports = new ArrayList<Transports>();

		when(
				mockTransportsDaoImpl.getTransportsListForAddTrips(1, 10,
						"T000000001", "Stryy-Pyatnychany", "1000000000003",
						150, 150, 150, 24.0, "routeCode", "ASC")).thenReturn(
				expectedListOfTransports);

		List<Transports> actualListOfTransports = transportsManagerImpl
				.getTransportsListForAddTrips(1, 10,
						"T000000001", "Stryy-Pyatnychany", "1000000000003",
						150, 150, 150, 24.0, "routeCode", "ASC");

		assertEquals(expectedListOfTransports, actualListOfTransports);
	}
	
}
