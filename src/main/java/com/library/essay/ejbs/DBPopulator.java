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
@DataSourceDefinition(name = "java:global/jdbc/essayLibrary", className = "org.apache.derby.jdbc.EmbeddedDriver", url = "jdbc:derby:memory:lab11DB;create=true;user=app;password=app")
public class DBPopulator {

	@Inject
	private EssayEJB essayEJB;

	private Logger logger = Logger.getLogger("com.library.essay");

	@PostConstruct
	private void createDummyData() {
		essayEJB.saveEssay(new Essay("Java EE Tutorial 1", "Oracle",
				"Java EE is cool!"));
		essayEJB.saveEssay(new Essay("Java EE Tutorial 2", "Oracle",
				"Java EE is cool!"));
		essayEJB.saveEssay(new Essay("Java EE Tutorial 3", "Oracle",
				"Java EE is cool!"));
		essayEJB.saveEssay(new Essay("Java EE Tutorial 4", "Oracle",
				"Java EE is cool!"));

		logger.info("&&&&&&&&&&&&&& Inserted "
				+ essayEJB.findAllEssays().size() + " Essays");
	}
}
