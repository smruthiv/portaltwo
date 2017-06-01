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

package com.ssi.holiday.service.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Holiday}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Holiday
 * @generated
 */
@ProviderType
public class HolidayWrapper implements Holiday, ModelWrapper<Holiday> {
	public HolidayWrapper(Holiday holiday) {
		_holiday = holiday;
	}

	@Override
	public Class<?> getModelClass() {
		return Holiday.class;
	}

	@Override
	public String getModelClassName() {
		return Holiday.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("holidayId", getHolidayId());
		attributes.put("holidayName", getHolidayName());
		attributes.put("holidayDate", getHolidayDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer holidayId = (Integer)attributes.get("holidayId");

		if (holidayId != null) {
			setHolidayId(holidayId);
		}

		String holidayName = (String)attributes.get("holidayName");

		if (holidayName != null) {
			setHolidayName(holidayName);
		}

		Date holidayDate = (Date)attributes.get("holidayDate");

		if (holidayDate != null) {
			setHolidayDate(holidayDate);
		}
	}

	@Override
	public Holiday toEscapedModel() {
		return new HolidayWrapper(_holiday.toEscapedModel());
	}

	@Override
	public Holiday toUnescapedModel() {
		return new HolidayWrapper(_holiday.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _holiday.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _holiday.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _holiday.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _holiday.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Holiday> toCacheModel() {
		return _holiday.toCacheModel();
	}

	@Override
	public int compareTo(Holiday holiday) {
		return _holiday.compareTo(holiday);
	}

	/**
	* Returns the holiday ID of this holiday.
	*
	* @return the holiday ID of this holiday
	*/
	@Override
	public int getHolidayId() {
		return _holiday.getHolidayId();
	}

	/**
	* Returns the primary key of this holiday.
	*
	* @return the primary key of this holiday
	*/
	@Override
	public int getPrimaryKey() {
		return _holiday.getPrimaryKey();
	}

	@Override
	public int hashCode() {
		return _holiday.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _holiday.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new HolidayWrapper((Holiday)_holiday.clone());
	}

	/**
	* Returns the holiday name of this holiday.
	*
	* @return the holiday name of this holiday
	*/
	@Override
	public java.lang.String getHolidayName() {
		return _holiday.getHolidayName();
	}

	@Override
	public java.lang.String toString() {
		return _holiday.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _holiday.toXmlString();
	}

	/**
	* Returns the holiday date of this holiday.
	*
	* @return the holiday date of this holiday
	*/
	@Override
	public Date getHolidayDate() {
		return _holiday.getHolidayDate();
	}

	@Override
	public void persist() {
		_holiday.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_holiday.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_holiday.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_holiday.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_holiday.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the holiday date of this holiday.
	*
	* @param holidayDate the holiday date of this holiday
	*/
	@Override
	public void setHolidayDate(Date holidayDate) {
		_holiday.setHolidayDate(holidayDate);
	}

	/**
	* Sets the holiday ID of this holiday.
	*
	* @param holidayId the holiday ID of this holiday
	*/
	@Override
	public void setHolidayId(int holidayId) {
		_holiday.setHolidayId(holidayId);
	}

	/**
	* Sets the holiday name of this holiday.
	*
	* @param holidayName the holiday name of this holiday
	*/
	@Override
	public void setHolidayName(java.lang.String holidayName) {
		_holiday.setHolidayName(holidayName);
	}

	@Override
	public void setNew(boolean n) {
		_holiday.setNew(n);
	}

	/**
	* Sets the primary key of this holiday.
	*
	* @param primaryKey the primary key of this holiday
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_holiday.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_holiday.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HolidayWrapper)) {
			return false;
		}

		HolidayWrapper holidayWrapper = (HolidayWrapper)obj;

		if (Objects.equals(_holiday, holidayWrapper._holiday)) {
			return true;
		}

		return false;
	}

	@Override
	public Holiday getWrappedModel() {
		return _holiday;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _holiday.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _holiday.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_holiday.resetOriginalValues();
	}

	private final Holiday _holiday;
}