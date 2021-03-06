package com.cv4j.netdiscovery.example

import com.cv4j.netdiscovery.core.Spider
import com.cv4j.netdiscovery.core.pipeline.debug.DebugPipeline
import com.cv4j.netdiscovery.dsl.request
import io.vertx.core.http.HttpMethod

/**
 * Created by tony on 2018/9/18.
 */
object TestDSL4Request {

    @JvmStatic
    fun main(args: Array<String>) {

        val request = request {

            url = "https://www.baidu.com/"

            httpMethod = HttpMethod.GET

            spiderName = "tony"

            header {

                "111" to "2222"
                "333" to "44444"
            }

            extras {

                "tt" to "qqq"
            }
        }

        Spider.create().name("tony").request(request).pipeline(DebugPipeline()).run()
    }

}