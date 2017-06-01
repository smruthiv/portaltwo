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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class HolidaySoap implements Serializable {
	public static HolidaySoap toSoapModel(Holiday model) {
		HolidaySoap soapModel = new HolidaySoap();

		soapModel.setHolidayId(model.getHolidayId());
		soapModel.setHolidayName(model.getHolidayName());
		soapModel.setHolidayDate(model.getHolidayDate());

		return soapModel;
	}

	public static HolidaySoap[] toSoapModels(Holiday[] models) {
		HolidaySoap[] soapModels = new HolidaySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HolidaySoap[][] toSoapModels(Holiday[][] models) {
		HolidaySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HolidaySoap[models.length][models[0].length];
		}
		else {
			soapModels = new HolidaySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HolidaySoap[] toSoapModels(List<Holiday> models) {
		List<HolidaySoap> soapModels = new ArrayList<HolidaySoap>(models.size());

		for (Holiday model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HolidaySoap[soapModels.size()]);
	}

	public HolidaySoap() {
	}

	public int getPrimaryKey() {
		return _holidayId;
	}

	public void setPrimaryKey(int pk) {
		setHolidayId(pk);
	}

	public int getHolidayId() {
		return _holidayId;
	}

	public void setHolidayId(int holidayId) {
		_holidayId = holidayId;
	}

	public String getHolidayName() {
		return _holidayName;
	}

	public void setHolidayName(String holidayName) {
		_holidayName = holidayName;
	}

	public Date getHolidayDate() {
		return _holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		_holidayDate = holidayDate;
	}

	private int _holidayId;
	private String _holidayName;
	private Date _holidayDate;
}