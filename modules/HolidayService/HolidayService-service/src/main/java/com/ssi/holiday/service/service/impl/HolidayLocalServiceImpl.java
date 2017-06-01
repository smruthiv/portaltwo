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

package com.ssi.holiday.service.service.impl;

import aQute.bnd.annotation.ProviderType;

import java.util.List;

import com.ssi.holiday.service.model.Holiday;
import com.ssi.holiday.service.service.base.HolidayLocalServiceBaseImpl;
import com.ssi.holiday.service.service.persistence.HolidayUtil;

/**
 * The implementation of the holiday local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ssi.holiday.service.service.HolidayLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayLocalServiceBaseImpl
 * @see com.ssi.holiday.service.service.HolidayLocalServiceUtil
 */
@ProviderType
public class HolidayLocalServiceImpl extends HolidayLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ssi.holiday.service.service.HolidayLocalServiceUtil} to access the holiday local service.
	 */ 
	
	/**
	 * This method is to get all the list of holidays from the holiday table.
	 */
	public List<Holiday> getCurrentYearHolidays() {  
		
		return HolidayUtil.findAll();
		
	}
	
	
}