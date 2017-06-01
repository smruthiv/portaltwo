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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.ssi.holiday.service.exception.NoSuchHolidayException;
import com.ssi.holiday.service.model.Holiday;

import java.util.Date;

/**
 * The persistence interface for the holiday service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ssi.holiday.service.service.persistence.impl.HolidayPersistenceImpl
 * @see HolidayUtil
 * @generated
 */
@ProviderType
public interface HolidayPersistence extends BasePersistence<Holiday> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HolidayUtil} to access the holiday persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the holidaies where holidayDate = &#63;.
	*
	* @param holidayDate the holiday date
	* @return the matching holidaies
	*/
	public java.util.List<Holiday> findByholidayYear(Date holidayDate);

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
	public java.util.List<Holiday> findByholidayYear(Date holidayDate,
		int start, int end);

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
	public java.util.List<Holiday> findByholidayYear(Date holidayDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Holiday> orderByComparator);

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
	public java.util.List<Holiday> findByholidayYear(Date holidayDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Holiday> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first holiday in the ordered set where holidayDate = &#63;.
	*
	* @param holidayDate the holiday date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday
	* @throws NoSuchHolidayException if a matching holiday could not be found
	*/
	public Holiday findByholidayYear_First(Date holidayDate,
		com.liferay.portal.kernel.util.OrderByComparator<Holiday> orderByComparator)
		throws NoSuchHolidayException;

	/**
	* Returns the first holiday in the ordered set where holidayDate = &#63;.
	*
	* @param holidayDate the holiday date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	public Holiday fetchByholidayYear_First(Date holidayDate,
		com.liferay.portal.kernel.util.OrderByComparator<Holiday> orderByComparator);

	/**
	* Returns the last holiday in the ordered set where holidayDate = &#63;.
	*
	* @param holidayDate the holiday date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday
	* @throws NoSuchHolidayException if a matching holiday could not be found
	*/
	public Holiday findByholidayYear_Last(Date holidayDate,
		com.liferay.portal.kernel.util.OrderByComparator<Holiday> orderByComparator)
		throws NoSuchHolidayException;

	/**
	* Returns the last holiday in the ordered set where holidayDate = &#63;.
	*
	* @param holidayDate the holiday date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching holiday, or <code>null</code> if a matching holiday could not be found
	*/
	public Holiday fetchByholidayYear_Last(Date holidayDate,
		com.liferay.portal.kernel.util.OrderByComparator<Holiday> orderByComparator);

	/**
	* Returns the holidaies before and after the current holiday in the ordered set where holidayDate = &#63;.
	*
	* @param holidayId the primary key of the current holiday
	* @param holidayDate the holiday date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next holiday
	* @throws NoSuchHolidayException if a holiday with the primary key could not be found
	*/
	public Holiday[] findByholidayYear_PrevAndNext(int holidayId,
		Date holidayDate,
		com.liferay.portal.kernel.util.OrderByComparator<Holiday> orderByComparator)
		throws NoSuchHolidayException;

	/**
	* Removes all the holidaies where holidayDate = &#63; from the database.
	*
	* @param holidayDate the holiday date
	*/
	public void removeByholidayYear(Date holidayDate);

	/**
	* Returns the number of holidaies where holidayDate = &#63;.
	*
	* @param holidayDate the holiday date
	* @return the number of matching holidaies
	*/
	public int countByholidayYear(Date holidayDate);

	/**
	* Caches the holiday in the entity cache if it is enabled.
	*
	* @param holiday the holiday
	*/
	public void cacheResult(Holiday holiday);

	/**
	* Caches the holidaies in the entity cache if it is enabled.
	*
	* @param holidaies the holidaies
	*/
	public void cacheResult(java.util.List<Holiday> holidaies);

	/**
	* Creates a new holiday with the primary key. Does not add the holiday to the database.
	*
	* @param holidayId the primary key for the new holiday
	* @return the new holiday
	*/
	public Holiday create(int holidayId);

	/**
	* Removes the holiday with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayId the primary key of the holiday
	* @return the holiday that was removed
	* @throws NoSuchHolidayException if a holiday with the primary key could not be found
	*/
	public Holiday remove(int holidayId) throws NoSuchHolidayException;

	public Holiday updateImpl(Holiday holiday);

	/**
	* Returns the holiday with the primary key or throws a {@link NoSuchHolidayException} if it could not be found.
	*
	* @param holidayId the primary key of the holiday
	* @return the holiday
	* @throws NoSuchHolidayException if a holiday with the primary key could not be found
	*/
	public Holiday findByPrimaryKey(int holidayId)
		throws NoSuchHolidayException;

	/**
	* Returns the holiday with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param holidayId the primary key of the holiday
	* @return the holiday, or <code>null</code> if a holiday with the primary key could not be found
	*/
	public Holiday fetchByPrimaryKey(int holidayId);

	@Override
	public java.util.Map<java.io.Serializable, Holiday> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the holidaies.
	*
	* @return the holidaies
	*/
	public java.util.List<Holiday> findAll();

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
	public java.util.List<Holiday> findAll(int start, int end);

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
	public java.util.List<Holiday> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Holiday> orderByComparator);

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
	public java.util.List<Holiday> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Holiday> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the holidaies from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of holidaies.
	*
	* @return the number of holidaies
	*/
	public int countAll();
}