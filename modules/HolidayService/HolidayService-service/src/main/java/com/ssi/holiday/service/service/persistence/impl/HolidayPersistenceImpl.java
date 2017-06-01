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

package com.ssi.holiday.service.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.ssi.holiday.service.exception.NoSuchHolidayException;
import com.ssi.holiday.service.model.Holiday;
import com.ssi.holiday.service.model.impl.HolidayImpl;
import com.ssi.holiday.service.model.impl.HolidayModelImpl;
import com.ssi.holiday.service.service.persistence.HolidayPersistence;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the holiday service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayPersistence
 * @see com.ssi.holiday.service.service.persistence.HolidayUtil
 * @generated
 */
@ProviderType
public class HolidayPersistenceImpl extends BasePersistenceImpl<Holiday>
	implements HolidayPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HolidayUtil} to access the holiday persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HolidayImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HolidayModelImpl.ENTITY_CACHE_ENABLED,
			HolidayModelImpl.FINDER_CACHE_ENABLED, HolidayImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HolidayModelImpl.ENTITY_CACHE_ENABLED,
			HolidayModelImpl.FINDER_CACHE_ENABLED, HolidayImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HolidayModelImpl.ENTITY_CACHE_ENABLED,
			HolidayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_HOLIDAYYEAR =
		new FinderPath(HolidayModelImpl.ENTITY_CACHE_ENABLED,
			HolidayModelImpl.FINDER_CACHE_ENABLED, HolidayImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByholidayYear",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYYEAR =
		new FinderPath(HolidayModelImpl.ENTITY_CACHE_ENABLED,
			HolidayModelImpl.FINDER_CACHE_ENABLED, HolidayImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByholidayYear",
			new String[] { Date.class.getName() },
			HolidayModelImpl.HOLIDAYDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_HOLIDAYYEAR = new FinderPath(HolidayModelImpl.ENTITY_CACHE_ENABLED,
			HolidayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByholidayYear",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the holidaies where holidayDate = &#63;.
	 *
	 * @param holidayDate the holiday date
	 * @return the matching holidaies
	 */
	@Override
	public List<Holiday> findByholidayYear(Date holidayDate) {
		return findByholidayYear(holidayDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Holiday> findByholidayYear(Date holidayDate, int start, int end) {
		return findByholidayYear(holidayDate, start, end, null);
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
	@Override
	public List<Holiday> findByholidayYear(Date holidayDate, int start,
		int end, OrderByComparator<Holiday> orderByComparator) {
		return findByholidayYear(holidayDate, start, end, orderByComparator,
			true);
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
	@Override
	public List<Holiday> findByholidayYear(Date holidayDate, int start,
		int end, OrderByComparator<Holiday> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYYEAR;
			finderArgs = new Object[] { holidayDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_HOLIDAYYEAR;
			finderArgs = new Object[] { holidayDate, start, end, orderByComparator };
		}

		List<Holiday> list = null;

		if (retrieveFromCache) {
			list = (List<Holiday>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Holiday holiday : list) {
					if (!Objects.equals(holidayDate, holiday.getHolidayDate())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_HOLIDAY_WHERE);

			boolean bindHolidayDate = false;

			if (holidayDate == null) {
				query.append(_FINDER_COLUMN_HOLIDAYYEAR_HOLIDAYDATE_1);
			}
			else {
				bindHolidayDate = true;

				query.append(_FINDER_COLUMN_HOLIDAYYEAR_HOLIDAYDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(HolidayModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindHolidayDate) {
					qPos.add(new Timestamp(holidayDate.getTime()));
				}

				if (!pagination) {
					list = (List<Holiday>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Holiday>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first holiday in the ordered set where holidayDate = &#63;.
	 *
	 * @param holidayDate the holiday date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday
	 * @throws NoSuchHolidayException if a matching holiday could not be found
	 */
	@Override
	public Holiday findByholidayYear_First(Date holidayDate,
		OrderByComparator<Holiday> orderByComparator)
		throws NoSuchHolidayException {
		Holiday holiday = fetchByholidayYear_First(holidayDate,
				orderByComparator);

		if (holiday != null) {
			return holiday;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("holidayDate=");
		msg.append(holidayDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHolidayException(msg.toString());
	}

	/**
	 * Returns the first holiday in the ordered set where holidayDate = &#63;.
	 *
	 * @param holidayDate the holiday date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday, or <code>null</code> if a matching holiday could not be found
	 */
	@Override
	public Holiday fetchByholidayYear_First(Date holidayDate,
		OrderByComparator<Holiday> orderByComparator) {
		List<Holiday> list = findByholidayYear(holidayDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last holiday in the ordered set where holidayDate = &#63;.
	 *
	 * @param holidayDate the holiday date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday
	 * @throws NoSuchHolidayException if a matching holiday could not be found
	 */
	@Override
	public Holiday findByholidayYear_Last(Date holidayDate,
		OrderByComparator<Holiday> orderByComparator)
		throws NoSuchHolidayException {
		Holiday holiday = fetchByholidayYear_Last(holidayDate, orderByComparator);

		if (holiday != null) {
			return holiday;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("holidayDate=");
		msg.append(holidayDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHolidayException(msg.toString());
	}

	/**
	 * Returns the last holiday in the ordered set where holidayDate = &#63;.
	 *
	 * @param holidayDate the holiday date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday, or <code>null</code> if a matching holiday could not be found
	 */
	@Override
	public Holiday fetchByholidayYear_Last(Date holidayDate,
		OrderByComparator<Holiday> orderByComparator) {
		int count = countByholidayYear(holidayDate);

		if (count == 0) {
			return null;
		}

		List<Holiday> list = findByholidayYear(holidayDate, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Holiday[] findByholidayYear_PrevAndNext(int holidayId,
		Date holidayDate, OrderByComparator<Holiday> orderByComparator)
		throws NoSuchHolidayException {
		Holiday holiday = findByPrimaryKey(holidayId);

		Session session = null;

		try {
			session = openSession();

			Holiday[] array = new HolidayImpl[3];

			array[0] = getByholidayYear_PrevAndNext(session, holiday,
					holidayDate, orderByComparator, true);

			array[1] = holiday;

			array[2] = getByholidayYear_PrevAndNext(session, holiday,
					holidayDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Holiday getByholidayYear_PrevAndNext(Session session,
		Holiday holiday, Date holidayDate,
		OrderByComparator<Holiday> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_HOLIDAY_WHERE);

		boolean bindHolidayDate = false;

		if (holidayDate == null) {
			query.append(_FINDER_COLUMN_HOLIDAYYEAR_HOLIDAYDATE_1);
		}
		else {
			bindHolidayDate = true;

			query.append(_FINDER_COLUMN_HOLIDAYYEAR_HOLIDAYDATE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(HolidayModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindHolidayDate) {
			qPos.add(new Timestamp(holidayDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(holiday);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Holiday> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the holidaies where holidayDate = &#63; from the database.
	 *
	 * @param holidayDate the holiday date
	 */
	@Override
	public void removeByholidayYear(Date holidayDate) {
		for (Holiday holiday : findByholidayYear(holidayDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(holiday);
		}
	}

	/**
	 * Returns the number of holidaies where holidayDate = &#63;.
	 *
	 * @param holidayDate the holiday date
	 * @return the number of matching holidaies
	 */
	@Override
	public int countByholidayYear(Date holidayDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_HOLIDAYYEAR;

		Object[] finderArgs = new Object[] { holidayDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HOLIDAY_WHERE);

			boolean bindHolidayDate = false;

			if (holidayDate == null) {
				query.append(_FINDER_COLUMN_HOLIDAYYEAR_HOLIDAYDATE_1);
			}
			else {
				bindHolidayDate = true;

				query.append(_FINDER_COLUMN_HOLIDAYYEAR_HOLIDAYDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindHolidayDate) {
					qPos.add(new Timestamp(holidayDate.getTime()));
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_HOLIDAYYEAR_HOLIDAYDATE_1 = "holiday.holidayDate IS NULL";
	private static final String _FINDER_COLUMN_HOLIDAYYEAR_HOLIDAYDATE_2 = "holiday.holidayDate = ?";

	public HolidayPersistenceImpl() {
		setModelClass(Holiday.class);
	}

	/**
	 * Caches the holiday in the entity cache if it is enabled.
	 *
	 * @param holiday the holiday
	 */
	@Override
	public void cacheResult(Holiday holiday) {
		entityCache.putResult(HolidayModelImpl.ENTITY_CACHE_ENABLED,
			HolidayImpl.class, holiday.getPrimaryKey(), holiday);

		holiday.resetOriginalValues();
	}

	/**
	 * Caches the holidaies in the entity cache if it is enabled.
	 *
	 * @param holidaies the holidaies
	 */
	@Override
	public void cacheResult(List<Holiday> holidaies) {
		for (Holiday holiday : holidaies) {
			if (entityCache.getResult(HolidayModelImpl.ENTITY_CACHE_ENABLED,
						HolidayImpl.class, holiday.getPrimaryKey()) == null) {
				cacheResult(holiday);
			}
			else {
				holiday.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all holidaies.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HolidayImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the holiday.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Holiday holiday) {
		entityCache.removeResult(HolidayModelImpl.ENTITY_CACHE_ENABLED,
			HolidayImpl.class, holiday.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Holiday> holidaies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Holiday holiday : holidaies) {
			entityCache.removeResult(HolidayModelImpl.ENTITY_CACHE_ENABLED,
				HolidayImpl.class, holiday.getPrimaryKey());
		}
	}

	/**
	 * Creates a new holiday with the primary key. Does not add the holiday to the database.
	 *
	 * @param holidayId the primary key for the new holiday
	 * @return the new holiday
	 */
	@Override
	public Holiday create(int holidayId) {
		Holiday holiday = new HolidayImpl();

		holiday.setNew(true);
		holiday.setPrimaryKey(holidayId);

		return holiday;
	}

	/**
	 * Removes the holiday with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param holidayId the primary key of the holiday
	 * @return the holiday that was removed
	 * @throws NoSuchHolidayException if a holiday with the primary key could not be found
	 */
	@Override
	public Holiday remove(int holidayId) throws NoSuchHolidayException {
		return remove((Serializable)holidayId);
	}

	/**
	 * Removes the holiday with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the holiday
	 * @return the holiday that was removed
	 * @throws NoSuchHolidayException if a holiday with the primary key could not be found
	 */
	@Override
	public Holiday remove(Serializable primaryKey)
		throws NoSuchHolidayException {
		Session session = null;

		try {
			session = openSession();

			Holiday holiday = (Holiday)session.get(HolidayImpl.class, primaryKey);

			if (holiday == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHolidayException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(holiday);
		}
		catch (NoSuchHolidayException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Holiday removeImpl(Holiday holiday) {
		holiday = toUnwrappedModel(holiday);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(holiday)) {
				holiday = (Holiday)session.get(HolidayImpl.class,
						holiday.getPrimaryKeyObj());
			}

			if (holiday != null) {
				session.delete(holiday);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (holiday != null) {
			clearCache(holiday);
		}

		return holiday;
	}

	@Override
	public Holiday updateImpl(Holiday holiday) {
		holiday = toUnwrappedModel(holiday);

		boolean isNew = holiday.isNew();

		HolidayModelImpl holidayModelImpl = (HolidayModelImpl)holiday;

		Session session = null;

		try {
			session = openSession();

			if (holiday.isNew()) {
				session.save(holiday);

				holiday.setNew(false);
			}
			else {
				holiday = (Holiday)session.merge(holiday);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !HolidayModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((holidayModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYYEAR.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						holidayModelImpl.getOriginalHolidayDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_HOLIDAYYEAR, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYYEAR,
					args);

				args = new Object[] { holidayModelImpl.getHolidayDate() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_HOLIDAYYEAR, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYYEAR,
					args);
			}
		}

		entityCache.putResult(HolidayModelImpl.ENTITY_CACHE_ENABLED,
			HolidayImpl.class, holiday.getPrimaryKey(), holiday, false);

		holiday.resetOriginalValues();

		return holiday;
	}

	protected Holiday toUnwrappedModel(Holiday holiday) {
		if (holiday instanceof HolidayImpl) {
			return holiday;
		}

		HolidayImpl holidayImpl = new HolidayImpl();

		holidayImpl.setNew(holiday.isNew());
		holidayImpl.setPrimaryKey(holiday.getPrimaryKey());

		holidayImpl.setHolidayId(holiday.getHolidayId());
		holidayImpl.setHolidayName(holiday.getHolidayName());
		holidayImpl.setHolidayDate(holiday.getHolidayDate());

		return holidayImpl;
	}

	/**
	 * Returns the holiday with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the holiday
	 * @return the holiday
	 * @throws NoSuchHolidayException if a holiday with the primary key could not be found
	 */
	@Override
	public Holiday findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHolidayException {
		Holiday holiday = fetchByPrimaryKey(primaryKey);

		if (holiday == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHolidayException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return holiday;
	}

	/**
	 * Returns the holiday with the primary key or throws a {@link NoSuchHolidayException} if it could not be found.
	 *
	 * @param holidayId the primary key of the holiday
	 * @return the holiday
	 * @throws NoSuchHolidayException if a holiday with the primary key could not be found
	 */
	@Override
	public Holiday findByPrimaryKey(int holidayId)
		throws NoSuchHolidayException {
		return findByPrimaryKey((Serializable)holidayId);
	}

	/**
	 * Returns the holiday with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the holiday
	 * @return the holiday, or <code>null</code> if a holiday with the primary key could not be found
	 */
	@Override
	public Holiday fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HolidayModelImpl.ENTITY_CACHE_ENABLED,
				HolidayImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Holiday holiday = (Holiday)serializable;

		if (holiday == null) {
			Session session = null;

			try {
				session = openSession();

				holiday = (Holiday)session.get(HolidayImpl.class, primaryKey);

				if (holiday != null) {
					cacheResult(holiday);
				}
				else {
					entityCache.putResult(HolidayModelImpl.ENTITY_CACHE_ENABLED,
						HolidayImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HolidayModelImpl.ENTITY_CACHE_ENABLED,
					HolidayImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return holiday;
	}

	/**
	 * Returns the holiday with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param holidayId the primary key of the holiday
	 * @return the holiday, or <code>null</code> if a holiday with the primary key could not be found
	 */
	@Override
	public Holiday fetchByPrimaryKey(int holidayId) {
		return fetchByPrimaryKey((Serializable)holidayId);
	}

	@Override
	public Map<Serializable, Holiday> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Holiday> map = new HashMap<Serializable, Holiday>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Holiday holiday = fetchByPrimaryKey(primaryKey);

			if (holiday != null) {
				map.put(primaryKey, holiday);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(HolidayModelImpl.ENTITY_CACHE_ENABLED,
					HolidayImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Holiday)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_HOLIDAY_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Holiday holiday : (List<Holiday>)q.list()) {
				map.put(holiday.getPrimaryKeyObj(), holiday);

				cacheResult(holiday);

				uncachedPrimaryKeys.remove(holiday.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(HolidayModelImpl.ENTITY_CACHE_ENABLED,
					HolidayImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the holidaies.
	 *
	 * @return the holidaies
	 */
	@Override
	public List<Holiday> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Holiday> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<Holiday> findAll(int start, int end,
		OrderByComparator<Holiday> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<Holiday> findAll(int start, int end,
		OrderByComparator<Holiday> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Holiday> list = null;

		if (retrieveFromCache) {
			list = (List<Holiday>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HOLIDAY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HOLIDAY;

				if (pagination) {
					sql = sql.concat(HolidayModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Holiday>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Holiday>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the holidaies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Holiday holiday : findAll()) {
			remove(holiday);
		}
	}

	/**
	 * Returns the number of holidaies.
	 *
	 * @return the number of holidaies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HOLIDAY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return HolidayModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the holiday persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HolidayImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HOLIDAY = "SELECT holiday FROM Holiday holiday";
	private static final String _SQL_SELECT_HOLIDAY_WHERE_PKS_IN = "SELECT holiday FROM Holiday holiday WHERE holidayId IN (";
	private static final String _SQL_SELECT_HOLIDAY_WHERE = "SELECT holiday FROM Holiday holiday WHERE ";
	private static final String _SQL_COUNT_HOLIDAY = "SELECT COUNT(holiday) FROM Holiday holiday";
	private static final String _SQL_COUNT_HOLIDAY_WHERE = "SELECT COUNT(holiday) FROM Holiday holiday WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "holiday.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Holiday exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Holiday exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(HolidayPersistenceImpl.class);
}