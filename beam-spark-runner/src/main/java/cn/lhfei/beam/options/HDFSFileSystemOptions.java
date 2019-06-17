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

package cn.lhfei.beam.options;

import org.apache.beam.runners.spark.SparkPipelineOptions;
import org.apache.beam.sdk.io.hdfs.HadoopFileSystemOptions;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Validation;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 *         Created on Jun 17, 2019
 */
public interface HDFSFileSystemOptions extends PipelineOptions, SparkPipelineOptions, HadoopFileSystemOptions {

	@Validation.Required
	@Description("Path of the local file to read from")
	String getInputFile();

	void setInputFile(String value);

	@Validation.Required
	@Description("Path of the HDFS to write to")
	String getOutputFile();

	void setOutputFile(String value);
}
