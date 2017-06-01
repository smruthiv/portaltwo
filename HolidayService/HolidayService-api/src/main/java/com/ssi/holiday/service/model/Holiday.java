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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Holiday service. Represents a row in the &quot;ssi_holiday&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see HolidayModel
 * @see com.ssi.holiday.service.model.impl.HolidayImpl
 * @see com.ssi.holiday.service.model.impl.HolidayModelImpl
 * @generated
 */
@ImplementationClassName("com.ssi.holiday.service.model.impl.HolidayImpl")
@ProviderType
public interface Holiday extends HolidayModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ssi.holiday.service.model.impl.HolidayImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Holiday, Integer> HOLIDAY_ID_ACCESSOR = new Accessor<Holiday, Integer>() {
			@Override
			public Integer get(Holiday holiday) {
				return holiday.getHolidayId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<Holiday> getTypeClass() {
				return Holiday.class;
			}
		};
}