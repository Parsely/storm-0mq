storm-0mq
=========

Storm 0MQ Transport Plugin. This code was extracted out of Storm 0.9 as part of
the transition from ZeroMQ to Netty as the default transport. You need to build
a JAR out of this repository in order to revert 0.9 to use the ZeroMQ transport
plugin.

# Building the storm-0mq JAR

To make Storm work with this plugin, you need to perform these steps:

1. download ZeroMQ native libraries

2. download jzmq native libraries

3. modify the `project.clj` in this project to make sure these native libraries
   are on the `java.library.path`

4. build a JAR using `lein` by running `lein jar`

5. make sure the native libraries downloaded are also on all of your storm
   worker machines and are in the `java.library.path` specified in `storm.yaml`

6. make sure the built JAR, `storm-0mq.jar` is in the `storm/lib` directory

7. make sure the JAR bundled with jzmq, `zmq.jar` is also in the `storm/lib`
   directory

# Verifying the JAR is loadable by Storm

You can verify that the transport will be available to storm by going on one of
your worker machines and running `bin/storm repl`. Then, within the Clojure
prompt that loads up, run:

    $ bin/storm repl
    (use '[backtype.storm.messaging.zmq])

If this returns `nil`, you know the import worked successfully and Storm will
be able to find ZeroMQ on your classpath and load it. If it fails, the error it
fails with will indicate what's still wrong.

# Updating storm.yaml to use ZeroMQ

Once this REPL command succeeds, you can update your `storm.yaml` everywhere to
include this line:

    storm.messaging.transport: "backtype.storm.messaging.zmq"

Then restart your supervisor/workers and you should see these log entries in
the worker logs:

    $ grep "transport plugin" /path/to/storm/logs/worker*.log
    [INFO] Storm peer transport plugin:backtype.storm.messaging.zmq

If you still see "Netty" there, you know it's not loading the right ZeroMQ transport.
