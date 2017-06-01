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

package com.ssi.holiday.service.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HolidayLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HolidayLocalService
 * @generated
 */
@ProviderType
public class HolidayLocalServiceWrapper implements HolidayLocalService,
	ServiceWrapper<HolidayLocalService> {
	public HolidayLocalServiceWrapper(HolidayLocalService holidayLocalService) {
		_holidayLocalService = holidayLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _holidayLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _holidayLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _holidayLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the holiday to the database. Also notifies the appropriate model listeners.
	*
	* @param holiday the holiday
	* @return the holiday that was added
	*/
	@Override
	public com.ssi.holiday.service.model.Holiday addHoliday(
		com.ssi.holiday.service.model.Holiday holiday) {
		return _holidayLocalService.addHoliday(holiday);
	}

	/**
	* Creates a new holiday with the primary key. Does not add the holiday to the database.
	*
	* @param holidayId the primary key for the new holiday
	* @return the new holiday
	*/
	@Override
	public com.ssi.holiday.service.model.Holiday createHoliday(int holidayId) {
		return _holidayLocalService.createHoliday(holidayId);
	}

	/**
	* Deletes the holiday from the database. Also notifies the appropriate model listeners.
	*
	* @param holiday the holiday
	* @return the holiday that was removed
	*/
	@Override
	public com.ssi.holiday.service.model.Holiday deleteHoliday(
		com.ssi.holiday.service.model.Holiday holiday) {
		return _holidayLocalService.deleteHoliday(holiday);
	}

	/**
	* Deletes the holiday with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayId the primary key of the holiday
	* @return the holiday that was removed
	* @throws PortalException if a holiday with the primary key could not be found
	*/
	@Override
	public com.ssi.holiday.service.model.Holiday deleteHoliday(int holidayId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayLocalService.deleteHoliday(holidayId);
	}

	@Override
	public com.ssi.holiday.service.model.Holiday fetchHoliday(int holidayId) {
		return _holidayLocalService.fetchHoliday(holidayId);
	}

	/**
	* Returns the holiday with the primary key.
	*
	* @param holidayId the primary key of the holiday
	* @return the holiday
	* @throws PortalException if a holiday with the primary key could not be found
	*/
	@Override
	public com.ssi.holiday.service.model.Holiday getHoliday(int holidayId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayLocalService.getHoliday(holidayId);
	}

	/**
	* Updates the holiday in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param holiday the holiday
	* @return the holiday that was updated
	*/
	@Override
	public com.ssi.holiday.service.model.Holiday updateHoliday(
		com.ssi.holiday.service.model.Holiday holiday) {
		return _holidayLocalService.updateHoliday(holiday);
	}

	/**
	* Returns the number of holidaies.
	*
	* @return the number of holidaies
	*/
	@Override
	public int getHolidaiesCount() {
		return _holidayLocalService.getHolidaiesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _holidayLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _holidayLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ssi.holiday.service.model.impl.HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _holidayLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ssi.holiday.service.model.impl.HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _holidayLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.ssi.holiday.service.model.Holiday> getCurrentYearHolidays() {
		return _holidayLocalService.getCurrentYearHolidays();
	}

	/**
	* Returns a range of all the holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ssi.holiday.service.model.impl.HolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holidaies
	* @param end the upper bound of the range of holidaies (not inclusive)
	* @return the range of holidaies
	*/
	@Override
	public java.util.List<com.ssi.holiday.service.model.Holiday> getHolidaies(
		int start, int end) {
		return _holidayLocalService.getHolidaies(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _holidayLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _holidayLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public HolidayLocalService getWrappedService() {
		return _holidayLocalService;
	}

	@Override
	public void setWrappedService(HolidayLocalService holidayLocalService) {
		_holidayLocalService = holidayLocalService;
	}

	private HolidayLocalService _holidayLocalService;
}