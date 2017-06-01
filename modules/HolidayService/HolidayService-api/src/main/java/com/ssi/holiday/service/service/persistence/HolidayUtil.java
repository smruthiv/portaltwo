/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.ssi.holiday.service.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ssi.holiday.service.model.Holiday;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the holiday service. This utility wraps {@link com.ssi.holiday.service.service.persistence.impl.HolidayPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayPersistence
 * @see com.ssi.holiday.service.service.persistence.impl.HolidayPersistenceImpl
 * @generated
 */
@ProviderType
public class HolidayUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Holiday holiday) {
		getPersistence().clearCache(holiday);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Holiday> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Holiday> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Holiday> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Holiday> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Holiday update(Holiday holiday) {
		return getPersistence().update(holiday);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Holiday update(Holiday holiday, ServiceContext serviceContext) {
		return getPersistence().update(holiday, serviceContext);
	}

	/**
	* Returns all the holidaies where holidayDate = &#63;.
	*
	* @param holidayDate the holiday date
	* @return the matching holidaies
	*/
	public static List<Holiday> findByholidayYear(Date holidayDate) {
		return getPersistence().findByholidayYear(holidayDate);
	}

	/**
	* Returns a range of all the holidaies where holidayDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param holidayDate the holiday date
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @return the range of matching holidaies
	*/
	public static List<Holiday> findByholidayYear(Date holidayDate, int start,
		int end) {
		return getPersistence().findByholidayYear(holidayDate, start, end);
	}

	/**
	* Returns an ordered range of all the holidaies where holidayDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param holidayDate the holiday date
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching holidaies
	*/
	public static List<Holiday> findByholidayYear(Date holidayDate, int start,
		int end, OrderByComparator<Holiday> orderByComparator) {
		return getPersistence()
				   .findByholidayYear(holidayDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the holidaies where holidayDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param holidayDate the holiday date
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching holidaies
	*/
	public static List<Holiday> findByholidayYear(Date holidayDate, int start,
		int end, OrderByComparator<Holiday> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByholidayYear(holidayDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first holiday in the ordered set where holidayDate = &#63;.
	*
	* @param holidayDate the holiday date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday
	* @throws NoSuchHolidayException if a matching holiday could not be found
	*/
	public static Holiday findByholidayYear_First(Date holidayDate,
		OrderByComparator<Holiday> orderByComparator)
		throws com.ssi.holiday.service.exception.NoSuchHolidayException {
		return getPersistence()
				   .findByholidayYear_First(holidayDate, orderByComparator);
	}

	/**
	* Returns the first holiday in the ordered set where holidayDate = &#63;.
	*
	* @param holidayDate the holiday date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	public static Holiday fetchByholidayYear_First(Date holidayDate,
		OrderByComparator<Holiday> orderByComparator) {
		return getPersistence()
				   .fetchByholidayYear_First(holidayDate, orderByComparator);
	}

	/**
	* Returns the last holiday in the ordered set where holidayDate = &#63;.
	*
	* @param holidayDate the holiday date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday
	* @throws NoSuchHolidayException if a matching holiday could not be found
	*/
	public static Holiday findByholidayYear_Last(Date holidayDate,
		OrderByComparator<Holiday> orderByComparator)
		throws com.ssi.holiday.service.exception.NoSuchHolidayException {
		return getPersistence()
				   .findByholidayYear_Last(holidayDate, orderByComparator);
	}

	/**
	* Returns the last holiday in the ordered set where holidayDate = &#63;.
	*
	* @param holidayDate the holiday date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	public static Holiday fetchByholidayYear_Last(Date holidayDate,
		OrderByComparator<Holiday> orderByComparator) {
		return getPersistence()
				   .fetchByholidayYear_Last(holidayDate, orderByComparator);
	}

	/**
	* Returns the holidaies before and after the current holiday in the ordered set where holidayDate = &#63;.
	*
	* @param holidayId the primary key of the current holiday
	* @param holidayDate the holiday date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next holiday
	* @throws NoSuchHolidayException if a holiday with the primary key could not be found
	*/
	public static Holiday[] findByholidayYear_PrevAndNext(int holidayId,
		Date holidayDate, OrderByComparator<Holiday> orderByComparator)
		throws com.ssi.holiday.service.exception.NoSuchHolidayException {
		return getPersistence()
				   .findByholidayYear_PrevAndNext(holidayId, holidayDate,
			orderByComparator);
	}

	/**
	* Removes all the holidaies where holidayDate = &#63; from the database.
	*
	* @param holidayDate the holiday date
	*/
	public static void removeByholidayYear(Date holidayDate) {
		getPersistence().removeByholidayYear(holidayDate);
	}

	/**
	* Returns the number of holidaies where holidayDate = &#63;.
	*
	* @param holidayDate the holiday date
	* @return the number of matching holidaies
	*/
	public static int countByholidayYear(Date holidayDate) {
		return getPersistence().countByholidayYear(holidayDate);
	}

	/**
	* Caches the holiday in the entity cache if it is enabled.
	*
	* @param holiday the holiday
	*/
	public static void cacheResult(Holiday holiday) {
		getPersistence().cacheResult(holiday);
	}

	/**
	* Caches the holidaies in the entity cache if it is enabled.
	*
	* @param holidaies the holidaies
	*/
	public static void cacheResult(List<Holiday> holidaies) {
		getPersistence().cacheResult(holidaies);
	}

	/**
	* Creates a new holiday with the primary key. Does not add the holiday to the database.
	*
	* @param holidayId the primary key for the new holiday
	* @return the new holiday
	*/
	public static Holiday create(int holidayId) {
		return getPersistence().create(holidayId);
	}

	/**
	* Removes the holiday with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayId the primary key of the holiday
	* @return the holiday that was removed
	* @throws NoSuchHolidayException if a holiday with the primary key could not be found
	*/
	public static Holiday remove(int holidayId)
		throws com.ssi.holiday.service.exception.NoSuchHolidayException {
		return getPersistence().remove(holidayId);
	}

	public static Holiday updateImpl(Holiday holiday) {
		return getPersistence().updateImpl(holiday);
	}

	/**
	* Returns the holiday with the primary key or throws a {@link NoSuchHolidayException} if it could not be found.
	*
	* @param holidayId the primary key of the holiday
	* @return the holiday
	* @throws NoSuchHolidayException if a holiday with the primary key could not be found
	*/
	public static Holiday findByPrimaryKey(int holidayId)
		throws com.ssi.holiday.service.exception.NoSuchHolidayException {
		return getPersistence().findByPrimaryKey(holidayId);
	}

	/**
	* Returns the holiday with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param holidayId the primary key of the holiday
	* @return the holiday, or <code>null</code> if a holiday with the primary key could not be found
	*/
	public static Holiday fetchByPrimaryKey(int holidayId) {
		return getPersistence().fetchByPrimaryKey(holidayId);
	}

	public static java.util.Map<java.io.Serializable, Holiday> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the holidaies.
	*
	* @return the holidaies
	*/
	public static List<Holiday> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @return the range of holidaies
	*/
	public static List<Holiday> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of holidaies
	*/
	public static List<Holiday> findAll(int start, int end,
		OrderByComparator<Holiday> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of holidaies
	*/
	public static List<Holiday> findAll(int start, int end,
		OrderByComparator<Holiday> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the holidaies from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of holidaies.
	*
	* @return the number of holidaies
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static HolidayPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HolidayPersistence, HolidayPersistence> _serviceTracker =
		ServiceTrackerFactory.open(HolidayPersistence.class);
}