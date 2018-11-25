package com.bage.hbase;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import com.bage.domain.module.ParamRow;
import com.bage.utils.HBConfUtils;

@Component
public class CustomerDao2 {

	private String htable = "customers";

	/**
	 * 
	 * @param rowkey
	 * @param paramRows
	 */
	public boolean insert(String rowkey, List<ParamRow> paramRows) {
		Configuration hbConf = HBConfUtils.getDefaultHBConf();
		Connection conn = null;
		Table table = null;
		try {

			conn = ConnectionFactory.createConnection(hbConf);
			TableName tn = TableName.valueOf(htable);

			table = conn.getTable(tn);

			Put put = new Put(Bytes.toBytes(rowkey));
			for (ParamRow paramRow : paramRows) {
				
				put.addColumn(Bytes.toBytes(paramRow.getFimally()), Bytes.toBytes(paramRow.getQualifier()),
						Bytes.toBytes(paramRow.getValue()));
				put.setDurability(Durability.SYNC_WAL);
			}

			table.put(put);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (table != null) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean deleteData(String rowKey, ParamRow paramRow) {
		Configuration hbConf = HBConfUtils.getDefaultHBConf();
		Connection conn = null;
		Table table = null;
		try {

			conn = ConnectionFactory.createConnection(hbConf);
			TableName tn = TableName.valueOf("htable");

			table = conn.getTable(tn);
			Delete delete = new Delete(Bytes.toBytes(rowKey));
			if (paramRow != null) {
				// get family family
				if (paramRow.getFimally() != null) {
					delete.addFamily(Bytes.toBytes(paramRow.getFimally()));
				}
				// if just get cols
				if (paramRow.getQualifier() != null && paramRow.getValue() != null) {
					delete.addColumns(Bytes.toBytes(paramRow.getFimally()), Bytes.toBytes("name"));
				}
			}

			table.delete(delete);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (table != null) {

				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private boolean updateData(String rowkey, List<ParamRow> paramRows) {
		return insert(rowkey, paramRows);

	}

	/**
	 * for (Cell cell : result.rawCells()) { <br>
	 * System.out.println("Rowkey : " + Bytes.toString(result.getRow()) <br>
	 * + "\tFamiliy : "+ Bytes.toString(CellUtil.cloneFamily(cell))<br>
	 * +"\tQuilifier : "+ Bytes.toString(CellUtil.cloneQualifier(cell))<br>
	 * + "\tValue : "+ Bytes.toString(CellUtil.cloneValue(cell)));<br>
	 * }
	 * 
	 * @param rowKey
	 * @param paramRow
	 * @return
	 */
	public Result selectdata(String rowKey, ParamRow paramRow) {
		Configuration hbConf = HBConfUtils.getDefaultHBConf();
		Connection conn = null;
		Table table = null;
		Result result = null;
		try {

			conn = ConnectionFactory.createConnection(hbConf);
			TableName tn = TableName.valueOf(htable);

			table = conn.getTable(tn);
			Get get = new Get(Bytes.toBytes(rowKey));// all

			if (paramRow != null) {
				// get family family
				if (paramRow.getFimally() != null) {
					get.addFamily(Bytes.toBytes(paramRow.getFimally()));
				}
				// if just get cols
				// get.addColumn(Bytes.toBytes("family"), Bytes.toBytes("age"));
				if (paramRow.getQualifier() != null && paramRow.getValue() != null) {
					get.addColumn(Bytes.toBytes(paramRow.getQualifier()), Bytes.toBytes(paramRow.getValue()));
				}
			}
			result = table.get(get);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (table != null) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * for (Result r : resultScanner) {<br>
	 * for (Cell cell : r.rawCells()) { <br>
	 * System.out.println("Rowkey : " + Bytes.toString(r.getRow()) <br>
	 * + "\tFamiliy : "+ Bytes.toString(CellUtil.cloneFamily(cell))<br>
	 * +"\tQuilifier : "+ Bytes.toString(CellUtil.cloneQualifier(cell))<br>
	 * + "\tValue : "+ Bytes.toString(CellUtil.cloneValue(cell)));<br>
	 * } }
	 * 
	 * @param startRow
	 * @param stopRow
	 * @return
	 */
	public ResultScanner getScanner(String startRow, String stopRow) {
		Configuration hbConf = HBConfUtils.getDefaultHBConf();
		Connection conn = null;
		Table table = null;
		ResultScanner resultScanner = null;
		try {
			conn = ConnectionFactory.createConnection(hbConf);
			TableName tn = TableName.valueOf(htable);
			table = conn.getTable(tn);
			Scan s = new Scan();
			s.setStartRow(Bytes.toBytes(startRow));// do contain the stop row
			s.setStopRow(Bytes.toBytes(stopRow));// do not contain the stop row
			resultScanner = table.getScanner(s);
			return resultScanner;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (table != null) {
				try {
					table.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultScanner;
	}

	private void alterTable(String family) {
		Configuration hbConf = HBConfUtils.getDefaultHBConf();
		Connection conn = null;
		Admin hbAdmin = null;
		try {

			conn = ConnectionFactory.createConnection(hbConf);
			TableName tablename = TableName.valueOf(htable);
			hbAdmin = conn.getAdmin();
			if (hbAdmin.tableExists(tablename)) {
				if (!hbAdmin.isTableDisabled(tablename)) {
					hbAdmin.disableTable(tablename);
				}

				// get the TableDescriptor of target table
				HTableDescriptor newtd = hbAdmin.getTableDescriptor(tablename);

				// remove 1 useless column families
				newtd.removeFamily(Bytes.toBytes("familyName"));

				// create HColumnDescriptor for new column family
				HColumnDescriptor newhcd = new HColumnDescriptor("familyNameNew");
				newhcd.setMaxVersions(10);
				newhcd.setKeepDeletedCells(true);
				// setKeepDeletedCells(true);

				// add the new column family(HColumnDescriptor) to
				// HTableDescriptor
				newtd.addFamily(newhcd);

				// modify target table struture
				hbAdmin.modifyTable(tablename, newtd);

				if (hbAdmin.isTableDisabled(tablename)) {
					hbAdmin.enableTable(tablename);
				}
				System.out.println("end ");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (hbAdmin != null) {

				try {
					hbAdmin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void dropTable() {
		Configuration hbConf = HBConfUtils.getDefaultHBConf();
		Connection conn = null;
		Admin hbAdmin = null;
		try {

			conn = ConnectionFactory.createConnection(hbConf);
			TableName tablename = TableName.valueOf("testTable3");
			hbAdmin = conn.getAdmin();
			if (hbAdmin.tableExists(tablename)) {
				if (hbAdmin.isTableDisabled(tablename)) {
					hbAdmin.disableTable(tablename);
				}
				hbAdmin.deleteTable(tablename);
				System.out.println("drop end");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (hbAdmin != null) {

				try {
					hbAdmin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void listTables() {
		Configuration hbConf = HBConfUtils.getDefaultHBConf();
		Connection conn = null;
		Admin hbAdmin = null;
		try {

			conn = ConnectionFactory.createConnection(hbConf);
			hbAdmin = conn.getAdmin();
			TableName[] list = hbAdmin.listTableNames();
			for (TableName temp : list) {

				System.out.print("namespace:" + Bytes.toString(temp.getNamespace()));
				System.out.println("\t TableName:" + Bytes.toString(temp.getName()));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (hbAdmin != null) {

				try {
					hbAdmin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	public void addFamily(String family) {
		Configuration hbConf = HBConfUtils.getDefaultHBConf();
		Connection conn = null;
		Admin hbAdmin = null;
		try {

			conn = ConnectionFactory.createConnection(hbConf);
			TableName tablename = TableName.valueOf(htable);
			hbAdmin = conn.getAdmin();
			if (hbAdmin.tableExists(tablename)) {
				if (!hbAdmin.isTableDisabled(tablename)) {
					hbAdmin.disableTable(tablename);
				}

				// get the TableDescriptor of target table
				HTableDescriptor newtd = hbAdmin.getTableDescriptor(tablename);
				// create HColumnDescriptor for new column family
				HColumnDescriptor newhcd = new HColumnDescriptor(family);
				newhcd.setMaxVersions(10);
				newhcd.setKeepDeletedCells(true);
				// setKeepDeletedCells(true);

				// add the new column family(HColumnDescriptor) to
				// HTableDescriptor				
				newtd.addFamily(newhcd);

				// modify target table struture
				hbAdmin.modifyTable(tablename, newtd);
				if (hbAdmin.isTableDisabled(tablename)) {
					hbAdmin.enableTable(tablename);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (hbAdmin != null) {
				try {
					hbAdmin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void createTable() {
		Configuration hbConf = HBConfUtils.getDefaultHBConf();
		Connection conn = null;
		Admin hbAdmin = null;
		try {
			conn = ConnectionFactory.createConnection(hbConf);
			hbAdmin = conn.getAdmin();
			TableName tn = TableName.valueOf(htable);
			HTableDescriptor desc = new HTableDescriptor(tn);
			HColumnDescriptor family = new HColumnDescriptor("default");
			desc.addFamily(family);
			hbAdmin.createTable(desc);
			desc.setDurability(Durability.SYNC_WAL);
			hbAdmin.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (hbAdmin != null) {
				try {
					hbAdmin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
