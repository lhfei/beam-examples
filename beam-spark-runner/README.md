# Apache Beam Example Code

An example Apache Beam project.

### Description

This `WordCount` example is implemented by Flink Funner.

## How to clone and run

1. Open a terminal window.

2. Run `git clone https://github.com/lhfei/beam-examples.git`

3. Run `cd beam-examples`

4. Run `mvn clean package -e -DskipTests `

5. Create local output directory: `mkdir output`

6. Run it:

   ```shell
   spark-submit  --class "cn.lhfei.beam.runner.spark.MinimalWordCount" \
       --master yarn \
       --deploy-mode cluster \
       --num-executors 2 \
       --executor-memory 1g \
       --executor-cores 2 \
       beam-spark-runner/target/beam-spark-runner-2.13.0-shaded.jar \
       --inputFile=hdfs://host-10-182-25-192:8020/apps/beam/datasets/README.md \
       --outputFile=hdfs://host-10-182-25-192:8020/apps/beam/output/wordcount \
       --hdfsConfiguration=[{\"fs.defaultFS\":\"hdfs://host-10-182-25-192:8020\"}] \
       --runner=SparkRunner
   ```

   

7. Run `cat output/user_score` to verify the program ran correctly and the output file was created.

### Using a Java IDE

1. Follow the [IDE Setup](http://beam.incubator.apache.org/contribute/contribution-guide/#optional-ide-setup) instructions on the Apache Beam Contribution Guide.

## Runners

### Apache Spark

1. Create the `output` directory.
2. Allow all users (Spark may run as a different user) to write to the `output` directory. `chmod 1777 output`.
3. Change the output file to a fully-qualified path. For example, `this("output/user_score");` to `this("/home/vmuser/output/user_score");`
4. Run `mvn package -Pspark-runner`
5. Run `spark-submit --jars ./target/BeamTutorial-bundled-spark.jar --class org.apache.beam.examples.tutorial.game.solution.Exercise2 --master yarn-client ./target/BeamTutorial-bundled-spark.jar --runner=SparkRunner`

- http://www.jesse-anderson.com/2016/07/question-and-answers-with-the-apache-beam-team/)