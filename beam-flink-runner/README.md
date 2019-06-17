# Apache Beam Example Code

An example Apache Beam project.

### Description

This `WordCount` example is implemented by Flink Funner.

## How to clone and run

1. Open a terminal window.

2. Run `git clone https://github.com/lhfei/beam-examples.git`

3. Run `cd beam-examples/beam-flink-runner`

4. Run `mvn compile`

5. Create local output directory: `mkdir output`

6. Run it :

   ```shell
   spark-submit  --class "cn.lhfei.beam.runner.flink.MinimalWordCount" \
       --master yarn \
       --deploy-mode cluster \
       --num-executors 2 \
       --executor-memory 1g \
       --executor-cores 2 \
       beam-flink-runner/target/beam-flink-runner-2.13.0-shaded.jar \
       --inputFile=hdfs://host-10-182-25-192:8020/apps/beam/datasets/README.md \
       --outputFile=hdfs://host-10-182-25-192:8020/apps/beam/output/wordcount \
       --hdfsConfiguration=[{\"fs.defaultFS\":\"hdfs://host-10-182-25-192:8020\"}] \
       --runner=SparkRunner
   ```

7. Run `cat output/user_score` to verify the program ran correctly and the output file was created.

### Using a Java IDE

1. Follow the [IDE Setup](http://beam.incubator.apache.org/contribute/contribution-guide/#optional-ide-setup) instructions on the Apache Beam Contribution Guide.

## Runners

### Apache Flink

1. Follow the first steps from [Flink's Quickstart](https://ci.apache.org/projects/flink/flink-docs-release-1.1/quickstart/setup_quickstart.html) to [download Flink](https://ci.apache.org/projects/flink/flink-docs-release-1.1/quickstart/setup_quickstart.html#download).
2. Create the `output` directory.
3. To run on a JVM-local cluster: `mvn compile exec:java -Dexec.mainClass=org.apache.beam.examples.tutorial.game.solution.Exercise1 -Dexec.args='--runner=FlinkRunner --flinkMaster=[local]' -Pflink-runner`
4. To run on an out-of-process local cluster (note that the steps below should also work on a real cluster if you have one running):
   1. [Start a local Flink cluster](https://ci.apache.org/projects/flink/flink-docs-release-1.1/quickstart/setup_quickstart.html#start-a-local-flink-cluster).
   2. Navigate to the WebUI (typically [http://localhost:8081](http://localhost:8081/)), click [JobManager](http://localhost:8081/#/jobmanager/config), and note the value of `jobmanager.rpc.port`. The default is probably 6123.
   3. Run `mvn package -Pflink-runner` to generate a JAR file. Note the location of the generated JAR (probably `./target/BeamTutorial-bundled-flink.jar`)
   4. Run `mvn -X -e compile exec:java -Dexec.mainClass=org.apache.beam.examples.tutorial.game.solution.Exercise1 -Dexec.args='--runner=FlinkRunner --flinkMaster=localhost:6123 --filesToStage=./target/BeamTutorial-bundled-flink.jar' -Pflink-runner`, replacing the defaults for port and JAR file if they differ.
   5. Check in the [WebUI](http://localhost:8081/) to see the job listed.
5. Run `cat output/user_score` to verify the pipeline ran correctly and the output file was created.
