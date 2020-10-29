<h1>Configuring a Fanout exchange through Yaml</h1>
<h2>Prerequisites</h2>
<ol>
    <li>RabbitMq running.</li>
</ol>
<h2>Producer</h2>
<ol>
    <li><b>Dependency: </b> spring-cloud-stream-binder-rabbit</li>
    <li><b>spring.rabbitmq.host: </b>The IP to RabbitMQ.</li>
    <li><b>spring.rabbitmq.port: </b>RabbitMQ service Port.</li>
    <li><b>spring.rabbitmq.username: </b>RabbitMQ username.</li>
    <li><b>spring.rabbitmq.password: </b>RabbitMQ password.</li>
    <li><b>spring.cloud.stream.bindings.{exchange-name}.destination.{rabbitmq-exchange-name}: </b>The name that the exchange will have on RabbitMQ.</li>
    <li><b>spring.cloud.stream.rabbit.bindings.{exchange-name}.producer.exchangeType=fanout: </b>Setting the exchange type to be fanout (default topic). This will create the exchange if not already created by consumer/s.</li>
    <li><b>com.jg.fanoutproducer.FanoutBinding: </b>Binding configuration to initialize as Output.</li>
    <li><b>com.jg.fanoutproducer.ProducerController @EnableBinding(FanoutBinding.class): </b>Enabled binding described in FanoutBinding.class.</li>
    <li><b>com.jg.fanoutproducer.ProducerController: </b>Has method to publish/produce message onto exchange.</li>
</ol>
<h2>Consumer/s</h2>
<ol>
    <li><b>Dependency: </b>spring-cloud-stream-binder-rabbit</li>
    <li><b>spring.rabbitmq.host: </b>The IP to RabbitMQ.</li>
    <li><b>spring.rabbitmq.port: </b>RabbitMQ service Port.</li>
    <li><b>spring.rabbitmq.username: </b>RabbitMQ username.</li>
    <li><b>spring.rabbitmq.password: </b>RabbitMQ password.</li>
    <li><b>spring.cloud.stream.bindings.{exchange-name}.destination.{rabbitmq-exchange-name}: </b>The name that the exchange will have on RabbitMQ.</li>
    <li><b>spring.cloud.stream.bindings.{exchange-name}.group.{rabbitmq-queue-name}: </b>The name that the queue bound to this consumer will have on RabbitMQ.</li>
    <li><b>spring.cloud.stream.rabbit.bindings.{exchange-name}.consumer.exchangeType=fanout: </b>Setting the exchange  being consumed's type to be fanout (default topic). This will create the exchange if not already created by producer.</li>
    <li><b>com.jg.fanoutconsumer.FanoutBinding: </b>Binding configuration to initialize as Input.</li>
    <li><b>com.jg.fanoutconsumer.FanoutConsumerApplication @EnableBinding(FanoutBinding.class): </b>Enabled binding described in FanoutBinding.class.</li>
    <li><b>com.jg.fanoutconsumer.FanoutConsumerApplication: </b>Has method to consume messages from exchange.</li>
</ol>

<h2>Testing</h2>
To test that the fanning out works, launch two instances of the Consumer service, having the second instance with different:

<ol>
    <li><b>server.port</b></li>
    <li><b>spring.cloud.stream.bindings.jg-fanout-exchange.group</b></li>
</ol>

Then, publish a message from the producer, and the message should be received by both consumers.

Also, be it the consumer or producer that is started first, the exchange should always be created by the first service that is run, while the queue is only created when the Consumer service is run.
		