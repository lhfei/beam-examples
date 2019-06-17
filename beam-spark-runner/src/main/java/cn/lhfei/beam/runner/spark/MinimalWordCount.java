/*
 * Copyright 2010-2011 the original author or authors.
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

package cn.lhfei.beam.runner.spark;

import java.util.Arrays;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TypeDescriptors;

import cn.lhfei.beam.options.HDFSFileSystemOptions;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * Created on Jun 13, 2019
 * 
 * Usage: <br>
 * spark-submit  --class "cn.lhfei.beam.runner.spark.MinimalWordCount" \
    --master yarn \
    --deploy-mode cluster \
    --num-executors 2 \
    --executor-memory 1g \
    --executor-cores 2 \
    /export/app_workspaces/beam-examples/beam-spark-runner/target/beam-spark-runner-2.13.0-shaded.jar \
    --runner=SparkRunner \
    --inputFile=hdfs://host-10-182-25-192:8020/apps/beam/datasets/README.md \
    --outputFile=hdfs://host-10-182-25-192:8020/apps/beam/output/wordcount \
    --hdfsConfiguration=[{\"fs.defaultFS\":\"hdfs://host-10-182-25-192:8020\"}]
 */

public class MinimalWordCount {

	public static void main(String[] args) {
	
		HDFSFileSystemOptions options = PipelineOptionsFactory.fromArgs(args).withValidation()
				.as(HDFSFileSystemOptions.class);

		Pipeline p = Pipeline.create(options);

		p.apply(TextIO.read().from(options.getInputFile()))
				.apply(FlatMapElements.into(TypeDescriptors.strings())
						.via((String word) -> Arrays.asList(word.split("\\W+"))))
				.apply(Filter.by((String word) -> !word.isEmpty())).apply(Count.perElement())
				.apply(MapElements.into(TypeDescriptors.strings())
						.via((KV<String, Long> wordCount) -> wordCount.getKey() + ": " + wordCount.getValue()))
				.apply(TextIO.write().to(options.getOutputFile()));
		
		p.run().waitUntilFinish();
	}

}
