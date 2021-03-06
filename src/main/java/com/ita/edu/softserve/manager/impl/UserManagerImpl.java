package com.ita.edu.softserve.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.UsersDAO;
import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.exception.UsersManagerExeption;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.UserManager;
import com.ita.edu.softserve.utils.StaticValidator;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.UserCriteriaContainer;

/**
 * UserManagerImpl
 * 
 * @author iryna
 * 
 */
@Service("userService")
public class UserManagerImpl implements UserManager {

	private static final String THE_USER_WAS_FOUND = "The user was found";
	private static final String USERS_NOT_FOUND = "Users not found";
	private static final String USER_DOES_NOT_EXIST = "User does not exist!";
	private static final String COULD_NOT_FIND_USER_BY_ID = "Could not find user by id";
	private static final String COULD_NOT_REMOVE_USER = "Could not remove User";
	private static final String COULD_NOT_UPDATE_USER = "Could not update User";

	private static final Logger LOGGER = Logger
			.getLogger(UserManagerImpl.class);

	@Autowired
	private UsersDAO userDao;

	/**
	 * Constructor
	 */
	public UserManagerImpl() {
		super();
	}

	/**
	 * Find all users
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Users> findAllUsers() {
		try {
			return userDao.getAllEntities();

		} catch (RuntimeException e) {
			LOGGER.error(USERS_NOT_FOUND, e);
			throw e;
		}
	}

	/**
	 * Find user by id
	 */
	@Override
	@Transactional(readOnly = true)
	public Users findUser(Integer id) {
		Users findById = null;
		try {
			findById = userDao.findById(id);
			LOGGER.info(THE_USER_WAS_FOUND);

			return findById;

		} catch (RuntimeException e) {
			LOGGER.error(COULD_NOT_FIND_USER_BY_ID, e);
			throw e;
		}
	}

	/**
	 * Update The User Data (for userEdit.jsp)
	 */
	@Transactional(readOnly = false)
	@Override
	public void updateTheUserData(Users user) {
		try {
			userDao.updateUserData(user);

		} catch (RuntimeException e) {
			LOGGER.error(COULD_NOT_UPDATE_USER, e);
			throw e;
		}
	}

	/**
	 * Delete user of DB
	 */
	@Override
	@Transactional
	public void removeUser(Integer id) {
		try {
			userDao.remove(userDao.findById(id));

		} catch (RuntimeException e) {
			LOGGER.error(COULD_NOT_REMOVE_USER, e);
			throw e;
		}
	}

	/**
	 * Find user by userName
	 */
	@Override
	@Transactional
	public Users findByUsername(String username) {
		try {
			return (Users) userDao.findByUsername(username);

		} catch (RuntimeException e) {
			LOGGER.error(USER_DOES_NOT_EXIST, e);
			throw e;
		}
	}

	/**
	 * Edit Profile (for profile.jsp)
	 */
	@Override
	@Transactional
	public void updateUser2(Integer userId, String firstName, String lastName,
			String email, String password) {
		Users user = null;
		try {
			user = userDao.findById(userId);

			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPassword(password);

			userDao.update(user);

		} catch (RuntimeException e) {
			LOGGER.error(COULD_NOT_UPDATE_USER, e);
			throw e;
		}
	}

	/**
	 * Create new user (for registration.jsp)
	 */
	@Transactional
	@Override
	public boolean createUser(String username, String firstname,
			String lastname, String email, String password, Role role) {
		Users tempUser = null;
		try {
			tempUser = (Users) userDao.findByUsername(username);
		} catch (NoResultException nr) {
		}
		if (tempUser == null) {
			tempUser = new Users(username, firstname, lastname, email,
					password, role);
			userDao.save(tempUser);
			System.out.println("Successfully registered!");
			return true;
		}
		return false;
	}

	/**
	 * Method getInstance
	 */
	public static UserManager getInstance() {
		return ManagerFactory.getManager(UserManager.class);
	}

	/**
	 * Validator for UserList
	 */
	@Override
	public void validateUserListCriteria(
			UserCriteriaContainer userCriteriaContainer, Locale locale) {
		StaticValidator.validateUserListCriteria(userCriteriaContainer, locale);
	}

	/**
	 * For pagging 1
	 */
	@Override
	public long getUsersListCountUsingContainer(
			UserCriteriaContainer userCriteriaContainer) {

		return getUsersListCountWithCriteria(
				"%" + userCriteriaContainer.getSearchString() + "%",
				userCriteriaContainer.getRoleArray(),
				userCriteriaContainer.getMinDate(),
				userCriteriaContainer.getMaxDate());
	}

	@Override
	public List<Users> getUsersForLimitUsingContainers(
			UserCriteriaContainer userCriteriaContainer,
			PageInfoContainer container) {
		return getUsersForPageWithCriteria(container.getPageNumber(),
				container.getResultsPerPage(),
				"%" + userCriteriaContainer.getSearchString() + "%",
				userCriteriaContainer.getRoleArray(),
				userCriteriaContainer.getMinDate(),
				userCriteriaContainer.getMaxDate(),
				userCriteriaContainer.getOrderByParam(),
				userCriteriaContainer.getOrderByDirection());
	}

	@Override
	public long getUsersListCountWithCriteria(String searchString,
			List<Role> roleArray, Date minDate, Date maxDate) {

		return userDao.getUsersListCountWithCriteria(searchString, roleArray,
				minDate, maxDate);
	}

	@Override
	public List<Users> getUsersForLimitWithCriteria(int firstElement,
			long count, String searchString, List<Role> roleArray,
			Date minDate, Date maxDate, String orderByParam,
			String orderByDirection) {

		return userDao.getUsersForOnePageWithCriteria(firstElement,
				(int) count, searchString, roleArray, minDate, maxDate,
				orderByParam, orderByDirection);
	}

	@Override
	public List<Users> getUsersForPageWithCriteria(int pageNumber, long count,
			String searchString, List<Role> roleArray, Date minDate,
			Date maxDate, String orderByParam, String orderByDirection) {
		return getUsersForLimitWithCriteria((int) ((pageNumber - 1) * count),
				count, searchString, roleArray, minDate, maxDate, orderByParam,
				orderByDirection);
	}

	/**
	 * For pagging2- get userlist Count
	 */
	@Override
	public long getUsersListCount() throws UsersManagerExeption {
		try {
			return userDao.getUsersListCount();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new UsersManagerExeption("Could not get Users list count", e);
		}
	}

	/**
	 * For pagging2- get all users
	 */
	@Override
	public List<Users> getUsersForPage(int from, int count)
			throws UsersManagerExeption {
		try {
			return userDao.getUsersForOnePage(from - 1, count);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new UsersManagerExeption("Could not get Users for one page",
					e);
		}
	}

}