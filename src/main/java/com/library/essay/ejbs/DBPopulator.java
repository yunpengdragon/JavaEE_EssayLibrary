package com.library.essay.ejbs;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.library.essay.persistence.entities.Essay;
import com.library.essay.services.EssayService;

//import java.util.logging.Logger;

@Singleton
@Startup

// H2 Database, need to add the driver jar under
// glassfish/domains/domain1/lib/ext
@DataSourceDefinition(name = "java:global/jdbc/essayLibrary", className = "org.h2.jdbcx.JdbcDataSource", url = "jdbc:h2:~/data/java-ee-essay2")

// Derby Database
// @DataSourceDefinition(name = "java:global/jdbc/essayLibrary", className =
// "org.apache.derby.jdbc.EmbeddedDriver", url =
// "jdbc:derby:memory:lab11DB;create=true;user=app;password=app")
public class DBPopulator {

	@Inject
	private EssayService essayService;

	private Logger logger = Logger.getLogger(getClass());

	@PostConstruct
	private void createDummyData() {

		List<Essay> essays = essayService.getEssays();
		int size = essays.size();

		if (size == 0) {
			essayService.saveOrUpdate(new Essay("Java EE Tutorial 1", "Oracle", "Java EE is cool!"));
			essayService.saveOrUpdate(new Essay("Java EE Tutorial 2", "Oracle", "Java EE is cool!"));
			essayService.saveOrUpdate(new Essay("Java EE Tutorial 3", "Oracle", "Java EE is cool!"));
			essayService.saveOrUpdate(new Essay("Java EE Tutorial 4", "Oracle", "Java EE is cool!"));

			logger.info("&&&&&&&&&&&&&& Inserted " + essayService.getEssays().size() + " Essays");
		}

		logger.info("=====================================================");
		for (Essay essay : essays) {
			logger.info(essay);
		}
		logger.info("=====================================================");
	}
}
