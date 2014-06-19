package be.vdab.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackageClasses = CreateAOPBeans.class)
@EnableAspectJAutoProxy
public class CreateAOPBeans {
}
