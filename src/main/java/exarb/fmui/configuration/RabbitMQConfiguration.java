package exarb.fmui.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;


@Configuration
public class RabbitMQConfiguration implements RabbitListenerConfigurer {

    /**
     * Creates a topic exchange bean.
     * @return TopicExchange
     */
    @Bean
    public TopicExchange achievementExchange(@Value("${achievement.exchange}") final String achievementExchange) {
        return new TopicExchange(achievementExchange);
    }

    /**
     * Creates a durable Queue for achievement events.
     * @param achievementQueue
     * @return Queue
     */
    // TODO: We make the Queue durable (the second true argument when creating it).
    //  We introduced this idea before: by doing this we can process pending events
    //  even after the broker goes down, given that they are persisted.
    @Bean
    public Queue newAchievementQueue(@Value("${achievement.queue}") final String achievementQueue) {
        return new Queue(achievementQueue, true);
    }

    /**
     * Binds the topic exchange and the queue together.
     * @param queue
     * @param achievementExchange
     * @param routingKey
     * @return
     */
    @Bean
    Binding binding(final Queue queue, final TopicExchange achievementExchange,
                    @Value("${achievement.anything.routing-key}") final String routingKey) {
        return BindingBuilder.bind(queue).to(achievementExchange).with(routingKey);
    }

    /**
     * Creating a converter from JSON
     * @return MappingJackson2MessageConverter
     */
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    /**
     * Method that customizes how the message payload will be converted from serialized to a typed object.
     * @return DefaultMessageHandlerMethodFactory
     */
    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    /**
     * Registering a RabbitListenerEndpoint that will use a customized MessageHandlerMethodFactory
     * @param registrar
     */
    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

}
