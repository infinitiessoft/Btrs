package resources;

import java.io.File;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.HibernateException;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("dbUtil")
public class DemoData {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@PostConstruct
	@Transactional("transactionManager")
	public void loadData() throws HibernateException, DatabaseUnitException,
			SQLException {
		IDatabaseConnection connection = new DatabaseConnection(
				((SessionImpl) (entityManagerFactory.createEntityManager()
						.getDelegate())).connection());
		connection.getConfig().setProperty(
				DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
				new HsqldbDataTypeFactory());

		FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
		flatXmlDataSetBuilder.setColumnSensing(true);
		// InputStream dataSet =
		// Thread.currentThread().getContextClassLoader().getResourceAsStream("test-data.xml");
		// dataset = flatXmlDataSetBuilder.build(dataSet);
		// DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
		String path = this.getClass().getClassLoader()
				.getResource("test-dataset").getPath();// getServletContext().getRealPath("/")
		IDataSet dataSet = new CsvDataSet(new File(path));
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
	}
}