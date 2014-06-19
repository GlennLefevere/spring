package be.vdab.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.logging.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
class Auditing {
	private final static Logger logger = Logger.getLogger(Auditing.class.getName());

	@Before("execution(* be.vdab.services.*.*(..))")
	void schrijAudit(JoinPoint joinPoint){
		StringBuilder builder =
				new StringBuilder("\nTijdstip\t").append(new Date());
				Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
				if (authentication != null) {
				builder.append("\nGebruiker\t").append(authentication.getName());
				}
				builder.append("\nMethod\t\t").append(joinPoint.getSignature().toLongString());
				int parameterVolgnummer = 0;
				for (Object object : joinPoint.getArgs()) {
				builder.append("\nParameter ").append(++parameterVolgnummer).append('\t')
				.append(object);
				}
				logger.info(builder.toString());
	}
}
