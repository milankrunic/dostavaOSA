package ftn.dostavaOSA2021.DostavaOSA2021.elastic.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@ComponentScan(basePackages = "ftn.dostavaOSA2021.DostavaOSA2021.elastic.serviceImpl")
@EnableElasticsearchRepositories(basePackages = "ftn.dostavaOSA2021.DostavaOSA2021.elastic.repository")
public class ElasticsearchConfig {

	@Value("${spring.data.elasticsearch.cluster-nodes}")
	private String hostAndPort;
	
	@Bean
	public RestHighLevelClient client() {
		ClientConfiguration clientConfiguration = ClientConfiguration.builder()
				.connectedTo(hostAndPort)
				.build();
		return RestClients.create(clientConfiguration).rest();
	}
	
	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		return new ElasticsearchRestTemplate(client());
	}
	
}

