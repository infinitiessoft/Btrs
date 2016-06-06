package resources;

import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.hibernate.HibernateException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.RequestContextFilter;

@ContextConfiguration("file:src/test/resource/test_context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { ServletTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		WithSecurityContextTestExecutionListener.class })
public abstract class ResourceTest extends JerseyTest {

	@Autowired
	protected DemoData dbUtil;

	private static boolean isLoad = false;

	@Override
	protected TestContainerFactory getTestContainerFactory() {
		return new GrizzlyWebTestContainerFactory();
	}

	@Override
	protected DeploymentContext configureDeployment() {
		return ServletDeploymentContext
				.forServlet(new ServletContainer(configure()))
				.addListener(ContextLoaderListener.class)
				.contextParam("contextConfigLocation",
						"file:src/test/resource/test_context.xml")
				.addFilter(
						org.springframework.web.filter.DelegatingFilterProxy.class,
						"springSecurityFilterChain").build();
	}

	@Override
	protected ResourceConfig configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		ResourceConfig rc = new ResourceConfig(getResource());
		rc.register(GenericExceptionMapper.class);
		rc.register(SpringLifecycleListener.class);
		rc.register(RequestContextFilter.class);
		rc.register(JacksonFeature.class);
		rc.property("contextConfigLocation",
				"file:src/test/resource/test_context.xml");
		return rc;
	}

	protected abstract Class<?>[] getResource();

	@Before
	public void initData() throws HibernateException, DatabaseUnitException,
			SQLException {
		// // As your project and list of tables grows, specifying
		// // what tables to load will be more important
		// dbUtil.createTables(new Class[] { Employee.class });

		// Different functional tests require different data sets
		if (!isLoad) {
			isLoad = true;
			System.err.println("load data");
			dbUtil.loadData();
		}
	}

	@AfterClass
	public static void end() {
		isLoad = false;
	}

}