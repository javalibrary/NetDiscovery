package com.cv4j.netdiscovery.extra.pipeline;

import com.cv4j.netdiscovery.core.domain.ResultItems;
import com.cv4j.netdiscovery.core.pipeline.Pipeline;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created by tony on 2018/1/25.
 */
@Slf4j
public class MongoDBPipeline implements Pipeline {

    private MongoClient mongoClient;
    private String collectionName;

    public MongoDBPipeline(MongoClient mongoClient,String collectionName){

        this.mongoClient = mongoClient;
        this.collectionName = collectionName;
    }
    
    @Override
    public void process(ResultItems resultItems) {

        JsonObject document = new JsonObject();
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {

            document.put(entry.getKey(),entry.getValue());
        }

        mongoClient.save(collectionName, document, res -> {

            if (res.succeeded()) {

                log.info("saved document with id " + res.result());
            } else {
                res.cause().printStackTrace();
            }

        });
    }
}
