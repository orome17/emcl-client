package com.safeway.emclclient.emcl.client.net;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class EMCLConnectionFactory extends BasePoolableObjectFactory {
    private static final Log logger = LogFactory.getLog(EMCLConnectionFactory.class);

    @Value("${emcl.server.hostname}")
    private String hostname;

    @Value("${emcl.server.port}")
    private int port;

    @Value("${emcl.client.connections.maxActive}")
    private int maxActive;

    @Value("${emcl.client.connections.testOnBorrow}")
    private String testOnBorrow;

    @Value("${emcl.client.connection.lifo}")
    private String lifo;

    private GenericObjectPool connectionPool;


    public GenericObjectPool getGenericObjectPool() {
        if (connectionPool != null) return connectionPool;

        connectionPool = new GenericObjectPool();
        connectionPool.setTestOnBorrow(true);
        connectionPool.setFactory(this);
        connectionPool.setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_FAIL);
        connectionPool.setMaxActive(maxActive);

        return connectionPool;
    }

    /**
     * By nullifying the ConnectionFactory, the next time it gets called a new instances will be created.
     */
    public void nullifyGenericObjectPool() {
        try {
            connectionPool.clear();
            connectionPool.close();
        } catch (Exception ex) {
            logger.error("Could not close the EMCL connection pool " + ex.getMessage());
        } finally {
            connectionPool = null;
        }
    }

    @Override
    public Object makeObject() throws Exception {
        logger.debug("hostname:port " + hostname + ":" + port);
        Socket socket = new Socket(hostname, port);
        return socket;
    }
}
