package com.safeway.emclclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:service-emcl.xml"})
public class XmlConfig {
}