package com.gulyaich.transformationparser;

import com.gulyaich.transformationparser.model.result.TransformationResult;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransformationParserApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void sendRequest_transformFile_successResult() {
        final ResponseEntity<TransformationResult> responseEntity =
                restTemplate.postForEntity(
                        this.getHost() + "/transform/customer/test_example.xlsx",
                        null,
                        TransformationResult.class
                );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        final TransformationResult body = responseEntity.getBody();
        Assertions.assertNotNull(body);
        Assertions.assertEquals(4, body.getRawDataSize());
        Assertions.assertEquals(4, body.getTransformedDataSize());
        Assertions.assertTrue(CollectionUtils.isEmpty(body.getUnmappedFieldsTargets()));
    }

    private String getHost() {
        return "http://localhost:" + port;
    }

}
