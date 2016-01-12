/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.dataflow.module.deployer.marathon;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.dataflow.admin.config.AdminProperties;
import org.springframework.cloud.dataflow.module.deployer.ModuleDeployer;
import org.springframework.cloud.dataflow.module.deployer.test.AbstractModuleDeployerTests;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Integration tests for {@link MarathonModuleDeployer}.
 */
@SpringApplicationConfiguration(classes = MarathonModuleDeployerTests.Config.class)
@IntegrationTest({"marathon.includes=org.springframework.cloud:spring-cloud-marathon-connector:1.0.0.BUILD-SNAPSHOT"})
public class MarathonModuleDeployerTests extends AbstractModuleDeployerTests {

	@Configuration
	@EnableConfigurationProperties({AdminProperties.class, MarathonProperties.class})
	public static class Config {

		@Bean
		public ModuleDeployer moduleDeployer(AdminProperties adminProperties,
		                                     MarathonProperties marathonProperties) {
			return new MarathonModuleDeployer(marathonProperties, adminProperties);
		}

	}

	@Override
	protected Attempts deploymentTimeout() {
		return new Attempts(12 * 10, 5000);
	}
}
