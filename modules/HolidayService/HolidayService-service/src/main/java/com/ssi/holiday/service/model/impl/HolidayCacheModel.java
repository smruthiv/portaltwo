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

package com.ssi.holiday.service.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.ssi.holiday.service.model.Holiday;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Holiday in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Holiday
 * @generated
 */
@ProviderType
public class HolidayCacheModel implements CacheModel<Holiday>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HolidayCacheModel)) {
			return false;
		}

		HolidayCacheModel holidayCacheModel = (HolidayCacheModel)obj;

		if (holidayId == holidayCacheModel.holidayId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, holidayId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{holidayId=");
		sb.append(holidayId);
		sb.append(", holidayName=");
		sb.append(holidayName);
		sb.append(", holidayDate=");
		sb.append(holidayDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Holiday toEntityModel() {
		HolidayImpl holidayImpl = new HolidayImpl();

		holidayImpl.setHolidayId(holidayId);

		if (holidayName == null) {
			holidayImpl.setHolidayName(StringPool.BLANK);
		}
		else {
			holidayImpl.setHolidayName(holidayName);
		}

		if (holidayDate == Long.MIN_VALUE) {
			holidayImpl.setHolidayDate(null);
		}
		else {
			holidayImpl.setHolidayDate(new Date(holidayDate));
		}

		holidayImpl.resetOriginalValues();

		return holidayImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		holidayId = objectInput.readInt();
		holidayName = objectInput.readUTF();
		holidayDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(holidayId);

		if (holidayName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(holidayName);
		}

		objectOutput.writeLong(holidayDate);
	}

	public int holidayId;
	public String holidayName;
	public long holidayDate;
}