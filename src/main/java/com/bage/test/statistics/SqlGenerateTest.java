package com.bage.test.statistics;

import java.util.ArrayList;
import java.util.List;

import com.bage.domain.statistics.SqlInfoStatistics;
import com.bage.utils.RandomUtils;
import com.bage.utils.TimeUtils;

public class SqlGenerateTest {

	List<String> listIId = getIID();
	
	public List<SqlInfoStatistics> getRandomMinuteList() {
		int n = 1000;
		List<SqlInfoStatistics> list = new ArrayList<SqlInfoStatistics>(10240);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, RandomUtils.nextInt(60), RandomUtils.nextInt(60), 0);
			SqlInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<SqlInfoStatistics> getRandomHourList() {
		int n = 1000;
		List<SqlInfoStatistics> list = new ArrayList<SqlInfoStatistics>(1024);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, RandomUtils.nextInt(60), 0, 0);
			SqlInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<SqlInfoStatistics> getRandomDayList() {
		int n = 1000;
		List<SqlInfoStatistics> list = new ArrayList<SqlInfoStatistics>(1024);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, 0, 0, 0);
			SqlInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<SqlInfoStatistics> getRandomMonthList() {
		int n = 200;
		List<SqlInfoStatistics> list = new ArrayList<SqlInfoStatistics>(256);
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, 0, 0, 0, 0);
			SqlInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<SqlInfoStatistics> getRandomYearList() {
		int n = 100;
		List<SqlInfoStatistics> list = new ArrayList<SqlInfoStatistics>(16);
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.generateTime(2017-i, 0, 0, 0, 0, 0);
			SqlInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(i / 10 + 1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}

	private SqlInfoStatistics getRandomMemoryInfoStatistics(int appid, String queryTime) {
		SqlInfoStatistics SqlInfoStatistics = null;
				int id = 0;
		String name = listIId.get(RandomUtils.nextInt(100) % listIId.size());
		String iid = name;
		float hits = RandomUtils.nextFloat(1000);
		float durationsSum = RandomUtils.nextFloat(22614);
		float durationsSquareSum = RandomUtils.nextFloat(20950);
		float maximum = RandomUtils.nextFloat(78629257);
		float cpuTimeSum = RandomUtils.nextFloat(28745005);
		float systemErrors= RandomUtils.nextFloat(100);
		float responseSizesSum = RandomUtils.nextFloat(68314);
		float childHits = RandomUtils.nextFloat(18253);
		float childDurationsSum = RandomUtils.nextFloat(1000);
		SqlInfoStatistics = new SqlInfoStatistics(id , appid, queryTime, name , iid , hits , durationsSum , durationsSquareSum , maximum, cpuTimeSum, systemErrors, responseSizesSum, childHits, childDurationsSum);
		return SqlInfoStatistics;
	}
	private List<String> getIID() {
		List<String> list = new ArrayList<String>();
		String sql = "insert into tb_user(phone,password,name,icon,gender,birthday,district_id,mail,status_delete,description) values(?,?,?,?,?,?,?,?,?,?); "+
			"update tb_user set password=?,name=?,icon=?,gender=?,birthday=?,district_id=?,mail=?,issh=?,status_delete=?,description=? where id=?; "+
			"select * from tb_user where status_delete='0' and id= ? ; "+
			"select * from tb_user where status_delete='0' and phone= ? ; "+
			"insert into tb_shop(user_id,shopname,shoptype,shopicon,address,street,description,time,status_delete) values(?,?,?,?,?,?,?,?,?) ; "+
			"select * from tb_shop where status_delete='0' and user_id= ? ; "+
			"update tb_shop set shopicon=?,address=?,street=?,description=? where user_id= ? ; "+
			"insert into tb_product(shop_id,ptype,pname,picon,pprice,ptime,pdescription,status_delete) values(?,?,?,?,?,?,?,?); "+
			"select * from tb_product where status_delete='0' and shop_id= ? ; "+
			"select count(*) as 'pll' from tb_liulan where status_delete='0' and product_id= ? ; "+
			"select count(*) as 'pco' from tb_collect where status_delete='0' and product_id= ? ; "+
			"select tb_product.*,tb_collect.id as 'colid',tb_collect.time as 'coltime' from tb_collect,tb_product where tb_collect.status_delete='0' and tb_collect.product_id=tb_product.id and tb_collect.user_id= ? ; "+
			"select * from tb_product where status_delete='0' and ptype= ? order by ptime desc limit 0,3 ";
		String[] sqls = sql.split(";");
		for(int i = 0;i < sqls.length;i ++){
			list.add("" + sqls[i]);
		}
		return list;
	}
	
	
	
}
