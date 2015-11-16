package com.library.essay.ejbs;

import javax.annotation.PostConstruct;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.library.essay.persistence.entities.Essay;

import java.util.logging.Logger;

@Singleton
@Startup

// H2 Database, need to add the driver jar under
// glassfish/domains/domain1/lib/ext
@DataSourceDefinition(name = "java:global/jdbc/essayLibrary", className = "org.h2.jdbcx.JdbcDataSource", url = "jdbc:h2:~/data/java-ee-essay")

// Derby Database
//@DataSourceDefinition(name = "java:global/jdbc/essayLibrary", className = "org.apache.derby.jdbc.EmbeddedDriver", url = "jdbc:derby:memory:lab11DB;create=true;user=app;password=app")
public class DBPopulator {

	@Inject
	private EssayEJB essayEJB;

	private Logger logger = Logger.getLogger("com.library.essay");

	@PostConstruct
	private void createDummyData() {

		int size = essayEJB.findAllEssays().size();

		if (size == 0) {
			essayEJB.saveEssay(new Essay("Java EE Tutorial 1", "Oracle", "Java EE is cool!"));
			essayEJB.saveEssay(new Essay("Java EE Tutorial 2", "Oracle", "Java EE is cool!"));
			essayEJB.saveEssay(new Essay("Java EE Tutorial 3", "Oracle", "Java EE is cool!"));
			essayEJB.saveEssay(new Essay("Java EE Tutorial 4", "Oracle", "Java EE is cool!"));

			logger.info("&&&&&&&&&&&&&& Inserted " + essayEJB.findAllEssays().size() + " Essays");
		}
	}
}
