package com.bage.service.statistics;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bage.constraints.ConfigConstraints;
import com.bage.domain.statistics.ActionInfoStatistics;
import com.bage.mapper.statistics.ActionInfoStatisticsMapper;
import com.bage.utils.TimeUtils;

@Service
public class ActionInfoStatisticsService {

	@Autowired
	private ActionInfoStatisticsMapper actionInfoStatisticsMapper;

	public List<Map<String, Object>> queryLatest(String appid, String maxId) {
		return actionInfoStatisticsMapper.queryLatest(appid, maxId);
	}

	public int insertBatchMinute(List<ActionInfoStatistics> list) {
		return actionInfoStatisticsMapper.insertBatchMinute(list);
	}

	public int insertBatchHour(List<ActionInfoStatistics> list) {
		return actionInfoStatisticsMapper.insertBatchHour(list);
	}

	public int insertBatchDay(List<ActionInfoStatistics> list) {
		return actionInfoStatisticsMapper.insertBatchDay(list);
	}

	public int insertBatchMonth(List<ActionInfoStatistics> list) {
		return actionInfoStatisticsMapper.insertBatchMonth(list);
	}

	public int insertBatchYear(List<ActionInfoStatistics> list) {
		return actionInfoStatisticsMapper.insertBatchYear(list);
	}

	/**
	 * 
	 * @param n
	 * @param startTime
	 * @param stopTime
	 * @param memorySortType
	 * @param memoryDurationType
	 * @return
	 */
	public List<Map<String, Object>> topN(String appid, int n, String startTime, String stopTime, String sortType,
			String durationType) {
		// 要么升序排序，要么降序
		if (ConfigConstraints.MEMORYSORTTYPE_ASC.equals(sortType)) {
			sortType = ConfigConstraints.MEMORYSORTTYPE_ASC;
		} else {
			sortType = ConfigConstraints.MEMORYSORTTYPE_DESC;
		}
		// 先判断是否是自定义时间
		if ("".equals(startTime) || "".equals(stopTime)) {
			// 不是自定义时间查询
			if (ConfigConstraints.MEMORYDURATIONTYPE_LATESTHOUR.equals(durationType)) {
				// 最近一小时
				stopTime = TimeUtils.getCurrentTime();
				startTime = TimeUtils.getNextTime(stopTime, 0, 0, 0, -1, 0, 0);
				return actionInfoStatisticsMapper.topNHour(appid, n, sortType, startTime, stopTime);
			} else if (ConfigConstraints.MEMORYDURATIONTYPE_LATESTDAY.equals(durationType)) {
				// 最近一天
				stopTime = TimeUtils.getCurrentTime();
				startTime = TimeUtils.getNextTime(stopTime, 0, 0, -1, 0, 0, 0);
				System.out.println("" + startTime + "cc" + stopTime);
				return actionInfoStatisticsMapper.topNDay(appid, n, sortType, startTime, stopTime);
			} else if (ConfigConstraints.MEMORYDURATIONTYPE_LATESTMONTH.equals(durationType)) {
				// 最近一月
				stopTime = TimeUtils.getCurrentTime();
				startTime = TimeUtils.getNextTime(stopTime, 0, -1, 0, 0, 0, 0);
				return actionInfoStatisticsMapper.topNMonth(appid, n, sortType, startTime, stopTime);
			} else if (ConfigConstraints.MEMORYDURATIONTYPE_LATESTYEAR.equals(durationType)) {
				// 最近一年
				stopTime = TimeUtils.getCurrentTime();
				startTime = TimeUtils.getNextTime(stopTime, -1, 0, 0, 0, 0, 0);
				return actionInfoStatisticsMapper.topNYear(appid, n, sortType, startTime, stopTime);
			}
		} else {
			// 自定义时间查询
			long[] dis = TimeUtils.getDistanceTimes(startTime, stopTime);
			if (dis[0] >= 365) { // 应该查询年统计表
				return actionInfoStatisticsMapper.topNYear(appid, n, sortType, startTime, stopTime);
			} else if (dis[0] > 31) { // 应该查询月统计表
				return actionInfoStatisticsMapper.topNMonth(appid, n, sortType, startTime, stopTime);
			} else if (dis[0] > 0) { // 应该查询日统计表
				return actionInfoStatisticsMapper.topNDay(appid, n, sortType, startTime, stopTime);
			} else if (dis[1] > 0) { // 应该查询时统计表
				return actionInfoStatisticsMapper.topNHour(appid, n, sortType, startTime, stopTime);
			} else {// 应该查询分统计表
				// return actionInfoStatisticsMapper.topNMinute(n, sortType,
				// startTime, stopTime);
			}
		}
		// 默认 最近一小时
		stopTime = TimeUtils.getCurrentTime();
		startTime = TimeUtils.getNextTime(stopTime, 0, 0, 0, -1, 0, 0);
		return actionInfoStatisticsMapper.topNHour(appid, n, sortType, startTime, stopTime);
	}

	/**
	 * 查询最新的数据(查询的是分数据表)，默认 最近一個月
	 * 
	 * @param appid
	 * @param n
	 * @param startTime
	 * @param stopTime
	 * @return
	 */
	public List<Map<String, Object>> latest(String appid, int n) {

		String stopTime = TimeUtils.getCurrentTime();
		String startTime = TimeUtils.getNextTime(stopTime, 0, -1, 0, 0, 0, 0);
		return actionInfoStatisticsMapper.latest(appid, n, startTime, stopTime);
	}

	// 查询分表
	private int getMinuteTotalRow(String appid, String condition, String startTime, String stopTime) {
		return actionInfoStatisticsMapper.getMinuteTotalRow(appid, condition, startTime, stopTime);
	}

	public int getMinuteTotalPageNum(String appid, int pageLength, String condition, String startTime,
			String stopTime) {
		int totalRow = getMinuteTotalRow(appid, condition, startTime, stopTime);
		int totalPage = totalRow / pageLength;
		if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
			totalPage = totalPage + 1;
		}
		return totalPage;
	}

	// 查询时表
	private int getHourTotalRow(String appid, String condition, String startTime, String stopTime) {
		return actionInfoStatisticsMapper.getHourTotalRow(appid, condition, startTime, stopTime);
	}

	public int getHourTotalPageNum(String appid, int pageLength, String condition, String startTime, String stopTime) {
		int totalRow = getHourTotalRow(appid, condition, startTime, stopTime);
		int totalPage = totalRow / pageLength;
		if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
			totalPage = totalPage + 1;
		}
		return totalPage;
	}

	// 查询日表
	private int getDayTotalRow(String appid, String condition, String startTime, String stopTime) {
		return actionInfoStatisticsMapper.getDayTotalRow(appid, condition, startTime, stopTime);
	}

	public int getDayTotalPageNum(String appid, int pageLength, String condition, String startTime, String stopTime) {
		int totalRow = getDayTotalRow(appid, condition, startTime, stopTime);
		int totalPage = totalRow / pageLength;
		if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
			totalPage = totalPage + 1;
		}
		return totalPage;
	}

	// 查询月表
	private int getMonthTotalRow(String appid, String condition, String startTime, String stopTime) {
		return actionInfoStatisticsMapper.getMonthTotalRow(appid, condition, startTime, stopTime);
	}

	public int getMonthTotalPageNum(String appid, int pageLength, String condition, String startTime, String stopTime) {
		int totalRow = getMonthTotalRow(appid, condition, startTime, stopTime);
		int totalPage = totalRow / pageLength;
		if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
			totalPage = totalPage + 1;
		}
		return totalPage;
	}

	// 查询年表
	private int getYearTotalRow(String appid, String condition, String startTime, String stopTime) {
		return actionInfoStatisticsMapper.getYearTotalRow(appid, condition, startTime, stopTime);
	}

	public int getYearTotalPageNum(String appid, int pageLength, String condition, String startTime, String stopTime) {
		int totalRow = getYearTotalRow(appid, condition, startTime, stopTime);
		int totalPage = totalRow / pageLength;
		if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
			totalPage = totalPage + 1;
		}
		return totalPage;
	}

	public int getTotalPageNum(String appid, int tagPage, int pageLength, String condition, String timeDuration,
			String startTime, String stopTime) {
		int totalPage = 1;
		// 先判断是否是自定义时间
		if ("".equals(startTime) || "".equals(stopTime)) {
			if (ConfigConstraints.TIMEDURATION_MINUTE.equals(timeDuration)) {
				// 查询分时间表
				int totalRow = getMinuteTotalRow(appid, condition, startTime, stopTime);
				totalPage = totalRow / pageLength;
				if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
					totalPage = totalPage + 1;
				}
				if (tagPage <= 0) {
					tagPage = totalPage;
				}
				if (tagPage > totalPage) {
					tagPage = totalPage;
				}
				if (totalPage <= 0) {
					tagPage = 1;
					totalPage = 1;
				}
			} else if (ConfigConstraints.TIMEDURATION_HOUR.equals(timeDuration)) {
				// 查询时时间表
				int totalRow = getHourTotalRow(appid, condition, startTime, stopTime);
				totalPage = totalRow / pageLength;
				if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
					totalPage = totalPage + 1;
				}
				if (tagPage <= 0) {
					tagPage = totalPage;
				}
				if (tagPage > totalPage) {
					tagPage = totalPage;
				}
				if (totalPage <= 0) {
					tagPage = 1;
					totalPage = 1;
				}
			} else if (ConfigConstraints.TIMEDURATION_DAY.equals(timeDuration)) {
				// 查询日时间表
				int totalRow = getDayTotalRow(appid, condition, startTime, stopTime);
				totalPage = totalRow / pageLength;
				if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
					totalPage = totalPage + 1;
				}
				if (tagPage <= 0) {
					tagPage = totalPage;
				}
				if (tagPage > totalPage) {
					tagPage = totalPage;
				}
				if (totalPage <= 0) {
					tagPage = 1;
					totalPage = 1;
				}
			} else if (ConfigConstraints.TIMEDURATION_MONTH.equals(timeDuration)) {
				// 查询月时间表
				int totalRow = getMonthTotalRow(appid, condition, startTime, stopTime);
				totalPage = totalRow / pageLength;
				if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
					totalPage = totalPage + 1;
				}
				if (tagPage <= 0) {
					tagPage = totalPage;
				}
				if (tagPage > totalPage) {
					tagPage = totalPage;
				}
				if (totalPage <= 0) {
					tagPage = 1;
					totalPage = 1;
				}
			} else if (ConfigConstraints.TIMEDURATION_YEAR.equals(timeDuration)) {
				// 查询年时间表
				int totalRow = getYearTotalRow(appid, condition, startTime, stopTime);
				totalPage = totalRow / pageLength;
				if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
					totalPage = totalPage + 1;
				}
				if (tagPage <= 0) {
					tagPage = totalPage;
				}
				if (tagPage > totalPage) {
					tagPage = totalPage;
				}
				if (totalPage <= 0) {
					tagPage = 1;
					totalPage = 1;
				}
			} else {

			}
		} else {
			// 自定义时间查询，直接去查询分数据表
			int totalRow = getMinuteTotalRow(appid, condition, startTime, stopTime);
			totalPage = totalRow / pageLength;
			if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
				totalPage = totalPage + 1;
			}
			if (tagPage <= 0) {
				tagPage = totalPage;
			}
			if (tagPage > totalPage) {
				tagPage = totalPage;
			}
			if (totalPage <= 0) {
				tagPage = 1;
				totalPage = 1;
			}
		}
		return totalPage;
	}

	public List<Map<String, Object>> queryByPage(String appid, int tagPage, int pageLength, String condition,
			String timeDuration, String startTime, String stopTime) {

		// 先判断是否是自定义时间
		if (!"".equals(timeDuration)) {

			if ("".equals(startTime) || "".equals(stopTime)) {
				if (ConfigConstraints.TIMEDURATION_MINUTE.equals(timeDuration)) {
					// 查询分时间表
					int totalRow = getMinuteTotalRow(appid, condition, startTime, stopTime);
					int totalPage = totalRow / pageLength;
					if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
						totalPage = totalPage + 1;
					}
					if (tagPage <= 0) {
						tagPage = totalPage;
					}
					if (tagPage > totalPage) {
						tagPage = totalPage;
					}
					if (totalPage <= 0) {
						tagPage = 1;
						totalPage = 1;
					}
					int startId = pageLength * (tagPage - 1);
					return actionInfoStatisticsMapper.queryMinuteByPage(appid, startId, pageLength, condition,
							startTime, stopTime);

				} else if (ConfigConstraints.TIMEDURATION_HOUR.equals(timeDuration)) {
					// 查询时时间表
					int totalRow = getHourTotalRow(appid, condition, startTime, stopTime);
					int totalPage = totalRow / pageLength;
					if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
						totalPage = totalPage + 1;
					}
					if (tagPage <= 0) {
						tagPage = totalPage;
					}
					if (tagPage > totalPage) {
						tagPage = totalPage;
					}
					if (totalPage <= 0) {
						tagPage = 1;
						totalPage = 1;
					}
					int startId = pageLength * (tagPage - 1);
					return actionInfoStatisticsMapper.queryHourByPage(appid, startId, pageLength, condition, startTime,
							stopTime);

				} else if (ConfigConstraints.TIMEDURATION_DAY.equals(timeDuration)) {
					// 查询日时间表
					int totalRow = getDayTotalRow(appid, condition, startTime, stopTime);
					int totalPage = totalRow / pageLength;
					if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
						totalPage = totalPage + 1;
					}
					if (tagPage <= 0) {
						tagPage = totalPage;
					}
					if (tagPage > totalPage) {
						tagPage = totalPage;
					}
					if (totalPage <= 0) {
						tagPage = 1;
						totalPage = 1;
					}
					int startId = pageLength * (tagPage - 1);
					return actionInfoStatisticsMapper.queryDayByPage(appid, startId, pageLength, condition, startTime,
							stopTime);

				} else if (ConfigConstraints.TIMEDURATION_MONTH.equals(timeDuration)) {
					// 查询月时间表
					int totalRow = getMonthTotalRow(appid, condition, startTime, stopTime);
					int totalPage = totalRow / pageLength;
					if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
						totalPage = totalPage + 1;
					}
					if (tagPage <= 0) {
						tagPage = totalPage;
					}
					if (tagPage > totalPage) {
						tagPage = totalPage;
					}
					if (totalPage <= 0) {
						tagPage = 1;
						totalPage = 1;
					}
					int startId = pageLength * (tagPage - 1);
					return actionInfoStatisticsMapper.queryMonthByPage(appid, startId, pageLength, condition, startTime,
							stopTime);

				} else if (ConfigConstraints.TIMEDURATION_YEAR.equals(timeDuration)) {
					// 查询年时间表
					int totalRow = getYearTotalRow(appid, condition, startTime, stopTime);
					int totalPage = totalRow / pageLength;
					if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
						totalPage = totalPage + 1;
					}
					if (tagPage <= 0) {
						tagPage = totalPage;
					}
					if (tagPage > totalPage) {
						tagPage = totalPage;
					}
					if (totalPage <= 0) {
						tagPage = 1;
						totalPage = 1;
					}
					int startId = pageLength * (tagPage - 1);
					return actionInfoStatisticsMapper.queryYearByPage(appid, startId, pageLength, condition, startTime,
							stopTime);

				} else {

				}
			}
		} else {

			// 自定义时间查询，直接去查询分数据表

			int totalRow = getMinuteTotalRow(appid, condition, startTime, stopTime);
			int totalPage = totalRow / pageLength;
			if (totalPage * pageLength < totalRow) { // 不能够整除，总页数应该 + 1
				totalPage = totalPage + 1;
			}
			if (tagPage <= 0) {
				tagPage = totalPage;
			}
			if (tagPage > totalPage) {
				tagPage = totalPage;
			}
			if (totalPage <= 0) {
				tagPage = 1;
				totalPage = 1;
			}
			int startId = pageLength * (tagPage - 1);
			return actionInfoStatisticsMapper.queryMinuteByPage(appid, startId, pageLength, condition, startTime,
					stopTime);

		}
		return null;
	}
}
